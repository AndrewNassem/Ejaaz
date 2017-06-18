package com.example.dell.ejaaztest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dell.ejaaztest.Model.Hit;
import com.example.dell.ejaaztest.Model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 09/06/2017.
 */

public class Helper {
    public static void askForPermission(String[] permission, Integer requestCode , Activity activity) {
        ActivityCompat.requestPermissions(activity, permission, requestCode);
    }
    // SEARCH FUNCTION IN ADAPTER
    public static void search(CharSequence text, List<Hit> hitList, ItemAdapter itemAdapter , RecyclerView recyclerView, Activity activity) {
        if(text.length() > 0) {
            List<Hit> temp = new ArrayList<>();
            for (Hit hit : hitList
                    ) {
                if (hit.getFields().getItemName().toLowerCase().contains(text))
                    temp.add(hit);
            }
            itemAdapter = new ItemAdapter(temp, View.GONE, activity);
            recyclerView.setAdapter(itemAdapter);
        }
        else {
            itemAdapter = new ItemAdapter(hitList , View.GONE, activity);
            recyclerView.setAdapter(itemAdapter);
        }
    }


}
