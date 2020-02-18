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

public class RotateToColor extends CommandBase {
  /**
   * Creates a new RotateToColor.
   */
  private ControlPanelSubsystem controlPanelSubsystem;
  private String fmsColor;
  private ColorValue desiredSensorColor;
  private int unknownCounter;

  public RotateToColor(ControlPanelSubsystem controlPanelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    fmsColor = controlPanelSubsystem.fmsColor();
    if (fmsColor.length() > 0) {
      switch (fmsColor.charAt(0)) {
      case 'R':
        desiredSensorColor = ColorValue.BLUE;
        break;
      case 'B':
        desiredSensorColor = ColorValue.RED;
        break;
      case 'Y':
        desiredSensorColor = ColorValue.GREEN;
        break;
      case 'G':
        desiredSensorColor = ColorValue.YELLOW;
        break;
      default:
        break;
      }
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (desiredSensorColor != null) {
      controlPanelSubsystem.rotateWithSpeed(Constants.CONTROL_PANEL_COLOR_SPEED);
      SmartShuffleboard.put("Control Panel", "Action", "Spinning");
      if (controlPanelSubsystem.getCurrentColor() == ColorValue.UNKNOWN) {
        unknownCounter++;
        SmartShuffleboard.put("Control Panel", "Unknown Counter", unknownCounter);
      } else {
        unknownCounter = 0;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.stopRotation();
    SmartShuffleboard.put("Control Panel", "Action", "Stopping");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if ((desiredSensorColor != null) && (controlPanelSubsystem.getCurrentColor() == desiredSensorColor  )) {
      return true;
    } else if (unknownCounter > Constants.CONTROL_PANEL_UNKNOWN_LIMIT){
      return true;
    } else {
      return false;
    }
  }
}
