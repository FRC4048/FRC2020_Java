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

  public void moveStager(double speed) {
    stageMotor.set(speed);
  } 

  public void moveConveyor(double speed) {
    conveyorMotor.set(speed);
  }

  public void moveShooter(double speed) {
    shooterMotor.set(speed);
  } 

  public boolean getSlot1() {
    return slot1.get();
  }
  
  public boolean getSlot2() {
    return slot2.get();
  }
  
  public boolean getSlot3() {
    return slot3.get();
  }
  
  public boolean getSlot4() {
    return slot4.get();
  }
  
  public boolean getSlot5() {
    return slot5.get();
  }
}
