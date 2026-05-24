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

        //displays the currents temperature when heater is turned on
        JLabel currentTemperature =
                new JLabel(
                        "Aktuelle Temperatur: "
                                + Math.round(componentsManager.getCurrentRoomTemperature()*10.0)/10.0
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

        JPanel saveButtonPanel = new JPanel();
        JButton setTargetTemperatureButton =
                new JButton("Speichern");
        saveButtonPanel.add(setTargetTemperatureButton);

        frame.add(title);
        frame.add(currentTemperature);
        frame.add(centerPanel);
        frame.add(saveButtonPanel);

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

                Timer timer = new Timer(1000, event -> {

                    double currentTemp =
                            componentsManager.getCurrentRoomTemperature();
                    //round temperature for better ux
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