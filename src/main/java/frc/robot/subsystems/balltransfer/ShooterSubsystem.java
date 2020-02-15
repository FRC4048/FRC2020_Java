/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.balltransfer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.DigitalInputGroup;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.logging.Logging;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_TalonSRX shooterMotor;
  private static DigitalInputGroup slot1;
//  private static DigitalInput slot1;

  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {
    shooterMotor = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_ID);
    slot1 = new DigitalInputGroup(new DigitalInput(Constants.SLOT1_A_ID), new DigitalInput(Constants.SLOT1_B_ID));
//    slot1 = new DigitalInput(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartShuffleboard.put("Driver", "Slot1", getSlot1());
  }

  public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {
    protected void addAll() {
      add("slot1", getSlot1());
      add("Shooter Motor Running?", shooterMotor.get() != 0 ? true : false);
    }
  };

  /**
   * Sets the shooter (M1) to a set value
   * 
   * @param speed
   */
  public void moveShooter(double speed) {
    shooterMotor.set(speed);
  }

  /**
   * Returns state of Slot 1
   * 
   * @return boolean state of slot 1
   */
  public static boolean getSlot1() {
    return !slot1.get();
  }

}
