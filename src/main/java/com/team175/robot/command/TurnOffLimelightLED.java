package com.team175.robot.command;

import com.team175.robot.subsystem.Limelight;

/**
 * TurnOffLimelightLED
 */
public class TurnOffLimelightLED extends CommandBase {

    private final Limelight limelight;

    public TurnOffLimelightLED(Limelight limelight) {
        this.limelight = limelight;
    }

    @Override
    public void initialize() {
        limelight.turnOffLED();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
