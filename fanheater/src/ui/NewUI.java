package ui;

import javax.swing.*;
import fanheater.src.manager.ComponentsManager;
import fanheater.src.heater.HeaterLevel;

import java.awt.*;

public class NewUI {

    private double targetTemperature;
    private boolean changing;
    private boolean on;
    private int currentTimerCount;
    private int timerCount;
    private int timerHour;
    private int timerMinute;
    private double timerTemperaure;
    private int newTimerSelection;
    private final int MAX_NEW_TIMER_SELECTION = 3;
    private final int HOUR = 24;
    private final int MINUTE = 60;
    private int newTimerHour;
    private int newTimerMinute;
    private double newTimerTargetTemperature;



    public NewUI(ComponentsManager componentsManager) {

        Font tempFont = new Font("Arial", Font.BOLD, 40);
        Font textFont = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.BOLD, 40);
        String levelActiveIndicator = "-------------------------------------------------";
        String levelInactiveIndicator = "";
        double tempChange = 0.5;
        targetTemperature = componentsManager.getTargetTemperature();
        changing = false;
        on = true;
        currentTimerCount = 1;
        timerCount = componentsManager.getTimerCount();
        timerHour = 0;
        timerMinute = 0;
        timerTemperaure = 0.0;
        newTimerSelection = 1;
        newTimerHour = 0;
        newTimerMinute = 0;
        newTimerTargetTemperature = 0.0;

