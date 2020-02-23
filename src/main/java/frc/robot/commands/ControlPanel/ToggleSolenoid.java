package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ToggleSolenoid extends CommandBase {
    private ControlPanelSubsystem controlPanelSubsystem;

    public ToggleSolenoid(ControlPanelSubsystem controlPanelSubsystem){
        this.controlPanelSubsystem = controlPanelSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        //This moves the piston to the opposite of its current state
        if(controlPanelSubsystem.getPistonState() == Value.kForward) {
            controlPanelSubsystem.movePiston(Value.kReverse);
        } else {
            controlPanelSubsystem.movePiston(Value.kForward);
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
