package fanheater.src.manager;

import fanheater.src.heater.HeaterLevel;
import fanheater.src.heater.HeaterStatus;

public class StatusManager {

    private HeaterStatus currentStatus;
    private HeaterStatus lastStatus;

    public  StatusManager(){
        currentStatus = HeaterStatus.OFF;
        lastStatus = HeaterStatus.OFF;
    }

    public HeaterStatus getCurrentStatus(){
        return currentStatus;
    }

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
