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

public class ConveyorSubsystem extends SubsystemBase {

  private WPI_TalonSRX conveyorMotor;
   private static DigitalInputGroup slot2;
   private static DigitalInputGroup slot3;
   private static DigitalInputGroup slot4;
//  private static DigitalInput slot2;
//  private static DigitalInput slot3;
//  private static DigitalInput slot4;
  private int commandCounter;

  public ConveyorSubsystem() {
    conveyorMotor = new WPI_TalonSRX(Constants.CONVEYOR_MOTOR_ID);

//    slot2 = new DigitalInput(2);
//    slot3 = new DigitalInput(3);
//    slot4 = new DigitalInput(4);
     slot2 = new DigitalInputGroup(new DigitalInput(Constants.SLOT2_A_ID), new DigitalInput(Constants.SLOT2_B_ID));
     slot3 = new DigitalInputGroup(new DigitalInput(Constants.SLOT3_A_ID), new DigitalInput(Constants.SLOT3_B_ID));
     slot4 = new DigitalInputGroup(new DigitalInput(Constants.SLOT4_A_ID), new DigitalInput(Constants.SLOT4_B_ID));
  }

  @Override
  public void periodic() {
    SmartShuffleboard.put("Driver", "Slot2", getSlot2());
    SmartShuffleboard.put("Driver", "Slot3", getSlot3());
    SmartShuffleboard.put("Driver", "Slot4", getSlot4());
  }
  
  public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {
    protected void addAll() {
      add("slot2", getSlot2());
      add("slot3", getSlot3());
      add("slot4", getSlot4());
      add("Conveyor Motor Running?", conveyorMotor.get() != 0 ? true : false);
    }
  };
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
    return !slot2.get();
  }
  /**
   * Returns state of Slot 3
   * 
   * @return boolean state of slot 3
   */
  public static boolean getSlot3() {
    return !slot3.get();
  }
  /**
   * Returns state of Slot 4
   * 
   * @return boolean state of slot 4
   */
  public static boolean getSlot4() {
    return !slot4.get();
  }

  /**
   * Checks if any commands are currently scheduled.
   * 
   * @return if the counter == 0
   */
  public boolean isCommandAvalible() {
    return commandCounter == 0;
  }

  /**
   * Adds one to the counter returns the counter
   * 
   * @return commandCounter
   */
  public int commandStarted() {
    return ++commandCounter;
  }

  /**
   * Subtracts one from the counter then returns the counter
   * 
   * @return commandCounter
   */
  public int commandEnded(){
    return --commandCounter;
  }

}