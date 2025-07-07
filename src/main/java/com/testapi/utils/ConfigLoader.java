package com.testapi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try{
            System.out.println("Env run: " + System.getProperty("env", "dev") + ".properties");
            InputStream envFile = ConfigLoader.class.getClassLoader().getResourceAsStream(System.getProperty("env", "dev") + ".properties");
            props.load(envFile);
            InputStream configFile = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseUrl() { return props.getProperty("baseUrl"); }
    public static String getRestApiRoute() { return props.getProperty("restApiRoute"); }
    public static String getPetApiRoute() { return props.getProperty("petApiRoute"); }
    public static boolean getRelaxedHttpValidation() {
        if (props.getProperty("relaxedHttpValidation") != null) {
            return Boolean.parseBoolean(props.getProperty("relaxedHttpValidation"));
        }
        return false;
    }
    private static Map<String,String> getApiKeyAndValue(String apiKey) {
        for (String key : props.stringPropertyNames()) {
            if (key.contains(apiKey)) {
                return Map.of(key,props.getProperty(key));
            }
        }
        return null;
    }
    public static Map<String, String> getRestApiKey() {
        if (getApiKeyAndValue("restApiKey") != null ) {
            return getApiKeyAndValue("restApiKey");
        }
        return Map.of("","");
    }
}
