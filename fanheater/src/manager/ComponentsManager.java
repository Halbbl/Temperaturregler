package fanheater.src.manager;
import config.Components;
import config.Settings;
import config.Simulations;
import fanheater.src.heater.Heater;
import fanheater.src.heater.HeaterLevel;
import fanheater.src.heater.HeaterStatus;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;
import fanheater.src.manager.StatusManager;
import fanheater.src.manager.LevelManager;
import manager.SettingsManager;
import manager.TimerManager;
import sensor.TimeSensor;
import simulation.TimeSimulation;

/**
 * Manager class for the components of the heater
 */
public class ComponentsManager {
    
    private final Heater heater;
    private final RoomTemperatureSensor roomTemperatureSensor;
    private final InternalTemperatureSensor internalTemperatureSensor;
    private final TimeSensor timeSensor;

    //simulations
    private final RoomTemperatureSimulation roomTemperatureSimulation;
    private final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
    private final TimeSimulation timeSimulation;

    private final StatusManager statusManager = new StatusManager();
    private final LevelManager levelManager = new LevelManager();
    private final SettingsManager settingsManager = new SettingsManager(System.getProperty("user.dir") + "/fanheater/src/config/settings.properties");
    private final TimerManager timerManager = new TimerManager(System.getProperty("user.dir") + "/fanheater/src/config/timers.properties");

    private final double MAX_INTERNAL_TEMPERATURE;
    private final double PUFFER_DEVICE_TEMPERATURE;
    private final double WINDOW_OPEN_THRESHOLD;

    private double targetTemperature;
    private double lastTemperatureMeasured;
    private boolean overheated;
    private boolean energySaving;
    private boolean windowOpenDetected;

    /**
     * Constructur for components manager
     * @param components all hardware components
     * @param settings present settings
     */
    public ComponentsManager(Components components, Simulations simulations, Settings settings) {
        this.heater = components.heater;
        this.roomTemperatureSensor = components.roomTemperatureSensor;
        this.internalTemperatureSensor = components.internalTemperatureSensor;
        this.timeSensor = components.timeSensor;

        //simulations
        this.roomTemperatureSimulation = simulations.roomTemperatureSimulation;
        this.fanHeaterTemperatureSimulation = simulations.fanHeaterTemperatureSimulation;
        this.timeSimulation = simulations.timeSimulation;


        targetTemperature = settings.TARGET_TEMPERATURE;
        energySaving = settings.ENERGY_SAVING;
        MAX_INTERNAL_TEMPERATURE = settings.MAX_TEMPERATURE_INTERNAL;
        PUFFER_DEVICE_TEMPERATURE = settings.PUFFER_DEVICE_TEMPERATURE;
        WINDOW_OPEN_THRESHOLD = settings.WINDOW_OPEN_THRESHOLD;

        overheated = false;
        windowOpenDetected = false;
        lastTemperatureMeasured = Math.round(roomTemperatureSensor.readCurrentTemperature()*100.0)/100.0;
    }

    /**
     * checks if heater needs to be activated, updates the temperature simulations and checks for overheating
     */
    public void update(){
        checkForOverheating();
        updateSimulations(); //simulations
        checkIfWindowOpen();
        checkForStatus();
        checkTimers();
        lastTemperatureMeasured = Math.round(roomTemperatureSensor.readCurrentTemperature()*100.0)/100.0;
    }

    private void updateSimulations(){
        roomTemperatureSimulation.updateTemperature(heater.getCurrentLevel());
        fanHeaterTemperatureSimulation.updateTemperature(heater.getCurrentLevel());
        timeSimulation.updateTime();
    }

    /**
     * Gets the current room temperature
     * @return current room temperature
     */
    public double getCurrentRoomTemperature() {
        return Math.round(roomTemperatureSensor.readCurrentTemperature()*100.0)/100.0;
    }

    /**
     * Gets the current device temperature
     * @return current device temperature
     */
    public double getCurrentDeviceTemperature() {
        return Math.round(internalTemperatureSensor.measureTemperature()*100.0)/100.0;
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

    private double getTargetTemperature(){
        return Math.round(targetTemperature*100.0)/100.0;
    }

    private void checkForStatus(){
        levelManager.updateLevel(getCurrentRoomTemperature(), getTargetTemperature(), overheated, windowOpenDetected, energySaving);
        heater.setCurrentLevel(levelManager.getCurrentLevel());
        statusManager.updateStatus(getHeaterLevel(), overheated, windowOpenDetected);
    }

    private void checkForOverheating(){
        if (getCurrentDeviceTemperature() > MAX_INTERNAL_TEMPERATURE && !overheated){
            heater.setCurrentLevel(HeaterLevel.OFF);
            overheated = true;
        }
        checkIfCooledDown();
    }

    private void checkIfCooledDown(){
        if (overheated && getCurrentDeviceTemperature() < MAX_INTERNAL_TEMPERATURE - PUFFER_DEVICE_TEMPERATURE) {
            overheated = false;
        }
    }

    private void activateEnergySaving(){
        energySaving = true;
    }

    private void deactivateEnergySaving(){
        energySaving = false;
    }

    /**
     * Returns energy saving
     * @return true if activated
     */
    public boolean isEnergySavingActivated(){
        return energySaving;
    }

    /**
     * Toggles the energy saving mode on or off.
     * If energy saving is active, it will be deactivated and vice versa.
     */
    public void updateEnergySaving(){
        if (isEnergySavingActivated()){
            deactivateEnergySaving();
        } else {
            activateEnergySaving();
        }
        settingsManager.setEnergySaving(energySaving);
    }

    /**
     * Simulates opening or closing the window in the room.
     * Updates the room temperature simulation accordingly.
     */
    public void updateWindow(){
        roomTemperatureSimulation.setWindowOpen(!roomTemperatureSimulation.isWindowOpen());
    }

    private void checkIfWindowOpen() {
        double difference = getCurrentRoomTemperature() - lastTemperatureMeasured;
        windowOpenDetected = Math.abs(difference) >= WINDOW_OPEN_THRESHOLD && difference < 0;
    }

    /**
     * Returns whether a window opening has been detected based on temperature changes.
     * @return true if a sudden temperature drop indicates an open window
     */
    public boolean isWindowOpen() {
        return windowOpenDetected;
    }


    //Timer
    public String getTime(){
        String time = "";
        if (getHours() < 10){
            time += "0" +  getHours();
        } else {
            time += getHours();
        }
        if (getMinutes() < 10){
            time += ":0" +  getMinutes();
        }  else {
            time += ":" + getMinutes();
        }
        return time;
    }

    private int getMinutes(){
        return timeSensor.getMinutes();
    }

    private int getHours(){
        return timeSensor.getHours();
    }

    public void addTimerEntry(int hour, int minute, double targetTemp){
        timerManager.addTimerEntry(hour, minute, targetTemp);
    }

    private void checkTimers(){
        for(int i = 1; i <= timerManager.getTimerCount(); i++){
            String[] timer = getTimerEntry(i);
            if (Integer.parseInt(timer[0]) == getHours() && Integer.parseInt(timer[1]) == getMinutes()){
                targetTemperature = Double.parseDouble(timer[2]);
                break;
            }
        }
    }

    public int getTimerCount(){
        return timerManager.getTimerCount();
    }

    public String[] getTimerEntry(int num){
        return timerManager.getTimerEntry(num);
    }

    public void removeTimerEntry(int num){
        timerManager.removeTimerEntry(num);
    }
}
