package fanheater.src.ui;

import fanheater.src.manager.ComponentsManager;

import javax.swing.*;
import java.awt.*;

/**
 * UI for the fan heater
 */
public class FanHeaterUI {

    private double targetTemperature;

    /**
     * Constructor and start of the UI
     * @param componentsManager manager
     */
    public FanHeaterUI(ComponentsManager componentsManager) {

        JFrame frame = new JFrame("Heizlüfter");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title =
                new JLabel("Heizlüfter Steuerung",
                        SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel currentTemperature =
                new JLabel(
                        "Aktuelle Temperatur: "
                                + componentsManager.getCurrentRoomTemperature()
                                + "°C",
                        SwingConstants.CENTER
                );


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();

        JLabel targetInput =
                new JLabel("Temperatureingabe:");

        JTextField temperatureField =
                new JTextField(10);

        inputPanel.add(targetInput);
        inputPanel.add(temperatureField);

        centerPanel.add(inputPanel, BorderLayout.NORTH);


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

        JPanel saveButtonPanel = new JPanel();
        JButton setTargetTemperatureButton =
                new JButton("Speichern");
        saveButtonPanel.add(setTargetTemperatureButton);

        frame.add(title);
        frame.add(currentTemperature);
        frame.add(centerPanel);
        frame.add(saveButtonPanel);

        setTargetTemperatureButton.addActionListener(e -> {

            try {

                targetTemperature =
                        Double.parseDouble(
                                temperatureField.getText()
                        );

                componentsManager.updateForTargetTemperature(targetTemperature);

                frame.remove(centerPanel);
                frame.remove(saveButtonPanel);

                JPanel changeButtonPanel = new JPanel();
                JButton changeTargetTemperatureButton = new JButton("Temperatur ändern");
                changeButtonPanel.add(changeTargetTemperatureButton);
                frame.add(changeButtonPanel);

                changeTargetTemperatureButton.addActionListener(returnToInput -> {
                    frame.remove(changeButtonPanel);
                    frame.add(centerPanel, BorderLayout.CENTER);

                    frame.add(saveButtonPanel, BorderLayout.CENTER);

                    frame.revalidate();
                    frame.repaint();
                });

                frame.revalidate();
                frame.repaint();

                Timer timer = new Timer(1000, event -> {

                    double currentTemp =
                            componentsManager.getCurrentRoomTemperature();

                    currentTemperature.setText(
                            "Aktuelle Temperatur: "
                                    + Math.round(currentTemp * 10.0) / 10.0
                                    + "°C"
                    );
                });

                timer.start();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Bitte eine gültige Zahl eingeben!"
                );
            }
        });
        frame.setVisible(true);
    }
}