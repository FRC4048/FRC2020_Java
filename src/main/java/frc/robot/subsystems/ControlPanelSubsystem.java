package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.utils.ColorSensor;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.ColorSensor.ColorValue;
import frc.robot.utils.diag.DiagColorSensor;
import frc.robot.utils.diag.DiagOpticalSensor;
import frc.robot.utils.diag.DiagTalonSrxEncoder;
import frc.robot.utils.logging.Logging;

import java.util.HashMap;

//Y = Toggle Piston X = Rotation B = Color
public class ControlPanelSubsystem extends SubsystemBase {  
    private WPI_TalonSRX controlPanelMotor = new WPI_TalonSRX(Constants.CONTROL_PANEL_CAN_ID);
    private DoubleSolenoid controlPanelSolenoid = new DoubleSolenoid(Constants.CONTROL_PANEL_PISTON_ID[0], Constants.CONTROL_PANEL_PISTON_ID[1]);
    private ColorSensor colorSensor = new ColorSensor(I2C.Port.kOnboard);
    private DigitalInput opticalSensor = new DigitalInput(Constants.CONTROL_PANEL_SENSOR_ID); 
    private final int TIMEOUT = 100;
    private String gameDataColor;

    public ControlPanelSubsystem() {
        controlPanelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT);
        controlPanelMotor.setNeutralMode(NeutralMode.Brake);
        controlPanelMotor.configPeakOutputForward(1);
        controlPanelMotor.configPeakOutputReverse(-1);
        
        Robot.getDiagnostics().addDiagnosable(new DiagColorSensor("Control Panel Color Sensor", colorSensor));
        Robot.getDiagnostics().addDiagnosable(new DiagOpticalSensor("Control Panel Optical Sensor", opticalSensor));
        Robot.getDiagnostics().addDiagnosable(new DiagTalonSrxEncoder("Control Panel Encoder", 100, controlPanelMotor));
    }

    public Value getPistonState() {
        return controlPanelSolenoid.get();
    }

    public void movePiston(Value state) {
        controlPanelSolenoid.set(state);
    }

    public void rotateWithSpeed(double speed) {
        controlPanelMotor.set(speed);
    }

    public int getEncoder() {
        return controlPanelMotor.getSelectedSensorPosition();
    }

    public void resetEncoder() {
        controlPanelMotor.setSelectedSensorPosition(0, 0, TIMEOUT);
    }

    //true = not making contact with control panel, false = making contact with control panel
    public boolean controlPanelSensor() {
        return opticalSensor.get();
    }

    // public int getDirectionsToTarget(ColorValue colorTarget) { //TODO: Figure out the directionality of the motor rotation.
    //     ColorValue currentColor = colorSensor.getColor();
    //     int directions = currentColor.getPos() - colorTarget.getPos();
    //     if(Math.abs(directions) > 2){
    //         directions = -(directions%2);
    //     }
    //     return directions;
    //     //This is the number of spaces we need to move, with negative = left and positive = right.
    // }

    public ColorValue getCurrentColor(){
        return colorSensor.getColor();
    }

    public void stopRotation() {
        controlPanelMotor.set(0);
    }

    public String fmsColor() {
        gameDataColor = DriverStation.getInstance().getGameSpecificMessage();
        return gameDataColor;
    }

    public void periodic() {
        
        SmartShuffleboard.put("Control Panel", "Data", "Encoder Value", getEncoder());
        SmartShuffleboard.put("Control Panel", "Data", "Color Sensor Value", getCurrentColor().name());
        SmartShuffleboard.put("Control Panel", "Data", "Game Data", fmsColor());

    }
    
    public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {
        protected void addAll() {
            add("Encoder Value", getEncoder());
            add("Color Sensor Value", getCurrentColor().name());
            add("Game Data", fmsColor());
        }
    };
}
