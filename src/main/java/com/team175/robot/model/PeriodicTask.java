package com.team175.robot.model;

/**
 * PeriodicTask is the base for objects that run tasks periodically.
 */
public interface PeriodicTask {

    void start();

    void periodic();

    void stop();

}