/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj.Timer;
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
  private double initTime;

  public RotateToColor(ControlPanelSubsystem controlPanelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initTime = Timer.getFPGATimestamp();
    unknownCounter = 0;
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
      if (controlPanelSubsystem.getCurrentColor() == ColorValue.UNKNOWN) {
        unknownCounter++;
        if (Constants.ENABLE_DEBUG) {
          SmartShuffleboard.put("Control Panel", "Unknown Counter", unknownCounter);
        }
      } else {
        unknownCounter = 0;
      }
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
    if ((Timer.getFPGATimestamp() - initTime) > Constants.CONTROL_PANEL_ROTATE_TO_COLOR_TIMEOUT) {
      return true;
    }
    if ((desiredSensorColor != null) && (controlPanelSubsystem.getCurrentColor() == desiredSensorColor)) {
      return true;
    } else if (unknownCounter > Constants.CONTROL_PANEL_UNKNOWN_LIMIT) {
      return true;
    } else {
      return false;
    }
  }
}
