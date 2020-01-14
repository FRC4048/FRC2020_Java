package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    left1 = new WPI_TalonSRX(Constants.MOTOR_LEFT1_ID);
    left2 = new WPI_TalonSRX(Constants.MOTOR_LEFT2_ID);
    right1 = new WPI_TalonSRX(Constants.MOTOR_RIGHT1_ID);
    right2 = new WPI_TalonSRX(Constants.MOTOR_RIGHT2_ID);
    leftEncoder = new Encoder(2,3);
    rightEncoder = new Encoder(0,1);
    left2.set(ControlMode.Follower, Constants.MOTOR_LEFT1_ID);
    right2.set(ControlMode.Follower, Constants.MOTOR_RIGHT1_ID);
    
    left1.setInverted(true);
    left2.setInverted(true);
    right1.setInverted(true);
    right2.setInverted(true);

    driveTrain = new DifferentialDrive(left1, right1);
  }

  public void drive(double speedLeft, double speedRight){
    // speedLeft = Math.signum(speedLeft) * Math.pow(speedLeft, 2);
    // speedRight = Math.signum(speedRight) * Math.pow(speedRight, 2);
    driveTrain.tankDrive(speedLeft, speedRight);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
  }
  
}