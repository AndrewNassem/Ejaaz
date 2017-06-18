package com.example.dell.ejaaztest;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ejaaztest.Model.Hit;
import com.example.dell.ejaaztest.Model.SearchRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.circular_progress_bar)
    CircularProgressBar circular_progress_bar;
    @BindView(R.id.calories_number)
    TextView calories_number;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    private List<Hit> hitList;
    private ItemAdapter itemAdapter;
    Gson gson;
    PreManager preManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        preManager = new PreManager(this);
        gson = new Gson();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // MAKE CALENDAR FOR THE NEW DAY
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    @OnTextChanged(R.id.edit_query)
    public void onTextChanged(CharSequence text) {
        Helper.search(text, hitList, itemAdapter, recyclerView, this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // UPDATE THE DATA EACH TIME ACTIVITY RESUME
        String json = preManager.getData(Constants.MY_FOOLD_LIST);
        hitList = gson.fromJson(json, new TypeToken<ArrayList<Hit>>() {
        }.getType());
        if (hitList != null) {
            itemAdapter = new ItemAdapter(hitList, View.VISIBLE, this);
            itemAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(itemAdapter);
            getSumOfCalories(hitList);

        } else {
            circular_progress_bar.setProgress(0);
            calories_number.setText("0 Calories");
        }
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // GET THE SUM FOR THE DIALY CALORIES AND DISPLAY IT
    private void getSumOfCalories(List<Hit> hitList) {
        float sum = 0;
        for (Hit hit : hitList
                ) {
            sum += hit.getFields().getNfCalories();
        }
        circular_progress_bar.setProgress((sum / 20));
        calories_number.setText((sum <= 2000 ? sum : 2000) + " Calories");


    }

    @OnClick(R.id.add)
    public void add(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }


}
