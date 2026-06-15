package simulation;

import config.ConfigTime;

public class TimeSimulation {
    private final static int MINUTES = 60;
    private final static int HOURS = 24;

    private int minute;
    private int hour;

    public TimeSimulation(ConfigTime config) {
        minute = config.minute;
        hour = config.hour;
    }

    public int getMinutes() {
        return minute;
    }

    public int getHours() {
        return hour;
    }

    public void updateTime(){
        minute++;
        if (minute > MINUTES){
            minute = 0;
            hour += 1;
        }
        if (hour > HOURS){
            hour = 0;
        }
    }
}
