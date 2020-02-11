/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.logging.Logging;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_TalonSRX intakeMotor;
  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    intakeMotor = new WPI_TalonSRX(Constants.MOTOR_LEFT1_ID);
  }

  public Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {

    @Override
    protected void addAll() {
      add("Applied Voltage to Intake Motor", intakeMotor.getMotorOutputVoltage());
    }
  };

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deployPiston() {
    System.out.println("deploy");
  }
  
  public void retractPiston() {
    System.out.println("retract");
  }

  public void spinMotor(double speed){
    System.out.println("spin");
  }

}
