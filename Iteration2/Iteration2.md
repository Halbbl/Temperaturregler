# Iteration 2

In dieser Iteration wurde das bestehende Heizlüfter-System funktional erweitert und um 
mehrere realitätsnahe Simulations- und Steuerungsmechanismen ergänzt. Dabei wurde der Fokus 
insbesondere auf eine realistischere Temperaturdynamik sowie zusätzliche Sicherheits- und 
Komfortfunktionen gelegt.

- verschiedene Heizstufen
- Sicherheitsabschaltung bei Überhitzung
- Energiesparmodus
- offenes Fenster erkennen

---

### Festgelegte Requirements

**Logik:**

Req 2.1: 3 unterschiedliche Heizstufen + eine für ausgeschalteten Zustand <br>
Req 2.2: Erkennen von Überhitzung des Geräts <br>
Req 2.3: Automatisches abschalten bei Überhitzung <br>
Req 2.4: Warten bis auf bestimmte Temperatur heruntergekühlt und dann automatisch wieder einschalten <br>
Req 2.5: Simulieren eines offenen Fensters <br>
Req 2.6: Erkennen eines rapiden Temperaturabfalls <br>
Req 2.7: Abschalten bei Erkennen eines rapiden Temperaturabfalls <br> 
Req 2.8: Energiesparmodus welcher max. auf mittlerer Stufe heizt <br>
Req 2.9: Verschiedene Status des Heizlüfters 

**Benutzeroberfläche:**

Req 2.10: Anzeigen des aktuellen Status durch passende Nachricht <br>
Req 2.11: Energiesparmodus über Knopf aktivieren und deaktivieren <br>
Req 2.12: Anzeigen ob Energiesparmodus aktiv ist oder nicht <br>

---

### Iteration Details

**Zeitraum:** 24.05.2026 - 11.06.2026

**Ziel:**

- Erweiterung der Heizlogik um realistische Heizstufen
- Einführung von Sicherheitsmechanismen (Überhitzungsschutz)
- Simulation von Umweltfaktoren (Fenster offen)
- Verbesserung der Systemstabilität und Fehlerbehandlung
- Erweiterung der Benutzeroberfläche um neue Steuerungsmöglichkeiten

---

## Einzelne Schritte

### 1. Erweiterung der Heizlogik

Zunächst wurde das bestehende Heizsystem um verschiedene Heizstufen erweitert. Diese wurden als Enum umgesetzt, wodurch eine klare und typisierte Darstellung der möglichen Leistungszustände des Heizlüfters ermöglicht wurde. <br>
Jede Heizstufe besitzt dabei eine eigene Erhöhungsrate, wodurch die Temperaturentwicklung realistischer simuliert werden kann. Höhere Heizstufen führen zu einer stärkeren Temperatursteigerung, während niedrigere Stufen eine langsamere Anpassung bewirken. <br>
Zusätzlich wurde berücksichtigt, dass auch die interne Temperatur des Geräts von der Heizstufe abhängig ist, wodurch sich ein realistischeres thermisches Verhalten ergibt.

### 2. Simulation der Gerätetemperatur und Sicherheitsmechanismen

Im nächsten Schritt wurde eine separate Simulation für die interne Gerätetemperatur eingeführt. Diese bildet die thermische Belastung des Heizlüfters ab und entwickelt sich unabhängig von der Raumtemperatur. <br>
Zur Absicherung des Systems wurde eine maximale Gerätetemperatur definiert. Wird dieser Grenzwert überschritten, schaltet sich der Heizlüfter automatisch ab, um eine Überhitzung zu verhindern. Zusätzlich wurde ein Pufferwert eingeführt, um ein kontrolliertes Wiederhochfahren nach dem Abkühlen zu ermöglichen.

### 3. Unterschiedliche Temperaturdynamik zwischen Gerät und Raum

