package com.example.dell.ejaaztest.Model;

import java.util.List;

/**
 * Created by DELL on 09/06/2017.
 */

public class SearchRequest  {
    private String appId ;
    private String appKey ;
    private String query ;


    private List<String> fields ;

    public SearchRequest(String appId, String appKey, String query, List<String> fields) {
        this.appId = appId;
        this.appKey = appKey;
        this.query = query;
        this.fields = fields;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

}
