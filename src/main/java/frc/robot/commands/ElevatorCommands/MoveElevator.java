package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberElevatorSubsystem;
import frc.robot.utils.SmartShuffleboard;

public class MoveElevator extends CommandBase {
    private ClimberElevatorSubsystem climberElevatorSubsystem;
    private XboxController xboxController;
    private final double MAX_SPEED = 0.5;

    public MoveElevator(ClimberElevatorSubsystem climberElevatorSubsystem, XboxController xboxController) {
        addRequirements(climberElevatorSubsystem);
        this.climberElevatorSubsystem = climberElevatorSubsystem;
        this.xboxController = xboxController;
    }
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        climberElevatorSubsystem.setClimber(xboxController.getY(GenericHID.Hand.kLeft) * Constants.ELEVATOR_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        climberElevatorSubsystem.stopClimber();
    }

    @Override
    public boolean isFinished() {
        return false; //TODO: figure out the actual logic for when command will stop

    }
}
