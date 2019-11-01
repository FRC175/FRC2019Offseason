package com.team175.robot.command;

import com.team175.robot.subsystem.Limelight;

/**
 * BlinkLimelightLED blinks the Limelight LED for one second.
 */
public class BlinkLimelightLED extends CommandBase {

    private final Limelight limelight;

    public BlinkLimelightLED(Limelight limelight) {
        // Run command for 1 sec
        super(1);
        this.limelight = limelight;
        addRequirements(this.limelight);
    }

    @Override
    public void initialize() {
        limelight.blinkLED();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end() {
        limelight.defaultLED();
    }

}
