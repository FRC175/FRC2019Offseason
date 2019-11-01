package com.team175.robot.util.model;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * XboxButton is an extension to {@link edu.wpi.first.wpilibj.buttons.Button} that uses Xbox controller button names
 * instead of button numbers.
 */
public class XboxButton extends Button {

    private final XboxController controller;
    private final Button button;

    public enum Button {
        LEFT_BUMPER(5),
        RIGHT_BUMPER(6),
        LEFT_STICK(9),
        RIGHT_STICK(10),
        A(1),
        B(2),
        X(3),
        Y(4),
        BACK(7),
        START(8);

        final int value;

        Button(int value) {
            this.value = value;
        }
    }

    public XboxButton(XboxController controller, Button button) {
        this.controller = controller;
        this.button = button;
    }

    @Override
    public boolean get() {
        return controller.getRawButton(button.value);
    }

}
