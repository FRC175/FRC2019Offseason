package com.team175.robot.util.model.control;

/**
 * OperatorStick represents the joystick of the operator and the various functions that can be performed by the operator.
 * The various methods of OperatorStick abstract away calling the <code>getRawButton()</code> and <code>getRawAxis()</code>
 * of {@link AldrinJoystick}.
 */
public class OperatorStick {

    private final AldrinJoystick joystick;

    public OperatorStick(int port) {
        joystick = new AldrinJoystick(port);
    }

    public boolean getManualElevatorTrigger() {
        return joystick.getRawButton(1);
    }

    public boolean getManualArm() {
        return joystick.getRawButton(1) && isNubPressed();
    }

    public boolean getShoveHatch() {
        return joystick.getRawButton(5);
    }

    public boolean getPickUpHatch() {
        return joystick.getRawButton(3);
    }

    public boolean getShootBall() {
        return joystick.getRawButton(6);
    }

    public boolean getShootBallSickoMode() {
        return joystick.getRawButton(6) && isNubPressed();
    }

    public boolean getPickUpBall() {
        return joystick.getRawButton(4);
    }

    public boolean getVelcroHitchMode() {
        return joystick.getRawButton(7);
    }

    public boolean getFingerHatchMode() {
        return joystick.getRawButton(9);
    }

    public boolean getBallMode() {
        return joystick.getRawButton(11);
    }

    public boolean getElevatorPosOne() {
        return joystick.getRawButton(8);
    }

    public boolean getElevatorPosTwo() {
        return joystick.getRawButton(10);
    }

    public boolean getElevatorPosThree() {
        return joystick.getRawButton(12);
    }

    public boolean getElevatorBallGroundPickupPos() {
        return joystick.getRawButton(11) && isNubPressed();
    }

    public boolean getArmStowPos() {
        return joystick.getRawButton(2);
    }

    public boolean getArmScorePos() {
        return joystick.getRawButton(8) && isNubPressed();
    }

    public boolean getArmBallPickupPos() {
        return joystick.getRawButton(10) && isNubPressed();
    }

    public boolean getArmHatchPickupPos() {
        return joystick.getRawButton(10) && isNubPressed();
    }

    private boolean isNubPressed() {
        return joystick.getPOV() > -1;
    }

    public double getX() {
        return joystick.getRawAxis(0);
    }

    public double getY() {
        return -joystick.getRawAxis(1);
    }

}
