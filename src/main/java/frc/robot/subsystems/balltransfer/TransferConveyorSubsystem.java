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
import frc.robot.utils.DigitalInputGroup;
import frc.robot.utils.SmartShuffleboard;

/**
 * Add your docs here.
 */
public class TransferConveyorSubsystem extends SubsystemBase {
    private WPI_TalonSRX transferMotor;
    private static DigitalInputGroup slot5;
    // private static DigitalInput slot5;

    public TransferConveyorSubsystem() {
        transferMotor = new WPI_TalonSRX(Constants.TRANSFER_MOTOR_ID);
        slot5 = new DigitalInputGroup(new DigitalInput(Constants.SLOT5_A_ID), new DigitalInput(Constants.SLOT5_B_ID));
        // slot5 = new DigitalInput(5);
    }

    public void periodic() {
        SmartShuffleboard.put("Driver", "Slot5", getSlot5());
    }
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
