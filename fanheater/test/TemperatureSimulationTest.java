package fanheater.test;

import fanheater.src.simulation.TemperatureSimulation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureSimulationTest {


    // UT 1.1
    @Test
    void temperatureShouldIncreaseWhenHeating() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        20.0,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        simulation.updateTemperature(true);

        assertEquals(
                20.1,
                simulation.getCurrentRoomTemperature(),
                0.001
        );
    }


    //UT 1.2
    @Test
    void temperatureShouldDecreaseWhenNotHeating() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        20.0,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        simulation.updateTemperature(false);

        assertEquals(
                19.98,
                simulation.getCurrentRoomTemperature(),
                0.001
        );
    }


    // UT 1.3
    @Test
    void temperatureShouldNotExceedMaximum() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        49.95,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        simulation.updateTemperature(true);

        assertTrue(
                simulation.getCurrentRoomTemperature() <= 50
        );
    }


    // UT 1.4
    @Test
    void temperatureShouldNotExceedMinimum() {

        TemperatureSimulation simulation =
                new TemperatureSimulation(
                        -49.95,
                        0.1,
                        0.02,
                        -50,
                        50
                );

        simulation.updateTemperature(true);

        assertTrue(
                simulation.getCurrentRoomTemperature() >= -50
        );
    }
}