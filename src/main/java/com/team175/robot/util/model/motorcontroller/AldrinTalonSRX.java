package com.team175.robot.util.model.motorcontroller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * AldrinTalonSRX is a wrapper around the TalonSRX that implements {@link MotorController} in order to use the Talon SRX
 * and the Spark Max in a similar fashion.
 *
 * TODO: Vet code
 */
public class AldrinTalonSRX extends TalonSRX implements MotorController {

    private Gains gains;
    private double setpoint;

    public AldrinTalonSRX(int deviceID) {
        super(deviceID);
    }

    @Deprecated
    public void set(ControlMode mode, double demand0, DemandType demand1Type, double demand1) {
        super.set(mode, demand0, demand1Type, demand1);
    }

    @Deprecated
    public void set(ControlMode mode, double outputValue) {
        super.set(mode, outputValue);
    }

    @Override
    public void set(MCControlMode mode, double demand) {
        set(mode, demand, 0);
    }

    @Override
    public void set(MCControlMode mode, double demand, double arbFeedFwd) {
        if (mode == MCControlMode.MOTION_MAGIC || mode == MCControlMode.POSITION || mode == MCControlMode.VELOCITY) {
            setpoint = demand;
        }
        super.set(mode.toCTRE(), demand, DemandType.ArbitraryFeedForward, arbFeedFwd);
    }

    @Override
    public void setInverted(boolean isInverted) {
        super.setInverted(isInverted);
    }

    @Override
    public void setBrakeMode(boolean enableBrake) {
        super.setNeutralMode(enableBrake ? NeutralMode.Brake : NeutralMode.Coast);
    }

    @Override
    public void follow(MotorController mc) {
        if (mc instanceof IMotorController) {
            super.follow((IMotorController) mc);
        } else {
            // TODO: Add error checking
        }
    }

    @Override
    public void setGains(Gains gains, int slotIdx) {
        super.config_kP(slotIdx, gains.getKp());
        super.config_kI(slotIdx, gains.getKi());
        super.config_kD(slotIdx, gains.getKd());
        super.config_kF(slotIdx, gains.getKf());
        super.configMotionAcceleration(gains.getAcceleration());
        super.configMotionCruiseVelocity(gains.getCruiseVelocity());
        this.gains = gains;
    }

    @Override
    public void selectGainsSlot(int slotIdx) {
        super.selectProfileSlot(slotIdx, 0);
    }

    @Override
    public Gains getGains() {
        return gains;
    }

    @Override
    public double getPercentOut() {
        return super.getMotorOutputPercent();
    }

    @Override
    public double getPosition() {
        return super.getSelectedSensorPosition();
    }

    @Override
    public double getVelocity() {
        return super.getSelectedSensorVelocity();
    }

    @Override
    public double getVoltage() {
        return super.getMotorOutputVoltage();
    }

    @Override
    public double getCurrent() {
        return super.getOutputCurrent();
    }

    @Override
    public double getSetpoint() {
        // TODO: Check
        // return super.getClosedLoopTarget();
        return setpoint;
    }

    @Override
    public double getSetpointError() {
        return super.getClosedLoopError();
    }

    @Override
    public int getDeviceID() {
        return super.getDeviceID();
    }

    @Override
    public boolean checkIntegrity() {
        // TODO: Implement
        return false;
    }

}
