/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.ConveyorStateMachine.State;
import frc.robot.utils.DigitalInputGroup;

public class ConveyorSubsystem extends SubsystemBase {

  private WPI_TalonSRX stageMotor;
  private WPI_TalonSRX conveyorMotor;
  private WPI_TalonSRX shooterMotor;
  private DigitalInputGroup slot1;
  private DigitalInputGroup slot2;
  private DigitalInputGroup slot3;
  private DigitalInputGroup slot4;
  private DigitalInputGroup slot5;

  

  public ConveyorSubsystem() {
    stageMotor = new WPI_TalonSRX(Constants.STAGER_MOTOR_ID);
    conveyorMotor = new WPI_TalonSRX(Constants.CONVEYOR_MOTOR_ID);
    shooterMotor = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_ID);

    slot1 = new DigitalInputGroup(new DigitalInput(Constants.SLOT1_A_ID), new DigitalInput(Constants.SLOT1_B_ID));
    slot2 = new DigitalInputGroup(new DigitalInput(Constants.SLOT2_A_ID), new DigitalInput(Constants.SLOT2_B_ID));
    slot3 = new DigitalInputGroup(new DigitalInput(Constants.SLOT3_A_ID), new DigitalInput(Constants.SLOT3_B_ID));
    slot4 = new DigitalInputGroup(new DigitalInput(Constants.SLOT4_A_ID), new DigitalInput(Constants.SLOT4_B_ID));
    slot5 = new DigitalInputGroup(new DigitalInput(Constants.SLOT5_A_ID), new DigitalInput(Constants.SLOT5_B_ID));
  }

  @Override
  public void periodic() {

  }

  /**
   * Sets the stager (M3) to a set value
   * 
   * @param speed
   */
  public void moveStager(double speed) {
    stageMotor.set(speed);
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
  public boolean getSlot1() {
    return slot1.get();
  }
  /**
   * Returns state of Slot 2
   * 
   * @return boolean state of slot 2
   */
  public boolean getSlot2() {
    return slot2.get();
  }
  /**
   * Returns state of Slot 3
   * 
   * @return boolean state of slot 3
   */
  public boolean getSlot3() {
    return slot3.get();
  }
  /**
   * Returns state of Slot 4
   * 
   * @return boolean state of slot 4
   */
  public boolean getSlot4() {
    return slot4.get();
  }
  /**
   * Returns state of Slot 5
   * 
   * @return boolean state of slot 5
   */
  public boolean getSlot5() {
    return slot5.get();
  }

  public State getState() {
    return ConveyorStateMachine.getState(getSlot1(), getSlot2(), getSlot3(), getSlot4(), getSlot5());
  }

}