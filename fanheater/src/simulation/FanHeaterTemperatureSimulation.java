package fanheater.src.simulation;

import fanheater.src.heater.HeaterLevel;

/**
 * Simple device temperature simulator
 */
public class FanHeaterTemperatureSimulation {
    private double currentTemperature;
    private final double temperatureDecreaseRate;
    private final double startTemperature;

    /**
     * Custructor of device temperature simulator
     * @param startTemperature starting temperature when turned on
     * @param temperatureDecreaseRate decrease rate of temperature
     */
    public FanHeaterTemperatureSimulation(double startTemperature, double temperatureDecreaseRate) {
        this.currentTemperature = startTemperature;
        this.temperatureDecreaseRate = temperatureDecreaseRate;
        this.startTemperature = startTemperature;
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

    /**
     * Setting new temperature
     * @param temperature new temperature
     */
    public void setCurrentTemperature(double temperature) {
        currentTemperature = temperature;
    }
}
