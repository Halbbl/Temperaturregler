package fanheater.src;
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
        final double ROOM_TEMPERATURE_DECREASE_RATE = 0.02;
        final double INITIAL_ROOM_TEMPERATURE = 18.0;
        final double MIN_TEMPERATURE_ROOM = -50.0;
        final double MAX_TEMPERATURE_ROOM = 50.0;
        final double START_TEMPERATURE_DEVICE = 20.0;
        final double DEVICE_TEMPERATURE_DECREASE_RATE = 0.2;
        final double MAX_DEVICE_TEMPERATURE = 90.0;
        final double PUFFER_DEVICE_TEMPERATURE = 5.0;

        RoomTemperatureSimulation roomTemperatureSimulation;
        RoomTemperatureSensor roomTemperatureSensor;
        FanHeaterTemperatureSimulation fanHeaterTemperatureSimulation;
        InternalTemperatureSensor internalTemperatureSensor;
        Heater heater;
        ComponentsManager componentsManager;


        roomTemperatureSimulation = new RoomTemperatureSimulation(INITIAL_ROOM_TEMPERATURE, ROOM_TEMPERATURE_DECREASE_RATE, MIN_TEMPERATURE_ROOM, MAX_TEMPERATURE_ROOM);
        roomTemperatureSensor = new RoomTemperatureSensor(roomTemperatureSimulation);
        fanHeaterTemperatureSimulation = new FanHeaterTemperatureSimulation(START_TEMPERATURE_DEVICE, DEVICE_TEMPERATURE_DECREASE_RATE);
        internalTemperatureSensor = new InternalTemperatureSensor(fanHeaterTemperatureSimulation);
        heater = new Heater();

        componentsManager = new ComponentsManager(heater, roomTemperatureSensor, roomTemperatureSimulation, fanHeaterTemperatureSimulation, MAX_DEVICE_TEMPERATURE, PUFFER_DEVICE_TEMPERATURE, internalTemperatureSensor);
        componentsManager.updateForTargetTemperature(INITIAL_ROOM_TEMPERATURE);

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
