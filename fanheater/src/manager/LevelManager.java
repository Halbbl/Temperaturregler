package fanheater.src.manager;

import fanheater.src.heater.HeaterLevel;

public class LevelManager {

    private HeaterLevel currentLevel;

    public LevelManager() {
        currentLevel = HeaterLevel.OFF;
    }

    public HeaterLevel getCurrentLevel() {
        return currentLevel;
    }

    public void updateLevel(double currentTemperature, double targetTemperature, boolean overheated) {
        double difference = targetTemperature- currentTemperature;

        if (!overheated) {
            if (Math.round(difference*100.0)/100.0 >= 5.0) {

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
}
