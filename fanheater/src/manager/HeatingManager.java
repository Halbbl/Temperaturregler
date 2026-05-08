package fanheater.src.manager;
import fanheater.src.heater.Heater;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;

/**
 * Manager class for the components of the heater
 */
public class HeatingManager {
    
    private final Heater heater;
    private final TemperatureSimulation temperatureSimulation;

    /**
     * Constructur for heating manager
     * @param heater the heater
     * @param temperatureSimulation the temperature simulation for testing without the hardware
     */
    public HeatingManager(Heater heater, TemperatureSimulation temperatureSimulation) {
        this.heater = heater;
        this.temperatureSimulation = temperatureSimulation;
    }

    /**
     * lets the heater check if it needs to activate and signals the temperature simulator if the temperature needs to increase or decrease
     */
    public void update(){
        heater.checkForActivation();
        temperatureSimulation.updateTemperature(heater.getActive());
    }
}
