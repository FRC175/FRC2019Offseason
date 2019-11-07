package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;

import java.io.IOException;

/**
 * DriveTrajectory
 */
public final class DriveTrajectory extends CommandBase {

    private final Drive drive;

    private Trajectory leftTrajectory, rightTrajectory;
    private boolean isInstantiationSuccessful;

    public DriveTrajectory(Drive drive, String pathName) {
        this.drive = drive;
        try {
            leftTrajectory = PathfinderFRC.getTrajectory(pathName + ".left");
            rightTrajectory = PathfinderFRC.getTrajectory(pathName + ".right");
            isInstantiationSuccessful = true;
        } catch (IOException e) {
            logger.error("FATAL ERROR! TRAJECTORY FAILED TO LOAD!", e);
            isInstantiationSuccessful = false;
        }
        addRequirements(this.drive);
    }

    @Override
    public void initialize() {
        if (isInstantiationSuccessful) {
            drive.setOpenLoop(0, 0);
            drive.setBrakeMode(true);
            drive.setTrajectory(leftTrajectory, rightTrajectory);
        }
    }

    @Override
    public void execute() {
        if (isInstantiationSuccessful) {
            drive.followTrajectory();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (isInstantiationSuccessful) {
            drive.setOpenLoop(0, 0);
            drive.setBrakeMode(false);
        }
    }

    @Override
    public boolean isFinished() {
        return !isInstantiationSuccessful || drive.isTrajectoryFinished();
    }

}
