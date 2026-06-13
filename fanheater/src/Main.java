package fanheater.src;
import config.*;
import fanheater.src.heater.Heater;
import fanheater.src.manager.ComponentsManager;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;
import fanheater.src.ui.FanHeaterUI;
import sensor.TimeSensor;
import simulation.TimeSimulation;

/**
 * Main class for starting the fan heater
 */
public class Main {

    /**
     * Main method which starts the fan heater and initializes all needed classes and variables
     * @param args
     */
    public static void main(String[] args) {

        final int UPDATE_INTERVAL_MS = 1000;

        //configs
        String settingsPath = System.getProperty("user.dir") + "/fanheater/src/config/settings.properties";
        Settings settings = new Settings(settingsPath);

        String configInternalPath = System.getProperty("user.dir") + "/fanheater/src/config/configInternalSimulation.properties";
        ConfigInternalSimulation configInternalSimulation = new ConfigInternalSimulation(configInternalPath);

        String configRoomPath = System.getProperty("user.dir") + "/fanheater/src/config/configRoomSimulation.properties";
        ConfigRoomSimulation configRoomSimulation = new ConfigRoomSimulation(configRoomPath);

        String configTimePath = System.getProperty("user.dir") + "/fanheater/src/config/configTime.properties";
        ConfigTime configTime = new ConfigTime(configTimePath);


        RoomTemperatureSimulation roomTemperatureSimulation;
        RoomTemperatureSensor roomTemperatureSensor;
        FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
        InternalTemperatureSensor internalTemperatureSensor;
        Heater heater;
        ComponentsManager componentsManager;
        TimeSensor timeSensor;
        TimeSimulation timeSimulation;


        timeSimulation = new TimeSimulation(configTime);
        timeSensor = new TimeSensor(timeSimulation);
        roomTemperatureSimulation = new RoomTemperatureSimulation(configRoomSimulation);
        roomTemperatureSensor = new RoomTemperatureSensor(roomTemperatureSimulation);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(configInternalSimulation);
        internalTemperatureSensor = new InternalTemperatureSensor(fanHeaterTemperatureSimulation);
        heater = new Heater();

        Components components = new Components(heater, roomTemperatureSensor, internalTemperatureSensor, timeSensor);
        Simulations simulations = new Simulations(roomTemperatureSimulation, fanHeaterTemperatureSimulation, timeSimulation);

        componentsManager = new ComponentsManager(components, simulations, settings);

        new FanHeaterUI(componentsManager);

        while (true) {
            componentsManager.update();
            try {
                Thread.sleep(UPDATE_INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
