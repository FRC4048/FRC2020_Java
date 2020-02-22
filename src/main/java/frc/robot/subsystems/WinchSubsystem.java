package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WinchSubsystem extends SubsystemBase {
    private WPI_TalonSRX winchMotor;
    public WinchSubsystem(){
        winchMotor = new WPI_TalonSRX(Constants.CLIMBER_WINCH_ID);

        int TIMEOUT = 100;

        winchMotor.configNominalOutputForward(0, TIMEOUT);
        winchMotor.configNominalOutputReverse(0, TIMEOUT);
        winchMotor.configPeakOutputForward(1, TIMEOUT);
        winchMotor.configPeakOutputReverse(-1, TIMEOUT);
        winchMotor.setNeutralMode(NeutralMode.Brake);
        winchMotor.setInverted(true);
    }

    public void moveWinch(double speed) {
        winchMotor.set(speed);
    }

    public void stopWinch() {
        winchMotor.set(0);
    }
}
