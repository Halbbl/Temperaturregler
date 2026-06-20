package iteration3.testcases;

import iteration3.src.manager.SettingsManager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsManagerTest {

    String TEST_FILE = System.getProperty("user.dir") + "/Iterations/Iteration 3/Link to Test cases/" + "settingsTest.properties";

    //UT 3.2
    @Test
    void shouldSaveTargetTemperature() {

        SettingsManager manager =
                new SettingsManager(TEST_FILE);

        manager.setTargetTemperature(23.5);

        SettingsManager reload =
                new SettingsManager(TEST_FILE);

        assertEquals(
                23.5,
                reload.getTargetTemperature()
        );
    }

    //UT 3.3
    @Test
    void shouldOverwriteExistingTargetTemperature() {

        SettingsManager manager =
                new SettingsManager(TEST_FILE);

        manager.setTargetTemperature(20.0);
        manager.setTargetTemperature(25.0);

        SettingsManager reload =
                new SettingsManager(TEST_FILE);

        assertEquals(
                25.0,
                reload.getTargetTemperature()
        );
    }

    //UT 3.4
    @Test
    void settingsShouldPersistAcrossSessions() {

        SettingsManager manager =
                new SettingsManager(TEST_FILE);

        manager.setTargetTemperature(22.0);

        SettingsManager secondSession =
                new SettingsManager(TEST_FILE);

        assertEquals(
                22.0,
                secondSession.getTargetTemperature()
        );
    }

}
