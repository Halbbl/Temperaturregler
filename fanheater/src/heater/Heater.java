package fanheater.src.heater;
import fanheater.src.sensor.TemperatureSensor;

public class Heater {

    private double currentRoomTemperature;
    private double targetTemperature;
    private boolean active;
    private final TemperatureSensor temperatureSensor;

    public Heater(TemperatureSensor temperatureSensor, double targetTemperature) {
        this.temperatureSensor = temperatureSensor;
        this.targetTemperature = targetTemperature;
        active = false;
    }

    public void updateCurrentRoomTemperature() {
        currentRoomTemperature = temperatureSensor.readCurrentTemperature();
    }

    public void heat(){
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
