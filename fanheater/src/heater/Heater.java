package fanheater.src.heater;

/**
 * Heater class for heating the room
 */
public class Heater {

    private HeaterLevel currentLevel;
    private boolean energieSaving;

    /**
     * Constructor of Heater
     */
    public Heater() {
        currentLevel = HeaterLevel.OFF;
        energieSaving = false;
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

    public boolean isEnergieSaving() {
        return energieSaving;
    }

    public void setEnergieSaving(boolean energieSaving) {
        this.energieSaving = energieSaving;
    }
}
