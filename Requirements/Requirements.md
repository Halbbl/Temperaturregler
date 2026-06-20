# Requirements

## Iteration 1

### Kernfunktionalität

| Req | Beschreibung |
| --- | --- |
| 1.1 | Automatisches Aktivieren des Heizlüfters, wenn die Raumtemperatur max. 1 °C kälter ist als die gewünschte Temperatur |
| 1.2 | Automatisches Deaktivieren des Heizlüfters bei Erreichen der gewünschten Temperatur |
| 1.3 | Halten der gewünschten Temperatur mit Schwankungen bis max. 1 °C |
| 1.4 | Strikte Trennung der zentralen Logikkomponente vom restlichen Code |

### Benutzeroberfläche

| Req | Beschreibung |
| --- | --- |
| 1.5 | Anzeige der aktuellen Raumtemperatur |
| 1.6 | Möglichkeit zum Ändern der gewünschten Temperatur |
| 1.7 | Ändern der Benutzeroberfläche bei Interaktion des Nutzers |
| 1.8 | Intuitive Benutzeroberfläche |
| 1.9 | Abfangen von nicht genehmigten Input-Variablen |

---

## Iteration 2

### Kernfunktionalität

| Req | Beschreibung |
| --- | --- |
| 2.1 | 3 unterschiedliche Heizstufen + eine für ausgeschalteten Zustand |
| 2.2 | Erkennen von Überhitzung des Geräts |
| 2.3 | Automatisches Abschalten bei Überhitzung |
| 2.4 | Warten bis auf bestimmte Temperatur heruntergekühlt und dann automatisches Wiedereinschalten |
| 2.5 | Simulieren eines offenen Fensters |
| 2.6 | Erkennen eines rapiden Temperaturabfalls |
| 2.7 | Abschalten bei Erkennen eines rapiden Temperaturabfalls |
| 2.8 | Energiesparmodus, welcher max. auf mittlerer Stufe heizt |

### Benutzeroberfläche

| Req | Beschreibung |
| --- | --- |
| 2.9 | Verschiedene Status des Heizlüfters |
| 2.10 | Anzeigen des aktuellen Status durch passende Nachricht |
| 2.11 | Energiesparmodus über Knopf aktivieren und deaktivieren |
| 2.12 | Anzeigen, ob Energiesparmodus aktiv ist oder nicht |

---

## Iteration 3

### Kernfunktionalität

| Req | Beschreibung |
| --- | --- |
| 3.1 | Settings über eine `.properties`-Datei speichern und bearbeiten können |
| 3.2 | SettingsManager zum Auslesen und Überschreiben von Settings |
| 3.3 | Reduzierung der Übergabeparameter |
| 3.4 | Simulation der aktuellen Uhrzeit (Stunde, Minute) |
| 3.5 | TimerSensor zum Auslesen der simulierten Zeit & Speicherung der Werte in einer `.properties`-Datei |
| 3.6 | Timer selbst stellbar, löschbar und aufrufbar (Stunde, Minute, targetTemp) |
| 3.7 | Nur ein Timer pro Uhrzeit zulässig |
| 3.8 | TimerManager zum Auslesen, Neuanlegen und Löschen von Timern |
| 3.9 | Übernehmen der im Timer angegebenen targetTemp, sobald die hinterlegte Zeit erreicht ist |
| 3.10 | Settings sitzungsübergreifend speichern |
| 3.11 | Timer sitzungsübergreifend speichern |

### Benutzeroberfläche

| Req | Beschreibung |
| --- | --- |
| 3.12 | UI benutzerfreundlich, einfach verständlich und minimalistisch gestalten |
| 3.13 | UI so nah wie möglich an der [Vorlage](https://www.otto.de/p/fakir-heizluefter-prestige-hl-300-heizluefter-hochglanzweiss-silber-CS09EM066/#ech=29693338) orientieren |
| 3.14 | UI in einzelne Funktionen unterteilen statt in einem einzigen Konstruktor |