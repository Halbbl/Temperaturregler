package fanheater.src.heater;

/**
 * Heater class for heating the room
 */
public class Heater {

    private boolean active;
    private double temperatureIncreaseRate;
    private double temperatureDecreaseRate;

    /**
     * Constructor of Heater
     * @param temperatureIncreaseRate Rate at which the temperature increases when heating
     * @param temperatureDecreaseRate Rate at which the temperature decreases when not heating
     */
    public Heater(double temperatureIncreaseRate, double temperatureDecreaseRate) {
        this.temperatureIncreaseRate = temperatureIncreaseRate;
        this.temperatureDecreaseRate = temperatureDecreaseRate;
        active = false;
    }


    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    /**
     * Checks if the heater is activated
     * @return if active
     */
    public boolean getActive() {
        return active;
    }

    public double getTemperatureIncreaseRate() {
        return temperatureIncreaseRate;
    }

    public double getTemperatureDecreaseRate() {
        return temperatureDecreaseRate;
    }
}
