package com.team175.robot.util.model.motorcontroller;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import java.util.Map;
import java.util.function.Supplier;

/**
 * MotorController is the base for a motor controller. It is intended to abstract control of motor controllers from
 * different vendors (e.g., Talon SRX, Victor SPX, and SPARK MAX).
 *
 * TODO: // Indicate methods that cannot be abstracted due to lack of features in one implementation
 */
public interface MotorController {

    void set(MCControlMode mode, double demand);

    void set(MCControlMode mode, double demand, double arbFeedFwd);

    void setInverted(boolean isInverted);

    // void setSensorPhase(boolean sensorPhase);

    // void setForwardSoftLimit(int forwardSoftLimitPos);

    // void setReverseSoftLimit(int reverseSoftLimitPos);

    // void setForwardLimitSwitch(boolean isNormallyOpen);

    // void setReverseLimitSwitch(boolean isNormallyOpen);

    // void setCurrentLimit(int currentLimit);

    // void setPrimarySensor(FeedbackDevice primarySensor);

    void setBrakeMode(boolean enableBrake);

    void follow(MotorController mc);

    void setGains(Gains gains, int slotIdx);

    void selectGainsSlot(int slotIdx);

    Gains getGains();

    double getPercentOut();

    double getPosition();

    double getVelocity();

    double getVoltage();

    double getCurrent();

    double getSetpoint();

    double getSetpointError();

    int getDeviceID();

    boolean checkIntegrity();

    Map<String, Supplier> getTelemetry();

}
