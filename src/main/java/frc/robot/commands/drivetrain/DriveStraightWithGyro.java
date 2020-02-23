/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.SmartShuffleboard;

public class DriveStraightWithGyro extends CommandBase {
  /**
   * Creates a new DriveStraightWithGyro.
   */
  private SixWheelDriveTrainSubsystem driveTrain;
  private double distance;
  private double speed;
  private double[] motorSpeeds;
  private double currDistance;
  private final double MIN_SPEED = 0.25;
  private final double MAX_SPEED = 0.8;
  private final double MIN_BACK_SPEED = -0.25;
  private final double MAX_BACK_SPEED = -0.8;
  private final double SLOW_DOWN_DISTANCE = 1; //The distance to start the pid calculation 
  private final double SPEEDUP_FACTOR = 15; // % to increase speed
  private final double GYRO_ERROR_THRESHOLD = 0.1;
  
  public DriveStraightWithGyro(SixWheelDriveTrainSubsystem driveTrain, double speed, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.distance = distance;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.resetEncoders();
    currDistance = 0;
    if (speed > 0){
      speed = Math.max(speed, MIN_SPEED);
      speed = Math.min(speed, MAX_SPEED);
    }
    if (speed < 0){
      speed = Math.max(speed, MAX_BACK_SPEED);
      speed = Math.min(speed, MIN_BACK_SPEED);
    }
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
    return false;
  }

  public double[] PIDCalc(double speed, double distanceError, double leftEncoder, double rightEncoder) {
    double[] speeds = {0,0};   // left speed, right speed
    double speedupFactor = 1;
    if (Math.abs(driveTrain.getAngle()) > GYRO_ERROR_THRESHOLD) {
      speedupFactor = 1 + SPEEDUP_FACTOR/100;
    }
    SmartShuffleboard.put("Drive", "Speeds", "speed up", speedupFactor);

    if (speed != 0) {
       if (Math.abs(distanceError) < SLOW_DOWN_DISTANCE) {
          if (speed > 0) {
             speeds[0] = (distanceError/SLOW_DOWN_DISTANCE) * (Math.abs(speed) - MIN_SPEED) + MIN_SPEED;
             speeds[1] = (distanceError/SLOW_DOWN_DISTANCE) * (Math.abs(speed) - MIN_SPEED) + MIN_SPEED;
          }
          else {
             speeds[0] = -((distanceError/SLOW_DOWN_DISTANCE) * (Math.abs(speed) - MIN_SPEED) + MIN_SPEED);
             speeds[1] = -((distanceError/SLOW_DOWN_DISTANCE) * (Math.abs(speed) - MIN_SPEED) + MIN_SPEED); 
          }
       }
       else {
         speeds[0] = speed;
         speeds[1] = speed;
       }
       if (Math.abs(leftEncoder) > Math.abs(rightEncoder)) {
          speeds[1] *= speedupFactor;
       } else if (Math.abs(rightEncoder) > Math.abs(leftEncoder)) {
          speeds[0] *= speedupFactor;
       }
    }
    return speeds;
  }
}
