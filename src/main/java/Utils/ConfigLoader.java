package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public static final Properties props = new Properties();

    static {
        String env = System.getProperty("env", "dev");
        String envFilePath = "src/main/resources/" + env + ".properties";
        String restApiFile = "src/main/resources/restApiConfig.properties";
        try {
            FileInputStream envFile = new FileInputStream(envFilePath);
            props.load(envFile);
            FileInputStream apiFile = new FileInputStream(restApiFile);
            props.load(apiFile);
        } catch (IOException e) {
            throw new RuntimeException("FAILED TO LOAD CONFIG FILE");
        }
    }

    public static String getEnvUrl() {
        return props.getProperty("baseUrl");
    }
    public static String getRestApiEndpointGetAllEntries() { return props.getProperty("getEntriesEndpoint"); }
    public static String getRestApiEndpointCreateEntry() {return props.getProperty("createEntryEndpoint"); }
}
