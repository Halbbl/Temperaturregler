package config;

import fanheater.src.heater.Heater;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;
import sensor.TimeSensor;
import simulation.TimeSimulation;

public class Components {
    public final Heater heater;
    public final RoomTemperatureSensor roomTemperatureSensor;
    public final InternalTemperatureSensor internalTemperatureSensor;
    public final TimeSensor timeSensor;

    public Components(Heater heater, RoomTemperatureSensor roomTemperatureSensor, InternalTemperatureSensor internalTemperatureSensor, TimeSensor timeSensor) {
        this.heater = heater;
        this.roomTemperatureSensor = roomTemperatureSensor;
        this.internalTemperatureSensor = internalTemperatureSensor;
        this.timeSensor = timeSensor;
    }
}
