package fanheater.src.manager;

import fanheater.src.heater.HeaterLevel;
import fanheater.src.heater.HeaterStatus;

/**
 * Manager for heater status
 */
public class StatusManager {

    private HeaterStatus currentStatus;
    private HeaterStatus lastStatus;

    /**
     * Constructor of status manager
     */
    public  StatusManager(){
        currentStatus = HeaterStatus.OFF;
        lastStatus = HeaterStatus.OFF;
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
     */
    public void updateStatus(HeaterLevel heaterLevel, boolean overheated){
        if (overheated && !currentStatus.equals(HeaterStatus.OVERHEATED)){
            lastStatus = currentStatus;
            currentStatus = HeaterStatus.OVERHEATED;
        } else if (!heaterLevel.equals(HeaterLevel.OFF) && !currentStatus.equals(HeaterStatus.HEATING)){
            lastStatus = currentStatus;
            currentStatus = HeaterStatus.HEATING;
        } else if (heaterLevel.equals(HeaterLevel.OFF) && !currentStatus.equals(HeaterStatus.OFF) && !overheated){
            lastStatus = currentStatus;
            currentStatus = HeaterStatus.STANDBY;
        }
    }
}
