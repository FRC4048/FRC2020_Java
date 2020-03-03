package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
  private final double MIN_SPEED = 0.2;
  private final double MAX_SPEED = 0.6;
  private final double MIN_BACK_SPEED = -0.25;
  private final double MAX_BACK_SPEED = -0.8;
  private final double SLOW_DOWN_DISTANCE = 1; //The distance to start the pid calculation 
  private final double MAX_SPEEDUP = 40; // % to increase speed
  private final double GYRO_ERROR_THRESHOLD = 0.1;
  private final double MAX_ERROR = 15;
  private double currAngle;
  private double speedupFactor = 1;
  private double angle;
  
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
    angle = driveTrain.getAngle() * -1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currDistance = Math.abs((driveTrain.getLeftEncoderDistance() + driveTrain.getRightEncoderDistance())/2);
    double DistanceError = Math.abs(distance - currDistance);
    motorSpeeds = PIDCalc(speed, DistanceError);
    driveTrain.drive(motorSpeeds[0], motorSpeeds[1], false);
    if (Constants.ENABLE_DEBUG) {
      SmartShuffleboard.put("Drive", "Speeds", "Speed", speed);
      SmartShuffleboard.put("Drive", "Speeds", "LeftS", motorSpeeds[0]);
      SmartShuffleboard.put("Drive", "Speeds", "RightS", motorSpeeds[1]);
      SmartShuffleboard.put("Drive", "Angle", currAngle);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return currDistance >= distance;
  }

  public double[] PIDCalc(double speed, double distanceError) {
    double[] speeds = {0,0};   // left speed, right speed
    currAngle = driveTrain.getAngle() * -1;
    double angleError = currAngle - angle;

    if (Math.abs(driveTrain.getAngle()) > GYRO_ERROR_THRESHOLD) {
      speedupFactor = 1 + ((Math.abs(angleError)/MAX_ERROR) * MAX_SPEEDUP)/100;
    }

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
      if (speed > 0) {
        if (currAngle > GYRO_ERROR_THRESHOLD) {
          speeds[1] *= speedupFactor;
        } else if (currAngle < GYRO_ERROR_THRESHOLD) {
          speeds[0] *= speedupFactor;
        }
      } else if (speed < 0) {
        if (currAngle > GYRO_ERROR_THRESHOLD){
          speeds[0] *= speedupFactor;
        } else if (currAngle < GYRO_ERROR_THRESHOLD) {
          speeds[1] *= speedupFactor;
        }
      }
    }
    return speeds;
  }
}