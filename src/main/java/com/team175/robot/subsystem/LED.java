package com.team175.robot.subsystem;

import com.ctre.phoenix.CANifier;

/**
 * LED represents the light array on the robot. This array is powered by the CTRE CANifier.
 */
public final class LED extends SubsystemBase {

    private final CANifier canifier;

    private static final int CANIFIER_TALON_PORT = 1;

    private static LED instance;

    private LED() {
        canifier = new CANifier(CANIFIER_TALON_PORT);
    }

    public static LED getInstance() {
        if (instance == null) {
            instance = new LED();
        }

        return instance;
    }

    @Override
    public void resetSensors() {

    }

    @Override
    public boolean checkIntegrity() {
        return false;
    }

}
