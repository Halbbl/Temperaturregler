package manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Manager for application settings
 * Loads, modifies and saves settings during runtime
 */
public class SettingsManager {

    private final Properties props = new Properties();
    private final String path;

    /**
     * Default constructor
     * Loads settings from the specified file
     *
     * @param path path to settings file
     */
    public SettingsManager(String path) {
        this.path = path;

        try (FileReader reader = new FileReader(path)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the target temperature from the settings
     *
     * @return target temperature
     */
    public double getTargetTemperature() {
        return Double.parseDouble(props.getProperty("targetTemperature"));
    }

    /**
     * Updates the target temperature and saves the changes
     *
     * @param value new target temperature
     */
    public void setTargetTemperature(double value) {
        props.setProperty("targetTemperature", String.valueOf(value));
        save();
    }

    /**
     * Updates the energy saving mode and saves the changes
     *
     * @param value true to enable energy saving mode, false to disable it
     */
    public void setEnergySaving(boolean value) {
        props.setProperty("energySaving", String.valueOf(value));
        save();
    }

    private void save() {
        try (FileWriter writer = new FileWriter(path)) {
            props.store(writer, "Updated settings at runtime");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}