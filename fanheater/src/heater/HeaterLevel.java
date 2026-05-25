package fanheater.src.heater;

/**
 * Different heating levels for the heater
 */
public enum HeaterLevel {
    OFF(0.0),
    LOW(0.1),
    MEDIUM(0.3),
    HIGH(0.6);

    private final double heatingPower;

    HeaterLevel(double heatingPower) {
        this.heatingPower = heatingPower;
    }

    /**
     * Returns the heating power for the current level
     * @return temperature increase rate
     */
    public double getHeatingPower() {
        return heatingPower;
    }
}


