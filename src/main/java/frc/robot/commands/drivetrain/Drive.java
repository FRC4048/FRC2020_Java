package frc.robot.commands.drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

public class Drive extends CommandBase {
  private final SixWheelDriveTrainSubsystem driveTrain;
  private final DoubleSupplier leftSpeed;
  private final DoubleSupplier rightSpeed;
  /**
   * Creates a new Drive.
   */
  public Drive(SixWheelDriveTrainSubsystem driveTrain, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
    this.leftSpeed = leftSpeed; 
    this.rightSpeed = rightSpeed;
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // driveTrain.drive(leftSpeed.getAsDouble(), rightSpeed.getAsDouble());
    driveTrain.drive(0.5, 0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}