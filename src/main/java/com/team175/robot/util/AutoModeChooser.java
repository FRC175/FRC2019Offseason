package com.team175.robot.util;

import com.team175.robot.command.Autopilot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * AutoModeChooser
 */
public final class AutoModeChooser {

    private final SendableChooser<Command> chooser;

    private Command mode;

    private static AutoModeChooser instance;

    private AutoModeChooser() {
        chooser = new SendableChooser<>();
        chooser.setDefaultOption("No Auto", null);
        chooser.addOption("Autopilot", new Autopilot());
    }

    public static AutoModeChooser getInstance() {
        if (instance == null) {
            instance = new AutoModeChooser();
        }

        return instance;
    }

    private boolean isAutoModeSelected() {
        return mode != null;
    }

    public void outputToDashboard() {
        SmartDashboard.putData("Auto Mode Chooser", chooser);
    }

    public void updateFromDashboard() {
        mode = chooser.getSelected();
    }

    public void start() {
        if (isAutoModeSelected()) {
            mode.start();
        }
    }

    public void stop() {
        if (isAutoModeSelected()) {
            mode.cancel();
            mode = null;
        }
    }

}
