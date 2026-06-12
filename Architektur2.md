# Architektur

Hinsichtlich der Architektur hat sich in der zweiten Iteration nichts geändert, wodurch in dieser Datei nichts anderes steht als in [Architektur1](./Iteration1/Architektur1.md).

## Architekturmodell

### Schichtenmodell

- Aufgaben klar aufteilen (Benutzeroberfläche, Logik, Heizen, Temperatur messen)
- gute Test- und Wartbarkeit der einzelen Schichten
- jede Schicht darf nur auf die darunterliegende zugreifen


## Komponentendiagramm

![Komponentendiagramm](fanheater/references/Componentsdiagram.png)

### Verantwortlichkeiten der Komponenten

| **Komponente**     | **Rolle**                  | **Verantwortlichkeiten**                           |
|--------------------|----------------------------|----------------------------------------------------|
| UserInterface      | Präsentationsschicht       | Tempraturanzeige, User-Interaktionen               |
| Logic              | Business-Logik             | Heater (de-)aktivieren, User-Interaktionen handeln |
| HardwareComponents | Hardware-Interface         | Zugriff auf Sensoren / Simulatoren                 |


## Technologiestack

| Kategorie                | Technologie / Tool | Begründung                                                       |
|--------------------------|--------------------|------------------------------------------------------------------|
| Sprache                  | Java Temurin 21    | familiärität, persönliche Erfahrung                              |
| Buildsystem              | --keins--          | kein Build                                                       |
| Versionskontrolle        | Git + GitHub       | Standard                                                         |
| IDE                      | IntelliJ Idea      | Übersichtlich, modular                                           |
| Ausgabe                  | Konsole  + Swing   | Einfache, fast responsive Lösung                                 |
| Dokumentation            | Markdown           | Einfache und schnelle Dokumentation + gute Anbindung in IntelliJ |
| Codeanalyse              | Sonar Qube         | IntelliJ Idea Plugin, einfache Bedienung                         |
| Test-Framework           | JUnit              | Standard für Java, einfach, erweiterbar                          |
| Frameworks, Bibliotheken |                    |                                                                  |
