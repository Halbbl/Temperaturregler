# Tracability Matrix

| Requirement-ID | Jira-Issue | Komponente                         | Klasse(n)                             | Schnittstelle(n)                | Testfall(e) |
|----------------|------------|------------------------------------|---------------------------------------|---------------------------------|-------------|
| 1.1            | BAT-7      | batteryLogic, hardwareAbstraction  | `VoltageSensor`                       | readVoltage()                   | BB1         |
| 1.2            | BAT-8      | batteryLogic                       | `BatteryStateController`              | calculateStateOfCharge()        | UT3         |
| 1.3            | BAT-9      | batteryLogic                       | `BatteryStateController`              | getDisplayState()               | UX1         |
| 1.4            | BAT-10     | userInterface                      | `SimpleGUI`                           | getDisplayState()               | BB2         |
| 1.5            | BAT-11     | userInterface, hardwareAbstraction | `ButtonInput`, `InteractionHandler`   | ButtonInput()                   | BB4         |
| 1.6            | BAT-13     | userInterface                      | `SimpleGUI`                           |                                 |             |
| 1.7            | BAT-14     | userInterface, hardwareAbstraction | `InteractionHandler`                  | setState()                      | BB3         |
| 2.6            | BAT-15     | userInterface                      | `SimpleGUI`                           |                                 | UX3         |
| 2.7            | BAT-16     | userInterface                      | `SimpleGUI`                           |                                 | UX2         |
| 2.8            | BAT-17     | userInterface                      | `SimpleGUI`                           |                                 |             |
| 2.9            | BAT-18     | userInterface                      | `SimpleGUI`                           |                                 | UX4         |
| 2.10           | BAT-19     | userInterface                      | `SimpleGUI`                           | getDisplayState()               | BB5         |
| 3.1            | BAT-20     | persistenceManager                 | `SettingsStorage`, `VoltageSimulator` | calculateStateOfCharge()        | UT1, UT2    |
| 3.2            | BAT-21     | persistenceManager                 | `SettingsStorage`                     | readCalibVoltageToSoCFromDisc() |             |
