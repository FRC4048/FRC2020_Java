/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import java.nio.DoubleBuffer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.logging.Logging;
import frc.robot.utils.logging.Logging.MessageLevel;

public class RotateDegrees extends CommandBase {
  /**
   * Creates a new RotateDegrees.
   */
  private double degreesTurn;
  private double encoderValue;
  private ControlPanelSubsystem controlPanelSubsystem;
  // private final double TARGET_ENCODER_VALUE = 100;
  private double ticksToTurn;
  private double speed;
  private double initTime;

  public RotateDegrees(ControlPanelSubsystem controlPanelSubsystem, double degreesTurn, double rotationSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.degreesTurn = degreesTurn;
    speed = rotationSpeed;
    ticksToTurn = degreesTurn * Constants.CONTROL_PANEL_DEGREES_TO_TICKS;
    addRequirements(controlPanelSubsystem);
  }

  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoderValue = controlPanelSubsystem.getEncoder();
    initTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanelSubsystem.rotateWithSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.stopRotation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {    
    return (Math.abs(controlPanelSubsystem.getEncoder() - encoderValue) > Math.abs(ticksToTurn)) || (Timer.getFPGATimestamp() - initTime) >= Constants.CONTROL_PANEL_ROTATE_DEGREES_TIMEOUT;
  }
}
