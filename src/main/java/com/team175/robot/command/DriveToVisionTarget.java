package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;
import com.team175.robot.subsystem.Limelight;

/**
 * DriveToTarget uses the limelight to continuously calculate the throttle and turn needed to reach the retro reflective
 * tape targets and feeds the outputs into the drive motors.
 */
public class DriveToVisionTarget extends CommandBase {

    private final Drive drive;
    private final Limelight limelight;

    private double throttle, turn;

    public DriveToVisionTarget(Drive drive, Limelight limelight) {
        this.drive = drive;
        this.limelight = limelight;
        throttle = 0;
        turn = 0;
        addRequirements(this.drive, this.limelight);
    }

    @Override
    public void initialize() {
        limelight.setCameraMode(true);
        drive.setOpenLoop(0, 0);
    }

    @Override
    public void execute() {
        throttle = limelight.calculateTargetDrive()[0];
        turn = limelight.calculateTargetDrive()[1];
        drive.arcadeDrive(throttle, turn);
    }

    @Override
    public boolean isFinished() {
        return limelight.isAtTarget();
    }

    @Override
    public void end() {
        limelight.setCameraMode(false);
    }

}
