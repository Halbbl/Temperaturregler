package fanheater.src;
import fanheater.src.heater.Heater;
import fanheater.src.manager.HeatingManager;
import fanheater.src.sensor.TemperatureSensor;
import fanheater.src.simulation.TemperatureSimulation;
import fanheater.src.ui.FanHeaterUI;

/**
 * Main class for starting the fan heater
 */
public class Main {


    private static final int UPDATE_INTERVAL_MS = 1000; // Update interval in milliseconds
    private static final double TEMPERATURE_INCREASE_RATE = 0.1;
    private static final double TEMPERATURE_DECREASE_RATE = 0.02;

    private static TemperatureSimulation temperatureSimulation;
    private static TemperatureSensor temperatureSensor;
    private static FanHeaterUI fanHeaterUI;
    private static Heater heater;
    private static HeatingManager heatingManager;

    /**
     * Main method which starts the fan heater and initializes all needed classes and variables
     * @param args
     */
    public static void main(String[] args) {

        double initialRoomTemperature = 18.0;
        double targetTemperature = -1;

        temperatureSimulation = new TemperatureSimulation(initialRoomTemperature, TEMPERATURE_INCREASE_RATE, TEMPERATURE_DECREASE_RATE);
        temperatureSensor = new TemperatureSensor(temperatureSimulation);
        fanHeaterUI = new FanHeaterUI(temperatureSensor);

        while (!fanHeaterUI.isTargetTemperatureSet()){
            try {
                Thread.sleep(UPDATE_INTERVAL_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        targetTemperature = fanHeaterUI.getTargetTemperature();

        heater = new Heater(temperatureSensor, targetTemperature, TEMPERATURE_INCREASE_RATE, TEMPERATURE_DECREASE_RATE);
        heatingManager = new HeatingManager(heater, temperatureSimulation);

        while (true) {
            if (fanHeaterUI.getTargetTemperature() != targetTemperature) {
                heater.setTargetTemperature(fanHeaterUI.getTargetTemperature());
            }
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
