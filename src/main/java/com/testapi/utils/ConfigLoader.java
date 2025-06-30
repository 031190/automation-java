package com.testapi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();
    static {
        try{
            InputStream envFile = ConfigLoader.class.getClassLoader().getResourceAsStream(System.getProperty("env", "dev") + ".properties");
            props.load(envFile);
            InputStream configFile = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(configFile);
            InputStream apiFile = ConfigLoader.class.getClassLoader().getResourceAsStream("restApiConfig.properties");
            props.load(apiFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEnvUrl() {
        return props.getProperty("baseUrl");
    }
    public static String getRestApiEndpointGetAllEntries() { return props.getProperty("getEntriesEndpoint"); }
    public static String getRestApiEndpointCreateEntry() {return props.getProperty("createEntryEndpoint"); }
    public static Boolean getRelaxedHttpValidation() {
        if (props.getProperty("relaxedHttpValidation") != null) {
            return Boolean.valueOf(props.getProperty("relaxedHttpValidation"));
        }
        return false;
    }
}
