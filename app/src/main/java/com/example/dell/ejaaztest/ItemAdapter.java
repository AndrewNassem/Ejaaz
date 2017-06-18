package com.example.dell.ejaaztest;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.ejaaztest.Model.Hit;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

/**
 * Created by DELL on 09/06/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    List<Hit> list;
    int visibilty;
    Activity activity;
    PreManager preManager;
    Gson gson;

    public ItemAdapter(List<Hit> list, int visibilty, Activity activity) {
        this.list = list;
        this.visibilty = visibilty;
        this.activity = activity;
        preManager = new PreManager(activity);
        gson = new Gson();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, calories, time;
        public LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            calories = (TextView) view.findViewById(R.id.calories);
            time = (TextView) view.findViewById(R.id.time);
            layout = (LinearLayout) view.findViewById(R.id.parent);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Hit hit = list.get(position);
        holder.name.setText(hit.getFields().getItemName());
        holder.calories.setText(String.valueOf(hit.getFields().getNfCalories()));
        holder.time.setText(hit.getFields().getTime());
        //VISIBILTY FOR REUSE OF THE RECYCLE VIEW IN TWO ACTIVITIES
        holder.calories.setVisibility(visibilty);
        holder.time.setVisibility(visibilty);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibilty == View.GONE)
                    hit.getFields().setTime(setTime());
                addToList(hit);
            }
        });
    }

    private String setTime() {
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        if (hours < 12)
            return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + " AM ";
        else
            return (cal.get(Calendar.HOUR_OF_DAY) - 12) + ":" + cal.get(Calendar.MINUTE) + " PM ";
    }

    private void addToList(Hit hit) {
        String list = preManager.getData(Constants.MY_FOOLD_LIST);
        if (list == "") {
            list = "[" + gson.toJson(hit) + "]";
        } else {
            list = list.substring(0, list.length() - 1) + "," + gson.toJson(hit) + "]";
        }
        preManager.put(Constants.MY_FOOLD_LIST, list);
        activity.finish();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
