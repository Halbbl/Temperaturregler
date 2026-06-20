package iteration3.src.heater;

/**
 * Heater class for heating the room
 */
public class Heater {

    private HeaterLevel currentLevel;

    /**
     * Constructor of Heater
     */
    public Heater() {
        currentLevel = HeaterLevel.OFF;
    }

    /**
     * Returns the current heater level
     * @return current heater level
     */
    public HeaterLevel getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets the new heating level
     * @param currentLevel
     */
    public void setCurrentLevel(HeaterLevel currentLevel) {
        this.currentLevel = currentLevel;
    }
}
