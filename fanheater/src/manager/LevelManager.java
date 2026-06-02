package fanheater.src.manager;

import fanheater.src.heater.HeaterLevel;

/**
 * Manager fo heating level
 */
public class LevelManager {

    private HeaterLevel currentLevel;
    private boolean energySaving;

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
     */
    public void updateLevel(double currentTemperature, double targetTemperature, boolean overheated) {
        double difference = targetTemperature- currentTemperature;

        if (!overheated) {
            if (Math.round(difference*100.0)/100.0 >= 5.0 && !energySaving) {

                currentLevel = HeaterLevel.HIGH;

            } else if (Math.round(difference*100.0)/100.0 >= 2.0) {

                currentLevel = HeaterLevel.MEDIUM;

            } else if (Math.round(difference*100.0)/100.0 > 0.0) {

                currentLevel = HeaterLevel.LOW;

            } else {

                currentLevel = HeaterLevel.OFF;
            }
        }
    }

    public void setEnergySaving(boolean energySaving) {
        this.energySaving = energySaving;
    }
}
