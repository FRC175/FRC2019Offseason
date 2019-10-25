package com.team175.robot.util.model.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Limelight holds the Limelight NetworkTable data in a convenient wrapper.
 *
 * @author Arvind
 */
public class Limelight {

    private final NetworkTable table;

    /**
     * Constructs a default limelight whose network configuration is unaltered.
     */
    public Limelight() {
        this("limelight");
    }

    /**
     * Constructs a limelight for a specific table name (if more than one limelight is attached or if the NetworkTables
     * name is altered in the configuration).
     *
     * @param tableName
     *         Name of Limelight NetworkTable
     */
    public Limelight(String tableName) {
        table = NetworkTableInstance.getDefault().getTable(tableName);
    }

    public void setLEDState(boolean enable) {
        table.getEntry("ledMode").setNumber(enable ? 3 : 1);
    }

    public void blinkLED() {
        table.getEntry("ledMode").setNumber(2);
    }

    public void setCameraMode(boolean isDriverMode) {
        table.getEntry("camMode").setNumber(isDriverMode ? 1 : 0);
    }

    public void setPipeline(int pipelineNum) {
        table.getEntry("pipeline").setNumber(pipelineNum);
    }

    public boolean isTargetDetected() {
        return table.getEntry("tv").getDouble(0) == 1;
    }

    public double getHorizontalOffset() {
        return table.getEntry("tx").getDouble(0);
    }

    public double getVerticalOffset() {
        return table.getEntry("ty").getDouble(0);
    }

    public double getTargetArea() {
        return table.getEntry("ta").getDouble(0);
    }

    public double getRotation() {
        return table.getEntry("ts").getDouble(0);
    }

    public int getPipelineNumber() {
        return (int) table.getEntry("getpipe").getDouble(0);
    }

    // Replace with object to hold variables
    public double[] get3DVariables() {
        return table.getEntry("camtran").getDoubleArray(new double[]{});
    }

}