package com.team175.robot.subsystem;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.Timer;

import java.awt.Color;

/**
 * LED represents the light array on the robot. This array is powered by the CTRE CANifier.
 */
public final class LED extends SubsystemBase {

    private final CANifier canifier;

    private Color color;
    private double startTime;
    private boolean isBlinkComplete;

    private static final int CANIFIER_TALON_PORT = 1;
    private static final int BLINK_DURATION = 2; // s

    private static LED instance;

    private LED() {
        canifier = new CANifier(CANIFIER_TALON_PORT);
        color = Color.WHITE;
        startTime = 0;
        isBlinkComplete = false;
    }

    public static LED getInstance() {
        if (instance == null) {
            instance = new LED();
        }

        return instance;
    }

    public void setColor(Color color) {
        this.color = color;
        canifier.setLEDOutput((double) this.color.getRed() / 255, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput((double) this.color.getGreen() / 255, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput((double) this.color.getBlue() / 255, CANifier.LEDChannel.LEDChannelC);
    }

    public void defaultLED() {
        // TODO: Replace with good color
        setColor(Color.WHITE);
    }

    // Call before handleBlink()
    public void blinkLED(Color color) {
        this.color = color;
        startTime = Timer.getFPGATimestamp();
    }

    // Call in state machine logic
    public void handleBlink() {
        double timeElapsed = Timer.getFPGATimestamp() - startTime;

        if (timeElapsed > BLINK_DURATION) {
            isBlinkComplete = true;
        } else {
            isBlinkComplete = false;
            int cycleCount = (int) (timeElapsed / BLINK_DURATION);
            if (cycleCount % 2 == 0) {
                setColor(color);
            } else {
                setColor(Color.BLACK);
            }
        }
    }

    public void handleColorCycle() {

    }

    public Color getColor() {
        return color;
    }

    public boolean isBlinkComplete() {
        return isBlinkComplete;
    }

    @Override
    public void resetSensors() {

    }

    @Override
    public boolean checkIntegrity() {
        return false;
    }

}
