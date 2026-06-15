package manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class TimerManager {

    private Properties props;
    private final String path;

    public TimerManager(String path){

        this.path = path;

        props = new Properties();

        try (FileReader reader = new FileReader(path)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings file: " + path, e);
        }
    }

    private void save() {
        try (FileWriter writer = new FileWriter(path)) {
            props.store(writer, null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save settings file: " + path, e);
        }
    }

    public void addTimerEntry(int hour, int minute, double targetTemp){
        props.setProperty("timer" + (props.size()+1), hour + "," +  minute + "," + targetTemp);
        save();
    }

    public String[] getTimerEntry(int num){
        String timer = props.getProperty("timer" + num);
        return timer.split(",");
    }

    public int getTimerCount(){
        return props.size();
    }

    public void removeTimerEntry(int num) {
        props.remove("timer" + num);
        reindexTimers(num);
        save();
    }

    private void reindexTimers(int rmvTimerIndex) {
        for (int i = rmvTimerIndex; i <= getTimerCount()-1; i++){
            props.setProperty("timer" + i, props.getProperty("timer" + i+1));
        }
        props.remove(getTimerCount());
        save();
    }
}
