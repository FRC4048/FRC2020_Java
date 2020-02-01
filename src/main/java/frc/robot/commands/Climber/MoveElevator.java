package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class MoveElevator extends CommandBase {
    private ClimberSubsystem climberSubsystem;
    private boolean direction;
    private final double SPEED = 0.5; //TODO: Change to what we need.

    /**
     * Takes a direction and sets the motor to a constant speed.
     *
     * @param climberSubsystem
     * @param direction true is positive speed, false is negative speed
     */
    public MoveElevator(ClimberSubsystem climberSubsystem, boolean direction) {
        this.climberSubsystem = climberSubsystem;
        this.direction = direction;
    }
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (direction) {
            climberSubsystem.setClimber(SPEED);
        }else {
            climberSubsystem.setClimber(-SPEED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        climberSubsystem.stopClimber();
    }

    @Override
    public boolean isFinished() {

        return false; //TODO: figure out the actual logic for when command will stop

    }
}
