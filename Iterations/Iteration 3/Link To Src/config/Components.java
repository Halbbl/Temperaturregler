package iteration3.src.config;

import iteration3.src.heater.Heater;
import iteration3.src.sensor.InternalTemperatureSensor;
import iteration3.src.sensor.RoomTemperatureSensor;
import iteration3.src.sensor.TimeSensor;

/**
 * Class for all hardware components
 */
public class Components {
    /**
     * Heater
     */
    public final Heater heater;
    /**
     * Sensor for room temperature
     */
    public final RoomTemperatureSensor roomTemperatureSensor;
    /**
     * Sensor for device temperature
     */
    public final InternalTemperatureSensor internalTemperatureSensor;
    /**
     * Sensor for time
     */
    public final TimeSensor timeSensor;

    /**
     * Constructor for components
     * @param heater heater
     * @param roomTemperatureSensor room temperature sensor
     * @param internalTemperatureSensor device temperature sensor
     * @param timeSensor time sensor
     */
    public Components(Heater heater, RoomTemperatureSensor roomTemperatureSensor, InternalTemperatureSensor internalTemperatureSensor, TimeSensor timeSensor) {
        this.heater = heater;
        this.roomTemperatureSensor = roomTemperatureSensor;
        this.internalTemperatureSensor = internalTemperatureSensor;
        this.timeSensor = timeSensor;
    }
}
