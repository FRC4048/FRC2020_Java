package frc.robot.commands.WinchCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.WinchSubsystem;

public class MoveWinch extends CommandBase {
    private WinchSubsystem winchSubsystem;
    private XboxController xboxController;
    public MoveWinch(WinchSubsystem winchSubsystem, XboxController xboxController){
        addRequirements(winchSubsystem);
        this.winchSubsystem = winchSubsystem;
        this.xboxController = xboxController;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        winchSubsystem.moveWinch(xboxController.getY(GenericHID.Hand.kRight)* Constants.CLIMBER_WINCH_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        winchSubsystem.stopWinch();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

