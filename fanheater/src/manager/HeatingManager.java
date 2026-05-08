package fanheater.src.manager;
import fanheater.src.heater.Heater;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;

/**
 * Manager class for the components of the heater
 */
public class HeatingManager {
    
    private final Heater heater;
    private final TemperatureSensor temperatureSensor;
    private final TemperatureSimulation temperatureSimulation;

    /**
     * Constructur for heating manager
     * @param heater the heater
     * @param temperatureSensor the sensor for the temperature
     * @param temperatureSimulation the temperature simulation for testing without the hardware
     */
    public HeatingManager(Heater heater, TemperatureSensor temperatureSensor, TemperatureSimulation temperatureSimulation) {
        this.heater = heater;
        this.temperatureSensor = temperatureSensor;
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
