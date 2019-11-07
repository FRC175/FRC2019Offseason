package com.team175.robot;

import com.team175.robot.util.AutoModeChooser;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Robot is the hub where all the different components of the robot come together to make one cohesive masterpiece.
 */
public final class Robot extends TimedRobot {

    private RobotManager robotManager;
    private AutoModeChooser autoChooser;

    @Override
    public void robotInit() {
        robotManager = RobotManager.getInstance();
        /*autoChooser = AutoModeChooser.getInstance();
        autoChooser.outputToDashboard();*/
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        /*autoChooser.updateFromDashboard();
        autoChooser.start();*/
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        robotManager.checkIntegrity();
    }

    @Override
    public void testPeriodic() {
    }

    public static double getDefaultPeriod() {
        return kDefaultPeriod;
    }

}
