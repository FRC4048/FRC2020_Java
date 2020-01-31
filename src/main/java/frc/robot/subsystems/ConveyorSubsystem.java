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

  /**
   * https://docs.google.com/spreadsheets/d/1z1pBTtl-SJKcTKWXfU3sdGcWpE97y6huKPtR8nkVKk4/edit?usp=drivesdk
   */
  public enum State { 
    S0,S1,S2,S3,S4,S5,S6,S7,S8,S9,S10,S11,S12,S13,S14,S15,S16,S17,S18,S19,S20,S21,S22,S23,S24,S25,
    S26,S27,S28,S29,S30,S31, S32
  }

  public ConveyorSubsystem() {
    stageMotor = new WPI_TalonSRX(Constants.STAGE_MOTOR_ID);
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

  /**
   * Gets the current state of the subsystem
   * @return The state
   */
  public State getState() {
    if(getSlot1() == false && getSlot2() == false && getSlot3() == false && getSlot4() == false && getSlot5()) {
      return State.S1;
    } else if(getSlot1() == false && getSlot2() == false && getSlot3() == false && getSlot4() && getSlot5() == false) {
      return State.S2;
    } else if(getSlot1() == false && getSlot2() == false && getSlot3() == false && getSlot4() && getSlot5()) {
      return State.S3;
    } else if(getSlot1() == false && getSlot2() == false && getSlot3() && getSlot4() == false && getSlot5() == false) {
      return State.S4;
    } else if(getSlot1() == false && getSlot2() == false && getSlot3() && getSlot4() == false && getSlot5()) {
      return State.S5;
    } else if(getSlot1() == false && getSlot2() == false && getSlot3() && getSlot4() && getSlot5() == false) {
      return State.S6;
    } else if(getSlot1() == false && getSlot2() == false && getSlot3() && getSlot4() && getSlot5()) {
      return State.S7;
    } else if(getSlot1() == false && getSlot2() && getSlot3() == false && getSlot4() == false && getSlot5() == false) {
      return State.S8;
    } else if(getSlot1() == false && getSlot2() && getSlot3() == false && getSlot4() == false && getSlot5()) {
      return State.S9;
    } else if(getSlot1() == false && getSlot2() && getSlot3() == false && getSlot4() && getSlot5() == false) {
      return State.S10;
    } else if (getSlot1() == false && getSlot2() && getSlot3() == false && getSlot4() && getSlot5()) {
      return State.S11;
    } else if (getSlot1() == false && getSlot2() && getSlot3() && getSlot4() == false && getSlot5() == false) {
      return State.S12;
    } else if (getSlot1() == false && getSlot2() && getSlot3() && getSlot4() == false && getSlot5()) {
      return State.S13;
    } else if (getSlot1() == false && getSlot2() && getSlot3() && getSlot4() && getSlot5() == false) {
      return State.S14;
    } else if (getSlot1() == false && getSlot2() && getSlot3() && getSlot4() && getSlot5()) {
      return State.S15;
    } else if (getSlot1() && getSlot2() == false && getSlot3() == false && getSlot4() == false && getSlot5() == false) {
      return State.S16;
    } else if (getSlot1() && getSlot2() == false && getSlot3() == false && getSlot4() == false && getSlot5()) {
      return State.S17;
    } else if (getSlot1() && getSlot2() == false && getSlot3() == false && getSlot4() && getSlot5() == false) {
      return State.S18;
    } else if (getSlot1() && getSlot2() == false && getSlot3() == false && getSlot4() && getSlot5()) {
      return State.S19;
    } else if (getSlot1() && getSlot2() == false && getSlot3() && getSlot4() == false && getSlot5() == false) {
      return State.S20;
    } else if (getSlot1() && getSlot2() == false && getSlot3() && getSlot4() == false && getSlot5()) {
      return State.S21;
    } else if (getSlot1() && getSlot2() == false && getSlot3() && getSlot4() && getSlot5() == false) {
      return State.S22;
    } else if (getSlot1() && getSlot2() == false && getSlot3() && getSlot4() && getSlot5()) {
      return State.S23;
    } else if (getSlot1() && getSlot2() && getSlot3() == false && getSlot4() == false && getSlot5() == false) {
      return State.S24;
    } else if (getSlot1() && getSlot2() && getSlot3() == false && getSlot4() == false && getSlot5()) {
      return State.S25;
    } else if (getSlot1() && getSlot2() && getSlot3() == false && getSlot4() && getSlot5() == false) {
      return State.S26;
    } else if (getSlot1() && getSlot2() && getSlot3() == false && getSlot4() && getSlot5()) {
      return State.S27;
    } else if (getSlot1() && getSlot2() && getSlot3() && getSlot4() == false && getSlot5() == false) {
      return State.S28;
    } else if (getSlot1() && getSlot2() && getSlot3() && getSlot4() == false && getSlot5()) {
      return State.S29;
    } else if (getSlot1() && getSlot2() && getSlot3() && getSlot4() && getSlot5() == false) {
      return State.S30;
    } else if (getSlot1() && getSlot2() && getSlot3() && getSlot4() && getSlot5()) {
      return State.S31;
    } else if (getSlot1() == false && getSlot2() == false && getSlot3() == false && getSlot4() == false && getSlot5() == false) {
      return State.S0;
    } else {
      return State.S32;
    }
  }

  public State wantedState(State state) {
    switch(state) {
    case S0:
      return State.S1;
    case S1:
      return State.S2;
    case S2:
      return State.S3;
    case S3:
      return State.S6;
    case S4:
      return State.S8;
    case S5:
      return State.S9;
    case S6: 
      return State.S7;
    case S7: 
      return State.S14;
    case S8: 
      return State.S16;
    case S9:
      return State.S18;
    case S10:
      return State.S11;
    case S11:
      return State.S22;
    case S12:
      return State.S13;
    case S13: 
      return State.S26;
    case S14:
      return State.S15;
    case S15:
      return State.S30;
    case S16:
      return State.S17;
    case S17:
      return State.S18;
    case S18:
      return State.S19;
    case S19:
      return State.S22;
    case S20:
      return State.S21;
    case S21:
      return State.S26;
    case S22:
      return State.S23;
    case S23:
      return State.S30;
    case S24:
      return State.S25;
    case S25: 
      return State.S32;
    case S26:
      return State.S27;
    case S27:
      return State.S32;
    case S28:
      return State.S29; 
    case S29:
      return State.S32;
    case S30:
      return State.S31;
    case S31:               
      return State.S32;
    case S32:
      return State.S32;
    default:
      return State.S32; //This is just saying we must shoot
    }
  }
}