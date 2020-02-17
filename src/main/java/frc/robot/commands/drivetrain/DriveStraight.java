/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.SmartShuffleboard;

public class DriveStraight extends CommandBase {
  private SixWheelDriveTrainSubsystem drivetrain;
  private double distance;
  private double speed;
  private double[] motorSpeeds;
  private double currDistance;
  private final double MIN_SPEED = 0.25;
  private final double MAX_SPEED = 0.8;
  private final double SLOW_DOWN_DISTANCE = 1; //The distance to start the pid calculation 
  private final double SPEEDUP_FACTOR = 15; // % to increase speed
  private final double ENCODERE_ERROR_THRESHOLD = 10;

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
    currDistance = 0;
    speed = Math.max(speed, MIN_SPEED);
    speed = Math.min(speed, MAX_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currDistance = Math.abs((drivetrain.getLeftEncoderDistance() + drivetrain.getRightEncoderDistance())/2);
    double DistanceError = distance - currDistance;
    motorSpeeds = PIDCalc(speed, DistanceError, drivetrain.getLeftEncoderRaw(), drivetrain.getRightEncoderRaw());
    drivetrain.drive(motorSpeeds[0], motorSpeeds[1], false);
    if (Constants.ENABLE_DEBUG) {
       SmartShuffleboard.put("Drive", "Speeds", "Speed", speed);
       SmartShuffleboard.put("Drive", "Speeds", "LeftS", motorSpeeds[0]);
       SmartShuffleboard.put("Drive", "Speeds", "RightS", motorSpeeds[1]);
    }
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

  public double[] PIDCalc(double speed, double distanceError, double leftEncoder, double rightEncoder) {
    double[] speeds = {0,0};   // left speed, right speed
    double speedupFactor = 1;
    double encoderError =  Math.abs(leftEncoder - rightEncoder);
    if (encoderError > ENCODERE_ERROR_THRESHOLD) {
      speedupFactor = 1 + SPEEDUP_FACTOR/100;
    }
    SmartShuffleboard.put("Drive", "Speeds", "speed up", speedupFactor);

    if (speed != 0) {
       if (distanceError < SLOW_DOWN_DISTANCE) {
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
       if (leftEncoder > rightEncoder) {
          speeds[1] *= speedupFactor;
       } else if (rightEncoder > leftEncoder) {
          speeds[0] *= speedupFactor;
       }
    }
    return speeds;
  }
}
