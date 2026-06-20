package src.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Settings for the heating and temperature control system
 * Reads user-defined settings from a config file
 */
public class Settings {

    /**
     * Desired target room temperature
     */
    public final double TARGET_TEMPERATURE;

    /**
     * Indicates whether energy saving mode is enabled
     */
    public final boolean ENERGY_SAVING;

    /**
     * Maximum allowed room temperature
     */
    public final double MAX_TEMPERATURE_ROOM;

    /**
     * Maximum allowed internal device temperature
     */
    public final double MAX_TEMPERATURE_INTERNAL;

    /**
     * Temperature buffer for the device
     */
    public final double PUFFER_DEVICE_TEMPERATURE;

    /**
     * Threshold used to detect an open window
     */
    public final double WINDOW_OPEN_THRESHOLD;

    /**
     * Default constructor
     * Reads settings from a config file
     *
     * @param filepath path to config file
     */
    public Settings(String filepath) {

        Properties props = new Properties();

        try (FileReader reader = new FileReader(filepath)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + filepath, e);
        }

        double targetTemp = Double.parseDouble(props.getProperty("targetTemperature"));
        boolean energySaving = Boolean.parseBoolean(props.getProperty("energySaving"));
        double maxTempRoom = Double.parseDouble(props.getProperty("maxTemperatureRoom"));
        double maxTempInternal = Double.parseDouble(props.getProperty("maxInternalTemperature"));
        double pufferDeviceTemp = Double.parseDouble(props.getProperty("pufferDeviceTemperature"));
        double windowOpenThreshold = Double.parseDouble(props.getProperty("windowOpenThreshold"));

        TARGET_TEMPERATURE = targetTemp;
        ENERGY_SAVING = energySaving;
        MAX_TEMPERATURE_ROOM = maxTempRoom;
        MAX_TEMPERATURE_INTERNAL = maxTempInternal;
        PUFFER_DEVICE_TEMPERATURE = pufferDeviceTemp;
        WINDOW_OPEN_THRESHOLD = windowOpenThreshold;
    }
}