# Iteration 3

In dieser Iteration wurde das bestehende Heizlüfter-System um eine persistente Konfigurationsverwaltung,
eine simulierte Zeit- und Timersteuerung sowie eine vollständig überarbeitete Benutzeroberfläche erweitert.
Der Schwerpunkt lag dabei auf einer saubereren Architektur, einer reduzierten Anzahl an Übergabeparametern
sowie einer benutzerfreundlicheren und übersichtlicheren Bedienung.

- Konfigurationsdateien (.properties) für Settings und Simulationswerte
- SettingsManager zum Lesen und Schreiben von Einstellungen
- Components- und Simulationsklassen zur Reduzierung von Übergabeparametern
- Simulierte Zeit mit TimerSensor
- Timer-Funktion mit TimerManager
- Komplett überarbeitete Benutzeroberfläche

---

### Festgelegte Requirements

**Logik:**

Req 3.1: Settings über eine .properties Datei speichern und bearbeiten können <br>
Req 3.2: SettingsManager zum Auslesen und Überschreiben von Settings <br>
Req 3.3: Reduzierung der Übergabeparameter <br>
Req 3.4: Simulation der aktuellen Uhrzeit (Stunde, Minute) <br>
Req 3.5: TimerSensor zum Auslesen der simulierten Zeit & Speicherung der Werte in einer .properties Datei <br>
Req 3.6: Timer selbst stellbar, löschbar und aufrufbar (Stunde, Minute, targetTemp) <br>
Req 3.7: Nur ein Timer pro Uhrzeit zulässig <br>
Req 3.8: TimerManager zum Auslesen, Neuanlegen und Löschen von Timern <br>
Req 3.9: Übernehmen der im Timer angegebenen targetTemp, sobald die hinterlegte Zeit erreicht ist <br>
Req 3.10: Settings sitzungsübergreifend speichern <br>
Req 3.11: Timer sitzungsübergreifend speichern <br>

**Benutzeroberfläche:**

Req 3.12: UI benutzerfreundlich, einfach verständlich und minimalistisch gestalten <br>
Req 3.13: UI so nah wie möglich an der [Vorlage](https://www.otto.de/p/fakir-heizluefter-prestige-hl-300-heizluefter-hochglanzweiss-silber-CS09EM066/#ech=29693338) orientieren <br>
Req 3.14: UI in einzelne Funktionen unterteilen statt in einem einzigen Konstruktor <br>

---

### Iteration Details

**Zeitraum:** 11.06.2026 - 19.07.2026

**Ziel:**

- Einführung einer persistenten und einfach bearbeitbaren Konfigurationsverwaltung
- Reduzierung der Übergabeparameter durch eine saubere Klassenstruktur
- Simulation einer eigenen Systemzeit als Grundlage für die Timerfunktion
- Einführung einer Timerfunktion zur automatisierten Temperatursteuerung
- Überarbeitung der Benutzeroberfläche mit Anlehnung an ein reales Vorbild

---

## Einzelne Schritte

### 1. Einführung der Konfigurationsdateien (.properties)

Um gespeicherte Werte besser verwalten und bearbeiten zu können, wurden eigene .properties Dateien für
die Settings, die RoomTemperatureSimulation und die InternalTemperatureSimulation eingeführt. <br>
Dadurch lassen sich Werte außerhalb des Codes anpassen, ohne dass eine erneute Kompilierung notwendig ist.
Diese Trennung von Konfiguration und Code verbessert zudem die Wartbarkeit des Systems.

### 2. SettingsManager

Um die neu eingeführten Konfigurationsdateien zentral verwalten zu können, wurde ein SettingsManager
eingeführt. Dieser übernimmt das Auslesen und Überschreiben der Settings. <br>
Andere Klassen greifen ausschließlich über den SettingsManager auf die gespeicherten Werte zu, wodurch
eine klare Trennung zwischen Datenzugriff und restlicher Programmlogik entsteht.

### 3. Components- und Simulationsklassen

Um die Anzahl der Übergabeparameter im ComponentsManager zu reduzieren, wurden eigene Components
(Hardwarekomponenten) sowie eine Simulationsklasse eingeführt, welche zusammengehörige Werte und Zustände
bündeln. <br>
Anstatt einzelne Parameter separat zu übergeben, werden nun zusammengehörige Objekte übergeben, was die
Lesbarkeit und Wartbarkeit des Codes deutlich verbessert.

Zudem wurde ein SimulationsManager eingeführt, wodurch der ComponentsManager besser von den Simulationen 
abgegrenzt wurde.

### 4. Zeitsimulation und TimerSensor

Zur Vorbereitung der Timer-Funktion wurde eine eigene Zeit simuliert, bestehend aus Stunde und Minute. Die
Sekunde wurde aus Gründen der Einfachheit weggelassen. <br>
Diese simulierte Zeit wird über einen eigens eingeführten TimerSensor ausgelesen.

### 5. Timer und TimerManager

Auf Basis der simulierten Zeit wurde eine Timer-Funktion implementiert. Jeder Timer besteht aus einer
Stunde, einer Minute und einer targetTemp und wird ebenfalls in einer eigenen .properties Datei gespeichert. <br>
Zur Verwaltung der Timer wurde ein TimerManager eingeführt, welcher das Auslesen, Neuanlegen und Löschen
von Timern übernimmt. Sobald die im Timer hinterlegte Uhrzeit erreicht wird, übernimmt das System
automatisch die dort angegebene targetTemp.

### 6. Überarbeitung der Benutzeroberfläche

Die Benutzeroberfläche wurde vollständig überarbeitet und orientiert sich nun deutlich stärker an einem
echten [Vorbild](https://www.otto.de/p/fakir-heizluefter-prestige-hl-300-heizluefter-hochglanzweiss-silber-CS09EM066/#ech=29693338). <br>
Im Gegensatz zu vorherigen Iterationen, in denen die UI nur in einem einzigen Konstruktor
aufgebaut wurde, wurde der Code dieses Mal in einzelne Funktionen unterteilt, was die Übersichtlichkeit
deutlich erhöht. Zusätzlich wurde die Anzahl der Bedienknöpfe bewusst auf maximal fünf begrenzt, um die
Bedienung möglichst minimalistisch zu halten.

### 7. Fehlerbehandlung und Optimierungen

Während der Umsetzung der funktionsbasierten UI-Struktur musste ein erster Ansatz vollständig verworfen
und neu aufgebaut werden, da die ursprüngliche Struktur zu unübersichtlich geworden war. <br>
Auch beim Löschen von Timern traten zunächst Probleme auf, da anfangs falsche Werte gesetzt wurden,
wodurch teilweise falsche oder keine Timer gelöscht wurden. Dieses Problem wurde durch eine Überarbeitung
der Lösch-Logik im TimerManager behoben. <br>
Des Weiteren konnten blinkende UI-Komponenten nicht vollständig stabil umgesetzt werden; in einigen Fällen
verschieben sich diese minimal. <br>
Ursprünglich wurden Konfigurations- und Timerdaten in .json Dateien gespeichert. Im Verlauf der Iteration
wurde aus Gründen der einfacheren Handhabung auf .properties Dateien umgestellt.

### 8. Testing

Anschließend wurden umfangreiche Tests zur Überprüfung der Funktionalität durchgeführt. Diese sind in 
`TestReport3` zu finden.

### 9. Review

Auch in dieser Iteration wurde eine Retrospektive erstellt und unter `Retrospective3` gespeichert.