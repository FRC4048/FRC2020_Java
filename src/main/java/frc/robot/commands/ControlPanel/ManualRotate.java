package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;

import java.util.function.DoubleSupplier;

public class ManualRotate extends CommandBase {
    private ControlPanelSubsystem controlPanelSubsystem;
    private DoubleSupplier speed;
    private final double DEAD_ZONE = 0.1;

    //This is the default command for the Control Panel Subsystem.
    public ManualRotate(ControlPanelSubsystem controlPanelSubsystem, DoubleSupplier speed){
        addRequirements(controlPanelSubsystem);
        this.controlPanelSubsystem = controlPanelSubsystem;
        this.speed = speed;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if(Math.abs(speed.getAsDouble()) > DEAD_ZONE)
            controlPanelSubsystem.rotateWithSpeed(speed.getAsDouble() * Constants.CONTROL_PANEL_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        controlPanelSubsystem.rotateWithSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
