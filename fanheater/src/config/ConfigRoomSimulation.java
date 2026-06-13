package config;

public class ConfigRoomSimulation {
    public final double INITIAL_TEMPERATURE;
    public final double DECREASE_RATE;
    public final double MAX_TEMPERATURE;
    public final double OUTSIDE_TEMPERATURE;
    public final double WINDOW_OPEN_DECREASE_RATE;

    public ConfigRoomSimulation(double initialTemp, double decreaseRate, double maxTemp, double outsideTemp, double windowOpenDecreaseRate) {
        INITIAL_TEMPERATURE = initialTemp;
        DECREASE_RATE = decreaseRate;
        MAX_TEMPERATURE = maxTemp;
        OUTSIDE_TEMPERATURE = outsideTemp;
        WINDOW_OPEN_DECREASE_RATE = windowOpenDecreaseRate;
    }
}
