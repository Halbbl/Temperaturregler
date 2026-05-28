package fanheater.src.heater;

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

    public String getMessage() {
        return message;
    }
}
