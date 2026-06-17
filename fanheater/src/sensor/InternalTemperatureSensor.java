package sensor;

import simulation.FanHeaterTemperatureSimulation;

/**
 * Sensor inside the device for measuring the device temperature
 */
public class InternalTemperatureSensor {
    private final FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;

    /**
     * Constructor of internal sensor
     * @param fanHeaterTemperatureSimulation simulator for temperature
     */
    public InternalTemperatureSensor(FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation){
        this.fanHeaterTemperatureSimulation = fanHeaterTemperatureSimulation;
    }

    /**
     * Measures the current device temperature
     * @return current temperature
     */
    public double measureTemperature(){
        return fanHeaterTemperatureSimulation.getCurrentTemperature();
    }
}
