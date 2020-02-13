/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.logging.Logging;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_TalonSRX intakeMotor;
  private Solenoid piston;

  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    intakeMotor = new WPI_TalonSRX(Constants.MOTOR_LEFT1_ID);
    piston = new Solenoid(Constants.INTAKE_PISTON_ID);
  
    int TIMEOUT = 100;

    intakeMotor.configNominalOutputForward(0, TIMEOUT);
    intakeMotor.configNominalOutputReverse(0, TIMEOUT);
    intakeMotor.configPeakOutputForward(1, TIMEOUT);
    intakeMotor.configPeakOutputReverse(-1, TIMEOUT);
    intakeMotor.setNeutralMode(NeutralMode.Brake);
  }

  public Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {

    @Override
    protected void addAll() {
      add("Intake Motor Applied Voltage", intakeMotor.getMotorOutputVoltage());
    }
  };

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deployPiston() {
    System.out.println("deploy");
    piston.set(true);
  }

  public void retractPiston() {
    System.out.println("retract");
    piston.set(false);
  }

  public void spinMotor(double speed){
    System.out.println("spin");
    intakeMotor.set(speed);
  }
}