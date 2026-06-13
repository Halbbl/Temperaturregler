package fanheater.src.simulation;

import config.ConfigRoomSimulation;
import fanheater.src.heater.HeaterLevel;

/**
 * Simple room temperature simulation for testing without the hardware
 */
public class RoomTemperatureSimulation {

    private double currentRoomTemperature;
    private final double temperatureDecreaseRate;
    private final double maxTemperature;
    private final double outsideTemperature;
    private boolean windowOpen;
    private int windowOpenDecreaseRate;

    /**
     * Constructure of room temperature simulator
     * @param configRoomSimulation all needed configurations
     */
    public RoomTemperatureSimulation(ConfigRoomSimulation configRoomSimulation) {
        currentRoomTemperature = configRoomSimulation.INITIAL_TEMPERATURE;
        temperatureDecreaseRate = configRoomSimulation.DECREASE_RATE;
        maxTemperature = configRoomSimulation.MAX_TEMPERATURE;
        outsideTemperature = configRoomSimulation.OUTSIDE_TEMPERATURE;
        windowOpenDecreaseRate = (int)configRoomSimulation.WINDOW_OPEN_DECREASE_RATE;
        windowOpen = false;
    }

    /**
     * Updates the room temperature depending on if the heater is active or not
     * @param currentLevel level at which the heater is heating
     */
    public void updateTemperature(HeaterLevel currentLevel) {
        if (windowOpen && currentRoomTemperature > outsideTemperature) {
            currentRoomTemperature -= temperatureDecreaseRate*windowOpenDecreaseRate;
            if (currentRoomTemperature < outsideTemperature) {
                currentRoomTemperature =  outsideTemperature;
            }
        } else {
            if (!(currentLevel.equals(HeaterLevel.OFF)) && currentRoomTemperature < maxTemperature) {
                currentRoomTemperature += currentLevel.getHeatingPower();
            } else if ((currentLevel.equals(HeaterLevel.OFF)) && currentRoomTemperature > outsideTemperature) {
                currentRoomTemperature -= temperatureDecreaseRate;
            }
            if (currentRoomTemperature < outsideTemperature) {
                currentRoomTemperature = outsideTemperature;
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

    /**
     * Sets window open
     * @param windowOpen if window is open
     */
    public void setWindowOpen(boolean windowOpen) {
        this.windowOpen = windowOpen;
    }

    /**
     * Gets if window is open
     * @return true when open
     */
    public boolean isWindowOpen() {
        return windowOpen;
    }
}

