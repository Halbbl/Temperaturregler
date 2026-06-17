# Tests

## 1. Ziel der Tests

Das Ziel der Tests ist zur Überprüfung des Heizlüftersystems in Bezug auf korrekter Funktionalität, Benutzerfreundlichkeit und robuster Systemintegration. Die Test sollen sicherstellen, dass:
- Temperatur innerhalb der gesetzten Grenzen bleibt
- der Heizlüfter auf die angegebene Temperatur heizt und diese auch beibehält
- die Benutzeroberfläche intuitiv ist und aktuelle Werte anzeigt
- das System auf Eingaben des Nutzers korrekt reagiert

---

## 2. Testarten

### 2.1 Unit Tests

**Test 1.1:** Sichergehen, dass Temperatur sich in der Simulation erhöht, wenn `Heater` an ist

**Test 1.2:** Sichergehen, dass Temperatur sich in der Simulation verringert, wenn `Heater` aus ist

**Test 1.3:** Sichergehen, dass Temperatur nicht über Maximum hinausgeht

**Test 1.4:** Sichergehen, dass Temperatur nicht unter Minumum fällt

**Test 1.5:** Sichergehen, dass Heater sich aktiviert, wenn Temperatur zu niedrig ist

**Test 1.6:** Sichergehen, dass Heater sich deaktiviert, wenn gewollte Temperatur erreicht ist

**Test 1.7:** Sichergehen, dass Heater gewollte Temperatur hält

### 2.2 Usability Tests

**Test 1.8:** Sichergehen, dass aktuelle Temperatur in UI angezeigt wird ohne weitere Eingabe

**Test 1.9:** Sichergehen, dass 



