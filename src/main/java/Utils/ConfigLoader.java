package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public static final Properties props = new Properties();
    static {
        String env = System.getProperty("env");
        String filePath = "src/main/resources/" + env + ".properties";
        try {
            FileInputStream fs = new FileInputStream(filePath);
            props.load(fs);
        } catch (IOException e) {
            throw new RuntimeException("FAILED TO LOAD CONFIG FILE");
        }
    }

    public static String getEnvUrl() {
        return props.getProperty("baseUrl");
    }
}
