package fanheater.src.sensor;
import fanheater.src.simulation.TemperatureSimulation;

public class TemperatureSensor {

    private final TemperatureSimulation temperatureSimulation;

    public TemperatureSensor(TemperatureSimulation temperatureSimulation) {
        this.temperatureSimulation = temperatureSimulation;
    }

    public double readCurrentTemperature() {
        return temperatureSimulation.getCurrentRoomTemperature();
    }
}