package com.team175.robot.subsystem;

import com.team175.robot.util.DriveHelper;
import com.team175.robot.util.PeriodicTask;
import com.team175.robot.util.model.motorcontroller.AldrinTalonSRX;
import com.team175.robot.util.model.motorcontroller.MCControlMode;
import com.team175.robot.util.model.motorcontroller.MotorController;

import java.util.concurrent.TimeUnit;

/**
 * Drive represents the drivetrain of the robot. It is composed of 4 cim motors (controlled with 4 Talon SRXs) and a
 * Pigeon gyro.
 */
public class Drive extends Subsystem {

    private State driveState;

    private final MotorController leftMaster, leftSlave, rightMaster, rightSlave;
    private final DriveHelper driveHelper;

    private static Drive instance;

    private static final int LEFT_MASTER_PORT = 0;
    private static final int LEFT_SLAVE_PORT = 1;
    private static final int RIGHT_MASTER_PORT = 2;
    private static final int RIGHT_SLAVE_PORT = 3;

    private enum State {
        OPEN_LOOP,
        MOTION_MAGIC,
        DISABLED
    }

    private Drive() {
        driveState = State.OPEN_LOOP;
        leftMaster = new AldrinTalonSRX(LEFT_MASTER_PORT);
        leftSlave = new AldrinTalonSRX(LEFT_SLAVE_PORT);
        rightMaster = new AldrinTalonSRX(RIGHT_MASTER_PORT);
        rightSlave = new AldrinTalonSRX(RIGHT_SLAVE_PORT);
        driveHelper = new DriveHelper(leftMaster, rightMaster);

        configureTelemetry();
        configureStateMachine();
    }

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }

        return instance;
    }

    private void configureTelemetry() {
        telemetry.putAll(leftMaster.getTelemetry());
        telemetry.putAll(rightMaster.getTelemetry());
    }

    private void configureStateMachine() {
        stateMachine = new PeriodicTask() {
            @Override
            public void start() {
            }

            @Override
            public void periodic() {
                switch (driveState) {
                    case OPEN_LOOP:
                    case MOTION_MAGIC:
                    case DISABLED:
                }
            }

            @Override
            public void stop() {
            }
        };
    }

    public void setOpenLoop(double leftDemand, double rightDemand) {
        long startTime = System.nanoTime();
        leftMaster.set(MCControlMode.PERCENT_OUT, leftDemand);
        rightMaster.set(MCControlMode.PERCENT_OUT, rightDemand);
        long stopTime = System.nanoTime();
        logger.debug("setOpenLoop() took {} ms", TimeUnit.NANOSECONDS.toMillis(stopTime - startTime));
    }

    public void arcadeDrive(double throttle, double turn) {
        long startTime = System.nanoTime();
        driveHelper.arcadeDrive(throttle, turn);
        long stopTime = System.nanoTime();
        logger.debug("setOpenLoop() took {} ms", TimeUnit.NANOSECONDS.toMillis(stopTime - startTime));
    }

    public void cheesyDrive(double throttle, double turn, boolean isQuickTurn) {
        long startTime = System.nanoTime();
        driveHelper.cheesyDrive(throttle, turn, isQuickTurn, true);
        long stopTime = System.nanoTime();
        logger.debug("setOpenLoop() took {} ms", TimeUnit.NANOSECONDS.toMillis(stopTime - startTime));
    }

    @Override
    public void resetSensors() {

    }

    @Override
    public boolean checkIntegrity() {
        return false;
    }

}
