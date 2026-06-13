package config;

public class ConfigInternalSimulation {
    public final double INITIAL_TEMPERATURE;
    public final double DECREASE_RATE;
    public final double MAX_TEMPERATURE;
    public final double PUFFER;

    public ConfigInternalSimulation(double initialTemperature, double decreaseRate, double maxTemp, double puffer) {
        INITIAL_TEMPERATURE = initialTemperature;
        DECREASE_RATE = decreaseRate;
        MAX_TEMPERATURE = maxTemp;
        PUFFER = puffer;
    }
}
