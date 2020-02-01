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

import frc.robot.subsystems.ConveyorStateMachine;
import frc.robot.subsystems.ConveyorStateMachine.State;


/**
 * Add your docs here.
 */
public class ConveyorStateTransitionTest {

    private Map<State, State> states = new HashMap<State,State>();

    public ConveyorStateTransitionTest() {
        states.put(State.S0, State.S1);
        states.put(State.S1, State.S2);
        states.put(State.S2, State.S3);
        states.put(State.S3, State.S6);
        states.put(State.S4, State.S8);
        states.put(State.S5, State.S9);
        states.put(State.S6, State.S7);
        states.put(State.S7, State.S14);
        states.put(State.S8, State.S16);
        states.put(State.S9, State.S18);
        states.put(State.S10, State.S11);
        states.put(State.S11, State.S22);
        states.put(State.S12, State.S13);
        states.put(State.S13, State.S26);
        states.put(State.S14, State.S15);
        states.put(State.S15, State.S30);
        states.put(State.S16, State.S17);
        states.put(State.S17, State.S18);
        states.put(State.S18, State.S19);
        states.put(State.S19, State.S22);
        states.put(State.S20, State.S21);
        states.put(State.S21, State.S26);
        states.put(State.S22, State.S23);
        states.put(State.S23, State.S30);
        states.put(State.S24, State.S25);
        states.put(State.S25, State.S32);
        states.put(State.S26, State.S27);
        states.put(State.S27, State.S32);
        states.put(State.S28, State.S29);
        states.put(State.S29, State.S32);
        states.put(State.S30, State.S31);
        states.put(State.S31, State.S32);
        states.put(State.S32, State.S32);
    }

    @Test
    public void testTransitions() {
        for(State s : State.values()) {
            State expected = states.get(s);
            State actual = ConveyorStateMachine.wantedState(s);
            
            Assert.assertEquals(expected, actual);
        }
    }

}
