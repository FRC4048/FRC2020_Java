/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.balltransfer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private WPI_TalonSRX transferMotor;
    private DigitalInput slot5A;
    private DigitalInput slot5B; 
    private static DigitalInputGroup slot5;
    // private static DigitalInput slot5;

    public TransferConveyorSubsystem() {
        transferMotor = new WPI_TalonSRX(Constants.TRANSFER_MOTOR_ID);
        slot5A = new DigitalInput(Constants.SLOT5_A_ID);
        slot5B = new DigitalInput(Constants.SLOT5_B_ID);
        slot5 = new DigitalInputGroup(slot5A, slot5B);
        //slot5 = new DigitalInput(5);

        Robot.m_robotContainer.getDiagnostics().addDiagnosable(new DiagOpticalSensor("Transfer Slot5 Optical Sensor A", slot5A));
        Robot.m_robotContainer.getDiagnostics().addDiagnosable(new DiagOpticalSensor("Transfer Slot5 Optical Sensor B", slot5B));
    }

    public void periodic() {
        SmartShuffleboard.put("Driver", "Slot5", getSlot5());
    }

    public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {
        protected void addAll() {
          add("slot5", getSlot5());
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
    public static boolean getSlot5() {
        return !slot5.get();
    }
}
