import config.*;
import heater.Heater;
import manager.ComponentsManager;
import sensor.InternalTemperatureSensor;
import sensor.RoomTemperatureSensor;
import simulation.FanHeaterTemperatureSimulation;
import simulation.RoomTemperatureSimulation;
import sensor.TimeSensor;
import simulation.TimeSimulation;
import ui.UI;

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
        final String userDir = System.getProperty("user.dir");

        //configs
        String settingsPath = userDir + "/fanheater/src/config/settings.properties";
        Settings settings = new Settings(settingsPath);

        String configInternalPath = userDir + "/fanheater/src/config/configInternalSimulation.properties";
        ConfigInternalSimulation configInternalSimulation = new ConfigInternalSimulation(configInternalPath);

        String configRoomPath = userDir + "/fanheater/src/config/configRoomSimulation.properties";
        ConfigRoomSimulation configRoomSimulation = new ConfigRoomSimulation(configRoomPath);

        String configTimePath = userDir + "/fanheater/src/config/configTime.properties";
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

        new UI(componentsManager);

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
