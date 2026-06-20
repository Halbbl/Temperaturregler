package testcases;

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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComponentsManagerTest {

    final String userDir = System.getProperty("user.dir") + "/src/config/";
    final String testUserDir = System.getProperty("user.dir") + "/Test cases/";


    ConfigRoomSimulation configRoomSimulation = new ConfigRoomSimulation( userDir + "configRoomSimulation.properties");
    Settings settings = new Settings(testUserDir + "settingsTest.properties");
    ConfigInternalSimulation configInternalSimulation = new ConfigInternalSimulation(userDir + "configInternalSimulation.properties");
    ConfigTimeSimulation configTimeSimulation = new ConfigTimeSimulation(userDir + "configTimeSimulation.properties");

    RoomTemperatureSimulation roomTemperatureSimulation = new RoomTemperatureSimulation(configRoomSimulation);
    FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(configInternalSimulation);
    TimeSimulation timeSimulation = new TimeSimulation(configTimeSimulation);
    Simulations simulations = new Simulations(roomTemperatureSimulation, fanHeaterTemperatureSimulation, timeSimulation);
    SimulationManager simulationManager = new SimulationManager(simulations);

    Components components = new Components(new Heater(), new RoomTemperatureSensor(roomTemperatureSimulation), new InternalTemperatureSensor(fanHeaterTemperatureSimulation), new TimeSensor(timeSimulation));;

    ComponentsManager componentsManager = new ComponentsManager(components, simulationManager, settings);

    //UT 3.1
    @Test
    void shouldApplyTargetTemperatureWhenTimerTriggers() {

        componentsManager.addTimerEntry(
                8,
                0,
                25.0
        );

        timeSimulation.setTime(
                7,
                59
        );

        componentsManager.update();

        assertEquals(
                25.0,
                componentsManager.getTargetTemperature()
        );
    }

}
