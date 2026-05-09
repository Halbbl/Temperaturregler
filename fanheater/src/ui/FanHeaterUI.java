package fanheater.src.ui;

import fanheater.src.manager.ComponentsManager;

import javax.swing.*;
import java.awt.*;

/**
 * UI for the fan heater
 */
public class FanHeaterUI {

    private final ComponentsManager componentsManager;

    private double targetTemperature;

    /**
     * Constructor and start of the UI
     * @param componentsManager manager
     */
    public FanHeaterUI(ComponentsManager componentsManager) {

        this.componentsManager = componentsManager;

        JFrame frame = new JFrame("Heizlüfter");
        frame.setSize(400, 250);
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

        JPanel inputPanel = new JPanel();

        JLabel targetInput =
                new JLabel("Temperatureingae:");

        JTextField temperatureField =
                new JTextField(5);

        inputPanel.add(targetInput);
        inputPanel.add(temperatureField);

        JButton setTargetTemperatureButton =
                new JButton("Speichern");

        frame.add(title);
        frame.add(currentTemperature);
        frame.add(inputPanel);
        frame.add(setTargetTemperatureButton);

        setTargetTemperatureButton.addActionListener(e -> {

            try {

                targetTemperature =
                        Double.parseDouble(
                                temperatureField.getText()
                        );

                componentsManager.updateForTargetTemperature(targetTemperature);

                frame.remove(inputPanel);
                frame.remove(setTargetTemperatureButton);

                JButton changeTargetTemperatureButton = new JButton("Temperatur ändern");
                frame.add(changeTargetTemperatureButton);

                changeTargetTemperatureButton.addActionListener(returnToInput -> {
                    frame.remove(changeTargetTemperatureButton);
                    frame.add(inputPanel);

                    frame.add(setTargetTemperatureButton);

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