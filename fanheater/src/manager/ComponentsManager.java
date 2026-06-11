package fanheater.src.manager;
import fanheater.src.heater.Heater;
import fanheater.src.heater.HeaterLevel;
import fanheater.src.heater.HeaterStatus;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;

/**
 * Manager class for the components of the heater
 */
public class ComponentsManager {
    
    private final Heater heater;
    private final RoomTemperatureSimulation roomTemperatureSimulation;
    private final RoomTemperatureSensor roomTemperatureSensor;
    private final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
    private final InternalTemperatureSensor internalTemperatureSensor;

    private final StatusManager statusManager = new StatusManager();
    private final LevelManager levelManager = new LevelManager();

    private final double MAX_INTERNAL_TEMPERATURE;
    private final double PUFFER_DEVICE_TEMPERATURE;
    private final double WINDOW_OPEN_THRESHOLD;

    private double targetTemperature;
    private double lastTemperatureMeasured;
    private boolean overheated;
    private boolean energySaving;
    private boolean windowOpenDetected;

    /**
     * Constructur for heating manager
     * @param heater the heater
     * @param roomTemperatureSensor sensor
     * @param roomTemperatureSimulation the temperature simulation for testing without the hardware
     */
    public ComponentsManager(Heater heater, RoomTemperatureSensor roomTemperatureSensor, RoomTemperatureSimulation roomTemperatureSimulation,  FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation, double maxInternalTemperature, double pufferDeviceTemperature, InternalTemperatureSensor internalTemperatureSensor, double windowOpenThreshold) {
        this.heater = heater;
        this.roomTemperatureSimulation = roomTemperatureSimulation;
        this.roomTemperatureSensor = roomTemperatureSensor;
        this.fanHeaterTemperatureSimulation = fanHeaterTemperatureSimulation;
        this.internalTemperatureSensor = internalTemperatureSensor;
        this.MAX_INTERNAL_TEMPERATURE = maxInternalTemperature;
        this.PUFFER_DEVICE_TEMPERATURE = pufferDeviceTemperature;
        this.WINDOW_OPEN_THRESHOLD = windowOpenThreshold;

        targetTemperature = 0.0;
        lastTemperatureMeasured = Math.round(roomTemperatureSensor.readCurrentTemperature()*100.0)/100.0;
        overheated = false;
        energySaving = false;
        windowOpenDetected = false;
    }

    /**
     * checks if heater needs to be activated, updates the temperature simulations and checks for overheating
     */
    public void update(){
        checkForOverheating();
        roomTemperatureSimulation.updateTemperature(heater.getCurrentLevel());
        fanHeaterTemperatureSimulation.updateTemperature(heater.getCurrentLevel());
        checkIfWindowOpen();
        checkForStatus();
        lastTemperatureMeasured = Math.round(roomTemperatureSensor.readCurrentTemperature()*100.0)/100.0;
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
        System.out.println("Level: " +  heater.getCurrentLevel() + ", Status: " + getHeaterStatus() + "; Temp: " + getCurrentRoomTemperature());
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
}
