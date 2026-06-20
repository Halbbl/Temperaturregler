# Retrospektive zu Iteration 2

**Was lief gut?** <br>
Die Erweiterung der Heizlogik um verschiedene Heizstufen sowie die Einführung der Gerätetemperatur verlief strukturiert und funktional erfolgreich. Besonders die Simulation des physikalischen Verhaltens konnte deutlich realistischer gestaltet werden. Auch die Integration neuer UI-Elemente wie dem Energiesparmodus funktionierte ohne größere Probleme. <br>

**Was lief nicht so gut?** <br>
Während der Implementierung der Fenstererkennung kam es zunächst zu logischen Fehlern, da simulierte und erkannte Zustände nicht klar voneinander getrennt waren. Dies führte zu inkonsistentem Verhalten im System. Zusätzlich verursachten Floating-Point-Ungenauigkeiten zunächst unzuverlässige Temperaturwerte, was eine nachträgliche Anpassung der Berechnung erforderlich machte. <br>

**Lessons learned:** <br>
Es wurde deutlich, dass Simulationen physikalischer Systeme eine klare Trennung zwischen „echten Zuständen“ und „abgeleiteten Zuständen“ benötigen. Außerdem zeigte sich, dass selbst kleine Ungenauigkeiten bei Gleitkommazahlen erhebliche Auswirkungen auf das Systemverhalten haben können. Eine saubere Architektur sowie frühzeitige Definition von Zuständen und Verantwortlichkeiten ist entscheidend für stabile Simulationen.
