package src.simulation;

import src.config.ConfigInternalSimulation;
import src.heater.HeaterLevel;

/**
 * Simple device temperature simulator
 */
public class FanHeaterTemperatureSimulation {
    private double currentTemperature;
    private final double temperatureDecreaseRate;
    private final double startTemperature;

    /**
     * Custructor of device temperature simulator
     * @param configInternalSimulation all needed configurations
     */
    public FanHeaterTemperatureSimulation(ConfigInternalSimulation configInternalSimulation) {
        currentTemperature = configInternalSimulation.INITIAL_TEMPERATURE;
        temperatureDecreaseRate = configInternalSimulation.DECREASE_RATE;
        startTemperature = configInternalSimulation.INITIAL_TEMPERATURE;
    }

    /**
     * Returns current device temperature
     * @return current temperature
     */
    public double getCurrentTemperature() {
        return currentTemperature;
    }

    /**
     * Updates the current temperature based on the heating level
     * @param currentLevel heating level
     */
    public void updateTemperature(HeaterLevel currentLevel) {
        if (currentLevel == HeaterLevel.OFF && currentTemperature > startTemperature) {
            currentTemperature -= temperatureDecreaseRate;
            if (currentTemperature < startTemperature) {
                currentTemperature = startTemperature;
            }
        } else {
            currentTemperature += currentLevel.getFanHeaterTemperatureIncreaseRate();
        }
    }
}
