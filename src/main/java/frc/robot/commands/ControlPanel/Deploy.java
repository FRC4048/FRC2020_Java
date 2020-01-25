package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class Deploy extends CommandBase {
    private ControlPanel p_subsystem;
    private boolean extend;

    public Deploy(ControlPanel subsystem, boolean extend){
        p_subsystem = subsystem;
        this.extend = extend;
    }
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if(extend){
            p_subsystem.extend();
        } else{
            p_subsystem.retract();
        }

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }


}
