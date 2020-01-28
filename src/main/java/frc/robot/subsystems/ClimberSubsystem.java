/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  private Spark winchMotor; 
  private Spark climberMotor;
  private Solenoid climberSolenoid;
  private DigitalInput climberSwitch;
  /**
   * Creates a new ClimberSubsystem.
   */
  public ClimberSubsystem() {
    winchMotor = new Spark(Constants.WINCH_MOTOR_PWM);
    climberMotor = new Spark(Constants.LIFT_MOTOR_PWM);
    climberSolenoid = new Solenoid(Constants.PCM_CAN_ID, Constants.CLIMBER_SOLENOID);
    climberSwitch = new DigitalInput(Constants.CLIMBER_SWITCH_CHANNEL);
  }

  public void setWinch(double speed) {
    winchMotor.set(speed);
  }

  public void stopWinch() {
    winchMotor.set(0);
  }

  public void setClimber(double speed) {
    climberMotor.set(speed);
  }

  public void stopClimber() {
    climberMotor.set(0);
  }

  public void extend() {
    climberSolenoid.set(true);
  }

  public void retract() {
    climberSolenoid.set(false);
  }
  public boolean getState() {
    return climberSolenoid.get();
  }

  public boolean isPressed() {
    return climberSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
