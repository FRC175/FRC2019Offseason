package com.team175.robot;

import com.team175.robot.command.*;
import com.team175.robot.subsystem.Drive;
import com.team175.robot.subsystem.SubsystemBase;
import com.team175.robot.subsystem.Limelight;
import com.team175.robot.util.model.AldrinXboxController;
import com.team175.robot.util.model.XboxButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import java.util.List;

/**
 * RobotManager
 */
public final class RobotManager {

    private final Drive drive;
    private final Limelight limelight;
    private final List<SubsystemBase> subsystems;
    // private final Joystick driverStick, operatorStick;
    private final XboxController controller;

    private static RobotManager instance;

    private static final int DRIVER_STICK_PORT = 0;
    private static final int OPERATOR_STICK_PORT = 1;
    private static final int CONTROLLER_PORT = 0;
    private static final double DRIVER_STICK_DEAD_ZONE = 0.1;
    private static final double OPERATOR_STICK_DEAD_ZONE = 0.05;

    private RobotManager() {
        drive = Drive.getInstance();
        limelight = Limelight.getInstance();
        subsystems = List.of(drive, limelight);
        // driverStick = new AldrinJoystick(DRIVER_STICK_PORT, DRIVER_STICK_DEAD_ZONE);
        // operatorStick = new AldrinJoystick(OPERATOR_STICK_PORT, OPERATOR_STICK_DEAD_ZONE);
        controller = new AldrinXboxController(CONTROLLER_PORT);
    }

    public static RobotManager getInstance() {
        if (instance == null) {
            instance = new RobotManager();
        }

        return instance;
    }

    private void configureDefaultCommands() {
        // TODO: Test GTA drive and consider replacing ArcadeDrive with CheesyDrive
        drive.setDefaultCommand(
                new ArcadeDrive(
                        drive,
                        // Operate throttle with RT and LT like in games (GTA drive)
                        () -> controller.getTriggerAxis(GenericHID.Hand.kRight) - controller.getTriggerAxis(GenericHID.Hand.kLeft),
                        () -> controller.getX(GenericHID.Hand.kLeft)
                )
        );
        limelight.setDefaultCommand(
                // Driver camera mode
                new ConfigLimelightCameraMode(limelight, false)
        );
    }

    private void configureButtons() {
        new XboxButton(controller, XboxButton.Button.A).whenPressed(
                // Turn off Limelight LED
                new ConfigLimelightLED(limelight, false)
        );
        new XboxButton(controller, XboxButton.Button.B).whenPressed(
                new BlinkLimelightLED(limelight)
        );
        new XboxButton(controller, XboxButton.Button.B).whenPressed(
                // Turn on Limelight LED
                new ConfigLimelightLED(limelight, true)
        );
        new XboxButton(controller, XboxButton.Button.X).whenPressed(
                new DriveToVisionTarget(drive, limelight)
        );
    }

    public void resetSensors() {
        subsystems.forEach(SubsystemBase::resetSensors);
    }

    public boolean checkIntegrity() {
        boolean isGood = true;
        for (SubsystemBase subsystem : subsystems) {
            isGood &= subsystem.checkIntegrity();
        }
        return isGood;
    }

}
