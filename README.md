# Termostatregelung bei einem Heizlüfter

Software Engineering Projekt SS 26

### Persönliches Github Repository

https://github.com/Halbbl/Temperaturregler/tree/master

### Funktionalitäten

1. Raumtemperatur messen
2. Anzeige der Raumtemperatur
3. Einstellen der gewünschten Temperatur durch Nutzer
4. Automatische Deaktivierung des Lüfters bei Erreichen der gewünschten Temperatur
5. Automatisches Aktivieren des Lüfters um Temperatur konstant zu halten
6. Bei rapidem Temperaturabfall (Fenster offen) automatisch Deaktivieren um Energie zu sparen
7. Überhitzungsschutz des Geräts
8. Energiesparmodus
9. Timer stellen
10. Übernehmen der im Timer eingestellten Temperatur, wenn eingestellte Zeit erreicht ist

## Iterationen

### Iteration 1: <br>
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

### Iteration 2: <br>

In der zweiten Iteration des Projekts wurde das bestehende Heizlüftersystem um mehrere Funktionen erweitert, die eine realistischere Simulation und eine höhere Systemsicherheit ermöglichen. Der Schwerpunkt lag auf der Einführung verschiedener Heizstufen, der Simulation einer internen Gerätetemperatur, dem Überhitzungsschutz sowie der Erkennung eines geöffneten Fensters.

Hierfür wurden unterschiedliche Heizstufen implementiert, die das Heizverhalten abhängig von der gewählten Leistung beeinflussen. Zusätzlich wurde eine separate Gerätetemperatur simuliert, anhand derer eine Überhitzung erkannt und der Heizlüfter automatisch abgeschaltet werden kann. Nach ausreichender Abkühlung nimmt das System den Betrieb selbstständig wieder auf.

Darüber hinaus wurde ein Energiesparmodus eingeführt, der die maximale Heizleistung begrenzt und über die Benutzeroberfläche gesteuert werden kann. Die UI wurde außerdem um Statusanzeigen erweitert, die den aktuellen Zustand des Heizlüfters sowie den Energiesparmodus darstellen. Zusätzlich wurde eine Fenstersimulation integriert, die plötzliche Temperaturabfälle erkennt und das Heizverhalten entsprechend anpasst.

Abschließend wurden umfangreiche Tests durchgeführt, um die neuen Funktionen und Sicherheitsmechanismen zu überprüfen. Dabei zeigte sich, dass die erweiterten Heiz- und Schutzfunktionen zuverlässig arbeiten. Gleichzeitig wurde deutlich, wie wichtig eine klare Trennung zwischen Simulation, Sensorik und Steuerungslogik für die Wartbarkeit und Stabilität des Systems ist.

### Iteration 3: <br>
In der dritten Iteration des Projekts wurde das bestehende Heizlüftersystem um eine persistente Konfigurationsverwaltung, eine simulierte Zeitsteuerung sowie eine vollständig überarbeitete Benutzeroberfläche erweitert. Der Schwerpunkt lag auf einer saubereren Architektur, einer reduzierten Anzahl an Übergabeparametern sowie einer benutzerfreundlicheren Bedienung.

Hierfür wurden `.properties`-Dateien eingeführt, über die Settings und Simulationswerte außerhalb des Codes verwaltet und sitzungsübergreifend gespeichert werden können. Ein zentraler `SettingsManager` übernimmt dabei das Auslesen und Überschreiben dieser Werte. Durch die Einführung von `Components`- und `Simulations`-Klassen wurden zusammengehörige Objekte gebündelt, wodurch die Anzahl der Übergabeparameter im `ComponentsManager` deutlich reduziert wurde.

Darüber hinaus wurde eine eigene Zeitsimulation implementiert, die als Grundlage für eine neue Timerfunktion dient. Jeder Timer speichert eine Uhrzeit sowie eine Zieltemperatur; bei Erreichen der hinterlegten Zeit übernimmt das System die angegebene Zieltemperatur automatisch. Ein `TimerManager` verwaltet das Anlegen, Auslesen und Löschen von Timern, wobei pro Uhrzeit nur ein Timer zulässig ist.

Die Benutzeroberfläche wurde vollständig neu gestaltet und orientiert sich an einem realen Vorbild. Im Gegensatz zu vorherigen Iterationen ist der UI-Code nun in einzelne Funktionen aufgeteilt, was die Übersichtlichkeit deutlich erhöht. Die Anzahl der Bedienknöpfe wurde bewusst auf maximal fünf begrenzt, um eine minimalistische Bedienung zu gewährleisten.

Abschließend wurden umfangreiche Tests durchgeführt, die die korrekte Funktion der Persistenz, der Zeitsteuerung sowie der Timerverwaltung bestätigten. Dabei zeigte sich, dass eine klare Trennung von Konfiguration, Steuerungslogik und Benutzeroberfläche die Wartbarkeit und Stabilität des Systems erheblich verbessert.

