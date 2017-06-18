package com.example.dell.ejaaztest.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 09/06/2017.
 */

public class Hit implements Serializable {

    private String _index;
    private String _type;
    private String _id;
    private Double score;
    private Fields fields;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String getid() {
        return _id;
    }

    public void setid(String _id) {
        this._id = _id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}