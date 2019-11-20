package com.team175.robot.command;

import com.team175.robot.subsystem.Limelight;

/**
 * ToggleLimelightCameraMode toggles the camera mode on the Limelight between the driver and vision tracking view.
 */
public class ToggleLimelightCameraMode extends CommandBase {

    private final Limelight limelight;

    private boolean isTrackingMode;

    public ToggleLimelightCameraMode(Limelight limelight) {
        this.limelight = limelight;
        isTrackingMode = true;
        addRequirements(this.limelight);
    }

    @Override
    public void initialize() {
        // Switch mode when pressed
        isTrackingMode = !isTrackingMode;
        limelight.setCameraMode(isTrackingMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
