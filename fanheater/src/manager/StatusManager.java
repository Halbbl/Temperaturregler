package manager;

import heater.HeaterStatus;
import heater.HeaterLevel;

/**
 * Manager for heater status
 */
public class StatusManager {

    private HeaterStatus currentStatus;

    /**
     * Constructor of status manager
     */
    public  StatusManager(){
        currentStatus = HeaterStatus.OFF;
    }

    /**
     * Gets the current status
     * @return current status
     */
    public HeaterStatus getCurrentStatus(){
        return currentStatus;
    }

    /**
     * Updates the current status
     * @param heaterLevel current level of heating
     * @param overheated overheated or not
     * @param windowOpen is window open
     * @param isOn heater is on
     */
    public void updateStatus(HeaterLevel heaterLevel, boolean overheated, boolean windowOpen, boolean isOn){
        if (!isOn){
            currentStatus = HeaterStatus.OFF;
        } else {
            if (!windowOpen){
                if (overheated && !currentStatus.equals(HeaterStatus.OVERHEATED)){
                    currentStatus = HeaterStatus.OVERHEATED;
                } else if (!heaterLevel.equals(HeaterLevel.OFF) && !currentStatus.equals(HeaterStatus.HEATING)){
                    currentStatus = HeaterStatus.HEATING;
                } else if (heaterLevel.equals(HeaterLevel.OFF) && !currentStatus.equals(HeaterStatus.OFF) && !overheated){
                    currentStatus = HeaterStatus.STANDBY;
                }
            } else {
                currentStatus = HeaterStatus.WINDOW_OPEN;
            }
        }
    }
}
