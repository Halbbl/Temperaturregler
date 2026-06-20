package iteration1.src.manager;
import iteration1.src.heater.Heater;
import iteration1.src.sensor.TemperatureSensor;
import iteration1.src.simulation.TemperatureSimulation;

/**
 * Manager class for the components of the heater
 */
public class ComponentsManager {
    
    private final Heater heater;
    private final TemperatureSimulation temperatureSimulation;
    private final TemperatureSensor temperatureSensor;

    private double targetTemperature;
    private double currentRoomTemperature;

    /**
     * Constructur for heating manager
     * @param heater the heater
     * @param temperatureSensor sensor
     * @param temperatureSimulation the temperature simulation for testing without the hardware
     */
    public ComponentsManager(Heater heater, TemperatureSensor temperatureSensor, TemperatureSimulation temperatureSimulation) {
        this.heater = heater;
        this.temperatureSimulation = temperatureSimulation;
        this.temperatureSensor = temperatureSensor;

        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
    }

    /**
     * checks if heater needs to be activated and updates the temperature simulation
     */
    public void update(){
        checkForActivationHeater();
        temperatureSimulation.updateTemperature(heater.getActive());
    }

    /**
     * Gets the current room temperature
     * @return currents room temperature
     */
    public double getCurrentRoomTemperature() {
        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
        return currentRoomTemperature;
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

    private void checkForActivationHeater(){
        if (getCurrentRoomTemperature() < targetTemperature - heater.getTemperatureIncreaseRate()){
            heater.activate();
        } else {
            heater.deactivate();
        }
    }
}
