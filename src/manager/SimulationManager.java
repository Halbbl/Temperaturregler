package src.manager;

import src.config.Simulations;
import src.heater.HeaterLevel;
import src.simulation.FanHeaterTemperatureSimulation;
import src.simulation.RoomTemperatureSimulation;
import src.simulation.TimeSimulation;

/**
 * Manager for simulations
 */
public class SimulationManager {

    //simulations
    private final RoomTemperatureSimulation roomTemperatureSimulation;
    private final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
    private final TimeSimulation timeSimulation;

    /**
     * Default constructor
     * @param simulations all simulations
     */
    public SimulationManager(Simulations simulations) {
        roomTemperatureSimulation = simulations.roomTemperatureSimulation;
        fanHeaterTemperatureSimulation = simulations.fanHeaterTemperatureSimulation;
        timeSimulation = simulations.timeSimulation;
    }

    /**
     * Update all simulations
     * @param heaterLevel current heater level
     * @param isOn if heater is on
     */
    public void update(HeaterLevel heaterLevel, boolean isOn) {
        roomTemperatureSimulation.updateTemperature(heaterLevel, isOn);
        fanHeaterTemperatureSimulation.updateTemperature(heaterLevel);
        timeSimulation.updateTime();
    }

    /**
     * Simulates opening or closing the window in the room.
     * Updates the room temperature simulation accordingly.
     */
    public void updateWindow(){
        roomTemperatureSimulation.setWindowOpen(!roomTemperatureSimulation.isWindowOpen());
    }
}
