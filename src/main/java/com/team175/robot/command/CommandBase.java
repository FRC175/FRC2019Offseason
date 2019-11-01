package com.team175.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CommandBase is an extension to {@link edu.wpi.first.wpilibj.command.Command} to prepare the transition to the 2020
 * Command-Based rewrite.
 */
public abstract class CommandBase extends Command {

    public CommandBase(double timeout) {
        super(timeout);
    }

    public CommandBase() {
    }

    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    protected final synchronized void addRequirements(Subsystem... subsystems) {
        for (Subsystem s : subsystems)
            requires(s);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }

}
