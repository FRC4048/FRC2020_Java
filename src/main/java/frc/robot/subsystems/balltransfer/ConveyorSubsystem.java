/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.balltransfer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.DigitalInputGroup;

public class ConveyorSubsystem extends SubsystemBase {

  private WPI_TalonSRX conveyorMotor;
  private static DigitalInputGroup slot2;
  private static DigitalInputGroup slot3;
  private static DigitalInputGroup slot4;

  

  public ConveyorSubsystem() {
    conveyorMotor = new WPI_TalonSRX(Constants.CONVEYOR_MOTOR_ID);

    slot2 = new DigitalInputGroup(new DigitalInput(Constants.SLOT2_A_ID), new DigitalInput(Constants.SLOT2_B_ID));
    slot3 = new DigitalInputGroup(new DigitalInput(Constants.SLOT3_A_ID), new DigitalInput(Constants.SLOT3_B_ID));
    slot4 = new DigitalInputGroup(new DigitalInput(Constants.SLOT4_A_ID), new DigitalInput(Constants.SLOT4_B_ID));
  }

  @Override
  public void periodic() {

  }

  /**
   * Sets the conveyor (M2) to a set value
   * 
   * @param speed
   */
  public void moveConveyor(double speed) {
    conveyorMotor.set(speed);
  }

  /**
   * Returns state of Slot 2
   * 
   * @return boolean state of slot 2
   */
  public static boolean getSlot2() {
    return slot2.get();
  }
  /**
   * Returns state of Slot 3
   * 
   * @return boolean state of slot 3
   */
  public static boolean getSlot3() {
    return slot3.get();
  }
  /**
   * Returns state of Slot 4
   * 
   * @return boolean state of slot 4
   */
  public static boolean getSlot4() {
    return slot4.get();
  }



}