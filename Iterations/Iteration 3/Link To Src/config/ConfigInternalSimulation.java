package iteration3.src.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Config for device temperature simulation
 * Reads the config file
 */
public class ConfigInternalSimulation {
    /**
     * Starting temperature
     */
    public final double INITIAL_TEMPERATURE;
    /**
     * Decrease rate of temperature
     */
    public final double DECREASE_RATE;

    /**
     * Default Constructor
     * @param filepath path to config
     */
    public ConfigInternalSimulation(String filepath) {
        Properties prop = new Properties();
        try (FileReader reader = new FileReader(filepath)) {
            prop.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + filepath, e);
        }

        INITIAL_TEMPERATURE = Double.parseDouble(prop.getProperty("initialTemperature"));
        DECREASE_RATE = Double.parseDouble(prop.getProperty("decreaseRate"));
    }
}
