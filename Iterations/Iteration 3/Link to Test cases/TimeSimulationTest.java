package iteration3.testcases;

import iteration3.src.simulation.TimeSimulation;
import iteration3.src.config.ConfigTimeSimulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeSimulationTest {

    ConfigTimeSimulation configTimeSimulation = new iteration3.src.config.ConfigTimeSimulation(System.getProperty("user.dir") + "/Iterations/Iteration 3/Link To Src/config/" + "configTimeSimulation.properties");

    //UT 3.11
    @Test
    void shouldIncreaseHourWhenMinuteOverflows() {

        TimeSimulation simulation =
                new TimeSimulation(configTimeSimulation);

        simulation.setTime(10, 59);

        simulation.updateTime();

        assertEquals(
                11,
                simulation.getHours()
        );

        assertEquals(
                0,
                simulation.getMinutes()
        );
    }

    //UT 3.12
    @Test
    void shouldResetHourAfterMidnight() {

        TimeSimulation simulation =
                new TimeSimulation(configTimeSimulation);

        simulation.setTime(23, 60);

        simulation.updateTime();

        assertEquals(
                0,
                simulation.getHours()
        );

        assertEquals(
                0,
                simulation.getMinutes()
        );
    }



}
