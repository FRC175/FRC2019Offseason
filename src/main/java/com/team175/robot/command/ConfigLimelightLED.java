package com.team175.robot.command;

import com.team175.robot.subsystem.Limelight;

/**
 * ConfigLimelightLED turns on or off the LED on the limelight.
 */
public final class ConfigLimelightLED extends CommandBase {

    private final Limelight limelight;
    private final boolean enable;

    public ConfigLimelightLED(Limelight limelight, boolean enable) {
        this.limelight = limelight;
        this.enable = enable;
        addRequirements(this.limelight);
    }

    @Override
    public void initialize() {
        limelight.setLED(enable);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
