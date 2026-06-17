# Iteration 1

In dieser Iteration wurde das Architekturmodell gewählt, die anfänglichen Requirements 
festgelegt sowie die grundlegnden Klassen implementiert. Dabei wurden besonders folgend 
Funktionen berücksichtigt:

- Messen der Raumtemperatur
- Eingabemöglichkeit der gewünschten Temperatur für den Nutzer
- Visualisierung der Daten auf einfacher Benutzeroberfläche
- Eigenständige Steuerung des Geräts ohne externes Eingreifen

Diese Funktionen wurden hinsichtlich der realistisches Nutzung des Endprodukts ausgewählt. 
Dabei wurde besonders auf die Erweiterbarkeit und Gestaltung einer stabilen Basis für
das spätere Arbeiten geachtet.

---

### Festgelegte Requirements:

**Logik:**

Req. 1.1: Automatisches aktivieren des Heizlüfters wenn Raumtemperatur max. 1°C kälter ([Quelle](./RechercheErgbnisse.md)) ist als gewünschte Temperatur  <br>
Req. 1.2: Automatisches deaktivieren des Heizlüfters bei erreichen der gewünschten Temperatur <br>
Req. 1.3: Halten der gewünschten Temperatur mit Schwankungen bis max. 1°C ([Quelle](./RechercheErgbnisse.md)) <br>
Req. 1.4: Temperatur Simulation sollte Temperatur hoch bzw. runter gehen lassen <br>
Req. 1.5: Temperatursimulation sollte Grenzen (max, min) nicht überschreiten <br>
Req. 1.6: Strickte Trennung der zentralen Logikkomponente vom restlichen Code <br>


**Benutzeroberfläche:**

Req. 1.7: Anzeige der aktuellen Raumtemperatur <br>
Req. 1.8: Möglichkeit zum ändern der gewünschten Temperatur <br>
Req. 1.9: Ändern der Benutzeroberfläche bei Interaktion des Nutzers <br>
Req. 1.10: Intuitive Benutzeroberfläche <br>
Req. 1.11: Abfangen von nicht genehmigten Input Variablen <br>

---

### Iteration Details

**Zeitraum:** 08.05.2026 - 23.05.2026

**Ziel:** 

- Zentrale Logikeinheit implementieren
- Temperaturmessung und anzeigen dieser
- Nutzereingabe verarbeiten
- Einfache Benutzeroberfläche erstellen

---

## Einzelne Schritte

### 1. Definition des Projekts

Zuerst wurde die gegebene Aufgabenstellung analysiert und die einzelnen Teilbereiche für das
spezifische Projekt ausgearbeitet. Dabei wurde darauf geachtet die erarbeiteten Unteraufgabn
gleichmäßig auf die minimum 3 vorgeschriebenen Iterationen zu verteilen. <br>
Durch Recherche wurden die verschiedenen Funktionen eines handelsüblichen Heizlüfters erarbeitet
und aus der Liste dieser nach den Kriterien, der Komplexität und des zeitlichen Rahmens des Projekts
sowie der Priorität für das Gerät einige ausgewählt. Diese wurden daraufhin nach ihrer Komplexität
und benötigten Vorarbeit ebenfalls auf die Iterations aufgeteilt.

### 2. Auswahl der technischen Hilfsmittel und Architektur

Im zweiten Schritt wurden verschiedene Programmiersprachen gegenüber gestellt, welche für die
Erstellung des Projekts in Frage kamen. Darunter befanden sich unter anderem Java, Python, C++
und Rust. Diese wurden in Hinsicht auf die Anforderungen und verschiedenen Schwierigkeitsgrade 
verglichen, wobei die Wahl letztendlich auf Java viel. Genaueres zum warum befindet sich in 
[Architektur1](./Architektur1.md). <br>
Zudem wurde die IDE gewählt, wobei die Wahl hinsichtlich auf Java zwischen IntelliJ Idea und 
Visual Studio Code lag. Hierbei wurde sich für ersteres entschieden. <br>
Weitere Hilfsmittel wie Markdown und Swing ergaben sich während des Coding-Prozesses und wurden
daraufhin in die Liste aufgenommen. <br>

Die Architektur wurde nach Vergleich der verschiedenen Modelle ausgewählt. Hierbei wurde vor 
allem auf den späteren Verlauf, besonders die Wartbarkeit und Erweiterbarkeit geachtet. Dabei
traf das Schichtenmodell am ehesten auf die genannten Kriterien zu. <br>
Mit dieser Grundlage wurde das Komponentendiagramm erstellt, welches eine Visualisierung der
verschiedenen Projektschichten bildet. Dadurch kann man klar die zentralen Komponenten und deren
klar definierten Aufgaben erkennen.

### 3. Implementierung

Im vierten Schritt wurde mit der praktischen Implementierung der zuvor geplanten Architektur 
und des Designs begonnen. Hierfür wurden zunächst die grundlegenden Klassen erstellt, welche den 
Kern der Anwendung bilden. Dazu gehörten die Startklasse ([Main](fanheater/src/Main.java)), der 
Temperatursimulator ([TemperatureSimulation](fanheater/src/simulation/TemperatureSimulation.java)), 
der Temperatursensor ([TemperatureSensor](fanheater/src/sensor/TemperatureSensor.java)), der 
Heizlüfter (Heater), der zentrale Verwaltungsmechanismus 
([ComponentsManager](fanheater/src/manager/ComponentsManager.java)) sowie die Benutzeroberfläche 
([FanHeaterUI](fanheater/src/ui/FanHeaterUI.java)). <br>

