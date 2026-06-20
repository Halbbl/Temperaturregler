# Test Report Iteration 2

## Teststrategie

- Automatische Tests für alle testbaren Codeeinheiten
- Manuelle Tests für Usability und UI bezogene Anforderungen

### Testumgebung

- Simulierte Temperatur des Raumes und des Geräts
- Hardware-Komponenten so konzipiert, dass sie von Simulation ausgegebene Werte verarbeiten können
- JUnit 4/5 für automatisches Testen
- Optionaler Einsatz von Logging für Vergleiche zwischen UI und Code

---

## Testumfang

### In-Scope:

- Steuerung der Heizstufen (LOW, MEDIUM, HIGH, OFF) anhand der Temperaturdifferenz
- Erkennung und Reaktion auf Überhitzung des Geräts
- Erkennung und Reaktion auf einen rapiden Temperaturabfall (simuliertes offenes Fenster)
- Logik und Begrenzung des Energiesparmodus
- Anzeige des aktuellen Status und des Energiesparmodus in der Benutzeroberfläche

### Out-of-Scope:

- korrektes Messen der Temperatur ohne Simulation
- Langzeitverhalten (Alterung des Sensors)
- Fehlverhalten des Sensors
- reales Verhalten eines Fensters (nur simulierter Zustand)

---

# Tests

---
## Unit Tests

### UT 2.1 - Überhitzungsschutz: Abschalten bei Überhitzung

Ziel: Heizlüfter soll sich automatisch abschalten, wenn die Gerätetemperatur die maximal zulässige Temperatur überschreitet

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Überhitzung wird korrekt erkannt und der Heater entsprechend abgeschaltet

Status: Bestanden

Requirement: 2.2 & 2.3

Getestete Klasse: `ComponentsManager`

### UT 2.2 - Überhitzungsschutz: Wiedereinschalten nach Abkühlung

Ziel: Heizlüfter soll den Überhitzungsstatus verlassen, sobald das Gerät auf eine unkritische Temperatur abgekühlt ist

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Der Status wechselt korrekt aus dem Zustand OVERHEATED, sobald die Abkühlung erfolgt ist

Status: Bestanden

Requirement: 2.4

Getestete Klasse: `ComponentsManager`

### UT 2.3 - Fenstersimulation: Zustand umschalten

Ziel: Der simulierte Fensterzustand (offen/geschlossen) soll korrekt umgeschaltet werden können

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Der Fensterzustand wechselt beim Aufruf der entsprechenden Methode korrekt

Status: Bestanden

Requirement: 2.5

Getestete Klasse: `ComponentsManager`

### UT 2.4 - Erkennung eines rapiden Temperaturabfalls

Ziel: Ein plötzlicher, starker Abfall der Raumtemperatur soll als mögliches offenes Fenster erkannt werden

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Der rapide Temperaturabfall wird korrekt erkannt und das Fenster als offen markiert

Status: Bestanden

Requirement: 2.6

Getestete Klasse: `ComponentsManager`

### UT 2.5 - Abschalten bei erkanntem Temperaturabfall

Ziel: Der Heizlüfter soll sich abschalten, wenn ein rapider Temperaturabfall (offenes Fenster) erkannt wird

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Der Heater wird beim Erkennen des Temperaturabfalls korrekt abgeschaltet

Status: Bestanden

Requirement: 2.7

Getestete Klasse: `ComponentsManager`

### UT 2.6 - Energiesparmodus: Aktivierung

Ziel: Der Energiesparmodus soll sich aktivieren lassen

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Der Energiesparmodus wird nach Aufruf korrekt aktiviert

Status: Bestanden

Requirement: 2.8

Getestete Klasse: `ComponentsManager`

### UT 2.7 - Energiesparmodus: Deaktivierung

Ziel: Der Energiesparmodus soll sich wieder deaktivieren lassen

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Der Energiesparmodus wird nach erneutem Aufruf korrekt deaktiviert

Status: Bestanden

Requirement: 2.8

Getestete Klasse: `ComponentsManager`

### UT 2.8 - Energiesparmodus: Begrenzung der Heizstufe

Ziel: Im Energiesparmodus soll die Heizstufe HIGH nicht verwendet werden, auch bei großer Temperaturdifferenz

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Heizstufe bleibt im Energiesparmodus unter HIGH

Status: Bestanden

Requirement: 2.8

Getestete Klasse: `ComponentsManager`

### UT 2.9 - Heizstufe: HIGH bei großer Temperaturdifferenz

Ziel: Bei einer Differenz von 5°C oder mehr zur Zieltemperatur soll die Heizstufe HIGH verwendet werden

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Heizstufe wird korrekt auf HIGH gesetzt

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `ComponentsManager`

### UT 2.10 - Heizstufe: MEDIUM bei mittlerer Temperaturdifferenz

Ziel: Bei einer Differenz zwischen 2°C und 5°C zur Zieltemperatur soll die Heizstufe MEDIUM verwendet werden

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Heizstufe wird korrekt auf MEDIUM gesetzt

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `ComponentsManager`

### UT 2.11 - Heizstufe: LOW bei kleiner Temperaturdifferenz

Ziel: Bei einer Differenz von unter 2°C zur Zieltemperatur soll die Heizstufe LOW verwendet werden

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Heizstufe wird korrekt auf LOW gesetzt

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `ComponentsManager`

### UT 2.12 - Heizstufe: OFF bei erreichter Zieltemperatur

