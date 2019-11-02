package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;

import java.util.function.DoubleSupplier;

/**
 * ArcadeDrive
 */
public class ArcadeDrive extends CommandBase {

    private final Drive drive;
    private final DoubleSupplier throttle;
    private final DoubleSupplier turn;

    public ArcadeDrive(Drive drive, DoubleSupplier throttle, DoubleSupplier turn) {
        this.drive = drive;
        this.throttle = throttle;
        this.turn = turn;
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
        drive.arcadeDrive(throttle.getAsDouble(), turn.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drive.setOpenLoop(0,0);
    }

}
