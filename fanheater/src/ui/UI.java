package ui;

import javax.swing.*;
import manager.ComponentsManager;
import heater.HeaterLevel;

import java.awt.*;

public class UI {

    private final ComponentsManager componentsManager;

    private JFrame frame;

    // fonts
    private final Font tempFont = new Font("Arial", Font.BOLD, 40);
    private final Font textFont = new Font("Arial", Font.PLAIN, 20);
    private final Font buttonFont = new Font("Arial", Font.BOLD, 40);

    // heating level indicator
    private final String levelActiveIndicator = "-------------------------------------------------";
    private final String levelInactiveIndicator = "";

    private final double tempChange = 0.5; // how much target temp is changed

    // Screen 1: display variants
    private double targetTemperature;
    private boolean changing; // only update room temp when target temp is not changed right now
    private boolean on; // disable enerything except on/off button when turned off

    // Screen 1 UI components (kept as fields because they are reused by other methods,
    // e.g. the back-button on Screen 2 and the periodic update Timer)
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel currentTemperature;
    private JLabel celcius;
    private JLabel low;
    private JLabel middle;
    private JLabel high;
    private JButton startButton;
    private JButton timerButton;
    private JButton energyButton;
    private JButton plusButton;
    private JButton minusButton;

    // Screen 1 Timers
    private Timer blinkTimer;
    private Timer stopBlinkTimer;
    private Timer revertToRoomTempTimer;
    private Timer errorBlinkTimer;
    private Timer overheatedTimer;
    private Timer update;

    // Screen 2: current shown Timer entries
    private int currentTimerCount;
    private int timerHour;
    private int timerMinute;
    private double timerTemperaure;

    // Screen 3: create new Timer selection options
    private int newTimerSelection;
    private final int MAX_NEW_TIMER_SELECTION = 3;
    private final int HOUR = 24;
    private final int MINUTE = 60;
    private int newTimerHour;
    private int newTimerMinute;
    private double newTimerTargetTemperature;

    // Screen 4: saving selection Options
    private int saveSelection;
    private final int MAX_SAVE_SELECTION = 3;

    // Simulation
    private JFrame simulateFrame;
    private JButton openWindowButton;
    private JLabel timeLabel;
    private JLabel statusLabel;
    private Timer simUpdateTimer;


    public UI(ComponentsManager componentsManager) {
        this.componentsManager = componentsManager;

        initState();
        initFrame();

        buildScreen1();
        showScreen1();

        //timer for indicating an error
        errorBlinkTimer = createErrorOpenBlinkTimer();
        overheatedTimer = createOverheatedTimer();

        // timer for updating all information
        update = createUpdateTimer();
        update.start();

        frame.setVisible(true);

        //==================================================================================================================================
        // This part is only for simulation and can be removed later without any consequences
        buildSimulationFrame();
    }

    // sets the initial values for all four screens
    private void initState() {

        //Screen 1
        targetTemperature = componentsManager.getTargetTemperature();
        changing = false;
        on = true;

        //Screen 2
        currentTimerCount = 1;
        timerHour = 0;
        timerMinute = 0;
        timerTemperaure = 0.0;

        //Screen 3
        newTimerSelection = 1;
        newTimerHour = 0;
        newTimerMinute = 0;
        newTimerTargetTemperature = 0.0;

        //Screen 4
        saveSelection = 1;
    }

