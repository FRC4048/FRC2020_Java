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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.diag.DiagOpticalSensor;
import frc.robot.utils.logging.Logging;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_TalonSRX intakeMotor;
  private Solenoid piston;
  private static boolean isRunning;
  private static DigitalInput intakeSensor;

  /**
   * Creates a new IntakeSubsystem.
   */
  public IntakeSubsystem() {
    intakeMotor = new WPI_TalonSRX(Constants.INTAKE_MOTOR_ID);
    piston = new Solenoid(Constants.PCM_CAN_ID, Constants.INTAKE_PISTON_ID);
    intakeSensor = new DigitalInput(Constants.INTAKE_SENSOR);

    int TIMEOUT = 100;

    intakeMotor.configNominalOutputForward(0, TIMEOUT);
    intakeMotor.configNominalOutputReverse(0, TIMEOUT);
    intakeMotor.configPeakOutputForward(1, TIMEOUT);
    intakeMotor.configPeakOutputReverse(-1, TIMEOUT);
    intakeMotor.setNeutralMode(NeutralMode.Brake);
    intakeMotor.setInverted(true);
    isRunning = false;

    Robot.getDiagnostics().addDiagnosable(new DiagOpticalSensor("Intake Sensor Diagnostics", intakeSensor));

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
    piston.set(true);
  }

  public void retractPiston() {
    piston.set(false);
  }

  public void spinMotor(double speed) {
    intakeMotor.set(speed);
  }

  public static void setIsRunning(boolean run) {
    isRunning = run;
  }

  public static boolean getIsRunning() {
    return isRunning;
  }
  public static boolean getIntakeSensor() {
    return !intakeSensor.get();
  }
}