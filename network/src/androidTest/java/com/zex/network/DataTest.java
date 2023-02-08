package com.zex.network;

import android.util.Log;
import com.zex.network.common.RequestData;
import com.zex.network.common.RespondData;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * 测试 RequestData 和 RespondData 类
 */
public class DataTest {

    private static String TAG = "DataTest";

    @Test
    public void testRequestData() {
        RequestParam requestParam = new RequestParam();
        requestParam.id = 1;
        requestParam.name = "requestParam";
        String json = requestParam.toJson();
        Log.i(TAG, "json: " + json);
    }

    @Test
    public void testRespondData() {
        Student student = new Student();
        student.fromJson("{\"id\":11, \"name\":\"student1\", \"hobby\": \"hobby1\"}");
        Log.i(TAG, "student: " + student);
    }
}

class Student extends RespondData {

    int id;
    String name;
    String hobby;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    @Override
    protected void fromJsonObject(JSONObject jsonObject) {
        id = jsonObject.optInt("id");
        name = jsonObject.optString("name");
        hobby = jsonObject.optString("hobby");
    }
}

class RequestParam extends RequestData {

    int id;
    String name;

    @Override
    protected void toJsonObject(JSONObject jsonObject) throws JSONException {
        jsonObject.put("id", id);
        jsonObject.put("name", name);
    }
}
