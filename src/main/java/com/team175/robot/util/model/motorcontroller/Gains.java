package com.team175.robot.util.model.motorcontroller;

/**
 * Holds PID gains (Kp, Ki, Kd, Kf) and acceleration and cruise velocity data for closed loop control.
 */
public final class Gains {

    private final double kP, kI, kD, kF;
    private final int acceleration, cruiseVelocity;

    public Gains(double kP, double kI, double kD, double kF, int acceleration, int cruiseVelocity) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.acceleration = acceleration;
        this.cruiseVelocity = cruiseVelocity;
    }

    public double getKp() {
        return kP;
    }

    public double getKi() {
        return kI;
    }

    public double getKd() {
        return kD;
    }

    public double getKf() {
        return kF;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getCruiseVelocity() {
        return cruiseVelocity;
    }

}