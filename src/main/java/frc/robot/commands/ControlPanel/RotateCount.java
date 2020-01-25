package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanel;

public class RotateCount extends CommandBase {
    ControlPanel subsystem;
    double speed;

    public RotateCount(ControlPanel subsystem){
        this.subsystem = subsystem;
    }
    @Override
    public void initialize() {
        subsystem.resetEncoder();
    }

    @Override
    public void execute() {
        subsystem.spin(0.7);
    }
    @Override
    public boolean isFinished() {
        return subsystem.getEncoderValue() >= Constants.TARGET_CONTROL_PANEL_REVOLUTIONS;
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.stopSpin();
    }



}
