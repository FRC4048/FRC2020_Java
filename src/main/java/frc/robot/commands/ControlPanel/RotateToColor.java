/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;

public class RotateToColor extends CommandBase {
  /**
   * Creates a new RotateToColor.
   */
  private ControlPanelSubsystem controlPanelSubsystem;
  public RotateToColor(ControlPanelSubsystem controlPanelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //GET COLOR FROM FMS
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanelSubsystem.rotateWithSpeed(Constants.CONTROL_PANEL_COLOR_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.stopRotation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //IF COLOR = COLOR FROM FMS RETURN TRUE 
    return false;
  }
}
