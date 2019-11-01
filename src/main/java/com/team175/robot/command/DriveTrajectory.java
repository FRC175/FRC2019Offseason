package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;
import jaci.pathfinder.Trajectory;

import java.io.IOException;

/**
 * DriveTrajectory
 */
public class DriveTrajectory extends CommandBase {

    private final Drive drive;
    private final Trajectory leftTrajectory, rightTrajectory;

    public DriveTrajectory(Drive drive, Trajectory leftTrajectory, Trajectory rightTrajectory) throws IOException {
        this.drive = drive;
        this.leftTrajectory = leftTrajectory;
        this.rightTrajectory = rightTrajectory;
        addRequirements(this.drive);
    }

    @Override
    public void initialize() {
        drive.setOpenLoop(0, 0);
        drive.setBrakeMode(true);
        drive.setTrajectory(leftTrajectory, rightTrajectory);
    }

    @Override
    public void execute() {
        drive.followTrajectory();
    }

    @Override
    public boolean isFinished() {
        return drive.isTrajectoryFinished();
    }

    @Override
    public void end() {
        drive.setOpenLoop(0, 0);
        drive.setBrakeMode(false);
    }

}
