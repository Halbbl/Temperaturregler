# Traceability Matrix

| Requirement | Beschreibung | Zugeordnete Tests | Status |
| ------------- | ------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------- | --------- |
| **Req. 1.1** | Automatisches Aktivieren des Heizlüfters, wenn die Raumtemperatur max. 1 °C unter der gewünschten Temperatur liegt | UT 1.5 – Steuerung Heizmodul: Aktivierung | Bestanden |
| **Req. 1.2** | Automatisches Deaktivieren des Heizlüfters bei Erreichen der gewünschten Temperatur | UT 1.6 – Steuerung Heizmodul: Deaktivierung | Bestanden |
| **Req. 1.3** | Halten der gewünschten Temperatur mit Schwankungen bis max. 1 °C | UT 1.7 – Steuerung Heizmodul: Temperatur halten | Bestanden |
| **Req. 1.4** | Temperatursimulation soll die Temperatur erhöhen bzw. senken können | UT 1.1 – Änderung Temperatur: Heizmodul an<br>UT 1.2 – Änderung Temperatur: Heizmodul aus<br>BB 1.1 – Automatisches Anpassen der Temperatur | Bestanden |
| **Req. 1.5** | Temperatursimulation darf definierte Temperaturgrenzen nicht überschreiten | UT 1.3 – Temperaturgrenze: Obergrenze<br>UT 1.4 – Temperaturgrenze: Untergrenze | Bestanden |
| **Req. 1.6** | Anzeige der aktuellen Raumtemperatur | UX 1.1 – Anzeige: Aktuelle Temperatur | Bestanden |
| **Req. 1.7** | Möglichkeit zum Ändern der gewünschten Temperatur | UX 1.2 – Eingabe: Gewollte Temperatur | Bestanden |
| **Req. 1.8** | Benutzeroberfläche reagiert auf Nutzerinteraktionen | UX 1.3 – Anzeige: Ändern der UI nach Benutzerinteraktion | Bestanden |
| **Req. 1.9** | Intuitive Benutzeroberfläche | UX 1.2 – Eingabe: Gewollte Temperatur | Bestanden |
| **Req. 1.10** | Abfangen von ungültigen Eingaben | BB 1.2 – Eingabe: Abfangen von nicht genehmigten Eingaben | Bestanden |
| **Req. 2.1** | 3 unterschiedliche Heizstufen + eine für ausgeschalteten Zustand | UT 2.9 – Heizstufe: HIGH<br>UT 2.10 – Heizstufe: MEDIUM<br>UT 2.11 – Heizstufe: LOW<br>UT 2.12 – Heizstufe: OFF<br>UT 2.13–2.16 – Temperatursimulation der Stufen<br>UT 2.17 – Untergrenze der Gerätetemperatur | Bestanden |
| **Req. 2.2** | Erkennen von Überhitzung des Geräts | UT 2.1 – Überhitzungsschutz: Abschalten bei Überhitzung | Bestanden |
| **Req. 2.3** | Automatisches Abschalten bei Überhitzung | UT 2.1 – Überhitzungsschutz: Abschalten bei Überhitzung<br>BB 2.1 – Automatisches Abschalten und Wiedereinschalten bei Überhitzung | Bestanden |
| **Req. 2.4** | Warten bis auf bestimmte Temperatur heruntergekühlt und dann automatisch wieder einschalten | UT 2.2 – Überhitzungsschutz: Wiedereinschalten nach Abkühlung<br>BB 2.1 – Automatisches Abschalten und Wiedereinschalten bei Überhitzung | Bestanden |
| **Req. 2.5** | Simulieren eines offenen Fensters | UT 2.3 – Fenstersimulation: Zustand umschalten<br>BB 2.2 – Reaktion auf simuliertes offenes Fenster | Bestanden |
| **Req. 2.6** | Erkennen eines rapiden Temperaturabfalls | UT 2.4 – Erkennung eines rapiden Temperaturabfalls<br>BB 2.2 – Reaktion auf simuliertes offenes Fenster | Bestanden |
| **Req. 2.7** | Abschalten bei Erkennen eines rapiden Temperaturabfalls | UT 2.5 – Abschalten bei erkanntem Temperaturabfall<br>BB 2.2 – Reaktion auf simuliertes offenes Fenster | Bestanden |
| **Req. 2.8** | Energiesparmodus welcher max. auf mittlerer Stufe heizt | UT 2.6 – Energiesparmodus: Aktivierung<br>UT 2.7 – Energiesparmodus: Deaktivierung<br>UT 2.8 – Energiesparmodus: Begrenzung der Heizstufe | Bestanden |
| **Req. 2.9** | Verschiedene Status des Heizlüfters | UX 2.1 – Anzeige: Status des Heizlüfters | Bestanden |
| **Req. 2.10** | Anzeigen des aktuellen Status durch passende Nachricht | UX 2.1 – Anzeige: Status des Heizlüfters | Bestanden |
| **Req. 2.11** | Energiesparmodus über Knopf aktivieren und deaktivieren | UX 2.2 – Bedienung: Energiesparmodus per Knopf | Bestanden |
| **Req. 2.12** | Anzeigen ob Energiesparmodus aktiv ist oder nicht | UX 2.3 – Anzeige: Status des Energiesparmodus | Bestanden |
