package iteration3.testcases;

import iteration3.src.manager.TimerManager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimerManagerTest {

    String TEST_FILE = System.getProperty("user.dir") + "/Iterations/Iteration 3/Link to Test cases/" + "timersTest.properties";

    void whipeTimerList(TimerManager manager) {
        int timers = manager.getTimerCount();
        for (int i = timers; i >= 1; i--){
            manager.removeTimerEntry(i);
        }
    }

    //UT 3.5
    @Test
    void shouldAddTimerEntry() {

        TimerManager manager =
                new TimerManager(TEST_FILE);

        int startEntries = manager.getTimerCount();

        manager.addTimerEntry(
                8,
                30,
                21.0
        );

        assertEquals(
                startEntries + 1,
                manager.getTimerCount()
        );
    }

    //UT 3.6
    @Test
    void shouldReturnTimerEntry() {

        TimerManager manager =
                new TimerManager(TEST_FILE);

        manager.addTimerEntry(
                8,
                30,
                21.0
        );

        String[] timer =
                manager.getTimerEntry(manager.getTimerCount());

        assertEquals("8", timer[0]);
        assertEquals("30", timer[1]);
        assertEquals("21.0", timer[2]);
    }

    //UT 3.7
    @Test
    void shouldRemoveTimerEntry() {

        TimerManager manager =
                new TimerManager(TEST_FILE);

        whipeTimerList(manager);

        manager.addTimerEntry(8,30,21);
        manager.removeTimerEntry(1);

        assertEquals(
                0,
                manager.getTimerCount()
        );
    }

    //UT 3.8
    @Test
    void shouldReturnCorrectTimerCount() {

        TimerManager manager =
                new TimerManager(TEST_FILE);

        whipeTimerList(manager);

        manager.addTimerEntry(8,0,20);
        manager.addTimerEntry(9,0,22);

        assertEquals(
                2,
                manager.getTimerCount()
        );
    }

    //UT 3.9
    @Test
    void shouldShiftEntriesAfterDelete() {

        TimerManager manager =
                new TimerManager(TEST_FILE);

        whipeTimerList(manager);

        manager.addTimerEntry(8,0,20);
        manager.addTimerEntry(9,0,22);

        manager.removeTimerEntry(1);

        String[] timer =
                manager.getTimerEntry(1);

        assertEquals(
                "9",
                timer[0]
        );
    }

    //UT 3.10
    @Test
    void timerShouldPersistAcrossSessions() {

        TimerManager manager =
                new TimerManager(TEST_FILE);

        whipeTimerList(manager);

        manager.addTimerEntry(
                7,
                15,
                22
        );

        TimerManager reload =
                new TimerManager(TEST_FILE);

        assertEquals(
                1,
                reload.getTimerCount()
        );
    }
}
