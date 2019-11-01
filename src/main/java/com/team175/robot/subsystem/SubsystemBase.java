package com.team175.robot.subsystem;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * SubsystemBase is the base for all the different subsystems that make up the robot. Each subsystem must implement
 * automated integrity checking of necessary components and reset of sensor(s). A subsystem must only have one instance,
 * which is implemented by the singleton design pattern (after all, a robot can only have one drivetrain).
 */
public abstract class SubsystemBase extends Subsystem {

    /**
     * Logger used to document the various actions performed by a subsystem.
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    /**
     * Map for holding telemetry data of a subsystem. Meant to be defined in individual subsystem.
     */
    protected final Map<String, Supplier> telemetry = new LinkedHashMap<>();

    /**
     * Makes {@code initDefaultCommand()} optional for an easier transition to the 2020 season.
     */
    @Override
    public void initDefaultCommand() {
    }

    /**
     * Adds data to the builder from the telemetry map filtered by data type.
     *
     * @param builder
     */
    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        if (!telemetry.isEmpty()) {
            // Add data to builder filtered by data type
            telemetry.forEach((k, v) -> {
                String subKey = "." + k;
                var rawValue = v.get();

                // All consumers are null because data is to be read-only
                if (rawValue instanceof Double || rawValue instanceof Integer) {
                    builder.addDoubleProperty(subKey, () -> (double) rawValue, null);
                } else if (rawValue instanceof Boolean) {
                    builder.addDoubleProperty(subKey, () -> (double) rawValue, null);
                } else if (rawValue instanceof double[]) {
                    builder.addDoubleArrayProperty(subKey, v, null);
                } else if (rawValue instanceof boolean[]) {
                    builder.addBooleanArrayProperty(subKey, v, null);
                } else if (rawValue instanceof String[]) {
                    builder.addStringArrayProperty(subKey, v, null);
                } else {
                    builder.addStringProperty(subKey, rawValue::toString, null);
                }
            });
        }
    }

    /**
     * Resets the sensors of a subsystem to their initial values (e.g., set encoders to zero units).
     */
    public abstract void resetSensors();

    /**
     * Performs a set of tests to verify a subsystem is behaving properly.
     *
     * @return Whether the subsystem passed all the tests
     */
    public abstract boolean checkIntegrity();

}