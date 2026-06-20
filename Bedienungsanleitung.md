# Bedienungsanleitung

Beim Starten der Main Klasse öffnen sich zwei Fenster. 

Das Fenster in der Mitte des Bildschirms ist das eigentliche Kontrollfenster wohingegen das zweite Fenster nur für die Steuerung der Simulationen zuständig ist.

---

## Kontrollfenster

### Hauptanzeige

**Anzeige der aktuellen Temperatur:** 

Wenn die Anzeige nicht blinkt, wird die aktuelle Raumtemperatur dargestellt.

Wenn die Anzeige blinkt, nachdem man die gewünschte Temperatur mit `+` / `-` geändert hat, zeigt sie kurz die eingestellte Temperatur an.


**Anzeige der aktuellen Heizstufe:**

Unter der Temperaturanzeige befindet sich eine weitere Anzeige, welche angibt mit welcher Heizstufe aktuell geheizt wird. Diese wird durch 3 Striche visuallisiert.


| Strichanzahl | Heizstufe |
|:-------------|:----------|
| 0            | Off       |
| 1            | Low       |
| 2            | Medium    |
| 3            | High      |


**Knöpfe:**

`⏻` Ein-/Ausschalten des Heizlüfters

`⏱` Timer bearbeiten

`ECO` Energiesparmodus Ein/Ausschalten

`+` Gewünschte Temperatur erhöhen

`-` Gewünschte Temperatur niedriger stellen

---

**Energiesparmous:**

Wenn der Energiesparmodus aktiv ist, leuchtet der `ECO` Knopf grün und das Heizmodul kann maximal mit der Stufe Medium heizen.

**Error Indikatior:**

Sollte die Temperaturanzeige ohne Einstellen der Temperatur anfangen zu blinken bedeutet dies, dass der Heizlüfter einen rapiden Temperaturabfall erkannt hat (z.B. offenes Fenster) und das Heizmodul ausgeschalten hat. <br>
Es schaltet sich automatisch wieder an, wenn kein rapider Abfall mehr gemessen wird.

Sollte zusätzlich zur Temperaturanzeige auch die Heizstufenanzeige blinken bedeutet dies, dass das Heizmodul überhitzt ist. Zur Sicherheit schaltet es sich ab, um abzukühlen. Nach Abkühlung schaltet es sich automatisch wieder ein.

---

---

### Timer Anzeige

Nachdem man auf der Hauptanzeige auf `⏱` gedrückt hat kommt man zur Timer Anzeige.

**Anzeige der Timereinstellungen:** 

Auf der linken Seite wird die eingestellte Uhrzeit angezeigt, zu welcher der Timer aktiv wird.

Rechts wird die angegebene Temperatur angezeigt, auf welcher zu angegeben Uhrzeit geheizt wird.

Sollten die beiden Anzeigen "EE" anzeigen ist kein Timer gestellt.

**Knöpfe:**

`↵` Zurück zur Hauptanzeige

`←` vorherigen Timer anzeigen

`→` nächsten Timer anzeigen

`+` neuen Timer erstellen

`-` angezeigten Timer löschen

---

---

### Timer erstellen

Nachdem man auf der Timer Anzeige auf `+` gedrückt hat kommt man zum Timer erstellen.

**Anzeige der Timereinstellungen:**

Auf der linken Seite wird die eingestellte Uhrzeit angezeigt, zu welcher der Timer aktiv wird.

Rechts wird die angegebene Temperatur angezeigt, auf welcher zu angegeben Uhrzeit geheizt wird.

Sollten die beiden Anzeigen "EE" anzeigen ist kein Timer gestellt.

**Knöpfe:**

`↵` Zurück / Speichern

`←` vorherigen Timer anzeigen

`→` nächsten Timer anzeigen

`+` neuen Timer erstellen

`-` angezeigten Timer löschen

---

---

## Steuerung Simulation

**Open/Close Window:** Simuliert das Öffnen bzw. Schließen eines Fensters im Raum

**Zeitanzeige:** Zeigt die aktuell simulierte Zeit an

**Statusanzeige:** Zeigt den aktuellen Status des Heizmoduls an

**Eingestellte Temperatur Anzeige:** Zeigt die aktuell eingestellte Temperatur an
