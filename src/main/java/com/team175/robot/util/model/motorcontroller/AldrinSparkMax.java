package com.team175.robot.util.model.motorcontroller;

import com.revrobotics.CANSparkMax;

public class AldrinSparkMax extends CANSparkMax {

    public AldrinSparkMax(int deviceID, MotorType type) {
        super(deviceID, type);
    }

}
