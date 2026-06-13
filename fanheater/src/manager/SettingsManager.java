package manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {
    private final Properties props = new Properties();
    private final String path;

    public SettingsManager(String path) {
        this.path = path;

        try (FileReader reader = new FileReader(path)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getTargetTemperature() {
        return Double.parseDouble(props.getProperty("targetTemperature"));
    }

    public void setTargetTemperature(double value) {
        props.setProperty("targetTemperature", String.valueOf(value));
        save();
    }

    public void setEnergySaving(boolean value) {
        props.setProperty("energySaving", String.valueOf(value));
        save();
    }

    // saving in file
    private void save() {
        try (FileWriter writer = new FileWriter(path)) {
            props.store(writer, "Updated settings at runtime");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
