package src.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Config for room temperature simulation
 * Reads simulation values from a config file
 */
public class ConfigRoomSimulation {

    /**
     * Starting room temperature
     */
    public final double INITIAL_TEMPERATURE;

    /**
     * Normal temperature decrease rate
     */
    public final double DECREASE_RATE;

    /**
     * Maximum room temperature
     */
    public final double MAX_TEMPERATURE;

    /**
     * Outside temperature
     */
    public final double OUTSIDE_TEMPERATURE;

    /**
     * Temperature decrease rate when the window is open
     */
    public final double WINDOW_OPEN_DECREASE_RATE;

    /**
     * Default constructor
     * Reads configuration values from a file
     *
     * @param filepath path to config file
     */
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