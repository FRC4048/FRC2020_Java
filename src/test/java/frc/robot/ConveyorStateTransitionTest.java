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

import frc.robot.subsystems.balltransfer.ConveyorStateMachine;
import frc.robot.subsystems.balltransfer.BallTransferState;


/**
 * Add your docs here.
 */
public class ConveyorStateTransitionTest {

    private Map<BallTransferState, BallTransferState> states = new HashMap<BallTransferState,BallTransferState>();

    public ConveyorStateTransitionTest() {
        states.put(BallTransferState.S0, BallTransferState.S1);
        states.put(BallTransferState.S1, BallTransferState.S2);
        states.put(BallTransferState.S2, BallTransferState.S3);
        states.put(BallTransferState.S3, BallTransferState.S6);
        states.put(BallTransferState.S4, BallTransferState.S8);
        states.put(BallTransferState.S5, BallTransferState.S9);
        states.put(BallTransferState.S6, BallTransferState.S7);
        states.put(BallTransferState.S7, BallTransferState.S14);
        states.put(BallTransferState.S8, BallTransferState.S16);
        states.put(BallTransferState.S9, BallTransferState.S18);
        states.put(BallTransferState.S10, BallTransferState.S11);
        states.put(BallTransferState.S11, BallTransferState.S22);
        states.put(BallTransferState.S12, BallTransferState.S13);
        states.put(BallTransferState.S13, BallTransferState.S26);
        states.put(BallTransferState.S14, BallTransferState.S15);
        states.put(BallTransferState.S15, BallTransferState.S30);
        states.put(BallTransferState.S16, BallTransferState.S17);
        states.put(BallTransferState.S17, BallTransferState.S18);
        states.put(BallTransferState.S18, BallTransferState.S19);
        states.put(BallTransferState.S19, BallTransferState.S22);
        states.put(BallTransferState.S20, BallTransferState.S21);
        states.put(BallTransferState.S21, BallTransferState.S26);
        states.put(BallTransferState.S22, BallTransferState.S23);
        states.put(BallTransferState.S23, BallTransferState.S30);
        states.put(BallTransferState.S24, BallTransferState.S25);
        states.put(BallTransferState.S25, BallTransferState.S32);
        states.put(BallTransferState.S26, BallTransferState.S27);
        states.put(BallTransferState.S27, BallTransferState.S32);
        states.put(BallTransferState.S28, BallTransferState.S29);
        states.put(BallTransferState.S29, BallTransferState.S32);
        states.put(BallTransferState.S30, BallTransferState.S31);
        states.put(BallTransferState.S31, BallTransferState.S32);
        states.put(BallTransferState.S32, BallTransferState.S32);
    }

    @Test
    public void testTransitions() {
        for(BallTransferState s : BallTransferState.values()) {
            BallTransferState expected = states.get(s);
            BallTransferState actual = ConveyorStateMachine.wantedState(s);
            
            Assert.assertEquals(expected, actual);
        }
    }

}
