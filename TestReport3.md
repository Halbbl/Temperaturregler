# Test Report Iteration 3

## Teststrategie

- Automatische Tests für alle testbaren Codeeinheiten
- Manuelle Tests für Usability und UI bezogene Anforderungen
- Manuelle Code-Reviews für strukturelle/architektonische Anforderungen, die sich nicht über Verhalten testen lassen

### Testumgebung

- Simulierte Temperatur des Raumes und des Geräts
- Simulierte Uhrzeit (Stunde, Minute)
- Persistierung von Settings und Timern über separate `.properties`-Testdateien (`settingsTest.properties`, `timersTest.properties`), getrennt von den produktiven Konfigurationsdateien
- JUnit 4/5 für automatisches Testen
- Optionaler Einsatz von Logging für Vergleiche zwischen UI und Code

---

## Testumfang

### In-Scope:

- Speichern, Auslesen und Überschreiben von Settings über eine `.properties`-Datei
- Anlegen, Auslesen, Löschen und sitzungsübergreifendes Speichern von Timern
- Anwenden der im Timer hinterlegten Zieltemperatur bei Erreichen der hinterlegten Uhrzeit
- Simulation der Uhrzeit inkl. Überlauf von Minuten und Stunden
- Strukturelle Anforderungen an den Code (Parameterreduktion, Funktionsaufteilung in der UI)
- Gestaltung der Benutzeroberfläche (Benutzerfreundlichkeit, Anlehnung an die Vorlage)

### Out-of-Scope:

- korrektes Messen der Temperatur ohne Simulation
- Langzeitverhalten (Alterung des Sensors)
- Fehlverhalten des Sensors
- reales Verhalten eines Fensters (nur simulierter Zustand)
- Verwendung der echten Systemuhrzeit (nur simulierte Zeit)
- Persistenz über andere Mechanismen als `.properties`-Dateien (z. B. Datenbanken)

---

# Tests

---
## Unit Tests

### UT 3.1 - Timer: Übernehmen der Zieltemperatur bei Erreichen der Uhrzeit

Ziel: Sobald die im Timer hinterlegte Uhrzeit erreicht ist, soll die dort hinterlegte Zieltemperatur automatisch übernommen werden

Ausführung: siehe `ComponentsManagerTest`

Ergebnis: Die Zieltemperatur wird beim Erreichen der hinterlegten Uhrzeit korrekt übernommen

Status: Bestanden

Requirement: 3.9

Getestete Klasse: `ComponentsManager`

### UT 3.2 - SettingsManager: Speichern der Zieltemperatur

Ziel: Die Zieltemperatur soll über den SettingsManager in einer `.properties`-Datei gespeichert und nach erneutem Laden korrekt ausgelesen werden können

Ausführung: siehe `SettingsManagerTest`

Ergebnis: Der gespeicherte Wert wird nach erneutem Instanziieren korrekt ausgelesen

Status: Bestanden

Requirement: 3.1 & 3.2

Getestete Klasse: `SettingsManager`

### UT 3.3 - SettingsManager: Überschreiben einer bestehenden Einstellung

Ziel: Eine bereits gespeicherte Einstellung soll überschrieben werden können

Ausführung: siehe `SettingsManagerTest`

Ergebnis: Der zuletzt gesetzte Wert wird korrekt gespeichert und ausgelesen

Status: Bestanden

Requirement: 3.1 & 3.2

Getestete Klasse: `SettingsManager`

### UT 3.4 - SettingsManager: Sitzungsübergreifendes Speichern

Ziel: Einstellungen sollen über mehrere Sitzungen hinweg erhalten bleiben

Ausführung: siehe `SettingsManagerTest`

Ergebnis: Eine neue Instanz des SettingsManager liest den zuvor gespeicherten Wert korrekt aus

Status: Bestanden

Requirement: 3.10

Getestete Klasse: `SettingsManager`

### UT 3.5 - TimerManager: Timer anlegen

Ziel: Ein neuer Timer (Stunde, Minute, Zieltemperatur) soll angelegt werden können

Ausführung: siehe `TimerManagerTest`

Ergebnis: Die Anzahl der Timer erhöht sich nach dem Anlegen korrekt

Status: Bestanden

Requirement: 3.6 & 3.8

Getestete Klasse: `TimerManager`

### UT 3.6 - TimerManager: Timer auslesen

Ziel: Ein angelegter Timer soll mit seinen Werten (Stunde, Minute, Zieltemperatur) korrekt ausgelesen werden können

Ausführung: siehe `TimerManagerTest`

Ergebnis: Die ausgelesenen Werte entsprechen den beim Anlegen übergebenen Werten

Status: Bestanden

Requirement: 3.6 & 3.8

Getestete Klasse: `TimerManager`

### UT 3.7 - TimerManager: Timer löschen

Ziel: Ein bestehender Timer soll gelöscht werden können

Ausführung: siehe `TimerManagerTest`

Ergebnis: Nach dem Löschen ist die Anzahl der Timer korrekt auf 0 reduziert

Status: Bestanden

Requirement: 3.6 & 3.8

Getestete Klasse: `TimerManager`

### UT 3.8 - TimerManager: Korrekte Anzahl an Timern

Ziel: Die Anzahl der aktuell angelegten Timer soll korrekt zurückgegeben werden

Ausführung: siehe `TimerManagerTest`

Ergebnis: Die zurückgegebene Anzahl entspricht der Anzahl der zuvor angelegten Timer

Status: Bestanden