Im Rahmen der Verbesserung der Simulation wurde die Temperaturentwicklung des Geräts und der Raumtemperatur unterschiedlich modelliert. Während die Raumtemperatur vergleichsweise langsam reagiert, weist die Gerätetemperatur eine schnellere Veränderungsrate auf. <br>
Diese Trennung ermöglicht eine realistischere Darstellung physikalischer Prozesse und verbessert die Nachvollziehbarkeit des Systemverhaltens.

### 4. Energiesparmodus

Als zusätzliche Funktion wurde ein Energiesparmodus implementiert. Dieser begrenzt die maximal nutzbare Heizstufe auf „Medium“, um den Energieverbrauch des Systems zu reduzieren. <br>
Der Modus kann über einen Button in der Benutzeroberfläche aktiviert oder deaktiviert werden. Die aktuelle Aktivierung wird dabei visuell in der UI dargestellt.

### 5. Simulation und Erkennung eines offenen Fensters

Zur Erweiterung der Umweltsimulation wurde ein Mechanismus zur Darstellung eines offenen Fensters eingeführt. Dieses kann über die Benutzeroberfläche durch Drücken der Taste „F“ simuliert werden. <br>
Bei aktivem Fenster sinkt die Raumtemperatur deutlich schneller, was die Auswirkungen eines geöffneten Fensters realistisch abbildet. Zusätzlich wurde ein Algorithmus implementiert, der ein offenes Fenster anhand plötzlicher Temperaturabfälle erkennt. <br>
Wird ein geöffnetes Fenster erkannt, reagiert das System entsprechend und passt das Heizverhalten an bzw. deaktiviert die Heizung.

### 6. Fehlerbehandlung und Optimierungen

Während der Implementierung traten verschiedene technische Probleme auf. Ein zentrales Problem waren Floating-Point-Ungenauigkeiten, die zu ungenauen Temperaturwerten führten. Dieses Problem wurde durch gezielte Rundung der Werte gelöst, wodurch eine stabile Anzeige gewährleistet werden konnte. <br>
Zudem kam es zu einem Fehler in der Fenstererkennung, da zunächst eine falsche Variable zur Zustandsbestimmung verwendet wurde. Dies führte dazu, dass das Fensterverhalten inkonsistent erkannt wurde. <br>
Zur Lösung dieses Problems wurde die Logik überarbeitet und die Simulation des Fensters klar von der automatischen Erkennung getrennt. Dadurch konnte eine saubere Trennung zwischen simuliertem Zustand und ermitteltem Zustand erreicht werden.

### 7. Verbesserung der Systemarchitektur

Im Verlauf der Erweiterungen wurde die Architektur des Systems weiter stabilisiert. Besonders die Trennung zwischen Simulation, Sensorik und Steuerungslogik wurde konsequent umgesetzt. <br>
Dadurch konnte die Wartbarkeit verbessert und die Fehleranfälligkeit reduziert werden. Die einzelnen Komponenten sind nun klar voneinander getrennt und kommunizieren ausschließlich über definierte Schnittstellen.


### 8.Testing

**Test 2.1: Req. 2.1 (Heizstufen)** <br>
Die verschiedenen Heizstufen sowie der ausgeschaltete Zustand wurden durch wiederholtes Umschalten der Heizstufe getestet. Dabei wurde überprüft, ob jede Stufe korrekt gesetzt wird und ob sich die Temperaturentwicklung entsprechend der hinterlegten Heizleistung verändert. <br>
Es wurde kontrolliert, dass die Stufen OFF, LOW, MEDIUM und HIGH korrekt im System verarbeitet und in der Simulation sichtbar werden. Alle Stufen funktionierten wie erwartet.

**Test 2.2: Req. 2.2 und 2.3 (Überhitzungsschutz)** <br>
Zur Überprüfung der Überhitzungserkennung wurde die Gerätetemperatur künstlich erhöht, bis der definierte Grenzwert überschritten wurde. Dabei wurde beobachtet, ob das System den Heizlüfter automatisch abschaltet. <br>
Nach Erreichen der kritischen Temperatur wurde der Heizlüfter korrekt auf OFF gesetzt, wodurch die Überhitzung erfolgreich erkannt und verhindert wurde.

