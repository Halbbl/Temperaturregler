package fanheater.src;
import config.Settings;
import fanheater.src.heater.Heater;
import fanheater.src.manager.ComponentsManager;
import fanheater.src.sensor.InternalTemperatureSensor;
import fanheater.src.sensor.RoomTemperatureSensor;
import fanheater.src.simulation.FanHeaterTemperatureSimulation;
import fanheater.src.simulation.RoomTemperatureSimulation;
import fanheater.src.ui.FanHeaterUI;

/**
 * Main class for starting the fan heater
 */
public class Main {

    /**
     * Main method which starts the fan heater and initializes all needed classes and variables
     * @param args
     */
    public static void main(String[] args) {

        final int UPDATE_INTERVAL_MS = 1000;
        final double WINDOW_OPEN_THRESHOLD = 0.08;

        Settings settings = new Settings("settings.json")

        RoomTemperatureSimulation roomTemperatureSimulation;
        RoomTemperatureSensor roomTemperatureSensor;
        FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
        InternalTemperatureSensor internalTemperatureSensor;
        Heater heater;
        ComponentsManager componentsManager;


        roomTemperatureSimulation = new RoomTemperatureSimulation(INITIAL_ROOM_TEMPERATURE, ROOM_TEMPERATURE_DECREASE_RATE, MAX_TEMPERATURE_ROOM, OUTSIDE_TEMPERATURE, WINDOW_OPEN_DECREASE_RATE);
        roomTemperatureSensor = new RoomTemperatureSensor(roomTemperatureSimulation);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(START_TEMPERATURE_DEVICE, DEVICE_TEMPERATURE_DECREASE_RATE);
        internalTemperatureSensor = new InternalTemperatureSensor(fanHeaterTemperatureSimulation);
        heater = new Heater();

        componentsManager = new ComponentsManager(heater, roomTemperatureSensor, roomTemperatureSimulation, fanHeaterTemperatureSimulation, MAX_DEVICE_TEMPERATURE, PUFFER_DEVICE_TEMPERATURE, internalTemperatureSensor, WINDOW_OPEN_THRESHOLD);

        new FanHeaterUI(componentsManager);

        while (true) {
            componentsManager.update();
            try {
                Thread.sleep(UPDATE_INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
