package src.manager;

import src.config.Components;
import src.config.Settings;
import src.heater.Heater;
import src.heater.HeaterLevel;
import src.heater.HeaterStatus;
import src.sensor.InternalTemperatureSensor;
import src.sensor.RoomTemperatureSensor;
import src.sensor.TimeSensor;

/**
 * Manager class for the components of the heater
 */
public class ComponentsManager {

    private final Heater heater;
    private final RoomTemperatureSensor roomTemperatureSensor;
    private final InternalTemperatureSensor internalTemperatureSensor;
    private final TimeSensor timeSensor;

    private final StatusManager statusManager = new StatusManager();
    private final LevelManager levelManager = new LevelManager();
    private final String userDir = System.getProperty("user.dir") + "/src/config/";
    private final SettingsManager settingsManager = new SettingsManager(userDir + "settings.properties");
    private final TimerManager timerManager = new TimerManager(userDir + "timers.properties");

    private final double MAX_INTERNAL_TEMPERATURE;
    private final double PUFFER_DEVICE_TEMPERATURE;
    private final double WINDOW_OPEN_THRESHOLD;
    private final double MAX_ROOM_TEMPERATURE;

    private double targetTemperature;
    private double lastTemperatureMeasured;
    private boolean overheated;
    private boolean energySaving;
    private boolean windowOpenDetected;
    private boolean on;

    //simulation
    private SimulationManager simulationManager;

    /**
     * Constructor for components manager
     * @param components all hardware components
     * @param settings present settings
     */
    public ComponentsManager(Components components, SimulationManager simulationManager, Settings settings) {
        this.heater = components.heater;
        this.roomTemperatureSensor = components.roomTemperatureSensor;
        this.internalTemperatureSensor = components.internalTemperatureSensor;
        this.timeSensor = components.timeSensor;

        targetTemperature = settings.TARGET_TEMPERATURE;
        energySaving = settings.ENERGY_SAVING;
        MAX_INTERNAL_TEMPERATURE = settings.MAX_TEMPERATURE_INTERNAL;
        PUFFER_DEVICE_TEMPERATURE = settings.PUFFER_DEVICE_TEMPERATURE;
        WINDOW_OPEN_THRESHOLD = settings.WINDOW_OPEN_THRESHOLD;
        MAX_ROOM_TEMPERATURE = settings.MAX_TEMPERATURE_ROOM;

        on = true;
        overheated = false;
        windowOpenDetected = false;
        lastTemperatureMeasured = Math.round(roomTemperatureSensor.readCurrentTemperature() * 100.0) / 100.0;

        this.simulationManager = simulationManager;
    }


    /**
     * Checks if heater needs to be activated, updates the temperature simulations and checks for overheating
     */
    public void update() {
        if (isOn()) {
            checkForOverheating();
        }
        simulationManager.update(getHeaterLevel(), isOn());
        if (isOn()) {
            checkIfWindowOpen();
            checkForStatus();
            checkTimers();
            lastTemperatureMeasured = Math.round(roomTemperatureSensor.readCurrentTemperature() * 100.0) / 100.0;
        }
    }

    // ==================================================================================
    // On / Off

    /**
     * Turn heater on
     */
    public void turnOn() {
        on = true;
    }

    /**
     * Turn heater off
     */
    public void turnOff() {
        on = false;
    }

    /**
     * Check if heater is on
     * @return if on
     */
    public boolean isOn() {
        return on;
    }

    // ==================================================================================
    // Temperature

    /**
     * Gets the current room temperature
     * @return current room temperature
     */
    public double getCurrentRoomTemperature() {
        return Math.round(roomTemperatureSensor.readCurrentTemperature() * 100.0) / 100.0;
    }

    /**
     * Gets the current device temperature
     * @return current device temperature
     */
    public double getCurrentDeviceTemperature() {
        return Math.round(internalTemperatureSensor.measureTemperature() * 100.0) / 100.0;
    }

    /**
     * Get current target temperature
     * @return current target temperature
     */
    public double getTargetTemperature() {
        return Math.round(targetTemperature * 100.0) / 100.0;
    }

    /**
     * Updates the target temperature
     * @param newTargetTemperature new target temperature
     */
    public void updateForTargetTemperature(double newTargetTemperature) {
        if (newTargetTemperature != targetTemperature) {
            targetTemperature = newTargetTemperature;
            settingsManager.setTargetTemperature(targetTemperature);
        }
    }