**Test 2.3: Req. 2.4 (Wiederaktivierung nach Abkühlung)** <br>
Nach einer simulierten Überhitzung wurde die Gerätetemperatur unter den definierten Schwellenwert (inkl. Pufferbereich) gesenkt. Anschließend wurde überprüft, ob das System den Heizlüfter automatisch wieder aktiviert. <br>
Das System reagierte wie vorgesehen und nahm den Heizbetrieb nach ausreichender Abkühlung wieder auf.

**Test 2.4: Req. 2.5 bis 2.7 (Fenstersimulation und Erkennung)** <br>
Die Simulation eines offenen Fensters wurde durch Betätigung der vorgesehenen UI-Funktion (Taste „F“) getestet. Dabei wurde überprüft, ob die Raumtemperatur schneller sinkt und ob der Zustand korrekt im System reflektiert wird. <br>
Zusätzlich wurde getestet, ob ein rapider Temperaturabfall korrekt erkannt wird. In allen Fällen wurde das offene Fenster zuverlässig erkannt und das System reagierte durch Abschalten bzw. Anpassung des Heizverhaltens.

**Test 2.5: Req. 2.8 (Energiesparmodus)** <br>
Der Energiesparmodus wurde mehrfach aktiviert und deaktiviert, um zu überprüfen, ob die maximale Heizstufe korrekt auf MEDIUM begrenzt wird. Dabei wurde getestet, ob höhere Heizstufen im aktiven Modus verhindert werden. <br>
Die Einschränkung wurde korrekt umgesetzt und das System verhielt sich entsprechend der Spezifikation.

**Test 2.6: Req. 2.9 und 2.10 (Systemstatus)** <br>
Zur Überprüfung der Statusanzeige wurde das System in verschiedenen Zuständen (Heizen, Aus, Überhitzung, Fenster offen, Energiesparmodus) betrieben. <br>
Dabei wurde kontrolliert, ob der angezeigte Status in der Benutzeroberfläche korrekt mit dem internen Zustand des Systems übereinstimmt. Alle Status wurden korrekt und verständlich dargestellt.

**Test 2.7: Req. 2.11 und 2.12 (UI Energiesparmodus)** <br>
Der Button zur Aktivierung des Energiesparmodus wurde mehrfach betätigt. Dabei wurde überprüft, ob der Modus zuverlässig zwischen aktiv und inaktiv wechselt und ob die Anzeige in der Benutzeroberfläche korrekt aktualisiert wird. <br>
Die visuelle Rückmeldung funktionierte fehlerfrei und zeigte den aktuellen Zustand des Energiesparmodus korrekt an.

### 6. Review

**Was lief gut?** <br>
Die Erweiterung der Heizlogik um verschiedene Heizstufen sowie die Einführung der Gerätetemperatur verlief strukturiert und funktional erfolgreich. Besonders die Simulation des physikalischen Verhaltens konnte deutlich realistischer gestaltet werden. Auch die Integration neuer UI-Elemente wie dem Energiesparmodus funktionierte ohne größere Probleme. <br>

**Was lief nicht so gut?** <br>
Während der Implementierung der Fenstererkennung kam es zunächst zu logischen Fehlern, da simulierte und erkannte Zustände nicht klar voneinander getrennt waren. Dies führte zu inkonsistentem Verhalten im System. Zusätzlich verursachten Floating-Point-Ungenauigkeiten zunächst unzuverlässige Temperaturwerte, was eine nachträgliche Anpassung der Berechnung erforderlich machte. <br>

**Lessons learned:** <br>
Es wurde deutlich, dass Simulationen physikalischer Systeme eine klare Trennung zwischen „echten Zuständen“ und „abgeleiteten Zuständen“ benötigen. Außerdem zeigte sich, dass selbst kleine Ungenauigkeiten bei Gleitkommazahlen erhebliche Auswirkungen auf das Systemverhalten haben können. Eine saubere Architektur sowie frühzeitige Definition von Zuständen und Verantwortlichkeiten ist entscheidend für stabile Simulationen.