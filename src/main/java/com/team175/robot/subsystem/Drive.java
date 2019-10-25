package com.team175.robot.subsystem;

import com.team175.robot.util.DriveHelper;
import com.team175.robot.util.PeriodicTask;
import com.team175.robot.util.model.motorcontroller.AldrinTalonSRX;
import com.team175.robot.util.model.motorcontroller.MCControlMode;
import com.team175.robot.util.model.motorcontroller.MotorController;

/**
 * Drive represents the drivetrain of the robot. It is composed of 4 cim motors (controlled with 4 Talon SRXs) and a
 * Pigeon gyro.
 */
public class Drive extends Subsystem {

    private final MotorController leftMaster, leftSlave, rightMaster, rightSlave;
    private final DriveHelper driveHelper;

    private static Drive instance;

    private static final int LEFT_MASTER_PORT = 0;
    private static final int LEFT_SLAVE_PORT = 1;
    private static final int RIGHT_MASTER_PORT = 2;
    private static final int RIGHT_SLAVE_PORT = 3;

    private enum State {

    }

    private Drive() {
        leftMaster = new AldrinTalonSRX(LEFT_MASTER_PORT);
        leftSlave = new AldrinTalonSRX(LEFT_SLAVE_PORT);
        rightMaster = new AldrinTalonSRX(RIGHT_MASTER_PORT);
        rightSlave = new AldrinTalonSRX(RIGHT_SLAVE_PORT);
        driveHelper = new DriveHelper(leftMaster, rightMaster);
    }

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }

        return instance;
    }

    private void configureTelemetry() {
        telemetry.put("LeftPosition", leftMaster::getPosition);
        telemetry.put("RightPosition", rightMaster::getPosition);
        telemetry.put("LeftVelocity", leftMaster::getVelocity);
        telemetry.put("RightVelocity", rightMaster::getVelocity);
    }

    private void configureStateMachine() {
        stateMachine = new PeriodicTask() {
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
    }

    public void setOpenLoop(double leftDemand, double rightDemand) {
        leftMaster.set(MCControlMode.POSITION, leftDemand);
        rightMaster.set(MCControlMode.POSITION, leftDemand);
    }

    public void arcadeDrive(double throttle, double turn) {
        driveHelper.arcadeDrive(throttle, turn);
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
