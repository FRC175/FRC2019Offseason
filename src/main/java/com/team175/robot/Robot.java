package com.team175.robot;

import edu.wpi.first.wpilibj.TimedRobot;

/**
 * Robot is the hub where all the different components of the robot come together to make one cohesive masterpiece.
 */
public final class Robot extends TimedRobot {

    private ControlManager controlManager;

    @Override
    public void robotInit() {
        controlManager = ControlManager.getInstance();
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    public static double getDefaultPeriod() {
        return kDefaultPeriod;
    }

}
