package fanheater.src.simulation;

/**
 * Temperature simulation for testing without the hardware
 */
public class TemperatureSimulation {

    private double currentRoomTemperature;
    private final double temperatureIncreaseRate;
    private final double temperatureDecreaseRate;

    /**
     * Constructure of temperature simulator
     * @param currentRoomTemperature starting room temperature
     * @param temperatureIncreaseRate Rate at which the temperature increases when heating
     * @param temperatureDecreaseRate Rate at which the temperature decreases when not heating
     */
    public TemperatureSimulation(double currentRoomTemperature, double temperatureIncreaseRate, double temperatureDecreaseRate) {
        this.currentRoomTemperature = currentRoomTemperature;
        this.temperatureIncreaseRate = temperatureIncreaseRate;
        this.temperatureDecreaseRate = temperatureDecreaseRate;
    }

    /**
     * Updates the temperature depending on if the heater is active or not
     * @param isHeating indicates if the heater is active
     */
    public void updateTemperature(boolean isHeating) {
        if (isHeating) {
            currentRoomTemperature += temperatureIncreaseRate;
        } else {
            currentRoomTemperature -= temperatureDecreaseRate;
        }
    }

    /**
     * Fetches the current temperature of the room
     * @return the current temperature of the room
     */
    public double getCurrentRoomTemperature() {
        return currentRoomTemperature;
    }
}

