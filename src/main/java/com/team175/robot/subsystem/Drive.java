package com.team175.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.team175.robot.util.DriveHelper;
import com.team175.robot.model.Gains;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 * Drive represents the drivetrain of the robot. It is composed of 4 cim motors (controlled with 4 Talon SRXs) and a
 * Pigeon gyro.
 */
public final class Drive extends SubsystemBase {

    private final WPI_TalonSRX leftMaster, leftSlave, rightMaster, rightSlave;
    private final DriveHelper driveHelper;
    private final DifferentialDrive fatDriveHelper;
    private final PigeonIMU pigeon;
    private final EncoderFollower leftTrajectoryFollower, rightTrajectoryFollower;

    private int leftSetpoint, rightSetpoint;

    private static final int LEFT_MASTER_PORT = 3;
    private static final int LEFT_SLAVE_PORT = 1;
    private static final int RIGHT_MASTER_PORT = 2;
    private static final int RIGHT_SLAVE_PORT = 4;
    private static final int PIGEON_PORT = 5; // TODO: Fix ID (could even be attached to a Talon SRX)
    private static final int S_CURVE_STRENGTH = 4; // Half smoothing
    private static final int ENCODER_COUNTS_PER_REVOLUTION = 1024; // 4096
    private static final double WHEEL_DIAMETER = 0.33; // ft
    private static final double MAX_VELOCITY = 12; // fps
    private static final double K_V = 1.0 / MAX_VELOCITY;
    private static final Gains GAINS = new Gains(0.75, 0, 0, 0, 0, 0);

    private static Drive instance;

