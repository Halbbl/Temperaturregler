# Termostatregelung bei einem Heizlüfter

Software Engeneering Projekt SS 26

### Github Repository

Link lol

### Funktionalitäten

1. Raumtemperatur messen
2. Anzeige der Raumtemperatur
3. Einstellen der gewünschten Temperatur durch Nutzer
4. Automatische Deaktivierung des Lüfters bei Erreichen der gewünschten Temperatur
5. Automatisches Aktivieren des Lüfters um Temperatur konstant zu halten
6. Bei rapidem Temperaturabfall (Fenster offen) automatisch Deaktivieren um Energie zu sparen
7. Überhitzungsschutz des Geräts
8. Energiesparmodus

## Requirements

| **Nummer** | **Beschreibung**                                                                                           | **Zugehörigkeit** |
| ---------- | ---------------------------------------------------------------------------------------------------------- | ----------------- |
| 1.1        | Automatisches aktivieren des Heizlüfters wenn Raumtemperatur max. 1°C kälter ist als gewünschte Temperatur | Logik             |
| 1.2        | Automatisches deaktivieren des Heizlüfters bei erreichen der gewünschten Temperatur                        | Logik             |
| 1.3        | Halten der gewünschten Temperatur mit Schwankungen bis max. 1°C                                            | Logik             |
| 1.4        | Strickte Trennung der zentralen Logikkomponente vom restlichen Code                                        | Logik             |
| 1.5        | Anzeige der aktuellen Raumtemperatur                                                                       | UI                |
| 1.6        | Möglichkeit zum Ändern der gewünschten Temperatur                                                          | UI                |
| 1.7        | Ändern der Benutzeroberfläche bei Interaktion des Nutzers                                                  | UI                |
| 1.8        | Intuitive Benutzeroberfläche                                                                               | UI                |
| 1.9        | Abfangen von nicht genehmigten Input Variablen                                                             | UI                |
| 2.1        | Bereitstellung von drei unterschiedlichen Heizstufen sowie einem ausgeschalteten Zustand                   | Logik             |
| 2.2        | Erkennen einer Überhitzung des Heizlüfters anhand der simulierten Gerätetemperatur                         | Logik             |
| 2.3        | Automatisches Abschalten des Heizlüfters bei Überhitzung                                                   | Logik             |
| 2.4        | Automatisches Wiedereinschalten nach ausreichender Abkühlung des Geräts                                    | Logik             |
| 2.5        | Simulation eines geöffneten Fensters                                                                       | Logik             |
| 2.6        | Erkennen eines rapiden Temperaturabfalls zur Fenstererkennung                                              | Logik             |
| 2.7        | Automatisches Abschalten bzw. Anpassen des Heizverhaltens bei erkanntem geöffnetem Fenster                 | Logik             |
| 2.8        | Energiesparmodus mit Begrenzung der maximalen Heizstufe auf „Medium“                                       | Logik             |
| 2.9        | Verwaltung und Bereitstellung verschiedener System- und Heizlüfterzustände                                 | Logik             |
| 2.10       | Anzeige des aktuellen Systemstatus durch passende Statusmeldungen                                          | UI                |
| 2.11       | Aktivieren und Deaktivieren des Energiesparmodus über einen Button                                         | UI                |
| 2.12       | Anzeige, ob der Energiesparmodus aktiv oder deaktiviert ist                                                | UI                |

## Iterationen

### [Iteration1](Iteration1/Iteration1.md): <br>
In der ersten Iteration des Projekts wurden die grundlegende Architektur, die ersten Requirements sowie 
die zentralen Klassen der Anwendung entwickelt. Ziel war es, eine stabile Basis für die weitere Entwicklung 
des Heizlüftersystems zu schaffen. Dabei wurden Funktionen wie die Messung der Raumtemperatur, die Eingabe 
einer gewünschten Zieltemperatur, die automatische Steuerung des Heizlüfters sowie eine einfache grafische 
Benutzeroberfläche umgesetzt.

Im Verlauf der Iteration wurden verschiedene Technologien und Architekturmodelle verglichen, wobei Java 
in Kombination mit Swing und dem Schichtenmodell als Grundlage gewählt wurde. Anschließend erfolgte die 
Implementierung der Kernkomponenten, darunter der Temperatursimulator, der Temperatursensor, der Heizlüfter, 
der zentrale ComponentsManager sowie die Benutzeroberfläche.

Zusätzlich wurden umfangreiche Tests durchgeführt, um die automatische Temperaturregelung, die 
Benutzeroberfläche sowie die Behandlung ungültiger Eingaben zu überprüfen. Dabei zeigte sich, dass die 
grundlegenden Funktionen zuverlässig arbeiten. Gleichzeitig wurde deutlich, dass eine bessere Planung der 
Klassenstruktur viele spätere Umstrukturierungen vereinfacht hätte.

### [Iteration2](Iteration2/Iteration2.md): <br>

In der zweiten Iteration des Projekts wurde das bestehende Heizlüftersystem um mehrere Funktionen erweitert, die eine realistischere Simulation und eine höhere Systemsicherheit ermöglichen. Der Schwerpunkt lag auf der Einführung verschiedener Heizstufen, der Simulation einer internen Gerätetemperatur, dem Überhitzungsschutz sowie der Erkennung eines geöffneten Fensters.

Hierfür wurden unterschiedliche Heizstufen implementiert, die das Heizverhalten abhängig von der gewählten Leistung beeinflussen. Zusätzlich wurde eine separate Gerätetemperatur simuliert, anhand derer eine Überhitzung erkannt und der Heizlüfter automatisch abgeschaltet werden kann. Nach ausreichender Abkühlung nimmt das System den Betrieb selbstständig wieder auf.

Darüber hinaus wurde ein Energiesparmodus eingeführt, der die maximale Heizleistung begrenzt und über die Benutzeroberfläche gesteuert werden kann. Die UI wurde außerdem um Statusanzeigen erweitert, die den aktuellen Zustand des Heizlüfters sowie den Energiesparmodus darstellen. Zusätzlich wurde eine Fenstersimulation integriert, die plötzliche Temperaturabfälle erkennt und das Heizverhalten entsprechend anpasst.

Abschließend wurden umfangreiche Tests durchgeführt, um die neuen Funktionen und Sicherheitsmechanismen zu überprüfen. Dabei zeigte sich, dass die erweiterten Heiz- und Schutzfunktionen zuverlässig arbeiten. Gleichzeitig wurde deutlich, wie wichtig eine klare Trennung zwischen Simulation, Sensorik und Steuerungslogik für die Wartbarkeit und Stabilität des Systems ist.



