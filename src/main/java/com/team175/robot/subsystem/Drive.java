package com.team175.robot.subsystem;

import com.team175.robot.util.DriveHelper;
import com.team175.robot.util.PeriodicTask;
import com.team175.robot.util.model.motorcontroller.MotorController;

public class Drive extends Subsystem {

    private final MotorController leftMaster, leftSlave, rightMaster, rightSlave;
    private final DriveHelper driveHelper;

    private static Drive instance;

    private final PeriodicTask stateMachine = new PeriodicTask() {

        @Override
        public void start() {

        }

        @Override
        public void periodic() {

        }

        @Override
        public void stop() {

        }

    };

    private enum State {

    }

    private Drive() {
        leftMaster = null;
        leftSlave = null;
        rightMaster = null;
        rightSlave = null;
        driveHelper = null;
    }

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }

        return instance;
    }

    public void setOpenLoop(double leftDemand, double rightDemand) {

    }

    public void arcadeDrive(double throttle, double turn) {

    }

    @Override
    public void resetSensors() {

    }

    @Override
    public boolean checkIntegrity() {
        return false;
    }

    @Override
    public PeriodicTask getStateMachine() {
        return stateMachine;
    }

}
