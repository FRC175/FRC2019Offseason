package com.team175.robot.model;

import edu.wpi.first.wpilibj.Joystick;

/**
 * AldrinJoystick is an extension to {@link edu.wpi.first.wpilibj.Joystick} that adds a software deadband to the various
 * axes of the joystick.
 */
public class AldrinJoystick extends Joystick {

    /**
     * The possible dead zone to add.
     */
    private double deadband;

    /**
     * Constructs an instance of a joystick. The joystick index is the USB port on the driver station.
     *
     * @param port
     *         The port on the Driver Station that the joystick is plugged into.
     */
    public AldrinJoystick(int port) {
        this(port, 0);
    }

    /**
     * Constructs an instance of a joystick with a specified dead zone. The joystick index is the USB port on the
     * driver station.
     *
     * @param port
     *         The port on the Driver Station that the joystick is plugged into.
     * @param deadband
     *         The percent of deadband to add to the joystick axises.
     */
    public AldrinJoystick(int port, double deadband) {
        super(port);
        this.deadband = deadband;
    }

    /**
     * Gets the value of an axis and accounts for deadband.
     *
     * @param axis
     *         The axis to read
     * @return The value of the axis
     */
    @Override
    public double getRawAxis(int axis) {
        double value = super.getRawAxis(axis);

        if (Math.abs(value) > deadband) {
            value = Math.signum(value) * (Math.abs(value) - deadband) / (1.0 - deadband);
        } else {
            value = 0.0;
        }

        return (Math.abs(value) > deadband) ? value : 0.0;
    }

}