/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.logging.LogCommandWrapper;

public class RotateToColorScheduler extends CommandBase {
  private ControlPanelSubsystem controlPanelSubsystem;
  private SixWheelDriveTrainSubsystem driveTrain;
  private double driveBackSpeed;
  /**
   * Creates a new RotateToColorScheduler.
   */
  public RotateToColorScheduler(ControlPanelSubsystem controlPanelSubsystem, SixWheelDriveTrainSubsystem driveTrain, double driveBackSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.driveBackSpeed = driveBackSpeed;
    this.driveTrain = driveTrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.m_robotContainer.getManualOverride()) {
      new LogCommandWrapper(new RotateToColor(controlPanelSubsystem), "RotateToColor").schedule();
    } else {
      new LogCommandWrapper(new RotateToColorSequence(controlPanelSubsystem, driveTrain, driveBackSpeed), "RotateToColorSequence").schedule();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
