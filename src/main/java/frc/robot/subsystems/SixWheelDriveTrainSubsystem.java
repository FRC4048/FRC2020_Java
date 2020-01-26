package frc.robot.subsystems;

import java.nio.file.Paths;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SixWheelDriveTrainSubsystem extends SubsystemBase {
  private WPI_TalonSRX left1;
  private WPI_TalonSRX right1;
  private WPI_TalonSRX left2;
  private WPI_TalonSRX right2;
  private DifferentialDrive driveTrain;
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  private AHRS navX;
  private final DifferentialDriveOdometry driveOdometry;

  /**
   * Creates a new DriveTrain.
   */
  public SixWheelDriveTrainSubsystem() {
    left1 = new WPI_TalonSRX(Constants.MOTOR_LEFT1_ID);
    left2 = new WPI_TalonSRX(Constants.MOTOR_LEFT2_ID);
    right1 = new WPI_TalonSRX(Constants.MOTOR_RIGHT1_ID);
    right2 = new WPI_TalonSRX(Constants.MOTOR_RIGHT2_ID);
    leftEncoder = new Encoder(Constants.DRIVE_ENCODER_LEFT_ID[0], Constants.DRIVE_ENCODER_LEFT_ID[1]);
    rightEncoder = new Encoder(Constants.DRIVE_ENCODER_RIGHT_ID[0], Constants.DRIVE_ENCODER_RIGHT_ID[1], true);
    navX = new AHRS(SPI.Port.kMXP);

    left2.set(ControlMode.Follower, Constants.MOTOR_LEFT1_ID);
    right2.set(ControlMode.Follower, Constants.MOTOR_RIGHT1_ID);

    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getAngle()));
    right1.setInverted(true);
    right2.setInverted(true);

    driveTrain = new DifferentialDrive(left1, right1);

    leftEncoder.setDistancePerPulse(Constants.DRIVE_ENCODER_DISTANCE_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.DRIVE_ENCODER_DISTANCE_PER_PULSE);
  }

  /**
   * Drives the robot by a speed between -1 and +1
   * 
   * @param speedLeft
   * @param speedRight
   */
  public void drive(double speedLeft, double speedRight){
    speedLeft = Math.signum(speedLeft) * Math.pow(speedLeft, 2);
    speedRight = Math.signum(speedRight) * Math.pow(speedRight, 2);
    // driveTrain.tankDrive(speedLeft, speedRight);
    //The joysticks are inverted so inverting this makes it drive correctly.
    left1.set(-speedLeft);
    right1.set(-speedRight);
  }

  /**
   * Resets the Gyro
   */
  public void resetGyro() {
    navX.reset();
  }

  /**
   * Gets the angle of the robot
   * 
   * @return angle of robot between -180-180
   */
  public double getAngle() {
    return Math.IEEEremainder(navX.getAngle(), 360) * -1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
    //Updating the odemetry on regular basis
    driveOdometry.update(Rotation2d.fromDegrees(getAngle()), leftEncoder.getDistance(), rightEncoder.getDistance());
  }

  /**
   * Returns the current pose of the robot
   * 
   * @return The pose
   */
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  /**
   * Resets the drive encoders
   */
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  /**
   * Resets the Ododemtry to a specified pose
   * 
   * @param pose
   */
  public void resetOdodemtry(Pose2d pose) {
    resetEncoders();
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getAngle()));
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
  }

  /**
   * Sets the drivetrain based on a voltage between -12v and +12v
   * 
   * @param leftVolts
   * @param rightVolts
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    left1.setVoltage(leftVolts);
    right1.setVoltage(rightVolts);
  }
}
