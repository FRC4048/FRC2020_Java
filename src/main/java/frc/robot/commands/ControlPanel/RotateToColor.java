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
  private ColorValue sensorValue;

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
        sensorValue = ColorValue.BLUE;
        break;
      case 'B':
        sensorValue = ColorValue.RED;
        break;
      case 'Y':
        sensorValue = ColorValue.GREEN;
        break;
      case 'G':
        sensorValue = ColorValue.YELLOW;
        break;
      default:
        break;
      }
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (fmsColor.length() > 0) {
      //controlPanelSubsystem.rotateWithSpeed(Constants.CONTROL_PANEL_COLOR_SPEED);
      SmartShuffleboard.put("Control Panel", "Action", "Spinning");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //controlPanelSubsystem.stopRotation();
    SmartShuffleboard.put("Control Panel", "Action", "Stopping");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if ((fmsColor.length() == 0) || (controlPanelSubsystem.getCurrentColor() == sensorValue)) {
      return true;
    } else {
      return false;
    }
  }
}
