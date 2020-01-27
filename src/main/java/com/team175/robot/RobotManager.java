package com.team175.robot;

import com.team175.robot.command.*;
import com.team175.robot.subsystem.Drive;
import com.team175.robot.subsystem.SubsystemBase;
import com.team175.robot.subsystem.Limelight;
import com.team175.robot.model.AldrinXboxController;
import com.team175.robot.model.XboxButton;
import edu.wpi.first.wpilibj.GenericHID;

import java.util.List;

import static com.team175.robot.model.AldrinXboxController.*;

/**
 * RobotManager
 */
public final class RobotManager {

    private final Drive drive;
    private final Limelight limelight;
    private final List<SubsystemBase> subsystems;
    private final AldrinXboxController controller;

    private static RobotManager instance;

    private static final int CONTROLLER_PORT = 0;

    private RobotManager() {
        drive = Drive.getInstance();
        limelight = Limelight.getInstance();
        subsystems = List.of(drive, limelight);
        controller = new AldrinXboxController(CONTROLLER_PORT);

        configureDefaultCommands();
        configureButtons();
    }

    public static RobotManager getInstance() {
        if (instance == null) {
            instance = new RobotManager();
        }

        return instance;
    }

    private void configureDefaultCommands() {
        drive.setDefaultCommand(
                new CheesyDrive(
                        drive,
                        // Operate throttle with RT and LT like in games (GTA drive)
                        () -> controller.getTriggerAxis(GenericHID.Hand.kRight) - controller.getTriggerAxis(GenericHID.Hand.kLeft),
                        () -> controller.getX(GenericHID.Hand.kLeft),
                        () -> controller.getBumper(Hand.kRight)
                )
        );
        limelight.setDefaultCommand(
                // Driver camera mode
                new ConfigLimelightCameraMode(limelight, true)
        );
    }

    private void configureButtons() {
        new XboxButton(controller, DPad.UP).whenPressed(
                // Turn on Limelight LED
                new ConfigLimelightLED(limelight, true)
        );
        new XboxButton(controller, DPad.DOWN).whenPressed(
                // Turn off Limelight LED
                new ConfigLimelightLED(limelight, false)
        );
        new XboxButton(controller, DPad.LEFT).whenPressed(
                new BlinkLimelightLED(limelight)
        );
        new XboxButton(controller, DPad.RIGHT).whenPressed(
                new ConfigLimelightCameraMode(limelight,false)
        );
        new XboxButton(controller, Button.X).whenPressed(
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
