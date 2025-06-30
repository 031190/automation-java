package com.testapi.utils;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;

public class Parser {

    public static boolean isJson(String json) {
       try {
           new JSONObject(json);
           return true;
       } catch (JSONException e) {
           return false;
       }
    }

    public static Object getJsonKeyValue(String json, String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.get(key);
        } catch (JSONException e) {
            return false;
        }
    }

    public static String getJsonAsStringFromResources(String jsonFileName) {
        try {
            InputStream is = Parser.class.getClassLoader().getResourceAsStream("testData/" + jsonFileName);
            return new String(is.readAllBytes());
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND");
            throw new RuntimeException(e);
        }
    }
}
