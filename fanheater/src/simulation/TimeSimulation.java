package simulation;

import config.ConfigTime;

/**
 * Simulation for tracking and updating the current time
 */
public class TimeSimulation {

    private final static int MINUTES = 60;
    private final static int HOURS = 24;

    private int minute;
    private int hour;

    /**
     * Default constructor
     *
     * @param config time configuration containing the initial time values
     */
    public TimeSimulation(ConfigTime config) {
        minute = config.minute;
        hour = config.hour;
    }

    /**
     * Gets the current minute value
     *
     * @return current minute
     */
    public int getMinutes() {
        return minute;
    }

    /**
     * Gets the current hour value
     *
     * @return current hour
     */
    public int getHours() {
        return hour;
    }

    /**
     * Updates the simulation time by one minute
     * Handles minute and hour overflow
     */
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