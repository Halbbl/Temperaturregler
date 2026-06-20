package src.manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Manager for timer entries
 * Handles loading, adding, retrieving and removing timer configurations
 */
public class TimerManager {

    private Properties props;
    private final String path;

    /**
     * Default constructor
     * Loads timer entries from the specified file
     *
     * @param path path to timer configuration file
     */
    public TimerManager(String path) {

        this.path = path;

        props = new Properties();

        try (FileReader reader = new FileReader(path)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + path, e);
        }
    }

    private void save() {
        try (FileWriter writer = new FileWriter(path)) {
            props.store(writer, null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save settings file: " + path, e);
        }
    }

    /**
     * Adds a new timer entry
     *
     * @param hour execution hour of the timer
     * @param minute execution minute of the timer
     * @param targetTemp target temperature when the timer is triggered
     */
    public void addTimerEntry(int hour, int minute, double targetTemp) {
        props.setProperty("timer" + (props.size() + 1), hour + "," + minute + "," + targetTemp);
        save();
    }

    /**
     * Gets a timer entry by its number
     *
     * @param num timer entry number
     * @return array containing hour, minute and target temperature, or an empty array if no entry exists
     */
    public String[] getTimerEntry(int num) {
        String timer = props.getProperty("timer" + num);

        if (timer == null) {
            return new String[0];
        }

        return timer.split(",");
    }

    /**
     * Gets the amount of stored timer entries
     *
     * @return number of timer entries
     */
    public int getTimerCount() {
        return props.size();
    }

    /**
     * Removes a timer entry and shifts following entries up
     *
     * @param num timer entry number to remove
     */
    public void removeTimerEntry(int num) {
        for (int i = num; i <= getTimerCount() - 1; i++) {
            props.setProperty("timer" + i, props.getProperty("timer" + (i + 1)));
        }

        props.remove("timer" + getTimerCount());
        save();
    }
}