/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.text.MessageFormat;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;
import java.util.Map;

/**
 * Add your docs here.
 */
public class ConveyorSensorStateTest {
    private Map<String, BallTransferState> stateMap = new HashMap<String, BallTransferState>();

    public ConveyorSensorStateTest() {
        stateMap.put("00000", BallTransferState.S0);
        stateMap.put("00001", BallTransferState.S1);
        stateMap.put("00010", BallTransferState.S2);
        stateMap.put("00011", BallTransferState.S3);
        stateMap.put("00100", BallTransferState.S4);
        stateMap.put("00101", BallTransferState.S5);
        stateMap.put("00110", BallTransferState.S6);
        stateMap.put("00111", BallTransferState.S7);
        stateMap.put("01000", BallTransferState.S8);
        stateMap.put("01001", BallTransferState.S9);
        stateMap.put("01010", BallTransferState.S10);
        stateMap.put("01011", BallTransferState.S11);
        stateMap.put("01100", BallTransferState.S12);
        stateMap.put("01101", BallTransferState.S13);
        stateMap.put("01110", BallTransferState.S14);
        stateMap.put("01111", BallTransferState.S15);
        stateMap.put("10000", BallTransferState.S16);
        stateMap.put("10001", BallTransferState.S17);
        stateMap.put("10010", BallTransferState.S18);
        stateMap.put("10011", BallTransferState.S19);
        stateMap.put("10100", BallTransferState.S20);
        stateMap.put("10101", BallTransferState.S21);
        stateMap.put("10110", BallTransferState.S22);
        stateMap.put("10111", BallTransferState.S23);
        stateMap.put("11000", BallTransferState.S24);
        stateMap.put("11001", BallTransferState.S25);
        stateMap.put("11010", BallTransferState.S26);
        stateMap.put("11011", BallTransferState.S27);
        stateMap.put("11100", BallTransferState.S28);
        stateMap.put("11101", BallTransferState.S29);
        stateMap.put("11110", BallTransferState.S30);
        stateMap.put("11111", BallTransferState.S31);
    }

    @Test
    public void testSensorState() {
        for(String str : stateMap.keySet()) {
            BallTransferState expected = stateMap.get(str);

            boolean sensor1 = convertStringToBool(str, 0);
            boolean sensor2 = convertStringToBool(str, 1);
            boolean sensor3 = convertStringToBool(str, 2);
            boolean sensor4 = convertStringToBool(str, 3);
            boolean sensor5 = convertStringToBool(str, 4);

            BallTransferState actual = ConveyorStateMachine.getState(sensor1, sensor2, sensor3, sensor4, sensor5);
            Assert.assertEquals(expected, actual);
            
        }
    }

    private boolean convertStringToBool(String str, int location) {
        return str.charAt(location) == '1';
    }
}
