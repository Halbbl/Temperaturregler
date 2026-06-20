package iteration2.testcases;

import iteration2.src.heater.Heater;
import iteration2.src.heater.HeaterLevel;
import iteration2.src.heater.HeaterStatus;
import iteration2.src.manager.ComponentsManager;
import iteration2.src.sensor.InternalTemperatureSensor;
import iteration2.src.sensor.RoomTemperatureSensor;
import iteration2.src.simulation.FanHeaterTemperatureSimulation;
import iteration2.src.simulation.RoomTemperatureSimulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComponentsManagerTest {

    RoomTemperatureSimulation roomTemperatureSimulation;
    FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
    ComponentsManager componentsManager;


    void createManager(double roomtemp, double deviceTemp, double maxDeviceTemp){
        roomTemperatureSimulation = new RoomTemperatureSimulation(roomtemp, 0.02, 50.0, 8.0, 5);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(deviceTemp, 0.2);
        componentsManager = new ComponentsManager(
                new Heater(),
                new RoomTemperatureSensor(roomTemperatureSimulation),
                roomTemperatureSimulation, fanHeaterTemperatureSimulation,
                maxDeviceTemp,
                10.0,
                new InternalTemperatureSensor(fanHeaterTemperatureSimulation),
                0.08
        );
    }

    void createManager(){
        roomTemperatureSimulation = new RoomTemperatureSimulation(18, 0.02, 50.0, 8.0, 5);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(20, 0.2);
        componentsManager = new ComponentsManager(
                new Heater(),
                new RoomTemperatureSensor(roomTemperatureSimulation),
                roomTemperatureSimulation, fanHeaterTemperatureSimulation,
                50,
                10.0,
                new InternalTemperatureSensor(fanHeaterTemperatureSimulation),
                0.08
        );
    }

    // UT 2.1
    @Test
    void heaterShouldTurnOffWhenOverheated() {

        createManager(
                20,
                101,
                100
        );

        componentsManager.update();

        assertEquals(
                HeaterLevel.OFF,
                componentsManager.getHeaterLevel()
        );
    }

    // UT 2.2
    @Test
    void heaterShouldRecoverAfterCoolingDown() {

        createManager(
                20,
                101,
                100
        );

        componentsManager.update();

        fanHeaterTemperatureSimulation.setCurrentTemperature(80);

        componentsManager.update();

        assertNotEquals(
                HeaterStatus.OVERHEATED,
                componentsManager.getHeaterStatus()
        );
    }

    // UT 2.3
    @Test
    void windowStateShouldToggle() {

        createManager();

        boolean initial =
                componentsManager.isWindowOpen();

        componentsManager.updateWindow();

        assertFalse(
                initial &
                componentsManager.isWindowOpen()
        );
    }

    // UT 2.4
    @Test
    void shouldDetectRapidTemperatureDrop() {

        createManager();

        roomTemperatureSimulation.setCurrentRoomTemperature(20);

        componentsManager.update();

        roomTemperatureSimulation.setCurrentRoomTemperature(15);

        componentsManager.update();

        assertTrue(
                componentsManager.isWindowOpen()
        );
    }

    // UT 2.5
    @Test
    void heaterShouldTurnOffWhenWindowOpenDetected() {

        createManager();

        roomTemperatureSimulation.setCurrentRoomTemperature(20);
        componentsManager.update();

        roomTemperatureSimulation.setCurrentRoomTemperature(15);
        componentsManager.update();

        assertEquals(
                HeaterLevel.OFF,
                componentsManager.getHeaterLevel()
        );
    }

    // UT 2.6
    @Test
    void energySavingShouldActivate() {

        createManager();

        componentsManager.updateEnergySaving();

        assertTrue(
                componentsManager.isEnergySavingActivated()
        );
    }

    // UT 2.7
    @Test
    void energySavingShouldDeactivate() {

        createManager();

        componentsManager.updateEnergySaving();
        componentsManager.updateEnergySaving();

        assertFalse(
                componentsManager.isEnergySavingActivated()
        );
    }

    // UT 2.8
    @Test
    void heaterShouldNotUseHighLevelInEnergySavingMode() {

        createManager();

        componentsManager.updateEnergySaving();

        componentsManager.updateForTargetTemperature(30);

        componentsManager.update();

        assertNotEquals(
                HeaterLevel.HIGH,
                componentsManager.getHeaterLevel()
        );
    }

    // UT 2.9
    @Test
    void heatingLevelShouldUseHighLevelWhenDifferenceIsFiveOrMore() {

        createManager();

        roomTemperatureSimulation.setCurrentRoomTemperature(20);

        componentsManager.updateForTargetTemperature(30);

        componentsManager.update();

        assertEquals(
                HeaterLevel.HIGH,
                componentsManager.getHeaterLevel()
        );
    }

    // UT 2.10
    @Test
    void heatingLevelShouldUseMediumLevelWhenDifferenceIsBetweenTwoAndFive() {

        createManager();

        roomTemperatureSimulation.setCurrentRoomTemperature(20);

        componentsManager.updateForTargetTemperature(24);

        componentsManager.update();

        assertEquals(
                HeaterLevel.MEDIUM,
                componentsManager.getHeaterLevel()
        );
    }

    // UT 2.11
    @Test
    void heatingLevelShouldUseLowLevelWhenDifferenceIsUnderTwo() {

        createManager();

        roomTemperatureSimulation.setCurrentRoomTemperature(20);

        componentsManager.updateForTargetTemperature(21);

        componentsManager.update();

        assertEquals(
                HeaterLevel.LOW,
                componentsManager.getHeaterLevel()
        );
    }

    // UT 2.12
    @Test
    void heatingLevelShouldBeOffWhenTargetIsReachedOrHigher() {

        createManager();

        roomTemperatureSimulation.setCurrentRoomTemperature(25);

        componentsManager.updateForTargetTemperature(20);

        componentsManager.update();

        assertEquals(
                HeaterLevel.OFF,
                componentsManager.getHeaterLevel()
        );
    }



}