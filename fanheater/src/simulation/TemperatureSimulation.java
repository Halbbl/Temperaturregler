package fanheater.src.simulation;

public class TemperatureSimulation {

    private double currentRoomTemperature;
    private final double temperatureIncreaseRate = 0.5; // Rate at which the temperature increases when heating
    private final double temperatureDecreaseRate = 0.1; // Rate at which the temperature decreases when not heating

    public TemperatureSimulation(double currentRoomTemperature) {
        this.currentRoomTemperature = currentRoomTemperature;
    }

    public void updateTemperature(boolean isHeating) {
        if (isHeating) {
            currentRoomTemperature += temperatureIncreaseRate; // Increase temperature by the increase rate when heating
        } else {
            currentRoomTemperature -= temperatureDecreaseRate; // Decrease temperature by the decrease rate when not heating
        }
    }

    public double getCurrentRoomTemperature() {
        return currentRoomTemperature;
    }
}

