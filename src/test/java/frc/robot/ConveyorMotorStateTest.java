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
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ConveyorStateMachine.State;
import frc.robot.utils.logging.LogCommandWrapper;

/**
 * Add your docs here.
 */
public class ConveyorMotorStateTest {
    Map<State, String> stateMap = new HashMap<State, String>();
    ConveyorSubsystem subsystem;

    public ConveyorMotorStateTest() {
        stateMap.put(State.S0, "M3Command");
        stateMap.put(State.S1, "M2M3Command");
        stateMap.put(State.S2, "M3Command");
        stateMap.put(State.S3, "M2M3Command");
        stateMap.put(State.S4, "M2M3Command");
        stateMap.put(State.S5, "M2Command");
        stateMap.put(State.S6, "M3Command");
        stateMap.put(State.S7, "M2M3Command");
        stateMap.put(State.S8, "M1M2M3Command");
        stateMap.put(State.S9, "M1M2M3Command");
        stateMap.put(State.S10, "M3Command");
        stateMap.put(State.S11, "M1M2M3Command");
        stateMap.put(State.S12, "M3Command");
        stateMap.put(State.S13, "M1M2M3Command");
        stateMap.put(State.S14, "M3Command");
        stateMap.put(State.S15, "M1M2M3Command");
        stateMap.put(State.S16, "M3Command");
        stateMap.put(State.S17, "M2M3Command");
        stateMap.put(State.S18, "M3Command");
        stateMap.put(State.S19, "M2M3Command");
        stateMap.put(State.S20, "M3Command");
        stateMap.put(State.S21, "M2M3Command");
        stateMap.put(State.S22, "M3Command");
        stateMap.put(State.S23, "M2M3Command");
        stateMap.put(State.S24, "M3Command");
        stateMap.put(State.S25, "M3Command");
        stateMap.put(State.S26, "M3Command");
        stateMap.put(State.S27, "StopMotors");
        stateMap.put(State.S28, "M3Command");
        stateMap.put(State.S29, "M3Command");
        stateMap.put(State.S30, "M3Command");
        stateMap.put(State.S31, "StopMotors");
        stateMap.put(State.S32, "StopMotors");

        subsystem = new ConveyorSubsystem();
    }

    @Test
    public void testMotors() {

        for (State s : State.values()) {
            String expected = stateMap.get(s);
            LogCommandWrapper wrapper = (LogCommandWrapper) StateDetector.createCommand(subsystem, s);
            String actual = wrapper.getWrappedCommand().getClass().getSimpleName();

            Assert.assertEquals(expected, actual);

        }
    }
}
