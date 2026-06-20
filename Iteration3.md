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

**Test 3.1: Req. 3.1 und 3.2 (Settings über .properties / SettingsManager)** <br>
Es wurde überprüft, ob Settings über die .properties Datei verändert werden können und ob diese Änderungen
korrekt über den SettingsManager ausgelesen werden. Anschließend wurde geprüft, ob ein Überschreiben der
Settings über den SettingsManager korrekt in die Datei zurückgeschrieben wird. Beides funktionierte wie
vorgesehen.

**Test 3.2: Req. 3.3 (Components- und Simulationsklassen)** <br>
Es wurde kontrolliert, ob durch die Einführung der Components- und Simulationsklassen die Übergabeparameter
im ComponentsManager tatsächlich reduziert wurden und ob die enthaltenen Werte weiterhin korrekt verarbeitet
werden. Die Reduzierung der Parameter sowie die korrekte Funktionalität konnten bestätigt werden.

**Test 3.3: Req. 3.4 und 3.5 (Zeitsimulation und TimerSensor)** <br>
Die simulierte Zeit wurde über mehrere Durchläufe beobachtet, um zu prüfen, ob Stunde und Minute korrekt
fortschreiten und ob der TimerSensor die aktuelle Zeit zuverlässig ausliest. Zusätzlich wurde überprüft, ob
die Werte korrekt in der .properties Datei gespeichert werden. Beides funktionierte fehlerfrei.

**Test 3.4: Req. 3.6 bis 3.9 (Timer-Funktion)** <br>
Es wurden mehrere Timer mit unterschiedlichen Uhrzeiten und Zieltemperaturen angelegt, abgefragt und
gelöscht. Dabei wurde geprüft, ob pro Uhrzeit nur ein Timer existieren kann und ob die targetTemp beim
Erreichen der hinterlegten Zeit korrekt übernommen wird. Nach Behebung des anfänglichen Lösch-Fehlers
funktionierten alle genannten Punkte zuverlässig.

**Test 3.5: Req. 3.10 und 3.11 (sitzungsübergreifende Speicherung)** <br>
Nach einem Neustart des Programms wurde überprüft, ob die zuvor gesetzten Settings sowie die angelegten
Timer weiterhin vorhanden und korrekt geladen werden. Beide Datensätze blieben über mehrere Neustarts
hinweg konsistent erhalten.

**Test 3.6: Req. 3.12 bis 3.15 (Benutzeroberfläche)** <br>
Die überarbeitete UI wurde auf Verständlichkeit, Übersichtlichkeit und Nähe zur Vorlage hin geprüft.
Zusätzlich wurde kontrolliert, ob die Anzahl der Bedienknöpfe die Grenze von fünf nicht überschreitet und
ob die UI weiterhin in nachvollziehbare Funktionen unterteilt ist. Die UI erfüllte alle genannten Punkte.

### 9. Review

