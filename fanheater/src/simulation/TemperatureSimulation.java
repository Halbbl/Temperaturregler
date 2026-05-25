package fanheater.src.simulation;

import fanheater.src.heater.HeaterLevel;

/**
 * Simple Temperature simulation for testing without the hardware
 */
public class TemperatureSimulation {

    private double currentRoomTemperature;
    private final double temperatureDecreaseRate;
    private final double minTemperature;
    private final double maxTemperature;

    /**
     * Constructure of temperature simulator
     * @param currentRoomTemperature starting room temperature
     * @param temperatureDecreaseRate Rate at which the temperature decreases when not heating
     * @param minTemperature minimal temperature the simulation can display
     * @param maxTemperature maximal temperature the simulation can display
     */
    public TemperatureSimulation(double currentRoomTemperature, double temperatureDecreaseRate, double minTemperature, double maxTemperature) {
        this.currentRoomTemperature = currentRoomTemperature;
        this.temperatureDecreaseRate = temperatureDecreaseRate;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    /**
     * Updates the temperature depending on if the heater is active or not
     * @param currentLevel level at which the heater is heating
     */
    public void updateTemperature(HeaterLevel currentLevel) {
        if (!(currentLevel.equals(HeaterLevel.OFF))  && currentRoomTemperature < maxTemperature ) {
            currentRoomTemperature += currentLevel.getHeatingPower();
        } else if ((currentLevel.equals(HeaterLevel.OFF)) && currentRoomTemperature > minTemperature) {
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

