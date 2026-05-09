package fanheater.src.manager;
import fanheater.src.heater.Heater;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;

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
     * @param temperatureSimulation the temperature simulation for testing without the hardware
     */
    public ComponentsManager(Heater heater, TemperatureSensor temperatureSensor, TemperatureSimulation temperatureSimulation) {
        this.heater = heater;
        this.temperatureSimulation = temperatureSimulation;
        this.temperatureSensor = temperatureSensor;

        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
    }

    /**
     * lets the heater check if it needs to activate and signals the temperature simulator if the temperature needs to increase or decrease
     */
    public void update(){
        checkForActivationHeater();
        temperatureSimulation.updateTemperature(heater.getActive());
    }

    public double getCurrentRoomTemperature() {
        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
        return currentRoomTemperature;
    }

    public void updateForTargetTemperature(double newTargetTemperature) {
        if (newTargetTemperature != targetTemperature) {
            targetTemperature = newTargetTemperature;
        }
    }

    public void checkForActivationHeater(){
        if (getCurrentRoomTemperature() < targetTemperature - heater.getTemperatureIncreaseRate()){
            heater.activate();
        } else {
            heater.deactivate();
        }
    }
}
