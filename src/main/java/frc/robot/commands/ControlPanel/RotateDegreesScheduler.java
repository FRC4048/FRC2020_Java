/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.utils.logging.LogCommandWrapper;

public class RotateDegreesScheduler extends CommandBase {
  private ControlPanelSubsystem controlPanelSubsystem;
  private double degreesTurn;
  private double speed;
  private RobotContainer m_robotContainer = RobotContainer.instance();

  /**
   * Creates a new RotateDegreesScheduler.
   */
  public RotateDegreesScheduler(ControlPanelSubsystem controlPanelSubsystem, double degreesTurn, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.degreesTurn = degreesTurn;
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
      new LogCommandWrapper(new RotateDegreesSequence(controlPanelSubsystem, degreesTurn, speed), "RotateDegreesSequence").schedule();
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
