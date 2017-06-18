package com.example.dell.ejaaztest.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 09/06/2017.
 */

public class SearchResponse implements Serializable {
    @SerializedName("total")
    private Integer total;
    @SerializedName("maxScore")
    private Double maxScore;
    @SerializedName("hits")
    private List<Hit> hits = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}