package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {

    public ArcadeDrive(Drive drive) {
        // requires(drive);
    }

    @Override
    protected void execute() {
        Drive.getInstance().arcadeDrive(0, 0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Drive.getInstance().setOpenLoop(0,0);
    }

}
