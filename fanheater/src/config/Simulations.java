package config;

import fanheater.src.simulation.RoomTemperatureSimulation;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import simulation.TimeSimulation;

public class Simulations {
    public final RoomTemperatureSimulation roomTemperatureSimulation;
    public final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
    public final TimeSimulation timeSimulation;

    public Simulations(RoomTemperatureSimulation roomTemperatureSimulation, FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation, TimeSimulation timeSimulation) {
        this.roomTemperatureSimulation = roomTemperatureSimulation;
        this.fanHeaterTemperatureSimulation = fanHeaterTemperatureSimulation;
        this.timeSimulation = timeSimulation;
    }
}
