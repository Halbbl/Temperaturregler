package iteration1.src.simulation;

/**
 * Simple Temperature simulation for testing without the hardware
 */
public class TemperatureSimulation {

    private double currentRoomTemperature;
    private final double temperatureIncreaseRate;
    private final double temperatureDecreaseRate;
    private final double minTemperature;
    private final double maxTemperature;

    /**
     * Constructure of temperature simulator
     * @param currentRoomTemperature starting room temperature
     * @param temperatureIncreaseRate Rate at which the temperature increases when heating
     * @param temperatureDecreaseRate Rate at which the temperature decreases when not heating
     * @param minTemperature minimal temperature the simulation can display
     * @param maxTemperature maximal temperature the simulation can display
     */
    public TemperatureSimulation(double currentRoomTemperature, double temperatureIncreaseRate, double temperatureDecreaseRate, double minTemperature, double maxTemperature) {
        this.currentRoomTemperature = currentRoomTemperature;
        this.temperatureIncreaseRate = temperatureIncreaseRate;
        this.temperatureDecreaseRate = temperatureDecreaseRate;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    /**
     * Updates the temperature depending on if the heater is active or not
     * @param isHeating indicates if the heater is active
     */
    public void updateTemperature(boolean isHeating) {
        if (isHeating && currentRoomTemperature < maxTemperature) {
            currentRoomTemperature += temperatureIncreaseRate;
            if(currentRoomTemperature > maxTemperature) {
                currentRoomTemperature = maxTemperature;
            }
        } else if (!isHeating && currentRoomTemperature > minTemperature) {
            currentRoomTemperature -= temperatureDecreaseRate;
            if (currentRoomTemperature < minTemperature) {
                currentRoomTemperature = minTemperature;
            }
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

