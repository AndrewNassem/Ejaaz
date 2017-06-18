package com.example.dell.ejaaztest;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.ejaaztest.Model.Hit;
import com.example.dell.ejaaztest.Model.SearchRequest;
import com.example.dell.ejaaztest.Model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class SearchActivity extends AppCompatActivity implements ApiResponse {
    @BindView(R.id.edit_query)
    EditText edit_query ;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView ;
    private List<Hit> hitList ;
    private ItemAdapter itemAdapter ;
    SearchRequest request ;
    ProgressDialog progressDialog ;
    @BindView(R.id.error)
    TextView errorText;
    SearchResponse searchResponse = new SearchResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        hitList = new ArrayList<>();
        List<String> fieldsList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        fieldsList.add("nf_calories");
        fieldsList.add("item_name");
        request = new SearchRequest(Constants.APPID , Constants.APPKEY , "*" ,fieldsList );
        // CHECK FOR PERMISSION
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            showProgressDialog();
            new HttpCall(this , this ).getData(request);
        } else {
            Helper.askForPermission(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.NETWORK_PERMISSION_CODE , this);
        }
    }

    @OnTextChanged(R.id.edit_query)
    public void onTextChanged(CharSequence text){
        Helper.search(text , searchResponse.getHits() , itemAdapter , recyclerView , this);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == Constants.NETWORK_PERMISSION_CODE) {
                new HttpCall(this , this ).getData(request);
            }
        }
    }

    @Override
    public void onSuccess(Object response) {
        // FILL MY DATA
        searchResponse = (SearchResponse) response ;
        itemAdapter = new ItemAdapter(searchResponse.getHits() , View.GONE, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
        DismissProgressDialog();
    }

    @Override
    public void onFailed(String error) {
        // ERROR MSG FOR SOMETHING WRONG
        recyclerView.setVisibility(View.GONE);
        edit_query.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
    }

    public void DismissProgressDialog() {progressDialog.dismiss();}
    public void showProgressDialog(){progressDialog = ProgressDialog.show(this, "", "Please Wait...", true);}

}
