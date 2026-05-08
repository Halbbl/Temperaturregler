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

    /**
     * Constructor of Heater
     * @param temperatureSensor the embedded Temperature Sensor
     * @param targetTemperature the wanted Room Temperature of the user
     */
    public Heater(TemperatureSensor temperatureSensor, double targetTemperature) {
        this.temperatureSensor = temperatureSensor;
        this.targetTemperature = targetTemperature;
        active = false;
    }

    /**
     * Updates the current temperature of the room by fetching it from the sensor
     */
    public void updateCurrentRoomTemperature() {
        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
    }

    /**
     * Checking if activation is needed based on room temperature
     */
    public void checkForActivation(){
        updateCurrentRoomTemperature();
        if (currentRoomTemperature < targetTemperature) {
            activate();
        } else {
            deactivate();
        }
    }

    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public boolean getActive() {
        return active;
    }
}
