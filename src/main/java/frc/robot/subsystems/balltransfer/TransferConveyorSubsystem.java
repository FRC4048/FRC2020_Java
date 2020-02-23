/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.balltransfer;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.utils.DigitalInputGroup;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.diag.DiagOpticalSensor;
import frc.robot.utils.logging.Logging;

/**
 * Add your docs here.
 */
public class TransferConveyorSubsystem extends SubsystemBase {

    public enum S5RequiredSensor {UPPER, LOWER}

    private WPI_TalonSRX transferMotor;
    private static DigitalInput slot5Lower;
    private static DigitalInput slot5Upper;
//    private static DigitalInputGroup slot5;
    // private static DigitalInput slot5;

    public TransferConveyorSubsystem() {
        transferMotor = new WPI_TalonSRX(Constants.TRANSFER_MOTOR_ID);
        slot5Lower = new DigitalInput(Constants.SLOT5_LOWER);
        slot5Upper = new DigitalInput(Constants.SLOT5_UPPER);
//        slot5 = new DigitalInputGroup(slot5Lower, slot5Upper);
        //slot5 = new DigitalInput(5);

        transferMotor.setInverted(true);
        transferMotor.setNeutralMode(NeutralMode.Brake);
        
        Robot.getDiagnostics().addDiagnosable(new DiagOpticalSensor("Transfer Slot5 Optical Sensor Lower", slot5Lower));
        Robot.getDiagnostics().addDiagnosable(new DiagOpticalSensor("Transfer Slot5 Optical Sensor Upper", slot5Upper));
    }

    public void periodic() {
        SmartShuffleboard.put("Driver", "Slot5 Upper", getSlot5Upper());
        SmartShuffleboard.put("Driver", "Slot5 Lower", getSlot5Lower());
    }

    public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {
        protected void addAll() {
          add("slot5", getSlot5Upper());
          add("slot5", getSlot5Lower());
          add("Shooter Motor Running?", transferMotor.get() != 0);
        }
    };

    /**
     * Sets the stager (M3) to a set value
     * 
     * @param speed
     */
    public void moveTransfer(double speed) {
        transferMotor.set(speed);
    }

    /**
     * Returns state of Slot 5
     * 
     * @return boolean state of slot 5
     */
    public static boolean getSlot5Upper() {
        return !slot5Upper.get();
    }

    public static boolean getSlot5Lower() {
        return !slot5Lower.get();
    }
}
