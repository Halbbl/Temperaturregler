package fanheater.src.sensor;
import fanheater.src.simulation.TemperatureSimulation;

/**
 * Sensor for tracking the current temperature
 */
public class TemperatureSensor {

    private final TemperatureSimulation temperatureSimulation;

    /**
     * Constructure of the temperature sensor
     * @param temperatureSimulation the temperature simulator
     */
    public TemperatureSensor(TemperatureSimulation temperatureSimulation) {
        this.temperatureSimulation = temperatureSimulation;
    }

    /**
     * Measures the current temperature
     * @return current temperature
     */
    public double readCurrentTemperature() {
        return temperatureSimulation.getCurrentRoomTemperature();
    }
}