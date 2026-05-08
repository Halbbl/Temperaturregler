package fanheater.src.heater;
import fanheater.src.sensor.TemperatureSensor;

/**
 * Heater class for heating the room
 */
public class Heater {

    private double currentRoomTemperature;
    private double targetTemperature;
    private boolean active;
    private final TemperatureSensor temperatureSensor;
    private double temperatureIncreaseRate;
    private double temperatureDecreaseRate;

    /**
     * Constructor of Heater
     * @param temperatureSensor the embedded Temperature Sensor
     * @param targetTemperature the wanted Room Temperature of the user
     * @param temperatureIncreaseRate Rate at which the temperature increases when heating
     * @param temperatureDecreaseRate Rate at which the temperature decreases when not heating
     */
    public Heater(TemperatureSensor temperatureSensor, double targetTemperature, double temperatureIncreaseRate, double temperatureDecreaseRate) {
        this.temperatureSensor = temperatureSensor;
        this.targetTemperature = targetTemperature;
        this.temperatureIncreaseRate = temperatureIncreaseRate;
        this.temperatureDecreaseRate = temperatureDecreaseRate;
        active = false;
    }

    private void updateCurrentRoomTemperature() {
        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
    }

    /**
     * Checking if activation is needed based on room temperature
     */
    public void checkForActivation(){
        updateCurrentRoomTemperature();
        if (currentRoomTemperature < targetTemperature - temperatureIncreaseRate) {
            activate();
        } else {
            deactivate();
        }
    }

    /**
     * Changes the target temperature to a new value
     * @param targetTemperature new value
     */
    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    private void activate() {
        active = true;
    }

    private void deactivate() {
        active = false;
    }

    /**
     * Checks if the heater is activated
     * @return if active
     */
    public boolean getActive() {
        return active;
    }
}
