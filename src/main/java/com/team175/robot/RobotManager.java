package com.team175.robot;

public final class RobotManager {

    private static RobotManager instance;

    private RobotManager() {

    }

    public static RobotManager getInstance() {
        if (instance == null) {
            instance = new RobotManager();
        }

        return instance;
    }

}
