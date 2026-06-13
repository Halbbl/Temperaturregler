package config;

import fanheater.src.heater.Heater;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;

public class Components {
    public final Heater heater;
    public final RoomTemperatureSimulation roomTemperatureSimulation;
    public final RoomTemperatureSensor roomTemperatureSensor;
    public final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
    public final InternalTemperatureSensor internalTemperatureSensor;

    public Components(Heater heater, RoomTemperatureSimulation roomTemperatureSimulation, RoomTemperatureSensor roomTemperatureSensor, FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation,  InternalTemperatureSensor internalTemperatureSensor) {
        this.heater = heater;
        this.roomTemperatureSimulation = roomTemperatureSimulation;
        this.roomTemperatureSensor = roomTemperatureSensor;
        this.fanHeaterTemperatureSimulation = fanHeaterTemperatureSimulation;
        this.internalTemperatureSensor = internalTemperatureSensor;
    }
}
