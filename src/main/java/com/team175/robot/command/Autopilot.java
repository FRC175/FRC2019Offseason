package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autopilot is the finest autonomous mode in existence.
 */
public final class Autopilot extends CommandGroup {

    public Autopilot() {
        addSequential(new DriveTrajectory(Drive.getInstance(), "EPIC"));
    }

}
