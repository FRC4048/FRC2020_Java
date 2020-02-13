/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StallMotorTest extends SubsystemBase {
  private WPI_TalonSRX stallMotorTest;
  /**
   * Creates a new StallMotorTest.
   */
  public StallMotorTest() {
    stallMotorTest = new WPI_TalonSRX(1);

  }

  public void drive(double speed){
    speed = Math.signum(speed) * Math.pow(speed, 2);
    stallMotorTest.set(-speed);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
