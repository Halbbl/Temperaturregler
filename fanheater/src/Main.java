package fanheater.src;
import fanheater.src.heater.Heater;
import fanheater.src.manager.HeatingManager;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;

/**
 * Main class for starting the fan heater
 */
public class Main {


    public static final int UPDATE_INTERVAL_MS = 1000; // Update interval in milliseconds

    /**
     * Main method which starts the fan heater and initializes all needed classes and variables
     * @param args
     */
    public static void main(String[] args) {

        double initialRoomTemperature = 18.0; // Initial room temperature in °C
        double targetTemperature = 22.0; // Target temperature in °C

        TemperatureSimulation temperatureSimulation = new TemperatureSimulation(initialRoomTemperature);
        TemperatureSensor temperatureSensor = new TemperatureSensor(temperatureSimulation);
        Heater heater = new Heater(temperatureSensor, targetTemperature);
        HeatingManager heatingManager = new HeatingManager(heater, temperatureSensor, temperatureSimulation);

        while (true) { 
            heatingManager.update();
            System.out.println("Current Room Temperature: " + Math.round(temperatureSensor.readCurrentTemperature() * 10.0) / 10.0 + "°C");
            try {
                Thread.sleep(UPDATE_INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
