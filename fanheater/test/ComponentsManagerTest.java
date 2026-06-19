package fanheater.test;

import config.Components;
import config.ConfigRoomSimulation;
import config.ConfigTime;
import config.ConfigInternalSimulation;
import config.Settings;
import sensor.InternalTemperatureSensor;
import simulation.RoomTemperatureSimulation;
import simulation.FanHeaterTemperatureSimulation;
import manager.ComponentsManager;
import sensor.RoomTemperatureSensor;
import sensor.InternalTemperatureSensor;
import simulation.TimeSimulation;
import config.Simulations;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentsManagerTest {

    ConfigRoomSimulation configRoomSimulation = new ConfigRoomSimulation(System.getProperty("user.dir") + "/fanheater/src/config/configRoomSimulation.properties");
    Settings settings = new Settings(System.getProperty("user.dir") + "/fanheater/test/settingsTest.properties");
    ConfigInternalSimulation configInternalSimulation = new ConfigInternalSimulation(System.getProperty("user.dir") + "/fanheater/src/config/configInternalSimulation.properties");
    ConfigTime configTime = new ConfigTime(System.getProperty("user.dir") + "/fanheater/src/config/configTime.properties");

    RoomTemperatureSimulation roomTemperatureSimulation = new RoomTemperatureSimulation(configRoomSimulation);
    FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(configInternalSimulation);
    TimeSimulation timeSimulation = new TimeSimulation(configTime);
    Simulations simulations = new Simulations(roomTemperatureSimulation, fanHeaterTemperatureSimulation, timeSimulation);

    Components components = new Components(new heater.Heater(), new RoomTemperatureSensor(roomTemperatureSimulation), new InternalTemperatureSensor(fanHeaterTemperatureSimulation), new sensor.TimeSensor(timeSimulation));;

    ComponentsManager componentsManager = new ComponentsManager(components, simulations, settings);

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
