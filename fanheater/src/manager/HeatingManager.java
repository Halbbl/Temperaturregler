package fanheater.src.manager;
import fanheater.src.heater.Heater;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;

public class HeatingManager {
    
    private final Heater heater;
    private final TemperatureSensor temperatureSensor;
    private final TemperatureSimulation temperatureSimulation;

    public HeatingManager(Heater heater, TemperatureSensor temperatureSensor, TemperatureSimulation temperatureSimulation) {
        this.heater = heater;
        this.temperatureSensor = temperatureSensor;
        this.temperatureSimulation = temperatureSimulation;
    }

    public void update(){
        heater.heat();
        temperatureSimulation.updateTemperature(heater.getActive());
    }
}
