package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;
import com.team175.robot.subsystem.Limelight;

import java.util.function.DoubleSupplier;

/**
 * RotateToVisionTarget
 */
public class RotateToVisionTarget extends CommandBase {

    private final Drive drive;
    private final Limelight limelight;
    private final DoubleSupplier throttle;

    public RotateToVisionTarget(Drive drive, Limelight limelight, DoubleSupplier throttle) {
        this.drive = drive;
        this.limelight = limelight;
        this.throttle = throttle;
        addRequirements(this.drive, this.limelight);
    }

    @Override
    public void initialize() {
        limelight.setCameraMode(true);
        limelight.setLED(true);
        drive.setOpenLoop(0, 0);
    }

    @Override
    public void execute() {
        limelight.calculateTargetDrive();
        drive.arcadeDrive(throttle.getAsDouble(), limelight.getTurn());
    }

    @Override
    public void end(boolean interrupted) {
        limelight.setCameraMode(false);
        limelight.defaultLED();
    }

    @Override
    public boolean isFinished() {
        return limelight.isAtDesiredRotation();
    }

}