    /**
     * Get max room temperature
     * @return max temperature
     */
    public double getMaxRoomTemperature() {
        return MAX_ROOM_TEMPERATURE;
    }

    // ==================================================================================
    // Heating Level & Status

    /**
     * Gets current heating level
     * @return current heating level
     */
    public HeaterLevel getHeaterLevel() {
        return heater.getCurrentLevel();
    }

    /**
     * Gets current heating status
     * @return current heating status
     */
    public HeaterStatus getHeaterStatus() {
        return statusManager.getCurrentStatus();
    }

    private void checkForStatus() {
        levelManager.updateLevel(getCurrentRoomTemperature(), getTargetTemperature(), overheated, windowOpenDetected, energySaving);
        heater.setCurrentLevel(levelManager.getCurrentLevel());
        statusManager.updateStatus(getHeaterLevel(), overheated, windowOpenDetected, isOn());
    }

    // ==================================================================================
    // Overheating

    /**
     * Check if heater is overheated
     * @return if overheated
     */
    public boolean isOverheated() {
        return overheated;
    }

    private void checkForOverheating() {
        if (getCurrentDeviceTemperature() > MAX_INTERNAL_TEMPERATURE && !overheated) {
            heater.setCurrentLevel(HeaterLevel.OFF);
            overheated = true;
        }
        checkIfCooledDown();
    }

    private void checkIfCooledDown() {
        if (overheated && getCurrentDeviceTemperature() < MAX_INTERNAL_TEMPERATURE - PUFFER_DEVICE_TEMPERATURE) {
            overheated = false;
        }
    }

    // ==================================================================================
    // Energy Saving

    /**
     * Returns energy saving status
     * @return true if activated
     */
    public boolean isEnergySavingActivated() {
        return energySaving;
    }

    /**
     * Toggles the energy saving mode on or off.
     * If energy saving is active, it will be deactivated and vice versa.
     */
    public void updateEnergySaving() {
        if (isEnergySavingActivated()) {
            deactivateEnergySaving();
        } else {
            activateEnergySaving();
        }
        settingsManager.setEnergySaving(energySaving);
    }

    private void activateEnergySaving() {
        energySaving = true;
    }

    private void deactivateEnergySaving() {
        energySaving = false;
    }

    // ==================================================================================
    // Window Detection

    /**
     * Returns whether a window opening has been detected based on temperature changes.
     * @return true if a sudden temperature drop indicates an open window
     */
    public boolean isWindowOpen() {
        return windowOpenDetected;
    }

    private void checkIfWindowOpen() {
        double difference = getCurrentRoomTemperature() - lastTemperatureMeasured;
        windowOpenDetected = Math.abs(difference) >= WINDOW_OPEN_THRESHOLD && difference < 0;
    }

    // ==================================================================================
    // Timer

    /**
     * Get current Time
     * @return return time as hour:minute
     */
    public String getTime() {
        return String.format("%02d:%02d", getHours(), getMinutes());
    }

    private int getHours() {
        return timeSensor.getHours();
    }

    private int getMinutes() {
        return timeSensor.getMinutes();
    }

    /**
     * Add timer entry
     * @param hour hour
     * @param minute minute
     * @param targetTemp targetTemp
     */
    public void addTimerEntry(int hour, int minute, double targetTemp) {
        timerManager.addTimerEntry(hour, minute, targetTemp);
    }

    /**
     * Remove timer
     * @param num number of to be removed timer
     */
    public void removeTimerEntry(int num) {
        timerManager.removeTimerEntry(num);
    }

    /**
     * Get timer entry
     * @param num timer number
     * @return entry as string array
     */
    public String[] getTimerEntry(int num) {
        return timerManager.getTimerEntry(num);
    }

    /**
     * Get current timer count
     * @return current timer count
     */
    public int getTimerCount() {
        return timerManager.getTimerCount();
    }

    private void checkTimers() {
        for (int i = 1; i <= timerManager.getTimerCount(); i++) {
            String[] timer = getTimerEntry(i);
            if (Integer.parseInt(timer[0]) == getHours() && Integer.parseInt(timer[1]) == getMinutes()) {
                targetTemperature = Double.parseDouble(timer[2]);
                break;
            }
        }
    }

    // ==================================================================================
    // Simulation

    /**
     * Open/Close window in simulation
     */
    public void updateWindow() {
        simulationManager.updateWindow();
    }
}