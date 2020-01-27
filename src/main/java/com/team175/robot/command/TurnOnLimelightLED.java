package com.team175.robot.command;

import com.team175.robot.subsystem.Limelight;

/**
 * TurnOnLimelightLED
 */
public class TurnOnLimelightLED extends CommandBase {

    private final Limelight limelight;

    public TurnOnLimelightLED(Limelight limelight) {
        this.limelight = limelight;
    }

    @Override
    public void initialize() {
        limelight.turnOnLED();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
