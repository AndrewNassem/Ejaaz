package com.example.dell.ejaaztest.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 09/06/2017.
 */

public class Fields {

    private int nf_calories;
    private String item_name;
    private String time ;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getNfCalories() {
        return nf_calories;
    }

    public void setNfCalories(int nf_calories) {
        this.nf_calories = nf_calories;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
