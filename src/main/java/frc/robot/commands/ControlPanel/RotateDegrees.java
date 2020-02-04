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

public class RotateDegrees extends CommandBase {
  /**
   * Creates a new RotateDegrees.
   */
  private int degreesTurn;
  private ControlPanelSubsystem controlPanelSubsystem;
  private final double CONTROL_PANEL_SPEED = 0.5;
  //private final double TARGET_ENCODER_VALUE = 100;
  private int ticksToTurn;
  public RotateDegrees(ControlPanelSubsystem controlPanelSubsystem, int degreesTurn) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.degreesTurn = degreesTurn;
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
    controlPanelSubsystem.rotateWithSpeed(CONTROL_PANEL_SPEED);  

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.stopRotation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (controlPanelSubsystem.getEncoder() > ticksToTurn);
    }
  }
