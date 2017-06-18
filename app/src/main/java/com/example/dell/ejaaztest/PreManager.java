package com.example.dell.ejaaztest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DELL on 10/06/2017.
 */

public class PreManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = Constants.PREF_NAME;

    public PreManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getData(String key) {
        return pref.getString(key, "");
    }


}
