package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class MoveElevator extends CommandBase {
    private ElevatorSubsystem elevatorSubsystem;
    private double speed;

    /**
     * Takes a direction and sets the motor to a constant speed.
     *
     * @param elevatorSubsystem
     * @param speed sets the speed for the elevator motor.
     */
    public MoveElevator(ElevatorSubsystem elevatorSubsystem, double speed) { //TODO: set a limit to the speed of the elevator motor.
        this.elevatorSubsystem = elevatorSubsystem;
        this.speed = speed;
    }
    @Override
    public void initialize() {
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        elevatorSubsystem.setClimber(speed);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopClimber();
    }

    @Override
    public boolean isFinished() {
        return false; //TODO: figure out the actual logic for when command will stop

    }
}
