/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

public class GearSwitch extends CommandBase {
  private SixWheelDriveTrainSubsystem drivetrain;
  private boolean state;
  /**
   * Creates a new GearSwitch.
   * @param state true is low speed false is high speed
   */
  public GearSwitch(SixWheelDriveTrainSubsystem drivetrain, boolean state) {
    this.drivetrain = drivetrain;
    this.state = state;

    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.switchGear(state);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
