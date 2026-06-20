package iteration3.src.sensor;

import iteration3.src.simulation.TimeSimulation;

/**
 * Sensor for retrieving current simulation time values
 */
public class TimeSensor {

    private TimeSimulation timeSimulation;

    /**
     * Default constructor
     *
     * @param timeSimulation time simulation instance
     */
    public TimeSensor(TimeSimulation timeSimulation) {
        this.timeSimulation = timeSimulation;
    }

    /**
     * Gets the current minute value from the time simulation
     *
     * @return current minute
     */
    public int getMinutes(){
        return timeSimulation.getMinutes();
    }

    /**
     * Gets the current hour value from the time simulation
     *
     * @return current hour
     */
    public int getHours(){
        return timeSimulation.getHours();
    }
}