# Test Report Iteration 1

## Teststrategie

- Automatsche Tests für alle testbaren Codeeinheiten
- Manuelle Tests für Usability und UI bezogene Anforderungen

### Testumgebung

- Simulierte Temperatur des Raumes
- Hardware-Komponenten so konzipiert, dass sie von Simulation ausgegebene Werte verarbeiten können
- JUnit 4/5 für automatisches Testen
- Optionaler Einsatz von Logging für Vergleiche zwischen UI und Code

---

## Testumfang

### In-Scope:

- Verarbeiten der gemessenen Temperatur und bestimmen der Aktion für Heizmodul
- Reaktion auf Benutzereingaben
- Darstellung und Verhalten der Anzeige

### Out-of-Scope:

- korrektes Messen der Temperatur ohne Simulation
- Langzeitverhalten (Alterung des Sensors)
- Fehlverhalten des Sensors

---

# Tests

---
## Unit Tests

### UT 1.1 - Änderung Temperatur: Heizmodul an

Ziel: Temperatur soll sich um IncreaseRate erhöhen, wenn Heater angeschaltet ist

Ausführung: siehe `TemperaturSimulationTest`

Ergebnis: Die Methode erhöht die simulierte Temperatur korrekt

Status: Bestanden

Requirement: 1.4

Getestete Klasse: `TemperatureSimulation`

### UT 1.2 - Änderung Temperatur: Heizmodul aus

Ziel: Temperatur soll sich um DecreaseRate verringern, wenn Heater ausgeschaltet ist

Ausführung: siehe `TemperaturSimulationTest`

Ergebnis: Die Methode verringert die simulierte Temperatur korrekt

Status: Bestanden

Requirement: 1.4

Getestete Klasse: `TemperatureSimulation`

### UT 1.3 - Temperaturgrenze: Obergrenze

Ziel: Temperatur soll nicht über maxTemperature hinausgehen

Ausführung: siehe `TemperaturSimulationTest`

Ergebnis: Die Methode erhöht die Temperatur bei Erreichen der maxTemperature nicht weiter

Status: Bestanden

Requirement: 1.5

Getestete Klasse: `TemperatureSimulation`

### UT 1.4 - Temperaturgrenze: Untergrenze

Ziel: Temperatur soll nicht unter minTemperature fallen

Ausführung: siehe `TemperaturSimulationTest`

Ergebnis: Die Methode stoppt den Temperaturabfall bei Erreichen der minTemperature korrekt

Status: Bestanden

Requirement: 1.5

Getestete Klasse: `TemperatureSimulation`

### UT 1.5 - Steuerung Heizmodul: Aktivierung

Ziel: Heater soll sich aktivieren, wenn unter gewollter Temperatur

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Methode misst die Temperatur korrekt und aktiviert den Heater

Status: Bestanden

Requirement: 1.1

Getestete Klasse: `ComponentsManager`

### UT 1.6 - Steuerung Heizmodul: Deaktivierung

Ziel: Heater soll sich deaktivieren, wenn gewollte Temperatur erreicht

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Methode misst die Temperatur korrekt und deaktiviert den Heater

Status: Bestanden

Requirement: 1.2

Getestete Klasse: `ComponentsManager`

### UT 1.7 - Steuerung Heizmodul: Temperatur halten

Ziel: Die gewollte Temperatur soll bei Erreichen gehalten werden

Ausführung: `siehe ComponentsManagerTest`

Ergebnis: Die Methode (de)aktiviert den Heater korrekt und hält somit die Temperatur 

Status: Bestanden

Requirement: 1.3

Getestete Klasse: `ComponentsManager`

## Usability Tests

### UX 1.1 - Anzeige: Aktuelle Temperatur

Ziel: Aktuelle Raumtemperatur anzeigen ohne weiteres zutun des Nutzers

Ausführung: Ausgabe der aktuellen Temperatur im Terminal und manueller Vergleich zur in der UI angezeigten

Ergebnis: Die Methode (de)aktiviert den Heater korrekt und hält somit die Temperatur

Status: Bestanden

Requirement: 1.6

Getestete Klasse: `FanHeaterUI`

### UX 1.2 - Eingabe: Gewollte Temperatur

Ziel: Intuitive Eingabe der gewollten Temperatur

Ausführung: Mehrere Testpersonen Temperatur eingeben lassen

Ergebnis: Alle Testpersonen hatten keine Probleme die gewünschte Temperatur einzugeben

Status: Bestanden

Requirement: 1.7 & 1.9

Getestete Klasse: `FanHeaterUI`

### UX 1.3 - Anzeige: Ändern der UI nach Benutzerinteraktion

Ziel: Feedback für den Nutzer nach Interaktion mit der UI

Ausführung: Interagieren mit UI

Ergebnis: Die UI hat sich entsprechend der Interaktion verändert

Status: Bestanden

Requirement: 1.8

Getestete Klasse: `FanHeaterUI`

## Black-Box Tests

### BB 1.1 - Automatisches Anpassen der Temperatur

Ziel: Raumtemperatur passt sich automatisch nach Eingabe des Nutzers an

Ausführung: Nach Temperatureingabe beobachten ob Raumtemperatur sich in gewünschte Richtung verändert

Ergebnis: Die Temperatur hat sich immer richtung gewollter Temperatur geändert

Status: Bestanden

Requirement: 1.4

Getestete Klasse: `FanHeaterUI`, `CompartmentManager`, `TemperaturSimulation`

### BB 1.2 - Eingabe: Abfangen von nicht genehmigten Eingaben

Ziel: Der Nutzer soll nur Zahlen mit einem "." darin eingeben können

Ausführung: Eingabe von Buchstaben, Symbolen wie "!" und Zahlen mit mehreren Punkten

Ergebnis: Alle nicht zugelassenen Eingaben wurden korrekt abgefangen

Status: Bestanden

Requirement: 1.10

Getestete Klasse: `FanHeaterUI`