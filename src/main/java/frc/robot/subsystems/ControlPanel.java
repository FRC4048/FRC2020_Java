package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ControlPanel.Deploy;
import frc.robot.utils.SmartShuffleboard;

public class ControlPanel extends SubsystemBase {
    public static final int TIMEOUT = 100;
    private Solenoid controlPanelSolenoid = new Solenoid(Constants.PCM_CAN_ID, Constants.CONTROL_PANEL_SOLENOID);
    private WPI_TalonSRX wheelSpinner = new WPI_TalonSRX(Constants.WHEEL_SPINNER_CAN);
    private AnalogInput colorSensor;
    private Encoder encoder = new Encoder(Constants.CONTROL_PANEL_ENCODERS[0], Constants.CONTROL_PANEL_ENCODERS[1]);

    public ControlPanel() {
    }

    public void resetEncoder(){
        encoder.reset();
    }

    public void extend(){
        controlPanelSolenoid.set(true);
    }

    public void retract(){
        controlPanelSolenoid.set(false);
    }

    public void spin(double speed){
        wheelSpinner.set(speed);
    }
    public void stopSpin(){
        wheelSpinner.set(0);
    }

    public boolean getState(){
        return controlPanelSolenoid.get();
    }

    public double getEncoderValue(){
        return encoder.get();
    }

    @Override
    public void periodic() {
        SmartShuffleboard.put("Control Panel", "Encoder Value", getEncoderValue());
    }
}
