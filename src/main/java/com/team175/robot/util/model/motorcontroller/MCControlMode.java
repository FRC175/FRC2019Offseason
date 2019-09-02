package com.team175.robot.util.model.motorcontroller;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ControlType;

/**
 * MCControlMode is a set of common motor control modes that can be used the subclasses of {@link MotorController}.
 */
public enum MCControlMode {

    PERCENT_OUT(ControlMode.PercentOutput, ControlType.kDutyCycle),
    POSITION(ControlMode.Position, ControlType.kPosition),
    MOTION_MAGIC(ControlMode.MotionMagic, ControlType.kSmartMotion),
    VELOCITY(ControlMode.Velocity, ControlType.kVelocity),
    CURRENT(ControlMode.Current, ControlType.kCurrent),
    DISABLED(ControlMode.Disabled, ControlType.kDutyCycle);

    private final ControlMode ctreMode;
    private final ControlType revMode;

    MCControlMode(ControlMode ctreMode, ControlType revMode) {
        this.ctreMode = ctreMode;
        this.revMode = revMode;
    }

    public ControlMode toCTRE() {
        return ctreMode;
    }

    public ControlType toREV() {
        return revMode;
    }

}