Ziel: Wenn die Zieltemperatur erreicht oder überschritten ist, soll die Heizstufe OFF sein

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Heizstufe wird korrekt auf OFF gesetzt

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `ComponentsManager`

### UT 2.13 - Temperatursimulation: Anstieg bei Stufe LOW

Ziel: Die simulierte Gerätetemperatur soll bei Heizstufe LOW um den entsprechenden Wert ansteigen

Ausführung: siehe `FanHeaterTemperatureSimulationTest`

Ergebnis: Die Temperatur steigt bei Stufe LOW korrekt an

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `FanHeaterTemperatureSimulation`

### UT 2.14 - Temperatursimulation: Anstieg bei Stufe MEDIUM

Ziel: Die simulierte Gerätetemperatur soll bei Heizstufe MEDIUM um den entsprechenden Wert ansteigen

Ausführung: siehe `FanHeaterTemperatureSimulationTest`

Ergebnis: Die Temperatur steigt bei Stufe MEDIUM korrekt an

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `FanHeaterTemperatureSimulation`

### UT 2.15 - Temperatursimulation: Anstieg bei Stufe HIGH

Ziel: Die simulierte Gerätetemperatur soll bei Heizstufe HIGH um den entsprechenden Wert ansteigen

Ausführung: siehe `FanHeaterTemperatureSimulationTest`

Ergebnis: Die Temperatur steigt bei Stufe HIGH korrekt an

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `FanHeaterTemperatureSimulation`

### UT 2.16 - Temperatursimulation: Abfall bei Stufe OFF

Ziel: Die simulierte Gerätetemperatur soll sinken, wenn die Heizstufe auf OFF gesetzt wird

Ausführung: siehe `FanHeaterTemperatureSimulationTest`

Ergebnis: Die Temperatur sinkt bei Stufe OFF korrekt

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `FanHeaterTemperatureSimulation`

### UT 2.17 - Temperatursimulation: Untergrenze der Gerätetemperatur

Ziel: Die simulierte Gerätetemperatur soll nicht unter die Starttemperatur fallen

Ausführung: siehe `FanHeaterTemperatureSimulationTest`

Ergebnis: Die Temperatur fällt auch im ausgeschalteten Zustand nicht unter die Starttemperatur

Status: Bestanden

Requirement: 2.1

Getestete Klasse: `FanHeaterTemperatureSimulation`

## Usability Tests

### UX 2.1 - Anzeige: Status des Heizlüfters

Ziel: Der aktuelle Status des Heizlüfters (z.B. Heizen, Überhitzt, Aus) soll dem Nutzer durch eine passende Nachricht in der UI angezeigt werden

Ausführung: Verschiedene Zustände herbeiführen (z.B. Überhitzung simulieren) und Anzeige der UI mit der erwarteten Statusmeldung vergleichen

Ergebnis: Die UI zeigt für jeden Status die passende Nachricht an

Status: Bestanden

Requirement: 2.9 & 2.10

Getestete Klasse: `FanHeaterUI`

### UX 2.2 - Bedienung: Energiesparmodus per Knopf

Ziel: Der Energiesparmodus soll über einen Knopf in der UI aktiviert und deaktiviert werden können

Ausführung: Mehrere Testpersonen den Knopf für den Energiesparmodus betätigen lassen

Ergebnis: Alle Testpersonen konnten den Energiesparmodus problemlos über den Knopf (de)aktivieren

Status: Bestanden

Requirement: 2.11

Getestete Klasse: `FanHeaterUI`

### UX 2.3 - Anzeige: Status des Energiesparmodus

Ziel: Es soll für den Nutzer klar erkennbar sein, ob der Energiesparmodus aktiv ist oder nicht

Ausführung: Energiesparmodus aktivieren bzw. deaktivieren und Anzeige der UI beobachten

Ergebnis: Die UI zeigt den Aktivierungsstatus des Energiesparmodus korrekt und eindeutig an

Status: Bestanden

Requirement: 2.12

Getestete Klasse: `FanHeaterUI`

## Black-Box Tests

### BB 2.1 - Automatisches Abschalten und Wiedereinschalten bei Überhitzung

Ziel: Der Heizlüfter soll bei Überhitzung automatisch abschalten und nach Abkühlung automatisch wieder anlaufen, ohne Eingriff des Nutzers

Ausführung: Heizlüfter über simulierten Dauerbetrieb laufen lassen, bis Überhitzung eintritt, danach Abkühlung abwarten und Verhalten beobachten

Ergebnis: Der Heizlüfter schaltet bei Überhitzung ab und läuft nach Abkühlung automatisch wieder an

Status: Bestanden

Requirement: 2.2, 2.3 & 2.4

Getestete Klasse: `FanHeaterUI`, `ComponentsManager`, `FanHeaterTemperatureSimulation`

### BB 2.2 - Reaktion auf simuliertes offenes Fenster

Ziel: Bei einem simulierten rapiden Temperaturabfall (offenes Fenster) soll sich der Heizlüfter automatisch abschalten

Ausführung: Fensteröffnung über Simulation auslösen (rapiden Temperaturabfall erzeugen) und Verhalten des Heizlüfters beobachten

Ergebnis: Der Heizlüfter schaltet sich bei erkanntem offenem Fenster korrekt ab

Status: Bestanden

Requirement: 2.5, 2.6 & 2.7

Getestete Klasse: `FanHeaterUI`, `ComponentsManager`, `RoomTemperatureSimulation`