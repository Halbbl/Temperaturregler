package manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TimerManager {

    private Properties props;

    public TimerManager(String path){

        props = new Properties();

        try (FileReader reader = new FileReader(path)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + path, e);
        }
    }

    public void addTimerEntry(int hour, int minute, double targetTemp){
        props.setProperty("timer" + props.size(), hour + "," +  minute + "," + targetTemp);
    }

    public String[] getTimerEntry(int num){
        String timer = props.getProperty("timer" + num);
        return timer.split(",");
    }

    public int getTimerCount(){
        return props.size();
    }

}
