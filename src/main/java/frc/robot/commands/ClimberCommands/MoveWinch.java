package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class MoveWinch extends CommandBase {
    ClimberSubsystem climberSubsystem;
    double speed;
    public MoveWinch(ClimberSubsystem climberSubsystem, double speed){
        this.climberSubsystem = climberSubsystem;
        this.speed = speed;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        climberSubsystem.moveWinch(speed);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

