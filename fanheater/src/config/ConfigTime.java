package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigTime {
    public int minute;
    public int hour;

    public ConfigTime(String filepath) {
        Properties props = new Properties();

        try (FileReader reader = new FileReader(filepath)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + filepath, e);
        }

        int minute = Integer.parseInt(props.getProperty("minute"));
        int hour = Integer.parseInt(props.getProperty("hour"));

        this.minute = minute;
        this.hour = hour;
    }


}
