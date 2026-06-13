package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigInternalSimulation {
    public final double INITIAL_TEMPERATURE;
    public final double DECREASE_RATE;

    public ConfigInternalSimulation(double initialTemperature, double decreaseRate) {
        INITIAL_TEMPERATURE = initialTemperature;
        DECREASE_RATE = decreaseRate;
    }

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
