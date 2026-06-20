package iteration2.src.heater;

/**
 * Different heating levels for the heater
 */
public enum HeaterLevel {
    // °C/min
    OFF(0.0, 0.0),
    LOW(0.1, 0.5),
    MEDIUM(0.3, 1.2),
    HIGH(0.6, 2.5);

    private final double heatingPower;
    private final double fanHeaterTemperatureIncreaseRate;

    HeaterLevel(double heatingPower, double fanHeaterTemperatureIncreaseRate) {
        this.heatingPower = heatingPower;
        this.fanHeaterTemperatureIncreaseRate = fanHeaterTemperatureIncreaseRate;
    }

    /**
     * Returns the heating power for the current level
     * @return temperature increase rate
     */
    public double getHeatingPower() {
        return heatingPower;
    }

    /**
     * Returns the temperature increase rate for the fanheater
     * @return increase rate
     */
    public double getFanHeaterTemperatureIncreaseRate() {
        return fanHeaterTemperatureIncreaseRate;
    }
}


