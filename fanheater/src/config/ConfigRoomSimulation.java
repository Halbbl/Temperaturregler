package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigRoomSimulation {
    public final double INITIAL_TEMPERATURE;
    public final double DECREASE_RATE;
    public final double MAX_TEMPERATURE;
    public final double OUTSIDE_TEMPERATURE;
    public final double WINDOW_OPEN_DECREASE_RATE;

    public ConfigRoomSimulation(double initialTemp, double decreaseRate, double maxTemp, double outsideTemp, double windowOpenDecreaseRate) {
        INITIAL_TEMPERATURE = initialTemp;
        DECREASE_RATE = decreaseRate;
        MAX_TEMPERATURE = maxTemp;
        OUTSIDE_TEMPERATURE = outsideTemp;
        WINDOW_OPEN_DECREASE_RATE = windowOpenDecreaseRate;
    }

    public ConfigRoomSimulation(String filepath) {
        Properties prop = new Properties();
        try (FileReader reader = new FileReader(filepath)) {
            prop.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + filepath, e);
        }

        INITIAL_TEMPERATURE = Double.parseDouble(prop.getProperty("initialRoomTemperature"));
        DECREASE_RATE = Double.parseDouble(prop.getProperty("decreaseRate"));
        MAX_TEMPERATURE = Double.parseDouble(prop.getProperty("maxTemperature"));
        OUTSIDE_TEMPERATURE = Double.parseDouble(prop.getProperty("outsideTemperature"));
        WINDOW_OPEN_DECREASE_RATE = Double.parseDouble(prop.getProperty("windowOpenDecreaseRate"));
    }
}
