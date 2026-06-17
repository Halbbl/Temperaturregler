package fanheater.test;

import fanheater.src.heater.Heater;
import fanheater.src.manager.ComponentsManager;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComponentsManagerTest {


    //UT 1.5
    @Test
    void heaterShouldActivateWhenTemperatureIsTooLow() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        15.0,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        TemperatureSensor sensor =
                new TemperatureSensor(simulation);

        Heater heater =
                new Heater(0.1,0.02);


        ComponentsManager manager =
                new ComponentsManager(
                        heater,
                        sensor,
                        simulation
                );


        manager.updateForTargetTemperature(20.0);

        manager.update();


        assertTrue(
                heater.getActive()
        );
    }


    //UT 1.6
    @Test
    void heaterShouldDeactivateWhenTargetTemperatureReached() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        20.0,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        TemperatureSensor sensor =
                new TemperatureSensor(simulation);

        Heater heater =
                new Heater(0.1,0.02);


        ComponentsManager manager =
                new ComponentsManager(
                        heater,
                        sensor,
                        simulation
                );


        manager.updateForTargetTemperature(20.0);

        manager.update();


        assertFalse(
                heater.getActive()
        );
    }


    //UT 1.7
    @Test
    void heaterKeepTemperatureWhenTargetTemperatureReached() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        19.5,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        TemperatureSensor sensor =
                new TemperatureSensor(simulation);

        Heater heater =
                new Heater(0.1,0.02);

        ComponentsManager manager =
                new ComponentsManager(
                        heater,
                        sensor,
                        simulation
                );

        double targetTemperature = 20.0;
        manager.updateForTargetTemperature(targetTemperature);

        manager.update();

        try {
            Thread.sleep(20000); // 5 Sekunden warten
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertFalse(
                Math.abs(manager.getCurrentRoomTemperature()-targetTemperature) > 1.0
        );
    }
}