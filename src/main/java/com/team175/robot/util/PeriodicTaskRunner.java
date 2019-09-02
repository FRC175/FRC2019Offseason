package com.team175.robot.util;

import edu.wpi.first.wpilibj.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * PeriodicTaskRunner runs one or more periodic tasks on a separate thread at a certain frequency.
 */
public class PeriodicTaskRunner {

    private final List<? extends PeriodicTask> tasks;
    private final Notifier notifier;
    private final Logger logger;
    private final double period;

    private boolean isRunning;

    public PeriodicTaskRunner(double period, List<? extends PeriodicTask> tasks) {
        this.tasks = tasks;
        notifier = new Notifier(() -> this.tasks.forEach(PeriodicTask::periodic));
        logger = LoggerFactory.getLogger(getClass().getSimpleName());
        this.period = period;
        isRunning = false;
    }

    public PeriodicTaskRunner(double period, PeriodicTask... PeriodicTasks) {
        this(period, List.of(PeriodicTasks));
    }

    public synchronized void start() {
        if (!isRunning) {
            logger.info("Starting tasks.");
            tasks.forEach(PeriodicTask::start);
            logger.info("Starting tasks' periodic method.");
            notifier.startPeriodic(period);
            isRunning = true;
        }
    }

    public synchronized void stop() {
        if (isRunning) {
            logger.info("Stopping tasks.");
            notifier.stop();
            isRunning = false;
            tasks.forEach(PeriodicTask::stop);
            isRunning = false;
        }
    }

}