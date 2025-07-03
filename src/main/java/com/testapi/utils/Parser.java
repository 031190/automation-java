package com.testapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parser {

    public static String getFileAsStringFromResources(String jsonFileName) {
        try {
            //File file = new File("src/test/resources/testData/" + jsonFileName);
           // FileInputStream fs = new FileInputStream(file);
            InputStream is = Parser.class.getClassLoader().getResourceAsStream("testData/" + jsonFileName);
            return new String(is.readAllBytes());
        } catch (IOException e) {
            System.out.println("FILE " + jsonFileName + " NOT FOUND");
            throw new RuntimeException(e);
        }
    }

    public static Object getJsonFileAsPojoClassFromResources(String jsonFileName, Class<?> object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = Parser.class.getClassLoader().getResourceAsStream("testData/" + jsonFileName);
            return mapper.readValue(is,object);
        } catch (IOException e) {
            System.out.println("FILE " + jsonFileName + " NOT FOUND");
            throw new RuntimeException(e);
        }
    }

    public static String getFileAsStringFromAnyLocation(String jsonFileName) {
        try {
            return Files.readString(Path.of(jsonFileName));
        } catch (IOException e) {
            System.out.println("FILE " + jsonFileName + " NOT FOUND");
            throw new RuntimeException(e);
        }
    }

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
}
