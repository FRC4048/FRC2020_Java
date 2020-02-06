/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import java.nio.DoubleBuffer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;

public class RotateDegrees extends CommandBase {
  /**
   * Creates a new RotateDegrees.
   */
  private double degreesTurn;
  private ControlPanelSubsystem controlPanelSubsystem;
  //private final double TARGET_ENCODER_VALUE = 100;
  private double ticksToTurn;
  private double speed;
  public RotateDegrees(ControlPanelSubsystem controlPanelSubsystem, double degreesTurn, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.degreesTurn = degreesTurn;
    this.speed = speed;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controlPanelSubsystem.resetEncoder();
    ticksToTurn = degreesTurn * Constants.CONTROL_PANEL_DEGREES_TO_TICKS;
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
    return (Math.abs(controlPanelSubsystem.getEncoder()) > Math.abs(ticksToTurn));
    }
  }
