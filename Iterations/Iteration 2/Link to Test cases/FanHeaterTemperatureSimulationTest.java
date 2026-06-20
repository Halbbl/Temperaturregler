package iteration2.testcases;

import iteration2.src.heater.HeaterLevel;
import iteration2.src.simulation.FanHeaterTemperatureSimulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FanHeaterTemperatureSimulationTest {

    // UT 2.13
    @Test
    void temperatureShouldIncreaseOnLowLevel() {

        FanHeaterTemperatureSimulation simulation =
                new FanHeaterTemperatureSimulation(
                        20.0,
                        1.0
                );

        simulation.updateTemperature(
                HeaterLevel.LOW
        );

        assertEquals(
                20.5,
                simulation.getCurrentTemperature()
        );
    }

    // UT 2.14
    @Test
    void temperatureShouldIncreaseOnMediumLevel() {

        FanHeaterTemperatureSimulation simulation =
                new FanHeaterTemperatureSimulation(
                        20.0,
                        1.0
                );

        simulation.updateTemperature(
                HeaterLevel.MEDIUM
        );

        assertEquals(
                21.2,
                simulation.getCurrentTemperature()
        );
    }

    // UT 2.15
    @Test
    void temperatureShouldIncreaseOnHighLevel() {

        FanHeaterTemperatureSimulation simulation =
                new FanHeaterTemperatureSimulation(
                        20.0,
                        1.0
                );

        simulation.updateTemperature(
                HeaterLevel.HIGH
        );

        assertEquals(
                22.5,
                simulation.getCurrentTemperature()
        );
    }

    // UT 2.16
    @Test
    void temperatureShouldDecreaseWhenTurnedOff() {

        FanHeaterTemperatureSimulation simulation =
                new FanHeaterTemperatureSimulation(
                        20.0,
                        1.0
                );

        simulation.updateTemperature(
                HeaterLevel.HIGH
        );

        simulation.updateTemperature(
                HeaterLevel.OFF
        );

        assertEquals(
                21.5,
                simulation.getCurrentTemperature()
        );
    }

    // UT 2.17
    @Test
    void temperatureShouldNotFallBelowStartTemperature() {

        FanHeaterTemperatureSimulation simulation =
                new FanHeaterTemperatureSimulation(
                        20.0,
                        5.0
                );

        simulation.updateTemperature(
                HeaterLevel.OFF
        );

        assertEquals(
                20.0,
                simulation.getCurrentTemperature()
        );
    }


}
