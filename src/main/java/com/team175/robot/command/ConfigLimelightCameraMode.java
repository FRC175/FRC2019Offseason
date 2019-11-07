package com.team175.robot.command;

import com.team175.robot.subsystem.Limelight;

/**
 * ConfigLimelightCameraMode switches the camera mode on the Limelight to the driver view or vision tracking view.
 */
public final class ConfigLimelightCameraMode extends CommandBase {

    private final Limelight limelight;
    private final boolean isTrackingMode;

    public ConfigLimelightCameraMode(Limelight limelight, boolean isTrackingMode) {
        this.limelight = limelight;
        this.isTrackingMode = isTrackingMode;
        addRequirements(this.limelight);
    }

    @Override
    public void initialize() {
        limelight.setCameraMode(isTrackingMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
