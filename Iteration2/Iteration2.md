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

Erweiterung der Heizlogik um realistische Heizstufen
Einführung von Sicherheitsmechanismen (Überhitzungsschutz)
Simulation von Umweltfaktoren (Fenster offen)
Verbesserung der Systemstabilität und Fehlerbehandlung
Erweiterung der Benutzeroberfläche um neue Steuerungsmöglichkeiten

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

Zur Übeprüfung der korrekten Funktionalität wurden einige Unit, Usability und Black-Box Tests durchgeführt. Alle sind unter `TestReport2` zu finden.

### 6. Review

Zuletzt wurde eine Review mit lessons learned aus der Iteration erstellt. Diese ist unter `Retrospective2` zu finden.