package com.team175.robot;

import com.team175.robot.subsystem.Drive;
import com.team175.robot.util.PeriodicTask;
import com.team175.robot.util.model.control.DriverStick;
import com.team175.robot.util.model.control.OperatorStick;

/**
 * Manages all the controls of the robot and their respective actions.
 */
public final class ControlManager implements PeriodicTask {

    private final DriverStick driverStick;
    private final OperatorStick operatorStick;
    private final Drive drive;

    private static ControlManager instance;

    public static ControlManager getInstance() {
        if (instance == null) {
            instance = new ControlManager();
        }

        return instance;
    }

    private ControlManager() {
        driverStick = new DriverStick(0);
        operatorStick = new OperatorStick(1);
        drive = Drive.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void periodic() {
        // Driver Controls
        if (driverStick.getLateralDriveTrigger()) {

        } else {

        }

    }

    @Override
    public void stop() {

    }

}
