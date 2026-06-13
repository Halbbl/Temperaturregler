package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigTime {
    public int[] time;

    public ConfigTime(String filepath) {
        Properties props = new Properties();

        try (FileReader reader = new FileReader(filepath)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + filepath, e);
        }

        int[] time = new int[]{Integer.parseInt(props.getProperty("targetTemperature"))};
        this.time = time;
    }
}
