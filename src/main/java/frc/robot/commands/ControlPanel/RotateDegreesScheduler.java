/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.logging.LogCommandWrapper;

public class RotateDegreesScheduler extends CommandBase {
  private ControlPanelSubsystem controlPanelSubsystem;
  private SixWheelDriveTrainSubsystem driveTrain;
  private double degreesTurn;
  private double speed;
  private double driveBackSpeed;
  private RobotContainer m_robotContainer = Robot.m_robotContainer;

  /**
   * Creates a new RotateDegreesScheduler.
   */
  public RotateDegreesScheduler(ControlPanelSubsystem controlPanelSubsystem, SixWheelDriveTrainSubsystem driveTrain, double degreesTurn, double speed, double driveBackSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.driveTrain = driveTrain;
    this.degreesTurn = degreesTurn;
    this.driveBackSpeed = driveBackSpeed;
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_robotContainer.getManualOverride()) {
      new LogCommandWrapper(new RotateDegrees(controlPanelSubsystem, degreesTurn, speed), "RotateDegrees").schedule();
    } else {
      new LogCommandWrapper(new RotateDegreesSequence(controlPanelSubsystem, driveTrain, degreesTurn, speed, driveBackSpeed), "RotateDegreesSequence").schedule();
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
