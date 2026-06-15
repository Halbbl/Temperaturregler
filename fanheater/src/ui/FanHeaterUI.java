package fanheater.src.ui;

import fanheater.src.heater.HeaterStatus;
import fanheater.src.manager.ComponentsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * UI for the fan heater
 */
public class FanHeaterUI {

    private double targetTemperature;
    private final JFrame frame;

    /**
     * Constructor and start of the UI
     * @param componentsManager manager
     */
    public FanHeaterUI(ComponentsManager componentsManager) {

        frame = new JFrame("Heizlüfter");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1, 10, 10));
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Heizlüfter Steuerung",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Current Activity
        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new GridLayout(4, 1, 10, 10));

        //displays the currents temperature when heater is turned on
        JLabel currentTemperature =
                new JLabel(
                        "Aktuelle Temperatur: "
                                + Math.round(componentsManager.getCurrentRoomTemperature()*10.0)/10.0
                                + "°C",
                        SwingConstants.CENTER
                );

        JLabel currentTime = new JLabel("Uhrzeit: " + componentsManager.getTime() + " Uhr", SwingConstants.CENTER);

        //displays the current status of the heater
        JLabel status = new JLabel("Status: " + componentsManager.getHeaterStatus().getMessage(), SwingConstants.CENTER);

        activityPanel.add(currentTemperature);
        activityPanel.add(currentTime);
        activityPanel.add(status);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();

        JLabel targetInput =
                new JLabel("Temperatureingabe:");

        JTextField temperatureField =
                new JTextField(10);

        // blocks chars and negative numbers for the input
        temperatureField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();

                // Valid: Numbers, dot and backspace
                if (!Character.isDigit(c)
                        && c != '.'
                        && c != '\b') {
                    e.consume();
                }

                // just ONE dot
                if (c == '.' && temperatureField.getText().contains(".")) {
                    e.consume();
                }
            }
        });

        inputPanel.add(targetInput);
        inputPanel.add(temperatureField);

        centerPanel.add(inputPanel, BorderLayout.NORTH);

        //the keypad
        JPanel keypadPanel = new JPanel();
        keypadPanel.setLayout(new GridLayout(4,3,5,5));

        String[] buttons = {
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                ".", "0", "⌫"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(e -> {
                if (text.equals("⌫")) {
                    String current = temperatureField.getText();

                    if (!current.isEmpty()) {
                        temperatureField.setText(current.substring(0, current.length() - 1));
                    }
                } else {
                    temperatureField.setText(temperatureField.getText() + text);
                }
            });
            keypadPanel.add(button);
        }

        centerPanel.add(keypadPanel, BorderLayout.CENTER);

        //energy saving button and display
        JPanel saveButtonPanel = new JPanel();
        JButton setTargetTemperatureButton =
                new JButton("Speichern");
        saveButtonPanel.add(setTargetTemperatureButton);

        String energySavingLabelText = "";
        if (componentsManager.isEnergySavingActivated()) {
            energySavingLabelText = "Active";
        } else {
            energySavingLabelText = "Inactive";
        }
        JLabel energySavingLabel = new JLabel(energySavingLabelText);

        JPanel settingsButtonPanel = new JPanel();
        JButton energySavingButton = new JButton("Energiesparmodus");
        energySavingButton.addActionListener(e -> {
            componentsManager.updateEnergySaving();
            if (componentsManager.isEnergySavingActivated()) {
                energySavingLabel.setText("Active");
            } else {
                energySavingLabel.setText("Inactive");
            }
        });

        settingsButtonPanel.add(energySavingButton);
        settingsButtonPanel.add(energySavingLabel);


        JPanel timerButtonPanel = new JPanel();
        JButton timerButton = new JButton("Set Timer");
        timerButtonPanel.add(timerButton);
        timerButton.addActionListener(e -> {
            frame.remove(centerPanel);
            frame.remove(settingsButtonPanel);
            frame.remove(timerButtonPanel);

            JPanel timerPanel = new JPanel();
            timerPanel.setLayout(new GridLayout(5, 2, 10, 10));

            // Zeit-Eingabe (Stunde und Minute)
            JLabel timeLabel = new JLabel("Zeit (HH:mm):");
            JSpinner hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
            JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

            JPanel timeInputPanel = new JPanel();
            timeInputPanel.add(hourSpinner);
            timeInputPanel.add(new JLabel(":"));
            timeInputPanel.add(minuteSpinner);

            // Temperatur-Eingabe
            JLabel tempLabel = new JLabel("Zieltemperatur (°C):");
            JTextField timerTemperatureField = new JTextField(5);

            // Checkbox: Heater ausschalten statt Temperatur einstellen
            JCheckBox turnOffCheckBox = new JCheckBox("Heater zu dieser Zeit ausschalten");
            turnOffCheckBox.addActionListener(checkboxEvent -> {
                boolean selected = turnOffCheckBox.isSelected();
                timerTemperatureField.setEnabled(!selected);
                tempLabel.setEnabled(!selected);
            });

            // Speichern und Zurück Buttons
            JButton saveTimerButton = new JButton("Timer speichern");
            JButton backButton = new JButton("Zurück");

            timerPanel.add(timeLabel);
            timerPanel.add(timeInputPanel);
            timerPanel.add(tempLabel);
            timerPanel.add(timerTemperatureField);
            timerPanel.add(new JLabel());
            timerPanel.add(turnOffCheckBox);
            timerPanel.add(saveTimerButton);
            timerPanel.add(backButton);

            frame.add(title);
            frame.add(activityPanel);
            frame.add(timerPanel);

            frame.revalidate();
            frame.repaint();

            saveTimerButton.addActionListener(saveEvent -> {
                int hour = (int) hourSpinner.getValue();
                int minute = (int) minuteSpinner.getValue();
                boolean turnOff = turnOffCheckBox.isSelected();

                if (turnOff) {
                    componentsManager.addTimerEntry(hour, minute, 0.0);
                } else {
                    try {
                        double timerTargetTemperature = Double.parseDouble(timerTemperatureField.getText());
                        componentsManager.addTimerEntry(hour, minute, timerTargetTemperature);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Bitte eine gültige Zahl eingeben!"
                        );
                        return;
                    }
                }

                // zurück zur Hauptansicht
                frame.remove(timerPanel);
                frame.add(centerPanel);
                frame.add(saveButtonPanel);
                frame.add(settingsButtonPanel);
                frame.add(timerButtonPanel);
                frame.revalidate();
                frame.repaint();
            });

            backButton.addActionListener(backEvent -> {
                frame.remove(timerPanel);
                frame.add(centerPanel);
                frame.add(saveButtonPanel);
                frame.add(settingsButtonPanel);
                frame.add(timerButtonPanel);
                frame.revalidate();
                frame.repaint();
            });
        });



        frame.add(title);
        frame.add(activityPanel);
        frame.add(centerPanel);
        frame.add(saveButtonPanel);
        frame.add(settingsButtonPanel);
        frame.add(timerButtonPanel);

        // after saving let change temperature button appear and save button disappear
        setTargetTemperatureButton.addActionListener(e -> {

            try {

                targetTemperature =
                        Double.parseDouble(
                                temperatureField.getText()
                        );

                componentsManager.updateForTargetTemperature(targetTemperature);

                //remove input field and save button
                frame.remove(centerPanel);
                frame.remove(saveButtonPanel);

                JPanel changeButtonPanel = new JPanel();
                JButton changeTargetTemperatureButton = new JButton("Temperatur ändern");
                changeButtonPanel.add(changeTargetTemperatureButton);
                frame.add(changeButtonPanel);

                //when change temperature is pressed let input field and save button appear
                changeTargetTemperatureButton.addActionListener(returnToInput -> {
                    frame.remove(changeButtonPanel);
                    frame.add(centerPanel, BorderLayout.CENTER);

                    frame.add(saveButtonPanel, BorderLayout.CENTER);

                    frame.revalidate();
                    frame.repaint();
                });

                frame.revalidate();
                frame.repaint();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Bitte eine gültige Zahl eingeben!"
                );
            }
        });

        //simulate open window by pressing F
        InputMap inputMap =
                frame.getRootPane().getInputMap(
                        JComponent.WHEN_IN_FOCUSED_WINDOW
                );

        ActionMap actionMap =
                frame.getRootPane().getActionMap();

        inputMap.put(
                KeyStroke.getKeyStroke("F"),
                "openWindow"
        );

        actionMap.put(
                "openWindow",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        componentsManager.updateWindow();
                        //System.out.println("Window open: " +  componentsManager.isWindowOpen());
                    }
                }
        );

        Timer timer = new Timer(1000, event -> {

            double currentTemp = componentsManager.getCurrentRoomTemperature();
            //round temperature for better ux
            currentTemperature.setText(
                    "Aktuelle Temperatur: "
                            + Math.round(currentTemp * 10.0) / 10.0
                            + "°C"
            );

            String timeNow = componentsManager.getTime();
            currentTime.setText("Uhrzeit: " + timeNow + " Uhr");

            HeaterStatus heaterStatus = componentsManager.getHeaterStatus();
            status.setText("Status: " + heaterStatus.getMessage());
        });

        timer.start();

        frame.setVisible(true);
    }
}