package simulation;

import config.ConfigTime;

public class TimeSimulation {
    private final static int MINUTES = 60;
    private final static int HOURS = 24;

    private int[] time = new int[2];

    public TimeSimulation(ConfigTime config) {
        time = config.time;
    }

    public int[] getTime(){
        return time;
    }

    public void updateTime(){
        if (time[1] > MINUTES){
            time[1] = 0;
            time[0] += 1;
        }
        if (time[0] > HOURS){
            time[0] = 0;
        }
    }
}