Zu Beginn wurde der Temperatursimulator implementiert, welcher die Änderung der Raumtemperatur 
simuliert und damit die Grundlage für die weiteren Komponenten darstellt. Anschließend wurde der 
Temperatursensor entwickelt und getestet, um sicherzustellen, dass die simulierten Temperaturwerte 
korrekt ausgelesen werden können. <br>
Darauf aufbauend wurden die Klassen Heater und ComponentsManager erstellt und miteinander verbunden. 
Dabei übernahm der HeatingManager die zentrale Steuerung des Systems und kommunizierte sowohl mit 
dem Heizlüfter als auch mit dem Sensor. Dadurch entstand eine grundlegende Verbindungskette 
zwischen Heizlüfter, ComponentsManager und Sensor, wodurch die automatische Steuerungslogik vorbereitet wurde. <br>
Bis zu diesem Zeitpunkt erfolgten sämtliche Tests mithilfe von System.out.println()-Ausgaben im 
Terminal. Dadurch konnten die Temperaturwerte sowie die Zustandsänderungen des Heizlüfters 
schrittweise überprüft werden. <br>

Im weiteren Verlauf wurde die grafische Benutzeroberfläche implementiert. Diese ermöglicht dem 
Nutzer die Eingabe der gewünschten Temperatur sowie die Anzeige der aktuellen Raumtemperatur. 
Zusätzlich wurde die Benutzeroberfläche mit dem ComponentsManager verbunden, sodass eine 
Kommunikation zwischen Benutzerinteraktion und der eigentlichen Systemlogik möglich wurde. <br>

Während des gesamten Implementierungsprozesses wurden fortlaufend Tests durchgeführt, um die 
korrekte Funktionsweise der einzelnen Komponenten sicherzustellen. Dabei wurde insbesondere 
überprüft, ob Temperaturwerte korrekt übertragen, verarbeitet und anschließend richtig 
ausgegeben werden.

### 4. Design

Vor Beginn der eigentlichen Implementierung wurde zunächst ein grober Entwurf der Systemarchitektur 
erstellt. Dieser diente dazu, die wichtigsten Komponenten des Projekts sowie deren grundlegende 
Beziehungen zueinander zu visualisieren. Dabei lag der Fokus insbesondere auf der Aufteilung der 
Zuständigkeiten zwischen Benutzeroberfläche, Steuerungslogik und Temperatursimulation. <br>

Im weiteren Verlauf der Entwicklung wurde das Klassendiagramm schrittweise erweitert und präzisiert. 
Während das anfängliche Diagramm noch als konzeptionelle Skizze diente, wurde für die detaillierte 
Darstellung der finalen Klassenstruktur der Diagramm-Generator von IntelliJ IDEA verwendet. 
Dadurch konnte die tatsächliche Implementierung automatisch analysiert und als strukturiertes 
Klassendiagramm visualisiert werden. Dieses kann man in [Design1](Design1.md) finden. <br>
Das Diagramm zeigt die Beziehungen zwischen den einzelnen Klassen, darunter insbesondere die 
Kommunikation zwischen ComponentsManager, Heater, TemperatureSensor, TemperatureSimulation sowie der 
Benutzeroberfläche FanHeaterUI. Zusätzlich werden Abhängigkeiten, Methoden und Attribute der jeweiligen 
Klassen dargestellt, wodurch die Architektur des Systems übersichtlich nachvollzogen werden kann. <br>

Durch die Kombination aus vorheriger Planungsskizze und automatisch generiertem Klassendiagramm konnte 
sichergestellt werden, dass die entworfene Architektur während der Implementierung eingehalten und 
gleichzeitig nachvollziehbar dokumentiert wurde.


### 5. Review

**Was lief gut?** <br>
Die Implementierung der grundlegenden Logik sowie der verschiedenen Schnittstellen verlief insgesamt reibungslos 
und ohne größere Probleme. Besonders die Umsetzung der Kernfunktionen konnte schnell realisiert werden, da die 
einzelnen Komponenten klar voneinander getrennt waren. Auch die Kommunikation zwischen den verschiedenen Klassen 
funktionierte nach der Anpassung der Struktur zuverlässig. Dadurch konnte die Anwendung Schritt für Schritt 
erweitert und getestet werden.

**Was lief nicht so gut?** <br> 
Zu Beginn des Projekts musste der gesamte Code nahezu vollständig umgeschrieben werden, da die ursprüngliche 
Struktur der Anwendung nicht ausreichend durchdacht war. Die Logik wurde auf mehrere Klassen verteilt, wodurch 
schnell Unübersichtlichkeit entstand. Zwar war die Idee eines zentralen Managers bereits vorhanden, allerdings 
wurde dieser Ansatz nicht konsequent genug umgesetzt. Dadurch kam es zu Problemen bei der Kommunikation zwischen 
den einzelnen Komponenten. <br>
Zusätzlich wurde der Manager anfangs als oberste Instanz gewählt. Während der weiteren Entwicklung stellte sich 
jedoch heraus, dass diese Struktur die Implementierung unnötig kompliziert machte. Aus diesem Grund mussten sowohl 
der Manager als auch die UI-Klasse teilweise überarbeitet und neu strukturiert werden. Dies führte zu zusätzlichem 
Zeitaufwand, half jedoch dabei, die Architektur der Anwendung besser zu organisieren.

**Lessons learned:** <br>
Durch das Projekt wurde deutlich, wie wichtig eine gute Planung der Klassenstruktur bereits vor Beginn der 
eigentlichen Implementierung ist. Eine klar definierte Architektur hätte viele spätere Anpassungen und 
Umstrukturierungen vermeiden können. Außerdem wurde gelernt, dass Konzepte wie ein zentraler Manager nicht nur 
geplant, sondern auch konsequent umgesetzt werden müssen, damit die Anwendung langfristig übersichtlich und wartbar 
bleibt.

