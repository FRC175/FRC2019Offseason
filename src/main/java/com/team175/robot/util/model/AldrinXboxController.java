package com.team175.robot.util.model;

import edu.wpi.first.wpilibj.XboxController;

/**
 * AldrinXboxController is an extension to {@link edu.wpi.first.wpilibj.XboxController} that adds a software deadband to
 * the various axes of the controller.
 */
public class AldrinXboxController extends XboxController {

    private final double deadband;

    /**
     * Construct an instance of an xbox controller. The controller index is the USB port on the driver station.
     *
     * @param port The port on the Driver Station that the controller is plugged into.
     */
    public AldrinXboxController(int port) {
        this(port, 0);
    }

    /**
     * Construct an instance of an xbox controller with a specified deadband. The controller index is the USB port on the
     * driver station.
     *
     * @param port The port on the Driver Station that the controller is plugged into.
     */
    public AldrinXboxController(int port, double deadband) {
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
