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

public class WaitForSensor extends CommandBase {
  private ControlPanelSubsystem controlPanelSubsystem;
  private boolean sensorStateWanted;

  /**
   * Creates a new WaitForSensor.
   */
  Timer timer = new Timer();
  public WaitForSensor(ControlPanelSubsystem controlPanelSubsystem, boolean sensorStateWanted) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.sensorStateWanted = sensorStateWanted;
    this.controlPanelSubsystem = controlPanelSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controlPanelSubsystem.setWaitSensorTimeout(false);
    SmartShuffleboard.put("Control Panel", "Wait Sesnor Time", true);
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.hasPeriodPassed(Constants.CONTROL_PANEL_WAIT_SENSOR_TIMEOUT)) {
      controlPanelSubsystem.setWaitSensorTimeout(true);
      return true;
    } else if (controlPanelSubsystem.controlPanelSensor() == sensorStateWanted) { //The sensor is no longer looking to be broken, but rather made; the sensor has changed position
      return true;
    } else {
      return false;
    }
  }
}
