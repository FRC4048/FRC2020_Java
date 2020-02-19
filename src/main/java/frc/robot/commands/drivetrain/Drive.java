package frc.robot.commands.drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.ControlPanel.ManualOverride;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.SmartShuffleboard;

public class Drive extends CommandBase {
  private final SixWheelDriveTrainSubsystem driveTrain;
  private final DoubleSupplier leftSpeed;
  private final DoubleSupplier rightSpeed;
  ControlPanelSubsystem controlPanelSubsystem;

  /**
   * Creates a new Drive.
   */
  public Drive(SixWheelDriveTrainSubsystem driveTrain, ControlPanelSubsystem controlPanelSubsystem, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
    this.leftSpeed = leftSpeed; 
    this.rightSpeed = rightSpeed;
    this.driveTrain = driveTrain;
    this.controlPanelSubsystem = controlPanelSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_robotContainer.setDrivingEnabled(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (Robot.m_robotContainer.getDrivingEnabled() || Robot.m_robotContainer.getManualOverride()) {
      driveTrain.drive(leftSpeed.getAsDouble(), rightSpeed.getAsDouble(), true);
    } else {
      driveTrain.drive(0, 0, false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0,0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if (!controlPanelSubsystem.controlPanelSensor() && controlPanelSubsystem.getPistonState() && !Robot.m_robotContainer.getManualOverride()) {
    //   Robot.m_robotContainer.setDrivingEnabled(false);
    //   return false;
    // } else {
      return false;
    // }
  }
}