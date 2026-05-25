# Termostatregelung bei einem Heizlüfter

Software Engeneering Projekt SS 26

### Github Repository

Link lol

### Funktionalitäten

1. Raumtemperatur messen
2. Anzeige der Raumtemperatur
3. Einstellen der gewünschten Temperatur durch Nutzer
4. Automatische Deaktivierung des Lüfters bei erreichen der gewünschten Temperatur
5. Automatisches Aktivieren des Lüfters um Temperatur konstant zu halten
6. Bei rapidem Temperaturabfall automatisch Deaktivieren um Energie zu sparen

## Requirements

| **Nummer** | **Beschreibung**                                                                                                                               | **Zugehörigkeit** |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------|-------------------|
| 1.1        | Automatisches aktivieren des Heizlüfters wenn Raumtemperatur max. 1°C kälter ([Quelle](RechercheErgbnisse.md)) ist als gewünschte Temperatur | Logik             |
| 1.2        | Automatisches deaktivieren des Heizlüfters bei erreichen der gewünschten Temperatur                                                            | Logik             |
| 1.3        | Halten der gewünschten Temperatur mit Schwankungen bis max. 1°C ([Quelle](RechercheErgbnisse.md))                                            | Logik             |
| 1.4        | Strickte Trennung der zentralen Logikkomponente vom restlichen Code                                                                            | Logik             |
| 1.5        | Anzeige der aktuellen Raumtemperatur                                                                                                           | UI                |
| 1.6        | Möglichkeit zum ändern der gewünschten Temperatur                                                                                              | UI                |
| 1.7        | Ändern der Benutzeroberfläche bei Interaktion des Nutzers                                                                                      | UI                |
| 1.8        | Intuitive Benutzeroberfläche                                                                                                                   | UI                |
| 1.9        | Abfangen von nicht genehmigten Input Variablen                                                                                                 | UI                |
| 2.1        |                                                                                                                                                |                   |

## Iterationen

### [Iteration1](Iteration1.md): <br>
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


