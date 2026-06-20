package iteration1.src;
import iteration1.src.heater.Heater;
import iteration1.src.manager.ComponentsManager;
import iteration1.src.sensor.TemperatureSensor;
import iteration1.src.simulation.TemperatureSimulation;
import iteration1.src.ui.FanHeaterUI;

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
        final double TEMPERATURE_INCREASE_RATE = 0.1;
        final double TEMPERATURE_DECREASE_RATE = 0.02;
        final double INITIAL_ROOM_TEMPERATURE = 18.0;
        final double MIN_TEMPERATURE = -50.0;
        final double MAX_TEMPERATURE = 50.0;

        TemperatureSimulation temperatureSimulation;
        TemperatureSensor temperatureSensor;
        Heater heater;
        ComponentsManager componentsManager;

        temperatureSimulation = new TemperatureSimulation(INITIAL_ROOM_TEMPERATURE, TEMPERATURE_INCREASE_RATE, TEMPERATURE_DECREASE_RATE, MIN_TEMPERATURE, MAX_TEMPERATURE);
        temperatureSensor = new TemperatureSensor(temperatureSimulation);
        heater = new Heater(TEMPERATURE_INCREASE_RATE, TEMPERATURE_DECREASE_RATE);
        componentsManager = new ComponentsManager(heater, temperatureSensor, temperatureSimulation);
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
