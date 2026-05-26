package fanheater.src.sensor;
import fanheater.src.simulation.RoomTemperatureSimulation;

/**
 * Sensor for tracking the current room temperature
 */
public class RoomTemperatureSensor {

    private final RoomTemperatureSimulation roomTemperatureSimulation;

    /**
     * Constructure of the temperature sensor
     * @param roomTemperatureSimulation the temperature simulator
     */
    public RoomTemperatureSensor(RoomTemperatureSimulation roomTemperatureSimulation) {
        this.roomTemperatureSimulation = roomTemperatureSimulation;
    }

    /**
     * Measures the current room temperature
     * @return current temperature
     */
    public double readCurrentTemperature() {
        return roomTemperatureSimulation.getCurrentRoomTemperature();
    }
}