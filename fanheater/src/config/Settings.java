package config;

import java.io.FileReader;
import java.io.IOException;

public class Settings {
    public final double TARGET_TEMPERATURE;
    public final boolean ENERGY_SAVING;
    public final double MAX_TEMPERATURE_ROOM;
    public final double MAX_TEMPERATURE_INTERNAL;
    public final double WINDOW_OPEN_THRESHOLD;

    public Settings (double targetTemp, boolean energySaving, double maxTempRoom, double maxTempInternal, double windowOpenThreshold) {
        TARGET_TEMPERATURE = targetTemp;
        ENERGY_SAVING = energySaving;
        MAX_TEMPERATURE_ROOM = maxTempRoom;
        MAX_TEMPERATURE_INTERNAL = maxTempInternal;
        WINDOW_OPEN_THRESHOLD = windowOpenThreshold;
    }

    public Settings (String filepath) {



    TARGET_TEMPERATURE = targetTemp;
    ENERGY_SAVING = energySaving;
    MAX_TEMPERATURE_ROOM = maxTempRoom;
    MAX_TEMPERATURE_INTERNAL = maxTempInternal;
    WINDOW_OPEN_THRESHOLD = windowOpenThreshold;
}
}
