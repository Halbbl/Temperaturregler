package src;

import src.config.*;
import src.heater.Heater;
import src.manager.ComponentsManager;
import src.manager.SimulationManager;
import src.sensor.InternalTemperatureSensor;
import src.sensor.RoomTemperatureSensor;
import src.sensor.TimeSensor;
import src.simulation.FanHeaterTemperatureSimulation;
import src.simulation.RoomTemperatureSimulation;
import src.simulation.TimeSimulation;
import src.ui.FanHeaterUI;

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
        final String userDir = System.getProperty("user.dir") + "/src/config/";

        //configs
        String settingsPath = userDir + "settings.properties";
        Settings settings = new Settings(settingsPath);

        String configInternalPath = userDir + "configInternalSimulation.properties";
        ConfigInternalSimulation configInternalSimulation = new ConfigInternalSimulation(configInternalPath);

        String configRoomPath = userDir + "configRoomSimulation.properties";
        ConfigRoomSimulation configRoomSimulation = new ConfigRoomSimulation(configRoomPath);

        String configTimePath = userDir + "configTimeSimulation.properties";
        ConfigTimeSimulation configTimeSimulation = new ConfigTimeSimulation(configTimePath);

        //simulations
        RoomTemperatureSimulation roomTemperatureSimulation;
        FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
        TimeSimulation timeSimulation;

        roomTemperatureSimulation = new RoomTemperatureSimulation(configRoomSimulation);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(configInternalSimulation);
        timeSimulation = new TimeSimulation(configTimeSimulation);

        Simulations simulations = new Simulations(roomTemperatureSimulation, fanHeaterTemperatureSimulation, timeSimulation);

        SimulationManager simulationManager = new SimulationManager(simulations);


        RoomTemperatureSensor roomTemperatureSensor;
        InternalTemperatureSensor internalTemperatureSensor;
        Heater heater;
        ComponentsManager componentsManager;
        TimeSensor timeSensor;

        timeSensor = new TimeSensor(timeSimulation);
        roomTemperatureSensor = new RoomTemperatureSensor(roomTemperatureSimulation);
        internalTemperatureSensor = new InternalTemperatureSensor(fanHeaterTemperatureSimulation);
        heater = new Heater();

        Components components = new Components(heater, roomTemperatureSensor, internalTemperatureSensor, timeSensor);

        componentsManager = new ComponentsManager(components, simulationManager, settings);

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
