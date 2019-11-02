package com.team175.robot.util.model;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * XboxButton is an extension to {@link edu.wpi.first.wpilibj.buttons.Button} that uses Xbox controller button names
 * instead of button numbers.
 */
public class XboxButton extends Button {

    private final AldrinXboxController controller;

    private AldrinXboxController.Button button;
    private AldrinXboxController.DPad dPadButton;

    public XboxButton(AldrinXboxController controller, AldrinXboxController.Button button) {
        this.controller = controller;
        this.button = button;
    }

    public XboxButton(AldrinXboxController controller, AldrinXboxController.DPad dPadButton) {
        this.controller = controller;
        this.dPadButton = dPadButton;
    }

    @Override
    public boolean get() {
        return dPadButton == null ? controller.getRawButton(button.value) : controller.getPOV() == dPadButton.value;
    }

}
