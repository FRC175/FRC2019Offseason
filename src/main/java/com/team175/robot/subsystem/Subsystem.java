package com.team175.robot.subsystem;

import com.team175.robot.util.PeriodicTask;
import com.team175.robot.util.PeriodicTaskRunner;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

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
     * Map for holding telemetry data of a subsystem. Meant to be defined in individual subsystem.
     */
    protected final Map<String, Supplier> telemetry = new LinkedHashMap<>();

    /**
     * A subsystem's finite state machine. Run periodically in autonomous and teleop using a {@link PeriodicTaskRunner}.
     */
    protected PeriodicTask stateMachine = new PeriodicTask() {
        @Override
        public void start() {
        }

        @Override
        public void periodic() {
        }

        @Override
        public void stop() {
        }
    };

    /**
     * Shuffleboard tab for a subsystem that is used to send its telemetry data
     */
    private final ShuffleboardTab dashboardTab = Shuffleboard.getTab(getClass().getSimpleName());

    /**
     * Sends the telemetry map to the dashboard.
     */
    public void outputToDashboard() {
        if (!telemetry.isEmpty()) {
            telemetry.forEach((key, value) -> {
                dashboardTab.add(key, value.get());
            });
        }
    }

    /**
     * Returns the finite state machine of a subsystem.
     *
     * @return Subsystem's state machine
     */
    public PeriodicTask getStateMachine() {
        return stateMachine;
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