/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class MotorSpinIntake extends CommandBase {
  private IntakeSubsystem intakeSubsystem;
  private boolean isReverse;
  private double initialTime;
  private final double INTAKE_TIMER = 0.5;

  /**
   * Creates a new MotorSpinIntake.
   */
  public MotorSpinIntake(IntakeSubsystem intakeSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intakeSubsystem = intakeSubsystem;
    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    IntakeSubsystem.setIsRunning(true);
    isReverse = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (intakeSubsystem.getIntakeSensor() && !isReverse) {
      isReverse = true;
      initialTime = Timer.getFPGATimestamp(); 
      intakeSubsystem.spinMotor(-0.7);
    } else if (((Timer.getFPGATimestamp() - initialTime) < INTAKE_TIMER) && isReverse) {
      intakeSubsystem.spinMotor(-0.7);
    } else if (((Timer.getFPGATimestamp() - initialTime) >= INTAKE_TIMER) && isReverse) {
      isReverse = false;
      intakeSubsystem.spinMotor(0.7);
    } else {
      intakeSubsystem.spinMotor(0.7);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
