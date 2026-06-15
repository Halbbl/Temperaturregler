package sensor;

import simulation.TimeSimulation;

public class TimeSensor {
    private TimeSimulation timeSimulation;

    public TimeSensor(TimeSimulation timeSimulation) {
        this.timeSimulation = timeSimulation;
    }

    public int getMinutes(){
        return timeSimulation.getMinutes();
    }

    public int getHours(){
         return timeSimulation.getHours();
    }
}
