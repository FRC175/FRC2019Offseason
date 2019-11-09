package com.team175.robot.util.model;

/**
 * PeriodicTask is the base for objects that run tasks periodically.
 */
public interface PeriodicTask {

    void start();

    void periodic();

    void stop();

}