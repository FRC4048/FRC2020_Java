/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import frc.robot.commands.conveyorbelt.StateDetector;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.utils.logging.LogCommandWrapper;

/**
 * Add your docs here.
 */
public class ConveyorMotorStateTest {
    Map<BallTransferState, String> stateMap = new HashMap<BallTransferState, String>();
    ConveyorSubsystem conveyorSubsystem;
    TransferConveyorSubsystem transferConveyorSubsystem;
    ShooterSubsystem shooterSubsystem;

    public ConveyorMotorStateTest() {
        stateMap.put(BallTransferState.S0, "M3Command");
        stateMap.put(BallTransferState.S1, "M2M3Command");
        stateMap.put(BallTransferState.S2, "M3Command");
        stateMap.put(BallTransferState.S3, "M2M3Command");
        stateMap.put(BallTransferState.S4, "M2M3Command");
        stateMap.put(BallTransferState.S5, "M2Command");
        stateMap.put(BallTransferState.S6, "M3Command");
        stateMap.put(BallTransferState.S7, "M2M3Command");
        stateMap.put(BallTransferState.S8, "M1M2M3Command");
        stateMap.put(BallTransferState.S9, "M1M2M3Command");
        stateMap.put(BallTransferState.S10, "M3Command");
        stateMap.put(BallTransferState.S11, "M1M2M3Command");
        stateMap.put(BallTransferState.S12, "M3Command");
        stateMap.put(BallTransferState.S13, "M1M2M3Command");
        stateMap.put(BallTransferState.S14, "M3Command");
        stateMap.put(BallTransferState.S15, "M1M2M3Command");
        stateMap.put(BallTransferState.S16, "M3Command");
        stateMap.put(BallTransferState.S17, "M2M3Command");
        stateMap.put(BallTransferState.S18, "M3Command");
        stateMap.put(BallTransferState.S19, "M2M3Command");
        stateMap.put(BallTransferState.S20, "M3Command");
        stateMap.put(BallTransferState.S21, "M2M3Command");
        stateMap.put(BallTransferState.S22, "M3Command");
        stateMap.put(BallTransferState.S23, "M2M3Command");
        stateMap.put(BallTransferState.S24, "M3Command");
        stateMap.put(BallTransferState.S25, "M3Command");
        stateMap.put(BallTransferState.S26, "M3Command");
        stateMap.put(BallTransferState.S27, "StopMotors");
        stateMap.put(BallTransferState.S28, "M3Command");
        stateMap.put(BallTransferState.S29, "M3Command");
        stateMap.put(BallTransferState.S30, "M3Command");
        stateMap.put(BallTransferState.S31, "StopMotors");
        stateMap.put(BallTransferState.S32, "StopMotors");

        conveyorSubsystem = new ConveyorSubsystem();
        transferConveyorSubsystem = new TransferConveyorSubsystem();
        shooterSubsystem = new ShooterSubsystem();
    }

    @Test
    public void testMotors() {

        for (BallTransferState s : BallTransferState.values()) {
            String expected = stateMap.get(s);
            LogCommandWrapper wrapper = (LogCommandWrapper) StateDetector.createCommand(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem, s);
            String actual = wrapper.getWrappedCommand().getClass().getSimpleName();

            Assert.assertEquals(expected, actual);

        }
    }
}
