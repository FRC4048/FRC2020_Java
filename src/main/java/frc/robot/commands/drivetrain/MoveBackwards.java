/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

public class MoveBackwards extends CommandBase {
  /**
   * Creates a new MoveBackwards.
   */
  private ControlPanelSubsystem controlPanelSubsystem;
  private SixWheelDriveTrainSubsystem driveTrain;
  private double driveBackSpeed;
  public MoveBackwards(ControlPanelSubsystem controlPanelSubsystem, SixWheelDriveTrainSubsystem driveTrain, double driveBackSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.driveTrain = driveTrain;
    this.driveBackSpeed = Math.abs(driveBackSpeed);
    addRequirements(controlPanelSubsystem, driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drive(-driveBackSpeed, -driveBackSpeed);    
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
