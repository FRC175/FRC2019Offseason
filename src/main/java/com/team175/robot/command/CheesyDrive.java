package com.team175.robot.command;

import com.team175.robot.subsystem.Drive;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/**
 * CheesyDrive
 */
public final class CheesyDrive extends CommandBase {

    private final Drive drive;
    private final DoubleSupplier throttle;
    private final DoubleSupplier turn;
    private final BooleanSupplier isQuickTurn;

    public CheesyDrive(Drive drive, DoubleSupplier throttle, DoubleSupplier turn, BooleanSupplier isQuickTurn) {
        this.drive = drive;
        this.throttle = throttle;
        this.turn = turn;
        this.isQuickTurn = isQuickTurn;
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
        drive.cheesyDrive(throttle.getAsDouble(), turn.getAsDouble(), isQuickTurn.getAsBoolean());
    }

    @Override
    public void end(boolean interrupted) {
        Drive.getInstance().setOpenLoop(0,0);
    }

}
