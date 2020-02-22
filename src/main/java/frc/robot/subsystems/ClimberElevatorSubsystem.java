/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.diag.DiagTalonSrxSwitch;
import frc.robot.utils.diag.DiagTalonSrxSwitch.Direction;

public class ClimberElevatorSubsystem extends SubsystemBase {
  private WPI_TalonSRX climberMotor;
  private Solenoid climberSolenoid;

  /**
   * Creates a new ClimberSubsystem.
   */
  public ClimberElevatorSubsystem() {
    climberMotor = new WPI_TalonSRX(Constants.CLIMBER_ELEVATOR_ID);
    climberSolenoid = new Solenoid(Constants.PCM_CAN_ID, Constants.CLIMBER_PISTON_ID);

    int TIMEOUT = 100;

    climberMotor.configNominalOutputForward(0, TIMEOUT);
    climberMotor.configNominalOutputReverse(0, TIMEOUT);
    climberMotor.configPeakOutputForward(1, TIMEOUT);
    climberMotor.configPeakOutputReverse(-1, TIMEOUT);
    climberMotor.setNeutralMode(NeutralMode.Brake);
    climberMotor.setInverted(false);
    
    climberMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    climberMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

    Robot.getDiagnostics().addDiagnosable(new DiagTalonSrxSwitch("Climber Elevator Forward Switch", climberMotor, Direction.FORWARD));
    Robot.getDiagnostics().addDiagnosable(new DiagTalonSrxSwitch("Climber Elevator Reverse Switch", climberMotor, Direction.REVERSE));
  }

  public void setClimber(double speed) {
    climberMotor.set(speed);
  }

  public void stopClimber() {
    climberMotor.set(0);
  }

  public void extendPiston() {
    climberSolenoid.set(true);
  }

  public void retractPiston() {
    climberSolenoid.set(false);
  }

  public boolean getPistonState() {
    return climberSolenoid.get();
  }

  public boolean getBottomSwitch() {
    return climberMotor.getSensorCollection().isRevLimitSwitchClosed();
  }

  public boolean getTopSwitch(){
    return climberMotor.getSensorCollection().isFwdLimitSwitchClosed();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Constants.ENABLE_DEBUG == true){
      SmartShuffleboard.put("Climber", "Top Switch", getTopSwitch());
      SmartShuffleboard.put("Climber", "Bottom Switch", getBottomSwitch());
    }
   

  }
}
