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

public class DriveTrain extends SubsystemBase {
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
  public DriveTrain() {
    left1 = new WPI_TalonSRX(Constants.MOTOR_LEFT1_ID);
    left2 = new WPI_TalonSRX(Constants.MOTOR_LEFT2_ID);
    right1 = new WPI_TalonSRX(Constants.MOTOR_RIGHT1_ID);
    right2 = new WPI_TalonSRX(Constants.MOTOR_RIGHT2_ID);
    leftEncoder = new Encoder(2,3);
    rightEncoder = new Encoder(0,1, true);
    navX = new AHRS(SPI.Port.kMXP);

    left2.set(ControlMode.Follower, Constants.MOTOR_LEFT1_ID);
    right2.set(ControlMode.Follower, Constants.MOTOR_RIGHT1_ID);

    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getAngle()));
    // left1.setInverted(true);
    // left2.setInverted(true);
    right1.setInverted(true);
    right2.setInverted(true);

    //Todo: set the distance per pulse of the encoders

    driveTrain = new DifferentialDrive(left1, right1);

    leftEncoder.setDistancePerPulse(0.00303832381);
    rightEncoder.setDistancePerPulse(0.00303832381);
  }

  public void drive(double speedLeft, double speedRight){
    speedLeft = Math.signum(speedLeft) * Math.pow(speedLeft, 2);
    speedRight = Math.signum(speedRight) * Math.pow(speedRight, 2);
    // driveTrain.tankDrive(speedLeft, speedRight);
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
    SmartDashboard.putNumber("Angle", getAngle());
    SmartDashboard.putString("Pose", getPose().toString());
  }

  /**
   * Returns the current pose of the robot
   * 
   * @return The pose
   */
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

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

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    left1.setVoltage(leftVolts);
    right1.setVoltage(rightVolts);
  }
}



// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.kauailabs.navx.frc.AHRS;

// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.PWMVictorSPX;
// import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.geometry.Pose2d;
// import edu.wpi.first.wpilibj.geometry.Rotation2d;
// import edu.wpi.first.wpilibj.interfaces.Gyro;
// import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
// import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class DriveTrain extends SubsystemBase {
//   // The motors on the left side of the drive.
//   private final SpeedControllerGroup m_leftMotors =
//       new SpeedControllerGroup(new WPI_TalonSRX(Constants.MOTOR_LEFT1_ID),
//                                new WPI_TalonSRX(Constants.MOTOR_LEFT2_ID));

//   // The motors on the right side of the drive.
//   private final SpeedControllerGroup m_rightMotors =
//       new SpeedControllerGroup(new WPI_TalonSRX(Constants.MOTOR_RIGHT1_ID),
//                                new WPI_TalonSRX(Constants.MOTOR_RIGHT2_ID));

//   // The robot's drive
//   private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

//   // The left-side drive encoder
//   private final Encoder m_leftEncoder =
//       new Encoder(Constants.DRIVE_ENCODER_LEFT_ID[0], Constants.DRIVE_ENCODER_LEFT_ID[1]);

//   // The right-side drive encoder
//   private final Encoder m_rightEncoder =
//       new Encoder(Constants.DRIVE_ENCODER_RIGHT_ID[0], Constants.DRIVE_ENCODER_RIGHT_ID[1],
//                   true);

//   // The gyro sensor
//   private final AHRS m_gyro = new AHRS(SPI.Port.kMXP);

//   // Odometry class for tracking robot pose
//   private final DifferentialDriveOdometry m_odometry;

//   /**
//    * Creates a new DriveSubsystem.
//    */
//   public DriveTrain() {
//     // Sets the distance per pulse for the encoders
//     m_leftEncoder.setDistancePerPulse(0.00303832381);
//     m_rightEncoder.setDistancePerPulse(0.00303832381);
//     zeroHeading();
//     resetEncoders();
//     m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
//   }

//   @Override
//   public void periodic() {
//     SmartDashboard.putNumber("Gyro", getHeading());
//     // Update the odometry in the periodic block
//     m_odometry.update(Rotation2d.fromDegrees(getHeading()), m_leftEncoder.getDistance(),
//                       m_rightEncoder.getDistance());
//   }

//   public void drive(double i, double x) {}
//   /**
//    * Returns the currently-estimated pose of the robot.
//    *
//    * @return The pose.
//    */
//   public Pose2d getPose() {
//     return m_odometry.getPoseMeters();
//   }

//   /**
//    * Returns the current wheel speeds of the robot.
//    *
//    * @return The current wheel speeds.
//    */
//   public DifferentialDriveWheelSpeeds getWheelSpeeds() {
//     return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
//   }

//   /**
//    * Resets the odometry to the specified pose.
//    *
//    * @param pose The pose to which to set the odometry.
//    */
//   public void resetOdometry(Pose2d pose) {
//     resetEncoders();
//     m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
//   }

//   /**
//    * Drives the robot using arcade controls.
//    *
//    * @param fwd the commanded forward movement
//    * @param rot the commanded rotation
//    */
//   public void arcadeDrive(double fwd, double rot) {
//     m_drive.arcadeDrive(fwd, rot);
//   }

//   /**
//    * Controls the left and right sides of the drive directly with voltages.
//    *
//    * @param leftVolts  the commanded left output
//    * @param rightVolts the commanded right output
//    */
//   public void tankDriveVolts(double leftVolts, double rightVolts) {
//     m_leftMotors.setVoltage(leftVolts);
//     m_rightMotors.setVoltage(-rightVolts);
//   }

//   /**
//    * Resets the drive encoders to currently read a position of 0.
//    */
//   public void resetEncoders() {
//     m_leftEncoder.reset();
//     m_rightEncoder.reset();
//   }

//   /**
//    * Gets the average distance of the two encoders.
//    *
//    * @return the average of the two encoder readings
//    */
//   public double getAverageEncoderDistance() {
//     return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
//   }

//   /**
//    * Gets the left drive encoder.
//    *
//    * @return the left drive encoder
//    */
//   public Encoder getLeftEncoder() {
//     return m_leftEncoder;
//   }

//   /**
//    * Gets the right drive encoder.
//    *
//    * @return the right drive encoder
//    */
//   public Encoder getRightEncoder() {
//     return m_rightEncoder;
//   }

//   /**
//    * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
//    *
//    * @param maxOutput the maximum output to which the drive will be constrained
//    */
//   public void setMaxOutput(double maxOutput) {
//     m_drive.setMaxOutput(maxOutput);
//   }

//   /**
//    * Zeroes the heading of the robot.
//    */
//   public void zeroHeading() {
//     m_gyro.reset();
//   }

//   /**
//    * Returns the heading of the robot.
//    *
//    * @return the robot's heading in degrees, from 180 to 180
//    */
//   public double getHeading() {
//     return Math.IEEEremainder(m_gyro.getAngle(), 360);
//   }

//   /**
//    * Returns the turn rate of the robot.
//    *
//    * @return The turn rate of the robot, in degrees per second
//    */
//   public double getTurnRate() {
//     return m_gyro.getRate();
//   }
// }