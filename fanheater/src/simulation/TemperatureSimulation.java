package fanheater.src.simulation;

/**
 * Temperature simulation for testing without the hardware
 */
public class TemperatureSimulation {

    private double currentRoomTemperature;
    private final double temperatureIncreaseRate = 0.5; // Rate at which the temperature increases when heating
    private final double temperatureDecreaseRate = 0.1; // Rate at which the temperature decreases when not heating

    /**
     * Constructure of temperature simulator
     * @param currentRoomTemperature given starting room temperature
     */
    public TemperatureSimulation(double currentRoomTemperature) {
        this.currentRoomTemperature = currentRoomTemperature;
    }

    /**
     * Updates the temperature depending on if the heater is active or not
     * @param isHeating indicates if the heater is active
     */
    public void updateTemperature(boolean isHeating) {
        if (isHeating) {
            currentRoomTemperature += temperatureIncreaseRate; // Increase temperature by the increase rate when heating
        } else {
            currentRoomTemperature -= temperatureDecreaseRate; // Decrease temperature by the decrease rate when not heating
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

