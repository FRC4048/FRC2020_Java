package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.ColorSensor;
import frc.robot.utils.ColorSensor.ColorValue;

import java.util.HashMap;

public class ControlPanelSubsystem extends SubsystemBase {
    private WPI_TalonSRX controlPanelMotor = new WPI_TalonSRX(Constants.CONTROL_PANEL_MOTOR_CAN_ID);
    private Solenoid elevatorSolenoid = new Solenoid(Constants.PCM_CAN_ID, Constants.CONTROL_PANEL_ELEVATOR_ID);
    private ColorSensor colorSensor = new ColorSensor(I2C.Port.kOnboard);

    private HashMap<ColorValue, Integer> colorValues = new HashMap<ColorValue, Integer>(){{
        put(ColorValue.RED, 1);
        put(ColorValue.YELLOW, 2);
        put(ColorValue.BLUE, 3);
        put(ColorValue.GREEN, 4);
        }};

    private boolean state = false; //false = elevator down, true = elevator up

    public ControlPanelSubsystem() {
    }

    public boolean getPistonState() {
        return state;
    }

    public void extend() {
        elevatorSolenoid.set(true);
        state = true;
    }

    public void retract() {
        elevatorSolenoid.set(false);
        state = false;
    }

    public void manualRotate(double speed) {
        controlPanelMotor.set(speed);
    }

    public void colorRotate(ColorValue colorTarget) {
        ColorValue currentColor = colorSensor.getColor();

    }

    public void stopRotation() {
        controlPanelMotor.set(0);
    }

}
