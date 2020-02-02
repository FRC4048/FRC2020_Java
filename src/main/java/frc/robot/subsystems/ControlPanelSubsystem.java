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

    public void rotateWithSpeed(double speed) {
        controlPanelMotor.set(speed);
    }

    public int getDirectionsToTarget(ColorValue colorTarget) { //TODO: Figure out the directionality of the motor rotation.
        ColorValue currentColor = colorSensor.getColor();
        int directions = currentColor.getPos() - colorTarget.getPos();
        if(Math.abs(directions) > 2){
            directions = -(directions%2);
        }
        return directions;
        //This is the number of spaces we need to move, with negative = left and positive = right.
    }

    public ColorValue getCurrentColor(){
        return colorSensor.getColor();
    }

    public void stopRotation() {
        controlPanelMotor.set(0);
    }

}