    private Drive() {
        leftMaster = new WPI_TalonSRX(LEFT_MASTER_PORT);
        leftSlave = new WPI_TalonSRX(LEFT_SLAVE_PORT);
        rightMaster = new WPI_TalonSRX(RIGHT_MASTER_PORT);
        rightSlave = new WPI_TalonSRX(RIGHT_SLAVE_PORT);
        pigeon = new PigeonIMU(PIGEON_PORT);
        leftTrajectoryFollower = new EncoderFollower();
        rightTrajectoryFollower = new EncoderFollower();

        configureTalons();

        driveHelper = new DriveHelper(leftMaster, rightMaster);
        fatDriveHelper = new DifferentialDrive(
                new SpeedControllerGroup(leftMaster, leftSlave),
                new SpeedControllerGroup(rightMaster, rightSlave)
        );

        configureTelemetry();
    }

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }

        return instance;
    }

    private void configureTalons() {
        leftMaster.configFactoryDefault();
        leftMaster.setNeutralMode(NeutralMode.Coast);
        rightMaster.setInverted(false);
        leftMaster.configMotionSCurveStrength(S_CURVE_STRENGTH);
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        leftMaster.config_kP(0, GAINS.getKp());
        leftMaster.config_kI(0, GAINS.getKi());
        leftMaster.config_kD(0, GAINS.getKd());
        leftMaster.config_kF(0, GAINS.getKf());
        leftMaster.configMotionAcceleration(GAINS.getAcceleration());
        leftMaster.configMotionCruiseVelocity(GAINS.getCruiseVelocity());
        leftTrajectoryFollower.configurePIDVA(GAINS.getKp(), GAINS.getKi(), GAINS.getKd(), K_V, 0);

        leftSlave.configFactoryDefault();
        leftSlave.setNeutralMode(NeutralMode.Coast);
        leftSlave.follow(leftMaster);
        leftSlave.setInverted(InvertType.FollowMaster);

        rightMaster.configFactoryDefault();
        rightMaster.setNeutralMode(NeutralMode.Coast);
        rightMaster.setInverted(true);
        rightMaster.configMotionSCurveStrength(S_CURVE_STRENGTH);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        rightMaster.config_kP(0, GAINS.getKp());
        rightMaster.config_kI(0, GAINS.getKi());
        rightMaster.config_kD(0, GAINS.getKd());
        rightMaster.config_kF(0, GAINS.getKf());
        rightMaster.configMotionAcceleration(GAINS.getAcceleration());
        rightMaster.configMotionCruiseVelocity(GAINS.getCruiseVelocity());
        rightTrajectoryFollower.configurePIDVA(GAINS.getKp(), GAINS.getKi(), GAINS.getKd(), K_V, 0);

        rightSlave.configFactoryDefault();
        rightSlave.setNeutralMode(NeutralMode.Coast);
        rightSlave.follow(rightMaster);
        rightSlave.setInverted(InvertType.FollowMaster);
    }

    private void configureTelemetry() {
        telemetry.put("LeftPosition", this::getLeftPosition);
        telemetry.put("LeftSetpoint", () -> leftSetpoint);
        telemetry.put("LeftVelocity", this::getLeftVelocity);
        telemetry.put("LeftSetpointError", this::getLeftSetpointError);
        telemetry.put("RightPosition", this::getRightPosition);
        telemetry.put("RightSetpoint", () -> rightSetpoint);
        telemetry.put("RightVelocity", this::getRightVelocity);
        telemetry.put("RightSetpointError", this::getRightSetpointError);
        telemetry.put("Gains", GAINS::toArray);
    }

    public void setOpenLoop(double leftDemand, double rightDemand) {
        leftMaster.set(ControlMode.PercentOutput, leftDemand);
        rightMaster.set(ControlMode.PercentOutput, rightDemand);
    }

    public void arcadeDrive(double throttle, double turn) {
        // fatDriveHelper.arcadeDrive(throttle, turn);
        driveHelper.arcadeDrive(throttle, turn);
    }

    public void cheesyDrive(double throttle, double turn, boolean isQuickTurn) {
        // fatDriveHelper.curvatureDrive(throttle, turn, isQuickTurn);
        driveHelper.cheesyDrive(throttle, turn, isQuickTurn, false);
    }

    public void setBrakeMode(boolean enable) {
        leftMaster.setNeutralMode(enable ? NeutralMode.Brake : NeutralMode.Coast);
        rightMaster.setNeutralMode(enable ? NeutralMode.Brake : NeutralMode.Coast);
    }

    public void setLeftPosition(int position) {
        leftSetpoint = position;
        logger.debug("Setting left position to {}.", leftSetpoint);
        logger.debug("Current left position: {}", getLeftPosition());
        rightMaster.set(ControlMode.MotionMagic, leftSetpoint);
    }

    public void setLeftVelocity(int velocity) {
        logger.debug("Setting left velocity to {}.", rightSetpoint);
        logger.debug("Current left velocity: {}", getRightPosition());
        leftMaster.set(ControlMode.Velocity, velocity);
    }

    public void setRightPosition(int position) {
        rightSetpoint = position;
        logger.debug("Setting right position to {}.", rightSetpoint);
        logger.debug("Current right position: {}", getRightPosition());
        leftMaster.set(ControlMode.MotionMagic, rightSetpoint);
    }

    public void setRightVelocity(int velocity) {
        logger.debug("Setting right velocity to {}.", rightSetpoint);
        logger.debug("Current right velocity: {}", getRightPosition());
        leftMaster.set(ControlMode.Velocity, velocity);
    }

    public void setTrajectory(Trajectory leftTrajectory, Trajectory rightTrajectory) {
        leftTrajectoryFollower.setTrajectory(leftTrajectory);
        leftTrajectoryFollower.configureEncoder(getLeftPosition(), ENCODER_COUNTS_PER_REVOLUTION, WHEEL_DIAMETER);
        rightTrajectoryFollower.setTrajectory(rightTrajectory);
        rightTrajectoryFollower.configureEncoder(getRightPosition(), ENCODER_COUNTS_PER_REVOLUTION, WHEEL_DIAMETER);
    }

    public void followTrajectory() {
        if (pigeon.getState() == PigeonIMU.PigeonState.Ready) {
            double leftDemand = leftTrajectoryFollower.calculate(getLeftPosition());
            double rightDemand = rightTrajectoryFollower.calculate(getRightPosition());
            // TODO: Check headingDelta (Pigeon's yaw is in 100,000s)
            // headingDelta = desiredHeading - gyroHeading
            double headingDelta = Pathfinder.boundHalfDegrees(Pathfinder.r2d(leftTrajectoryFollower.getHeading()) - (-getHeading()));
            // double turn = headingDelta / 600; // Magic 254 constant
            // From Pathfinder documentation
            double turn = 0.8 * (-1.0 / 80.0) * headingDelta;

            setOpenLoop(leftDemand + turn, rightDemand - turn);
        }
    }

    public int getLeftPosition() {
        return leftMaster.getSelectedSensorPosition();
    }

    public int getLeftVelocity() {
        return leftMaster.getSelectedSensorVelocity();
    }

    public int getLeftSetpointError() {
        // return leftMaster.getClosedLoopError();
        return leftSetpoint - getLeftPosition();
    }

    public int getRightPosition() {
        return rightMaster.getSelectedSensorPosition();
    }

    public int getRightVelocity() {
        return rightMaster.getSelectedSensorVelocity();
    }

    public int getRightSetpointError() {
        // return rightMaster.getClosedLoopError();
        return rightSetpoint - getRightPosition();
    }

    public double getHeading() {
        return pigeon.getFusedHeading();
    }

    public boolean isTrajectoryFinished() {
        return leftTrajectoryFollower.isFinished() && rightTrajectoryFollower.isFinished();
    }

    @Override
    public void resetSensors() {
        leftMaster.setSelectedSensorPosition(0);
        rightMaster.setSelectedSensorPosition(0);
        pigeon.setYaw(0);
    }

    @Override
    public boolean checkIntegrity() {
        return false;
    }

}