    // Frame
    private void initFrame() {
        frame = new JFrame("Heizlüfter");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1, 10, 10));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    //==================================================================================================================================
    // Screen 1: Main Display

    private void buildScreen1() {

        titlePanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // temp display
        JPanel tempPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        currentTemperature = new JLabel(String.valueOf(componentsManager.getCurrentRoomTemperature()), SwingConstants.RIGHT);
        currentTemperature.setFont(tempFont);

        // °C display
        celcius = new JLabel("°C", SwingConstants.LEFT);
        celcius.setFont(textFont);
        tempPanel.add(currentTemperature);
        tempPanel.add(celcius);

        // hating level indicator: 0 lines = off, 1 line = low, 2 lines = medium, 3 lines = high
        JPanel heatingLevelPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        low = new JLabel(levelInactiveIndicator, SwingConstants.CENTER);
        middle = new JLabel(levelInactiveIndicator, SwingConstants.CENTER);
        high = new JLabel(levelInactiveIndicator, SwingConstants.CENTER);

        heatingLevelPanel.add(low);
        heatingLevelPanel.add(middle);
        heatingLevelPanel.add(high);

        // turn heater on/off
        buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        startButton = new JButton("⏻");
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

        // show Timers -> Screen 2
        timerButton = new JButton("⏱\uFE0E ");
        timerButton.setFont(buttonFont);
        timerButton.addActionListener(e -> buildScreen2());

        // turn energy saving mode on/off
        energyButton = new JButton("ECO");
        energyButton.setFont(buttonFont);
        energyButton.addActionListener(e -> componentsManager.updateEnergySaving());

        //timer that switches back to current room temperature
        revertToRoomTempTimer = createRevertToRoomTempTimer();

        //timer for text blinking when changing target temo
        blinkTimer = createBlinkTimer();

        stopBlinkTimer = createStopBlinkTimer();

        // increase taget temperature
        plusButton = new JButton("+");
        plusButton.setFont(buttonFont);
        plusButton.addActionListener(e -> {
            if (on) {
                changing = true;
                targetTemperature += tempChange;
                componentsManager.updateForTargetTemperature(targetTemperature);

                // display target temp instead of room temp
                currentTemperature.setText(String.valueOf(targetTemperature));

                blinkTimer.start();
                stopBlinkTimer.restart();

                // restart timer for target temp display
                revertToRoomTempTimer.restart();
            }
        });

        //decrease target temperature
        minusButton = new JButton("-");
        minusButton.setFont(buttonFont);
        minusButton.addActionListener(a -> {
            if (on) {
                changing = true;
                targetTemperature -= tempChange;
                componentsManager.updateForTargetTemperature(targetTemperature);

                // display target temp instead of room temp
                currentTemperature.setText(String.valueOf(targetTemperature));

                blinkTimer.start();
                stopBlinkTimer.restart();

                // restart timer for target temp display
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
    }

    private void showScreen1() {
        frame.add(titlePanel);
        frame.add(buttonPanel);
    }

    // Screen 1 end
    //==================================================================================================================================


    //==================================================================================================================================
    // Screen 2: show Timer Display

    private void buildScreen2() {

        frame.remove(titlePanel);
        frame.remove(buttonPanel);

        JPanel timerPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        if (componentsManager.getTimerEntry(currentTimerCount).length == 0) {
            timerHour = 0;
            timerMinute = 0;
            timerTemperaure = 0.0;
        } else {
            timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
            timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
            timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);
        }

        JLabel timerTime = new JLabel("", SwingConstants.CENTER);
        timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
        timerTime.setFont(tempFont);

        JLabel timerTemp = new JLabel("", SwingConstants.CENTER);
        timerTemp.setText(String.format("%.2f °C", timerTemperaure));
        timerTemp.setFont(tempFont);


        // back button -> Screen 1
        JPanel timerButtonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        JButton backButton = new JButton("↵");
        backButton.setFont(buttonFont);
        backButton.addActionListener(a -> {
            frame.remove(timerPanel);
            frame.remove(timerButtonPanel);
            frame.add(titlePanel);
            frame.add(buttonPanel);
            frame.revalidate();
            frame.repaint();
        });

        // navigating left through options
        JButton leftButton = new JButton("←");
        leftButton.setFont(buttonFont);
        leftButton.addActionListener(a -> {
            currentTimerCount--;
            if (currentTimerCount == 0) {
                currentTimerCount = componentsManager.getTimerCount();
            }
            timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
            timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
            timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

            timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
            timerTemp.setText(String.format("%.2f °C", timerTemperaure));
        });

        // navigating right through timers
        JButton rightButton = new JButton("→");
        rightButton.setFont(buttonFont);
        rightButton.addActionListener(a -> {
            currentTimerCount++;
            if (currentTimerCount == componentsManager.getTimerCount() + 1) {
                currentTimerCount = 1;
            }
            timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
            timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
            timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

            timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
            timerTemp.setText(String.format("%.2f °C", timerTemperaure));
        });

        // adding new Timer -> Screen 3
        // NOTE: renamed from "plusButton" to "timerPlusButton" to avoid shadowing the Screen 1 field of the same name
        JButton timerPlusButton = new JButton("+");
        timerPlusButton.setFont(buttonFont);
        timerPlusButton.addActionListener(a -> buildScreen3(timerPanel, timerButtonPanel));

        //remove current displayed timer
        // NOTE: renamed from "minusButton" to "timerMinusButton" to avoid shadowing the Screen 1 field of the same name
        JButton timerMinusButton = new JButton("-");
        timerMinusButton.setFont(buttonFont);
        timerMinusButton.addActionListener(a -> {
            componentsManager.removeTimerEntry(currentTimerCount);
            currentTimerCount--;
            if (currentTimerCount == 0) {
                currentTimerCount = componentsManager.getTimerCount();
            }
            timerHour = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[0]);
            timerMinute = Integer.parseInt(componentsManager.getTimerEntry(currentTimerCount)[1]);
            timerTemperaure = Double.parseDouble(componentsManager.getTimerEntry(currentTimerCount)[2]);

            timerTime.setText(String.format("%02d:%02d Uhr", timerHour, timerMinute));
        });

        timerPanel.add(timerTime);
        timerPanel.add(timerTemp);

        timerButtonPanel.add(backButton);
        timerButtonPanel.add(leftButton);
        timerButtonPanel.add(rightButton);
        timerButtonPanel.add(timerPlusButton);
        timerButtonPanel.add(timerMinusButton);

        frame.add(timerPanel);
        frame.add(timerButtonPanel);

        frame.revalidate();
        frame.repaint();
    }

    // Screen 2 end
    //==================================================================================================================================


    //==================================================================================================================================
    // Screen 3: create new Timer Display

    private void buildScreen3(JPanel timerPanel, JPanel timerButtonPanel) {

        frame.remove(timerPanel);
        frame.remove(timerButtonPanel);

        JPanel newTimerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel newTime = new JLabel(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute), SwingConstants.CENTER);
        newTime.setFont(tempFont);
        JLabel newTemp = new JLabel(String.format("%.2f °C", newTimerTargetTemperature), SwingConstants.CENTER);
        newTemp.setFont(tempFont);

        //timer for text blinking when changing something
        Timer selectionBlinkTimer = createSelectionBlinkTimer(newTime, newTemp);
        selectionBlinkTimer.start();

        newTimerPanel.add(newTime);
        newTimerPanel.add(newTemp);

        JPanel newTimerButtonPanel = new JPanel(new GridLayout(1, 6, 10, 10));

        // back button -> Screen 4
        JButton backButtonTimerView = new JButton("↵");
        backButtonTimerView.setFont(buttonFont);
        backButtonTimerView.addActionListener(i -> buildScreen4(newTimerPanel, newTimerButtonPanel, timerPanel, timerButtonPanel));

        // navigate left through timers
        JButton newTimerLeftButton = new JButton("←");
        newTimerLeftButton.setFont(buttonFont);
        newTimerLeftButton.addActionListener(i -> {
            newTimerSelection--;
            if (newTimerSelection == 0) {
                newTimerSelection = MAX_NEW_TIMER_SELECTION;
            }
            selectionBlinkTimer.restart();
        });

        // navigate right through timers
        JButton newTimerRightButton = new JButton("→");
        newTimerRightButton.setFont(buttonFont);
        newTimerRightButton.addActionListener(i -> {
            newTimerSelection++;
            if (newTimerSelection > MAX_NEW_TIMER_SELECTION) {
                newTimerSelection = 1;
            }
            selectionBlinkTimer.restart();
        });

        //increase current selected number (hour, minute, temp)
        JButton newTimerPlusButton = new JButton("+");
        newTimerPlusButton.setFont(buttonFont);
        newTimerPlusButton.addActionListener(i -> {
            if (newTimerSelection == 1) {
                newTimerHour++;
                if (newTimerHour == HOUR) {
                    newTimerHour = 0;
                }
            } else if (newTimerSelection == 2) {
                newTimerMinute++;
                if (newTimerMinute == MINUTE) {
                    newTimerMinute = 0;
                }
            } else {
                newTimerTargetTemperature += tempChange;
                if (newTimerTargetTemperature > componentsManager.getMaxRoomTemperature()) {
                    newTimerTargetTemperature = componentsManager.getMaxRoomTemperature();
                }
            }
            newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
            newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
        });

        //decrease current selected number (hour, minute, temp)
        JButton newTimerMinusLeftButton = new JButton("-");
        newTimerMinusLeftButton.setFont(buttonFont);
        newTimerMinusLeftButton.addActionListener(i -> {
            if (newTimerSelection == 1) {
                newTimerHour--;
                if (newTimerHour < 0) {
                    newTimerHour = HOUR - 1;
                }
            } else if (newTimerSelection == 2) {
                newTimerMinute--;
                if (newTimerMinute < 0) {
                    newTimerMinute = MINUTE - 1;
                }
            } else {
                newTimerTargetTemperature -= tempChange;
                if (newTimerTargetTemperature < 0) {
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
    }

    // Screen 3 end
    //==================================================================================================================================


    //==================================================================================================================================
    // Screen 4: save new Timer Display

    // checks whether a Timer entry for the given hour and minute already exists
    private boolean timerEntryExistsForTime(int hour, int minute) {
        for (int i = 1; i <= componentsManager.getTimerCount(); i++) {
            String[] entry = componentsManager.getTimerEntry(i);
            if (entry.length >= 2) {
                int entryHour = Integer.parseInt(entry[0]);
                int entryMinute = Integer.parseInt(entry[1]);
                if (entryHour == hour && entryMinute == minute) {
                    return true;
                }
            }
        }
        return false;
    }

    private void buildScreen4(JPanel newTimerPanel, JPanel newTimerButtonPanel, JPanel timerPanel, JPanel timerButtonPanel) {

        frame.remove(newTimerPanel);
        frame.remove(newTimerButtonPanel);

        JPanel savePanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JLabel back = new JLabel("Zurück", SwingConstants.CENTER);
        back.setFont(tempFont);

        JLabel save = new JLabel("Speichern", SwingConstants.CENTER);
        save.setFont(tempFont);

        // lock saving when a Timer entry for this time already exists -> gray out "Speichern" and disable its action
        boolean timeAlreadyTaken = timerEntryExistsForTime(newTimerHour, newTimerMinute);
        if (timeAlreadyTaken) {
            save.setForeground(Color.GRAY);
        }

        JLabel abort = new JLabel("Abbrechen", SwingConstants.CENTER);
        abort.setFont(tempFont);

        //timer for text blinking when saving new Timer
        Timer saveSelectionBlinkTimer = createSaveSelectionBlinkTimer(back, save, abort);
        saveSelectionBlinkTimer.start();

        savePanel.add(back);
        savePanel.add(save);
        savePanel.add(abort);

        JPanel saveTimerButtonPanel = new JPanel(new GridLayout(1, 5, 10, 10));

        // ok button for selected option
        JButton saveTimerBackButton = new JButton("↵");
        saveTimerBackButton.setFont(buttonFont);
        saveTimerBackButton.addActionListener(d -> {

            if (saveSelection == 1) {
                frame.remove(saveTimerButtonPanel);
                frame.remove(savePanel);
                frame.add(timerPanel);
                frame.add(timerButtonPanel);
                newTimerHour = 0;
                newTimerMinute = 0;
                newTimerTargetTemperature = 0.0;
            } else if (saveSelection == 2) {
                if (!timeAlreadyTaken) {
                    componentsManager.addTimerEntry(newTimerHour, newTimerMinute, newTimerTargetTemperature);
                    frame.remove(saveTimerButtonPanel);
                    frame.remove(savePanel);
                    frame.add(timerPanel);
                    frame.add(timerButtonPanel);
                    newTimerHour = 0;
                    newTimerMinute = 0;
                    newTimerTargetTemperature = 0.0;
                    saveSelection = 1;
                }
                // when a Timer for this time already exists: saving is locked, do nothing
            } else {
                frame.remove(saveTimerButtonPanel);
                frame.remove(savePanel);
                frame.add(newTimerPanel);
                frame.add(newTimerButtonPanel);
                saveSelection = 1;
            }

            frame.revalidate();
            frame.repaint();
        });

        // navigate left through options
        JButton saveTimerLeftButton = new JButton("←");
        saveTimerLeftButton.setFont(buttonFont);
        saveTimerLeftButton.addActionListener(d -> {
            saveSelection--;
            if (saveSelection == 0) {
                saveSelection = MAX_SAVE_SELECTION;
            }
            saveSelectionBlinkTimer.restart();
        });

        // navigate right through options
        JButton saveTimerRightButton = new JButton("→");
        saveTimerRightButton.setFont(buttonFont);
        saveTimerRightButton.addActionListener(d -> {
            saveSelection++;
            if (saveSelection > MAX_SAVE_SELECTION) {
                saveSelection = 1;
            }
            saveSelectionBlinkTimer.restart();
        });

        // no action -> disabled
        JButton saveTimerPlusButton = new JButton("+");
        saveTimerPlusButton.setFont(buttonFont);
        saveTimerPlusButton.setEnabled(false);

        // no action -> disabled
        JButton saveTimerMinusButton = new JButton("-");
        saveTimerMinusButton.setFont(buttonFont);
        saveTimerMinusButton.setEnabled(false);

        saveTimerButtonPanel.add(saveTimerBackButton);
        saveTimerButtonPanel.add(saveTimerLeftButton);
        saveTimerButtonPanel.add(saveTimerRightButton);
        saveTimerButtonPanel.add(saveTimerPlusButton);
        saveTimerButtonPanel.add(saveTimerMinusButton);

        frame.add(savePanel);
        frame.add(saveTimerButtonPanel);

        frame.revalidate();
        frame.repaint();
    }

    // Screen 4 end
    //==================================================================================================================================


    //==================================================================================================================================
    // Timers

    //timer for text blinking when changing target temp
    private Timer createBlinkTimer() {
        return new Timer(800, e -> {
            if (currentTemperature.getText().isEmpty()) {
                currentTemperature.setText(String.valueOf(targetTemperature));
            } else {
                currentTemperature.setText("");
            }
        });
    }

    private Timer createStopBlinkTimer() {
        Timer timer = new Timer(3000, e -> {
            blinkTimer.stop();
            changing = false;
        });
        timer.setRepeats(false);
        return timer;
    }

    //timer that switches back to current room temperature
    private Timer createRevertToRoomTempTimer() {
        Timer timer = new Timer(3000, e -> currentTemperature.setText(String.valueOf(componentsManager.getCurrentRoomTemperature())));
        timer.setRepeats(false);
        return timer;
    }

    //timer for text blinking when changing something
    private Timer createErrorOpenBlinkTimer() {
        return new Timer(800, e -> {
            if (currentTemperature.getText().isEmpty()) {
                currentTemperature.setText(String.valueOf(componentsManager.getCurrentRoomTemperature()));
            } else {
                currentTemperature.setText("");
            }
            if (componentsManager.isOverheated()){

            }
        });
    }

    private Timer createOverheatedTimer() {
        return new Timer(800, e -> {
            if (low.getText().isEmpty()) {
                low.setText(levelActiveIndicator);
            } else {
                low.setText("");
            }
            if (middle.getText().isEmpty()) {
                middle.setText(levelActiveIndicator);
            } else {
                middle.setText("");
            }
            if (high.getText().isEmpty()) {
                high.setText(levelActiveIndicator);
            } else {
                high.setText("");
            }
        });
    }

    // timer for updating all information
    private Timer createUpdateTimer() {
        return new Timer(1000, e -> {

            // when on enable all buttons and show everything on display
            if (on) {

                if (componentsManager.isWindowOpen()) {
                    errorBlinkTimer.start();
                } else if (componentsManager.isOverheated()) {
                    low.setText(levelActiveIndicator);
                    middle.setText(levelActiveIndicator);
                    high.setText(levelActiveIndicator);
                    overheatedTimer.start();
                    errorBlinkTimer.start();
                } else {
                    errorBlinkTimer.stop();
                    overheatedTimer.stop();
                    timerButton.setEnabled(true);
                    energyButton.setEnabled(true);
                    plusButton.setEnabled(true);
                    minusButton.setEnabled(true);

                    if (celcius.getText().isEmpty()) {
                        celcius.setText("°C");
                    }
                    if (!changing) {
                        currentTemperature.setText(String.valueOf(componentsManager.getCurrentRoomTemperature()));
                    }
                    if (componentsManager.isEnergySavingActivated()) {
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
                    } else if (heaterLevel == HeaterLevel.HIGH) {
                        low.setText(levelActiveIndicator);
                        middle.setText(levelActiveIndicator);
                        high.setText(levelActiveIndicator);
                    } else {
                        low.setText(levelInactiveIndicator);
                        middle.setText(levelInactiveIndicator);
                        high.setText(levelInactiveIndicator);
                    }
                }
            } else { //when off disable all button except on/off and show nothing on screen
                timerButton.setEnabled(false);
                energyButton.setEnabled(false);
                minusButton.setEnabled(false);
                plusButton.setEnabled(false);

                currentTemperature.setText("");
                celcius.setText("");
                energyButton.setForeground(new Color(Color.BLACK.getRGB()));
                low.setText(levelInactiveIndicator);
                middle.setText(levelInactiveIndicator);
                high.setText(levelInactiveIndicator);
            }
        });
    }

    //timer for text blinking when changing something
    private Timer createSelectionBlinkTimer(JLabel newTime, JLabel newTemp) {
        return new Timer(800, i -> {
            if (newTimerSelection == 1) {
                newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
                if (newTime.getText().equals(String.format("    :%02d Uhr", newTimerMinute))) {
                    newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                } else {
                    newTime.setText(String.format("    :%02d Uhr", newTimerMinute));
                }
            } else if (newTimerSelection == 2) {
                newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
                if (newTime.getText().equals(String.format("%02d:      Uhr", newTimerHour))) {
                    newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                } else {
                    newTime.setText((String.format("%02d:      Uhr", newTimerHour)));
                }
            } else {
                newTime.setText(String.format("%02d:%02d Uhr", newTimerHour, newTimerMinute));
                if (newTemp.getText().equals("         °C")) {
                    newTemp.setText(String.format("%.2f °C", newTimerTargetTemperature));
                } else {
                    newTemp.setText("         °C");
                }
            }
        });
    }

    //timer for text blinking when saving new Timer
    private Timer createSaveSelectionBlinkTimer(JLabel back, JLabel save, JLabel abort) {
        return new Timer(800, f -> {
            if (saveSelection == 1) { //back -> Screen 2
                save.setText("Speichern");
                abort.setText("Abbrechen");
                if (back.getText().isEmpty()) {
                    back.setText("Zurück");
                } else {
                    back.setText("");
                }
            } else if (saveSelection == 2) { //save and go back -> Screen 2
                abort.setText("Abbrechen");
                back.setText("Zurück");
                if (save.getText().isEmpty()) {
                    save.setText("Speichern");
                } else {
                    save.setText("");
                }
            } else { //abort -> Screen 3
                back.setText("Zurück");
                save.setText("Speichern");
                if (abort.getText().isEmpty()) {
                    abort.setText("Abbrechen");
                } else {
                    abort.setText("");
                }
            }
        });
    }

    // Timers end
    //==================================================================================================================================


    //==================================================================================================================================
    // This part is only for simulation and can be removed later without any consequences

    private void buildSimulationFrame() {
        simulateFrame = new JFrame();
        simulateFrame.setTitle("Simulation");
        simulateFrame.setSize(800, 600);
        simulateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulateFrame.setLayout(new GridLayout(3, 1, 10, 10));
        simulateFrame.setResizable(false);

        openWindowButton = new JButton("Open Window");
        openWindowButton.setFont(buttonFont);
        openWindowButton.addActionListener(e -> {
            componentsManager.updateWindow();
        });

        timeLabel = new JLabel(componentsManager.getTime() + " Uhr", SwingConstants.CENTER);
        timeLabel.setFont(buttonFont);

        statusLabel = new JLabel(componentsManager.getHeaterStatus().getMessage(), SwingConstants.CENTER);
        statusLabel.setFont(textFont);

        simulateFrame.add(openWindowButton);
        simulateFrame.add(timeLabel);
        simulateFrame.add(statusLabel);

        simUpdateTimer = createSimUpdateTimer();
        simUpdateTimer.start();

        simulateFrame.setVisible(true);
    }

    // timer for updating simulated time
    private Timer createSimUpdateTimer(){
        return new Timer(1000, f -> {
            timeLabel.setText(componentsManager.getTime() +  " Uhr");
            statusLabel.setText(componentsManager.getHeaterStatus().getMessage());
            if (componentsManager.isWindowOpen()){
                openWindowButton.setText("Close Window");
            } else {
                openWindowButton.setText("Open Window");
            }
        });
    }
}