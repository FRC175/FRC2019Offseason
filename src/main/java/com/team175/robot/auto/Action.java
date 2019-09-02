package com.team175.robot.auto;

import com.team175.robot.util.PeriodicTask;

/**
 * Action represents that commands can be performed by the robot (e.g., driving in open loop control, moving a subsystem
 * to a certain position, etc.). This is very similar to {@link PeriodicTask} except that it runs only until a condition
 * has been met (until <code>isFinished()</code> returns true).
 */
public interface Action extends PeriodicTask {

    boolean isFinished();

}
