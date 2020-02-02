package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

import java.util.function.DoubleSupplier;

public class ManualRotate extends CommandBase {
    private ControlPanelSubsystem controlPanelSubsystem;
    private DoubleSupplier speed;

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
        controlPanelSubsystem.rotateWithSpeed(speed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
