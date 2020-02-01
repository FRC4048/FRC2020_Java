/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.text.MessageFormat;
import java.util.HashMap;

import org.junit.Test;

import frc.robot.subsystems.ConveyorStateMachine;
import frc.robot.subsystems.ConveyorStateMachine.State;
import java.util.Map;

/**
 * Add your docs here.
 */
public class ConveyorSensorStateTest {
    private Map<String, State> stateMap = new HashMap<String, State>();

    public ConveyorSensorStateTest() {
        stateMap.put("00000", State.S0);
        stateMap.put("00001", State.S1);
        stateMap.put("00010", State.S2);
        stateMap.put("00011", State.S3);
        stateMap.put("00100", State.S4);
        stateMap.put("00101", State.S5);
        stateMap.put("00110", State.S6);
        stateMap.put("00111", State.S7);
        stateMap.put("01000", State.S8);
        stateMap.put("01001", State.S9);
        stateMap.put("01010", State.S10);
        stateMap.put("01011", State.S11);
        stateMap.put("01100", State.S12);
        stateMap.put("01101", State.S13);
        stateMap.put("01110", State.S14);
        stateMap.put("01111", State.S15);
        stateMap.put("10000", State.S16);
        stateMap.put("10001", State.S17);
        stateMap.put("10010", State.S18);
        stateMap.put("10011", State.S19);
        stateMap.put("10100", State.S20);
        stateMap.put("10101", State.S21);
        stateMap.put("10110", State.S22);
        stateMap.put("10111", State.S23);
        stateMap.put("11000", State.S24);
        stateMap.put("11001", State.S25);
        stateMap.put("11010", State.S26);
        stateMap.put("11011", State.S27);
        stateMap.put("11100", State.S28);
        stateMap.put("11101", State.S29);
        stateMap.put("11110", State.S30);
        stateMap.put("11111", State.S31);
    }

    @Test
    public void testSensorState() {
        for(State s : State.values()) {
            State expected = stateMap.get(s);
            MessageFormat.format("", arguments)
            State actual = ConveyorStateMachine.getState(, slot2, slot3, slot4, slot5);
            Assert

        }
    }

    private int convertBoolean(boolean bool) {
        return bool ? 1:0;
    }
    private String getSensorString(boolean s1, boolean s2, boolean s3, boolean s4, boolean s5) {
        return "" + convertBoolean(s1) + convertBoolean(s2) + convertBoolean(s3) + convertBoolean(s4) + convertBoolean(s5);
    }
}
