package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ToggleElevator extends CommandBase {
    private ControlPanelSubsystem controlPanelSubsystem;

    public ToggleElevator(ControlPanelSubsystem controlPanelSubsystem){
        this.controlPanelSubsystem = controlPanelSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (controlPanelSubsystem.getPistonState()){
            controlPanelSubsystem.retract();
        }else{
            controlPanelSubsystem.extend();
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
