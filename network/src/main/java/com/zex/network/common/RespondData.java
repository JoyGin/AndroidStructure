package com.zex.network.common;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class RespondData {

    private String message;
    private String code;

    public void fromJson(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fromJsonObject(jsonObject);
    }

    protected abstract void fromJsonObject(JSONObject jsonObject);
}
