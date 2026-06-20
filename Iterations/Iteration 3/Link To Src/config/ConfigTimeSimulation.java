package iteration3.src.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Config for simulation time
 * Reads the initial time from a config file
 */
public class ConfigTimeSimulation {

    /**
     * Initial minute value
     */
    public int minute;

    /**
     * Initial hour value
     */
    public int hour;

    /**
     * Default constructor
     * Reads time values from a config file
     *
     * @param filepath path to config file
     */
    public ConfigTimeSimulation(String filepath) {
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