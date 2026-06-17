package manager;

import heater.HeaterLevel;

/**
 * Manager fo heating level
 */
public class LevelManager {

    private HeaterLevel currentLevel;

    /**
     * Constructor of heating level manager
     */
    public LevelManager() {
        currentLevel = HeaterLevel.OFF;
    }

    /**
     * Gets the current level
     * @return current heating level
     */
    public HeaterLevel getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Updates the heating level
     * @param currentTemperature current room temperature
     * @param targetTemperature target room temperature
     * @param overheated overheated or not
     * @param windowOpen is window open
     * @param energySaving is energy saving active
     */
    public void updateLevel(double currentTemperature, double targetTemperature, boolean overheated, boolean windowOpen, boolean energySaving) {
        double difference = targetTemperature- currentTemperature;

        if (!overheated && !windowOpen) {
            if (Math.round(difference*100.0)/100.0 >= 5.0 && !energySaving) {

                currentLevel = HeaterLevel.HIGH;

            } else if (Math.round(difference*100.0)/100.0 >= 2.0) {

                currentLevel = HeaterLevel.MEDIUM;

            } else if (Math.round(difference*100.0)/100.0 > 0.0) {

                currentLevel = HeaterLevel.LOW;

            } else {

                currentLevel = HeaterLevel.OFF;
            }
        } else if(overheated || windowOpen) {
            currentLevel = HeaterLevel.OFF;
        }
    }
}
