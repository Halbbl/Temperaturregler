package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    public final double TARGET_TEMPERATURE;
    public final boolean ENERGY_SAVING;
    public final double MAX_TEMPERATURE_ROOM;
    public final double MAX_TEMPERATURE_INTERNAL;
    public final double PUFFER_DEVICE_TEMPERATURE;
    public final double WINDOW_OPEN_THRESHOLD;

    public Settings (double targetTemp, boolean energySaving, double maxTempRoom, double maxTempInternal, double pufferDeviceTemp, double windowOpenThreshold) {
        TARGET_TEMPERATURE = targetTemp;
        ENERGY_SAVING = energySaving;
        MAX_TEMPERATURE_ROOM = maxTempRoom;
        MAX_TEMPERATURE_INTERNAL = maxTempInternal;
        PUFFER_DEVICE_TEMPERATURE = pufferDeviceTemp;
        WINDOW_OPEN_THRESHOLD = windowOpenThreshold;
    }

    public Settings (String filepath) {

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
