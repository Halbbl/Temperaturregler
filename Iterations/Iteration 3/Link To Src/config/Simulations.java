package iteration3.src.config;

import iteration3.src.simulation.FanHeaterTemperatureSimulation;
import iteration3.src.simulation.RoomTemperatureSimulation;
import iteration3.src.simulation.TimeSimulation;

/**
 * Container class for all simulation instances
 * Provides access to the temperature and time simulations
 */
public class Simulations {

    /**
     * Room temperature simulation instance
     */
    public final RoomTemperatureSimulation roomTemperatureSimulation;

    /**
     * Fan heater temperature simulation instance
     */
    public final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;

    /**
     * Time simulation instance
     */
    public final TimeSimulation timeSimulation;

    /**
     * Default constructor
     *
     * @param roomTemperatureSimulation room temperature simulation
     * @param fanHeaterTemperatureSimulation fan heater temperature simulation
     * @param timeSimulation time simulation
     */
    public Simulations(RoomTemperatureSimulation roomTemperatureSimulation,
                       FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation,
                       TimeSimulation timeSimulation) {
        this.roomTemperatureSimulation = roomTemperatureSimulation;
        this.fanHeaterTemperatureSimulation = fanHeaterTemperatureSimulation;
        this.timeSimulation = timeSimulation;
    }
}