        JFrame frame = new JFrame("Heizlüfter");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1, 10, 10));
        frame.setLocationRelativeTo(null);


        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel tempPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JLabel currentTemperature = new JLabel(String.valueOf(componentsManager.getCurrentRoomTemperature()), SwingConstants.RIGHT);
        currentTemperature.setFont(tempFont);

        JLabel celcius = new JLabel("°C", SwingConstants.LEFT);
        celcius.setFont(textFont);
        tempPanel.add(currentTemperature);
        tempPanel.add(celcius);

        JPanel heatingLevelPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JLabel low = new JLabel(levelInactiveIndicator, SwingConstants.CENTER);
        JLabel middle = new JLabel(levelInactiveIndicator, SwingConstants.CENTER);
        JLabel high = new JLabel(levelInactiveIndicator, SwingConstants.CENTER);

        heatingLevelPanel.add(low);
        heatingLevelPanel.add(middle);
        heatingLevelPanel.add(high);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        JButton startButton = new JButton("⏻");
        startButton.setFont(buttonFont);
        startButton.addActionListener(e -> {
           if (componentsManager.isOn()) {
               componentsManager.turnOff();
               on = false;
           } else {
               componentsManager.turnOn();
               on = true;
           }
        });

        JButton timerButton = new JButton("⏱\uFE0E ");
        timerButton.setFont(buttonFont);
        timerButton.addActionListener(e -> {
           frame.remove(titlePanel);
           frame.remove(buttonPanel);

           JPanel timerPanel = new JPanel(new GridLayout(1, 3, 10, 10));

            timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
            timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
            timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

           JLabel timerTime = new JLabel("",  SwingConstants.CENTER);
            timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
           timerTime.setFont(tempFont);

           JLabel timerTemp = new JLabel("",  SwingConstants.CENTER);
           timerTemp.setText(String.format("%.2f °C", timerTemperaure));
           timerTemp.setFont(tempFont);


           JPanel timerButtonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
           JButton backButton = new JButton("↵");
           backButton.setFont(buttonFont);
           backButton.addActionListener(a ->{
               frame.remove(timerPanel);
               frame.remove(timerButtonPanel);
               frame.add(titlePanel);
               frame.add(buttonPanel);

               frame.revalidate();
               frame.repaint();
           });

           JButton leftButton = new JButton("←");
           leftButton.setFont(buttonFont);
           leftButton.addActionListener(a ->{
               currentTimerCount--;
               if (currentTimerCount == 0){
                   currentTimerCount = componentsManager.getTimerCount();
               }
               timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
               timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
               timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

               timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
               timerTemp.setText(String.format("%.2f °C", timerTemperaure));
           });

           JButton rightButton = new JButton("→");
           rightButton.setFont(buttonFont);
           rightButton.addActionListener(a ->{
               currentTimerCount++;
               if (currentTimerCount == componentsManager.getTimerCount()+1){
                   currentTimerCount = 1;
               }
               timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
               timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
               timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

               timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
               timerTemp.setText(String.format("%.2f °C", timerTemperaure));
           });

           JButton plusButton = new JButton("+");
           plusButton.setFont(buttonFont);
           plusButton.addActionListener(a ->{
               frame.remove(timerPanel);
               frame.remove(timerButtonPanel);

               JPanel newTimerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

               JLabel newTime = new JLabel(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute),  SwingConstants.CENTER);
               newTime.setFont(tempFont);
               JLabel newTemp = new JLabel(String.format("%.2f °C", newTimerTargetTemperature),  SwingConstants.CENTER);
               newTemp.setFont(tempFont);

               //timer for text blinking when changing something
               Timer selectionBlinkTimer = new Timer(800, i -> {
                   if (newTimerSelection == 1){
                       newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
                       if (newTime.getText().equals(String.format("___:%02d Uhr", newTimerMinute))){
                           newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                       } else {
                           newTime.setText(String.format("___:%02d Uhr", newTimerMinute));
                       }
                   } else if (newTimerSelection == 2){
                       newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
                       if (newTime.getText().equals(String.format("%02d:___ Uhr", newTimerHour))){
                           newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                       } else {
                           newTime.setText((String.format("%02d:___ Uhr", newTimerHour)));
                       }
                   } else {
                       newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                       if (newTemp.getText().equals("         °C")){
                           newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
                       } else {
                           newTemp.setText("         °C");
                       }
                   }
               });

               selectionBlinkTimer.start();

               newTimerPanel.add(newTime);
               newTimerPanel.add(newTemp);

               JPanel newTimerButtonPanel = new JPanel(new GridLayout(1, 6, 10, 10));

               JButton backButtonTimerView = new JButton("↵");
               backButtonTimerView.setFont(buttonFont);
               backButtonTimerView.addActionListener(i ->{
                   frame.remove(newTimerPanel);
                   frame.remove(newTimerButtonPanel);
                   frame.add(timerPanel);
                   frame.add(timerButtonPanel);

                   frame.revalidate();
                   frame.repaint();
               });

               JButton newTimerLeftButton = new JButton("←");
               newTimerLeftButton.setFont(buttonFont);
               newTimerLeftButton.addActionListener(i ->{
                   newTimerSelection--;
                   if (newTimerSelection == 0){
                       newTimerSelection = MAX_NEW_TIMER_SELECTION;
                   }
                   selectionBlinkTimer.restart();
               });

               JButton newTimerRightButton = new JButton("→");
               newTimerRightButton.setFont(buttonFont);
               newTimerRightButton.addActionListener(i ->{
                   newTimerSelection++;
                   if (newTimerSelection > MAX_NEW_TIMER_SELECTION){
                       newTimerSelection = 1;
                   }
                   selectionBlinkTimer.restart();
               });

               JButton newTimerPlusButton = new JButton("+");
               newTimerPlusButton.setFont(buttonFont);
               newTimerPlusButton.addActionListener(i ->{
                   if (newTimerSelection == 1){
                       newTimerHour++;
                       if (newTimerHour == HOUR){
                           newTimerHour = 0;
                       }
                   } else if (newTimerSelection == 2){
                       newTimerMinute++;
                       if (newTimerMinute == MINUTE){
                           newTimerMinute = 0;
                       }
                   } else {
                       newTimerTargetTemperature += tempChange;
                       if (newTimerTargetTemperature > componentsManager.getMaxRoomTemperature()){
                           newTimerTargetTemperature = componentsManager.getMaxRoomTemperature();
                       }
                   }
                   newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                   newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
               });

               JButton  newTimerMinusLeftButton = new JButton("-");
               newTimerMinusLeftButton.setFont(buttonFont);
               newTimerMinusLeftButton.addActionListener(i ->{
                   if (newTimerSelection == 1){
                       newTimerHour--;
                       if (newTimerHour < 0){
                           newTimerHour = HOUR-1;
                       }
                   } else if (newTimerSelection == 2){
                       newTimerMinute--;
                       if (newTimerMinute < 0){
                           newTimerMinute = MINUTE-1;
                       }
                   } else {
                       newTimerTargetTemperature -= tempChange;
                       if (newTimerTargetTemperature < 0){
                           newTimerTargetTemperature = 0;
                       }
                   }
                   newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                   newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
               });



               newTimerButtonPanel.add(backButtonTimerView);
               newTimerButtonPanel.add(newTimerLeftButton);
               newTimerButtonPanel.add(newTimerRightButton);
               newTimerButtonPanel.add(newTimerPlusButton);
               newTimerButtonPanel.add(newTimerMinusLeftButton);

               frame.add(newTimerPanel);
               frame.add(newTimerButtonPanel);

               frame.revalidate();
               frame.repaint();

           });

           JButton minusButton = new JButton("-");
           minusButton.setFont(buttonFont);
           minusButton.addActionListener(a ->{
               componentsManager.removeTimerEntry(currentTimerCount);
               currentTimerCount--;
               if (currentTimerCount == 0){
                   currentTimerCount = componentsManager.getTimerCount();
               }
               timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
               timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
               timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

               timerTime.setText(String.format("%02d:%02d", timerHour, timerMinute));
           });

           timerPanel.add(timerTime);
           timerPanel.add(timerTemp);

           timerButtonPanel.add(backButton);
           timerButtonPanel.add(leftButton);
           timerButtonPanel.add(rightButton);
           timerButtonPanel.add(plusButton);
           timerButtonPanel.add(minusButton);

           frame.add(timerPanel);
           frame.add(timerButtonPanel);

           frame.revalidate();
           frame.repaint();
        });

        JButton energyButton = new JButton("ECO");
        energyButton.setFont(buttonFont);
        energyButton.addActionListener(e -> {
           componentsManager.updateEnergySaving();
        });

        //timer that switches back to current room temperature
        Timer revertToRoomTempTimer = new Timer(3000, e -> {
            currentTemperature.setText(String.valueOf(componentsManager.getCurrentRoomTemperature()));
        });
        revertToRoomTempTimer.setRepeats(false);

        //timer for text blinking when changing something
        Timer blinkTimer = new Timer(800, e -> {
            if (currentTemperature.getText().isEmpty()) {
                currentTemperature.setText(String.valueOf(targetTemperature));
            } else {
                currentTemperature.setText("");
            }
        });

        Timer stopBlinkTimer = new Timer(3000, e -> {
            blinkTimer.stop();
            changing = false;
        });
        stopBlinkTimer.setRepeats(false);

        JButton plusButton = new JButton("+");
        plusButton.setFont(buttonFont);
        plusButton.addActionListener(e -> {
            if (on){
                changing = true;
                targetTemperature += tempChange;
                componentsManager.updateForTargetTemperature(targetTemperature);

                // Zieltemperatur anstelle der Raumtemperatur anzeigen
                currentTemperature.setText(String.valueOf(targetTemperature));

                blinkTimer.start();
                stopBlinkTimer.restart();

                // Timer neu starten, falls er bereits läuft
                revertToRoomTempTimer.restart();
            }
        });

        JButton minusButton = new JButton("-");
        minusButton.setFont(buttonFont);
        minusButton.addActionListener(a ->{
            if (on){
                changing = true;
                targetTemperature -= tempChange;
                componentsManager.updateForTargetTemperature(targetTemperature);

                // Zieltemperatur anstelle der Raumtemperatur anzeigen
                currentTemperature.setText(String.valueOf(targetTemperature));

                blinkTimer.start();
                stopBlinkTimer.restart();

                // Timer neu starten, falls er bereits läuft
                revertToRoomTempTimer.restart();
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(timerButton);
        buttonPanel.add(energyButton);
        buttonPanel.add(plusButton);
        buttonPanel.add(minusButton);

        titlePanel.add(tempPanel);
        titlePanel.add(heatingLevelPanel);


        frame.add(titlePanel);
        frame.add(buttonPanel);

        Timer update = new Timer(1000, e -> {

            if (on){
                if (celcius.getText().isEmpty()){
                    celcius.setText("°C");
                }
                if (!changing){
                    currentTemperature.setText(String.valueOf(componentsManager.getCurrentRoomTemperature()));
                }
                if (componentsManager.isEnergySavingActivated()){
                    energyButton.setForeground(new Color(0, 230, 150));
                } else {
                    energyButton.setForeground(new Color(Color.BLACK.getRGB()));
                }
                HeaterLevel heaterLevel = componentsManager.getHeaterLevel();
                if (heaterLevel == HeaterLevel.LOW) {
                    low.setText(levelActiveIndicator);
                    middle.setText(levelInactiveIndicator);
                    high.setText(levelInactiveIndicator);
                } else if (heaterLevel == HeaterLevel.MEDIUM) {
                    low.setText(levelActiveIndicator);
                    middle.setText(levelActiveIndicator);
                    high.setText(levelInactiveIndicator);
                }  else if (heaterLevel == HeaterLevel.HIGH) {
                    low.setText(levelActiveIndicator);
                    middle.setText(levelActiveIndicator);
                    high.setText(levelActiveIndicator);
                } else {
                    low.setText(levelInactiveIndicator);
                    middle.setText(levelInactiveIndicator);
                    high.setText(levelInactiveIndicator);
                }
            } else {
                currentTemperature.setText("");
                celcius.setText("");
                energyButton.setForeground(new Color(Color.BLACK.getRGB()));
                low.setText(levelInactiveIndicator);
                middle.setText(levelInactiveIndicator);
                high.setText(levelInactiveIndicator);
            }
        });

        update.start();
        frame.setVisible(true);
    }
}
