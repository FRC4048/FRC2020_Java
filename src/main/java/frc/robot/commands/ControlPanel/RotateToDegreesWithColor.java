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
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.ColorSensor.ColorValue;

public class RotateToDegreesWithColor extends CommandBase {
  /**
   * Creates a new RotateToDegreesWithColor.
   */
  private ControlPanelSubsystem controlPanelSubsystem;
  private int colorCounter = 0;
  private int turns;
  private boolean singleAddCounter = true;
  public RotateToDegreesWithColor(ControlPanelSubsystem controlPanelSubsystem, int turns) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.turns = turns;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanelSubsystem.rotateWithSpeed(Constants.CONTROL_PANEL_COLOR_SPEED);
    if ((controlPanelSubsystem.getCurrentColor() == ColorValue.RED) && (singleAddCounter)){
      colorCounter++;
      singleAddCounter = false;
      SmartShuffleboard.put("Control Panel", "ABuisf", colorCounter);
    } else if ((controlPanelSubsystem.getCurrentColor() != ColorValue.RED) && (!singleAddCounter)){
      singleAddCounter = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.stopRotation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (colorCounter >= (2*turns)) {
      return true;
    } else {
      return false;
    }
  }
}
