package fanheater.src.manager;
import fanheater.src.heater.Heater;
import fanheater.src.heater.HeaterLevel;
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
    private final double MAX_INTERNAL_TEMPERATURE;
    private final double PUFFER_DEVICE_TEMPERATURE;

    private double targetTemperature;
    private boolean overheated;

    /**
     * Constructur for heating manager
     * @param heater the heater
     * @param roomTemperatureSensor sensor
     * @param roomTemperatureSimulation the temperature simulation for testing without the hardware
     */
    public ComponentsManager(Heater heater, RoomTemperatureSensor roomTemperatureSensor, RoomTemperatureSimulation roomTemperatureSimulation,  FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation, double maxInternalTemperature, double pufferDeviceTemperature, InternalTemperatureSensor internalTemperatureSensor) {
        this.heater = heater;
        this.roomTemperatureSimulation = roomTemperatureSimulation;
        this.roomTemperatureSensor = roomTemperatureSensor;
        this.fanHeaterTemperatureSimulation = fanHeaterTemperatureSimulation;
        this.internalTemperatureSensor = internalTemperatureSensor;
        this.MAX_INTERNAL_TEMPERATURE = maxInternalTemperature;
        this.PUFFER_DEVICE_TEMPERATURE = pufferDeviceTemperature;

        targetTemperature = 0.0;
        overheated = false;
    }

    /**
     * checks if heater needs to be activated and updates the temperature simulation
     */
    public void update(){
        checkForHeatingLevel();
        checkForOverheating();
        roomTemperatureSimulation.updateTemperature(heater.getCurrentLevel());
        fanHeaterTemperatureSimulation.updateTemperature(heater.getCurrentLevel());
    }

    /**
     * Gets the current room temperature
     * @return current room temperature
     */
    public double getCurrentRoomTemperature() {
        return Math.round(roomTemperatureSensor.readCurrentTemperature()*100.0)/100.0;
    }

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

    private double getTargetTemperature(){
        return Math.round(targetTemperature*100.0)/100.0;
    }

    private void checkForHeatingLevel(){
        double difference = getTargetTemperature()- getCurrentRoomTemperature();

        if (!overheated) {
            if (Math.round(difference*100.0)/100.0 >= 5.0) {

                heater.setCurrentLevel(HeaterLevel.HIGH);

            } else if (Math.round(difference*100.0)/100.0 >= 2.0) {

                heater.setCurrentLevel(HeaterLevel.MEDIUM);

            } else if (Math.round(difference*100.0)/100.0 > 0.0) {

                heater.setCurrentLevel(HeaterLevel.LOW);

            } else {

                heater.setCurrentLevel(HeaterLevel.OFF);
            }
        }
        System.out.println(overheated);
        System.out.println(Math.round(difference*100.0)/100.0 + " : " + getCurrentRoomTemperature() + ", " + getCurrentDeviceTemperature() + " - Level: " +  heater.getCurrentLevel());
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
}
