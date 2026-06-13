package sensor;

import simulation.TimeSimulation;

public class TimeSensor {
    private TimeSimulation timeSimulation;

    public TimeSensor(TimeSimulation timeSimulation) {
        this.timeSimulation = timeSimulation;
    }

    public int getMinutes(){
        int[] time = timeSimulation.getTime();
        return time[1];
    }

    public int getHours(){
        int[] time = timeSimulation.getTime();
        return time[0];
    }
}
