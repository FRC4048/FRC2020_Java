/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  private WPI_TalonSRX winchMotor; 
  private WPI_TalonSRX climberMotor;
  private Solenoid climberSolenoid;
  private DigitalInput climberSwitch;
  private boolean state = false;

  private final int TIMEOUT = 100;

  /**
   * Creates a new ClimberSubsystem.
   */
  public ClimberSubsystem() {
    winchMotor = new WPI_TalonSRX(Constants.WINCH_MOTOR_CAN);
    climberMotor = new WPI_TalonSRX(Constants.CLIMBER_MOTOR_CAN);
    climberSolenoid = new Solenoid(Constants.PCM_CAN_ID, Constants.CLIMBER_SOLENOID);
    climberSwitch = new DigitalInput(Constants.CLIMBER_SWITCH_CHANNEL); //TODO: THIS WILL CHANGE TO GOING THROUGH THE TALON

    winchMotor.configNominalOutputForward(0, TIMEOUT);
    winchMotor.configNominalOutputReverse(0, TIMEOUT);
    winchMotor.configPeakOutputForward(1, TIMEOUT);
    winchMotor.configPeakOutputReverse(-1, TIMEOUT);
    winchMotor.setNeutralMode(NeutralMode.Brake);

    climberMotor.configNominalOutputForward(0, TIMEOUT);
    climberMotor.configNominalOutputReverse(0, TIMEOUT);
    climberMotor.configPeakOutputForward(1, TIMEOUT);
    climberMotor.configPeakOutputReverse(-1, TIMEOUT);
    climberMotor.setNeutralMode(NeutralMode.Brake);
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
    state = true;
  }

  public void retract() {
    climberSolenoid.set(false);
    state = false;
  }

  public boolean getState() {
    return state;
  }

  public boolean isPressed() {
    return climberSwitch.get();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
