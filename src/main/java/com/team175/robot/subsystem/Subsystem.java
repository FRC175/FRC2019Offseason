package com.team175.robot.subsystem;

import com.team175.robot.util.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subsystem is the base for all the different subsystems that make up the robot. The base for all of the different
 * subsystems that make up the robot. Each subsystem must implement automated checking of necessary components and creating
 * of telemetry data to be sent to the dashboard. In addition, a subsystem also has a state machine, meaning that it is
 * responsible for keep tracking of its state and has to implement routines that are performed when the robot has started,
 * stopped, and is running. The aforementioned routines are a part of the superinterface, {@link PeriodicTask}. Finally,
 * a subsystem must only have one instance, which is implemented by the singleton design pattern (after all, a robot can
 * only have one drivetrain).
 */
public abstract class Subsystem {

    /**
     * Logger used to document the various actions performed by a subsystem.
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

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

    /**
     * Returns the finite state machine of a subsystem in the form of a {@link PeriodicTask}.
     *
     * @return Subsystem's state machine
     */
    public abstract PeriodicTask getStateMachine();

    // public abstract String getTelemetry();

}