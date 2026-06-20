package iteration2.src.heater;

/**
 * Different heater status for the heater with messages
 */
public enum HeaterStatus {
    OFF("Aus"),
    STARTING("Fährt hoch"),
    STOPPING("Fährt herunter"),
    HEATING("Heizt"),
    STANDBY("Standby"),
    WINDOW_OPEN("Offenes Fenster erkannt"),
    OVERHEATED("Überhitzt. Kült herunter");

    private final String message;

    HeaterStatus(String message) {
        this.message = message;
    }

    /**
     * Gets the message for the status
     * @return message as String
     */
    public String getMessage() {
        return message;
    }
}
