# Umstellung bei Nutzung auf Hardware


- Entfernung aller Simulation Klassen + deren Configs
- `ComponentsManager`: Entfernung aller als Simulation markierten Teilen + Konstruktor Übergabeparameter "simulationsManager"
- `FanHeaterUI`: Entfernung aller als Simulation markierten Teilen & ggf. der Button Panels (4)
- Implementierung der Logik aller Sensoren 
- Anpassung der `HeatingLevel` & `Heater` Klasse an physisches Heizmodul
- Anpassung der Window Detection in `ComponentsManager` an realen Temperaturabfall

Anzeige Requirements (wenn aktuelle beibehalten werden soll):
- Digitale Anzeige
- 5 Hardware-Knöpfe (können auch auf dem Display sein -> Touch Display) mit 2 Symbolen (mittlerer soll auch grün leuchten können)