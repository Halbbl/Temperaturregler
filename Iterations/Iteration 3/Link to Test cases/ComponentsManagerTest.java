package iteration3.testcases;

import iteration3.src.config.*;
import iteration3.src.manager.ComponentsManager;
import iteration3.src.sensor.InternalTemperatureSensor;
import iteration3.src.sensor.RoomTemperatureSensor;
import iteration3.src.sensor.TimeSensor;
import iteration3.src.simulation.FanHeaterTemperatureSimulation;
import iteration3.src.simulation.RoomTemperatureSimulation;
import iteration3.src.simulation.TimeSimulation;
import iteration3.src.heater.Heater;
import iteration3.src.manager.SimulationManager;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ComponentsManagerTest {

    final String userDir = System.getProperty("user.dir") + "/Iterations/Iteration 3/Link To Src/config/";
    final String testUserDir = System.getProperty("user.dir") + "/Iterations/Iteration 3/Link to Test cases/";


    ConfigRoomSimulation configRoomSimulation = new ConfigRoomSimulation( userDir + "configRoomSimulation.properties");
    Settings settings = new Settings(testUserDir + "settingsTest.properties");
    ConfigInternalSimulation configInternalSimulation = new ConfigInternalSimulation(userDir + "configInternalSimulation.properties");
    ConfigTimeSimulation configTime = new ConfigTimeSimulation(userDir + "configTimeSimulation.properties");

    RoomTemperatureSimulation roomTemperatureSimulation = new RoomTemperatureSimulation(configRoomSimulation);
    FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(configInternalSimulation);
    TimeSimulation timeSimulation = new TimeSimulation(configTime);
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
