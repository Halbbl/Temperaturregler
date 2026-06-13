package fanheater.src;
import config.Components;
import config.ConfigInternalSimulation;
import config.ConfigRoomSimulation;
import config.Settings;
import fanheater.src.heater.Heater;
import fanheater.src.manager.ComponentsManager;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;
import fanheater.src.ui.FanHeaterUI;

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


        RoomTemperatureSimulation roomTemperatureSimulation;
        RoomTemperatureSensor roomTemperatureSensor;
        FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
        InternalTemperatureSensor internalTemperatureSensor;
        Heater heater;
        ComponentsManager componentsManager;


        roomTemperatureSimulation = new RoomTemperatureSimulation(configRoomSimulation);
        roomTemperatureSensor = new RoomTemperatureSensor(roomTemperatureSimulation);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(configInternalSimulation);
        internalTemperatureSensor = new InternalTemperatureSensor(fanHeaterTemperatureSimulation);
        heater = new Heater();

        Components components = new Components(heater, roomTemperatureSimulation, roomTemperatureSensor, fanHeaterTemperatureSimulation, internalTemperatureSensor);

        componentsManager = new ComponentsManager(components, settings);

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
