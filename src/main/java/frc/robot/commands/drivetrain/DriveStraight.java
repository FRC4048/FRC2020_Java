/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

public class DriveStraight extends CommandBase {
  private SixWheelDriveTrainSubsystem drivetrain;
  private double distance;
  private double speed;
  private double currDistance;
  private final double MIN_SPEED = 0.25;
  private final double MAX_ERROR = 1; //The distance to start the pid calculation 
  /**
   * Creates a new DriveStraight.
   * @param distance the distance to drive in meters
   * @param speed the speed of the robot -- has to be greater than 0.3
   */
  public DriveStraight(double distance, double speed, SixWheelDriveTrainSubsystem drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.distance = distance;
    this.speed = speed;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetEncoders();
    currDistance = (drivetrain.getLeftEncoder() + drivetrain.getRightEncoder()) / 2;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currDistance = Math.abs((drivetrain.getLeftEncoder() + drivetrain.getRightEncoder())/2);
    drivetrain.drive(PIDCalc(speed), PIDCalc(speed), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(0,0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(currDistance) >= Math.abs(distance);
  }

  public double PIDCalc(double speed) {
    double error = distance - currDistance;
    if(speed == 0) {
      return 0;
    } else if(error < MAX_ERROR) {
      if (speed > 0) {
        return (error/MAX_ERROR) * (Math.abs(speed) - MIN_SPEED) + MIN_SPEED;
      }
      return -((error/MAX_ERROR) * (Math.abs(speed) - MIN_SPEED) + MIN_SPEED); 
    } else {
      return speed;
    }
  }
}
