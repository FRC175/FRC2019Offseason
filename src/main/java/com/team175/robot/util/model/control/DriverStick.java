package com.team175.robot.util.model.control;

/**
 * DriverStick represents the joystick of the driver and the various functions that can be performed by the driver. The
 * various methods of DriverStick abstract away calling the <code>getRawButton()</code> and <code>getRawAxis()</code> of
 * {@link AldrinJoystick}.
 */
public class DriverStick {

    private final AldrinJoystick joystick;

    public DriverStick(int port) {
        joystick = new AldrinJoystick(port);
    }

    public boolean getLateralDriveTrigger() {
        return joystick.getRawButton(1);
    }

    public boolean getShiftLow() {
        return joystick.getRawButton(5);
    }

    public boolean getShiftHigh() {
        return joystick.getRawButton(3);
    }

    public boolean getManualLiftDrive() {
        return joystick.getRawButton(7);
    }

    public boolean getExtendFrontLift() {
        return joystick.getRawButton(12);
    }

    public boolean getRetractFrontLift() {
        return joystick.getRawButton(10);
    }

    public boolean getExtendRearLift() {
        return joystick.getRawButton(11);
    }

    public boolean getRetractRearLift() {
        return joystick.getRawButton(9);
    }

    public boolean getSwivelCamera() {
        return joystick.getRawButton(4);
    }

    public double getX() {
        return joystick.getRawAxis(0);
    }

    public double getY() {
        return -joystick.getRawAxis(1);
    }

}
