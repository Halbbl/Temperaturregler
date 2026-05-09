# Architektur

## Architekturmodell wählen

### Schichtenmodell

- Aufgaben klar aufteilen (Benutzeroberfläche, Logik, Heizen, Temperatur messen)
- gute Test- und Wartbarkeit der einzelen Schichten
- jede Schicht darf nur auf die darunterliegende zugreifen


## Komponentendiagramm

![Komponentendiagramm](fanheater/references/Componentsdiagram.png)

### Verantwortlichkeiten der Komponenten

| **Komponente**    | **Rolle**                  | **Verantwortlichkeiten**                           |
|-------------------|----------------------------|----------------------------------------------------|
| UserInterface     | Präsentationsschicht       | Tempraturanzeige, User-Interaktionen               |
| Logic             | Business-Logik             | Heater (de-)aktivieren, User-Interaktionen handeln |
| HardwareComonents | Hardware-Interface         | Zugriff auf Sensoren / Simulatoren                 |


## Technologiestack

| Kategorie                | Technologie / Tool | Begründung                                                                                       |
|--------------------------|--------------------|--------------------------------------------------------------------------------------------------|
| Sprache                  | Java Temurin 21    | familiärität, persönliche Erfahrung -> einfache wartbarkeit,                                     |
| Buildsystem              | --keins--          | kein Build                                                                                       |
| Versionskontrolle        | Git + GitHub       | Standard                                                                                         |
| IDE                      | IntelliJ           | Übersichtlich, modular                                                                           |
| Ausgabe                  | Konsole  + Swing   | Einfache, fast responsive Lösung                                                                 |
| Dokumentation            | Markdown           | Einfache ...                                                                                     |
| Codeanalyse              | MetricsReloaded    | CI/CD integrierbar, ideal für Clean Code, Integration mit Github ermöglicht automatische Prüfung |
| Test-Framework           | JUnit              | Standard für Java, einfach, erweiterbar                                                          |
| Frameworks, Bibliotheken |                    |                                                                                                  |
