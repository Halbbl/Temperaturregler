# Lastenheft

### Thermostatregelung bei einem Heizlüfter
### Software-Design SS 2026

## Ziele

- Automatische Regelung der Raumtemperatur auf einen gewünschten Zielwert
- Schutz des Geräts vor Überhitzung
- Energieeffizienter Betrieb durch Energiesparmodus und Fenstererkennung
- Benutzerfreundliche Bedienung über eine grafische Oberfläche
- Persistente Speicherung von Einstellungen und Timern über Sitzungen hinweg

## Produkteinsatz

Das System wird als Softwaresimulation im Rahmen des Software-Engineering-Projekts (SS 26) entwickelt. Es simuliert die Steuerung eines realen Heiülüfters und kann später als Grundlage für eine eingebettete Implementierung dienen.

### Zielgruppe

Endnutzer, die eine automatische Temperaturregelung im Raum wünschen

## Funktionale Anforderungen
| ID              | Anforderung                                                                                                        | Priorität |
|-----------------|--------------------------------------------------------------------------------------------------------------------| --- |
| **Iteration 1** | **Grundfunktionen**                                                                                                | |
| Req. 1.1        | Automatisches Aktivieren des Heizlüfters, wenn die Raumtemperatur max. 1 °C unter der gewünschten Temperatur liegt | Hoch |
| Req. 1.2        | Automatisches Deaktivieren des Heizlüfters bei Erreichen der gewünschten Temperatur                                | Hoch |
| Req. 1.3        | Halten der gewünschten Temperatur mit Schwankungen bis max. 1 °C                                                   | Hoch |
| Req. 1.4        | Strikte Trennung der zentralen Logikkomponente vom restlichen Code                                                 | Mittel |
| Req. 1.5        | Anzeige der aktuellen Raumtemperatur in der Benutzeroberfläche                                                     | Hoch |
| Req. 1.6        | Möglichkeit zum Ändern der gewünschten Temperatur durch den Nutzer                                                 | Hoch |
| Req. 1.7        | Die Benutzeroberfläche reagiert auf Nutzerinteraktionen mit visuellen Rückmeldungen                                | Mittel |
| Req. 1.8        | Intuitive Benutzeroberfläche, ohne Einarbeitung bedienbar                                                          | Mittel |
| Req. 1.9        | AbReq.ngen von ungültigen Eingaben (nur Dezimalzahlen mit „." zulässig)                                            | Hoch |
| **Iteration 2** | **Erweiterte Funktionen & Sicherheit**                                                                             | |
| Req. 2.1        | 3 unterschiedliche Heizstufen (LOW, MEDIUM, HIGH) sowie ein ausgeschalteter Zustand (OFF)                          | Hoch |
| Req. 2.2        | Erkennen von Überhitzung des Geräts anhand der simulierten internen Temperatur                                     | Hoch |
| Req. 2.3        | Automatisches Abschalten des Heizlüfters bei Erkennen einer Überhitzung                                            | Hoch |
| Req. 2.4        | Automatisches Wiedereinschalten nach Abkühlung auf eine unkritische Temperatur                                     | Hoch |
| Req. 2.5        | Simulation eines geöffneten Fensters über die Benutzeroberfläche                                                   | Mittel |
| Req. 2.6        | Erkennen eines rapiden TemperaturabReq.lls als Indikator für ein geöffnetes Fenster                                | Hoch |
| Req. 2.7        | Automatisches Abschalten des Heizlüfters bei erkanntem rapiden TemperaturabReq.ll                                  | Hoch |
| Req. 2.8        | Energiesparmodus, der die maximale Heizstufe auf MEDIUM begrenzt                                                   | Mittel |
| Req. 2.9        | Verschiedene Statusanzeigen des Heizlüfters (z. B. Heizen, Überhitzt, Aus)                                         | Mittel |
| Req. 2.10       | Anzeige des aktuellen Status durch eine passende Nachricht in der UI                                               | Mittel |
| Req. 2.11       | Energiesparmodus über einen Knopf in der UI aktivierbar und deaktivierbar                                          | Mittel |
| Req. 2.12       | Anzeige ob der Energiesparmodus aktiv ist oder nicht                                                               | Niedrig |
| **Iteration 3** | **Persistenz, Zeitsteuerung & überarbeitete UI**                                                                   | |
| Req. 3.1        | Einstellungen über eine .properties-Datei speichern und bearbeiten können                                          | Hoch |
| Req. 3.2        | SettingsManager zum Auslesen und Überschreiben von Einstellungen                                                   | Hoch |
| Req. 3.3        | Reduzierung der Übergabeparameter durch Bündelung in Konfigurationsklassen                                         | Mittel |
| Req. 3.4        | Simulation der aktuellen Uhrzeit (Stunde und Minute)                                                               | Hoch |
| Req. 3.5        | TimerSensor zum Auslesen der simulierten Zeit; Werte werden in einer .properties-Datei gespeichert                 | Hoch |
| Req. 3.6        | Timer selbst stellbar, löschbar und aufrufbar (Stunde, Minute, Zieltemperatur)                                     | Hoch |
| Req. 3.7        | Pro Uhrzeit ist nur ein Timer zulässig                                                                             | Hoch |
| Req. 3.8        | TimerManager zum Auslesen, Neuanlegen und Löschen von Timern                                                       | Hoch |
| Req. 3.9        | Automatisches Übernehmen der im Timer hinterlegten Zieltemperatur bei Erreichen der eingestellten Uhrzeit          | Hoch |
| Req. 3.10       | Einstellungen sitzungsübergreifend speichern                                                                       | Hoch |
| Req. 3.11       | Timer sitzungsübergreifend speichern                                                                               | Hoch |
| Req. 3.12       | UI benutzerfreundlich, einReq.ch verständlich und minimalistisch gestalten                                         | Mittel |
| Req. 3.13       | UI orientiert sich so nah wie möglich an einem realen Vorbildgerät                                                 | Niedrig |
| Req. 3.14       | UI-Code in einzelne Funktionen aufteilen statt in einem einzigen Konstruktor                                       | Mittel |

## Nicht-funktionale Anforderungen

| Kategorie | Anforderung |
| --- | --- |
| **Wartbarkeit** | Klare Trennung von Simulation, Sensorik, Steuerungslogik und UI durch ein Schichtenmodell |
| **Wartbarkeit** | Konfigurationswerte (Temperaturgrenzen, Schwellwerte) über .properties-Dateien anpassbar, ohne erneute Kompilierung |
| **Zuverlässigkeit** | Das System erkennt Überhitzung und offene Fenster zuverlässig und reagiert automatisch |
| **Benutzbarkeit** | Die UI soll ohne Einarbeitung bedienbar sein und maximal 5 Bedienelemente aufweisen |
| **Benutzbarkeit** | Ungültige Eingaben werden abgefangen und dem Nutzer kommuniziert |
| **Persistenz** | Einstellungen und Timer bleiben nach einem Neustart der Anwendung erhalten |
| **Technologie** | Implementierung in Java mit Swing als UI-Framework |
| **Testbarkeit** | Alle zentralen Logikkomponenten sind automatisiert testbar (JUnit 4/5) |

## Systemgrenzen & Abgrenzung

### Im Umfang enthalten
- Softwaresimulation der Raumtemperatur und der internen Gerätetemperatur
- Softwaresimulation einer Uhrzeit (Stunde, Minute)
- Automatische Temperatursteuerung, Überhitzungsschutz, Fenstererkennung und Energiesparmodus
- Persistente Speicherung von Einstellungen und Timern in .properties-Dateien
- Grafische Benutzeroberfläche mit Swing

### Nicht im Umfang enthalten
- Anbindung an reale Hardware (Sensoren, Aktoren)
- Korrekte physikalische Messung der Temperatur ohne Simulation
- Langzeitverhalten und Alterung des Sensors
- Fehlverhalten oder Ausfall des Sensors
- Netzwerkanbindung oder Fernsteuerung
- Persistenz über andere Mechanismen als .properties-Dateien (z.B Datenbanken)

## Rahmenbedingungen
- Programmiersprache: Java
- UI-Framework: Swing
- Testframework: JUnit 4 / 5
- Versionsverwaltung: Git / GitHub (https://github.com/Halbbl/Temperaturregler)
- Projektzeitraum: 08.05.2026 - 19.06.2026