Requirement: 3.8

Getestete Klasse: `TimerManager`

### UT 3.9 - TimerManager: Verschieben der Einträge nach Löschung

Ziel: Nach dem Löschen eines Timers sollen die nachfolgenden Timer-Einträge korrekt nachrücken

Ausführung: siehe `TimerManagerTest`

Ergebnis: Der nachfolgende Timer rutscht nach Löschung korrekt an die vorherige Position

Status: Bestanden

Requirement: 3.8

Getestete Klasse: `TimerManager`

### UT 3.10 - TimerManager: Sitzungsübergreifendes Speichern

Ziel: Angelegte Timer sollen über mehrere Sitzungen hinweg erhalten bleiben

Ausführung: siehe `TimerManagerTest`

Ergebnis: Eine neue Instanz des TimerManager liest die zuvor angelegten Timer korrekt aus

Status: Bestanden

Requirement: 3.11

Getestete Klasse: `TimerManager`

### UT 3.11 - Zeitsimulation: Überlauf der Minuten

Ziel: Bei Überschreiten von 59 Minuten soll die Stunde um 1 erhöht und die Minute auf 0 zurückgesetzt werden

Ausführung: siehe `TimeSimulationTest`

Ergebnis: Stunde und Minute werden beim Überlauf korrekt angepasst

Status: Bestanden

Requirement: 3.4

Getestete Klasse: `TimeSimulation`

### UT 3.12 - Zeitsimulation: Rücksetzen nach Mitternacht

Ziel: Beim Überschreiten von Stunde 23 soll die Uhrzeit korrekt auf 0:00 zurückgesetzt werden

Ausführung: siehe `TimeSimulationTest`

Ergebnis: Stunde und Minute werden nach Mitternacht korrekt auf 0 zurückgesetzt

Status: Bestanden

Requirement: 3.4

Getestete Klasse: `TimeSimulation`

## Usability Tests

### UX 3.1 - Benutzerfreundlichkeit der Benutzeroberfläche

Ziel: Die UI soll für Nutzer ohne Vorkenntnisse einfach verständlich und minimalistisch zu bedienen sein

Ausführung: Testpersonen ohne Vorkenntnisse lassen Kernfunktionen (Temperatur einstellen, Timer anlegen/löschen, Einstellungen ändern) ohne Erklärung bedienen

Ergebnis: Alle Testpersonen konnten die Kernfunktionen ohne weitere Erklärung bedienen

Status: Bestanden

Requirement: 3.12

Getestete Klasse: `FanHeaterUI`

### UX 3.2 - Optischer Abgleich mit der Vorlage

Ziel: Die UI soll sich optisch so nah wie möglich an der vorgegebenen Produkt-Vorlage orientieren

Ausführung: Visueller Vergleich der umgesetzten UI mit dem Bild der Vorlage durch das Testteam

Ergebnis: Layout und Anordnung der Bedienelemente entsprechen weitgehend der Vorlage

Status: Bestanden

Requirement: 3.13

Getestete Klasse: `FanHeaterUI`

## Black-Box Tests

### BB 3.1 - Auslesen der simulierten Zeit über den Zeitsensor

Ziel: Der Zeitsensor (TimeSensor) soll die simulierte Uhrzeit korrekt auslesen und für andere Komponenten zur Verfügung stellen

Ausführung: Simulierte Uhrzeit über die Simulation verändern und anschließend über den Sensor abfragen

Ergebnis: Der Sensor liefert stets die korrekte, simulierte Uhrzeit

Status: Bestanden

Requirement: 3.5

Getestete Klasse: `TimeSensor`, `ConfigTime`

### BB 3.2 - Verhindern mehrerer Timer zur selben Uhrzeit

Ziel: Es soll nicht möglich sein, zwei Timer mit identischer Uhrzeit (Stunde und Minute) gleichzeitig zu führen

Ausführung: Timer für eine bestimmte Uhrzeit anlegen, danach Versuch, einen weiteren Timer mit derselben Uhrzeit anzulegen

Ergebnis: Es existiert zu jedem Zeitpunkt maximal ein Timer pro Uhrzeit; der zweite Versuch wird abgewiesen

Status: Bestanden

Requirement: 3.7

Getestete Klasse: `TimerManager`

## Code-Reviews

### CR 3.1 - Reduzierung der Übergabeparameter

Ziel: Zustands- und Konfigurationsdaten sollen gebündelt in Objekten (z. B. `Components`, `Simulations`, `Settings`) übergeben werden, anstatt als einzelne Parameter

Ausführung: Manuelle Code-Überprüfung der Konstruktoren von `ComponentsManager` und verwandten Klassen

Ergebnis: Die Anzahl einzeln übergebener Parameter wurde durch Bündelung in Objekten reduziert

Status: Bestanden

Requirement: 3.3

Getestete Klasse: `ComponentsManager`, `Components`, `Simulations`, `Settings`

### CR 3.2 - Aufteilung der UI in einzelne Funktionen

Ziel: Die Logik der Benutzeroberfläche soll in einzelne, klar abgegrenzte Funktionen aufgeteilt sein, anstatt vollständig im Konstruktor zu stehen

Ausführung: Manuelle Code-Überprüfung der Klasse `FanHeaterUI`

Ergebnis: Die UI-Logik ist in mehrere Funktionen aufgeteilt, der Konstruktor übernimmt nur die Initialisierung

Status: Bestanden

Requirement: 3.14

Getestete Klasse: `FanHeaterUI`