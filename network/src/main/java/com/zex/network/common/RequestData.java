package com.zex.network.common;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class RequestData {

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            toJsonObject(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    protected abstract void toJsonObject(JSONObject jsonObject) throws JSONException;
}
