/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.MotorUtils; 

public class StalledMotor extends CommandBase {
  private final SixWheelDriveTrainSubsystem driveTrain;
  private final MotorUtils driveStall = new MotorUtils(1, 5.0, 2.0);
  private Timer time = new Timer();
  /**
   * Creates a new StalledMotor.
   */

  public StalledMotor(SixWheelDriveTrainSubsystem driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drive(0.3, 0.0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    driveTrain.drive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(driveStall.isStalled()){
      return true;

    }

    if(time.hasPeriodPassed(5.0)){
      return true;
    }

    return false;
    
  }
}
