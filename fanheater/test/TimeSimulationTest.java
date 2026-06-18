package fanheater.test;

import config.ConfigTime;
import simulation.TimeSimulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeSimulationTest {

    ConfigTime configTime = new ConfigTime(System.getProperty("user.dir") + "/fanheater/src/config/configTime.properties");

    //UT 3.11
    @Test
    void shouldIncreaseHourWhenMinuteOverflows() {

        TimeSimulation simulation =
                new TimeSimulation(configTime);

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
                new TimeSimulation(configTime);

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
