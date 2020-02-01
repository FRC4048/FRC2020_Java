/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

/**
 * Add your docs here.
 */
public class ConveyorStateMachine {
    /**
     * https://docs.google.com/spreadsheets/d/1z1pBTtl-SJKcTKWXfU3sdGcWpE97y6huKPtR8nkVKk4/edit?usp=drivesdk
     */
    public enum State {
        S0, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, S19, S20, S21, S22, S23,
        S24, S25, S26, S27, S28, S29, S30, S31, S32
    }

    /**
     * Gets the state of the robot passed in and returns the wanted state that it
     * can move to next.
     * 
     * @param state
     * @return the watned to move to state based on the state passed in
     */
    public static State wantedState(State state) {
        switch (state) {
        case S0:
            return State.S1;
        case S1:
            return State.S2;
        case S2:
            return State.S3;
        case S3:
            return State.S6;
        case S4:
            return State.S8;
        case S5:
            return State.S9;
        case S6:
            return State.S7;
        case S7:
            return State.S14;
        case S8:
            return State.S16;
        case S9:
            return State.S18;
        case S10:
            return State.S11;
        case S11:
            return State.S22;
        case S12:
            return State.S13;
        case S13:
            return State.S26;
        case S14:
            return State.S15;
        case S15:
            return State.S30;
        case S16:
            return State.S17;
        case S17:
            return State.S18;
        case S18:
            return State.S19;
        case S19:
            return State.S22;
        case S20:
            return State.S21;
        case S21:
            return State.S26;
        case S22:
            return State.S23;
        case S23:
            return State.S30;
        case S24:
            return State.S25;
        case S25:
            return State.S32;
        case S26:
            return State.S27;
        case S27:
            return State.S32;
        case S28:
            return State.S29;
        case S29:
            return State.S32;
        case S30:
            return State.S31;
        case S31:
            return State.S32;
        case S32:
            return State.S32;
        default:
            return State.S32; // This is just saying we must shoot
        }
    }

    /**
     * Gets the current state of the subsystem
     * 
     * @return The state
     */
    public static State getState(boolean slot1, boolean slot2, boolean slot3, boolean slot4, boolean slot5) {
        if (!slot1 && !slot2 && !slot3 && !slot4 && slot5) {
            return State.S1;
        } else if (!slot1 && !slot2 && !slot3 && slot4 && slot5) {
            return State.S2;
        } else if (!slot1 && !slot2 && !slot3 && slot4 && slot5) {
            return State.S3;
        } else if (!slot1 && !slot2 && slot3 && !slot4 && slot5) {
            return State.S4;
        } else if (!slot1 && !slot2 && slot3 && !slot4 && slot5) {
            return State.S5;
        } else if (!slot1 && !slot2 && slot3 && slot4 && slot5) {
            return State.S6;
        } else if (!slot1 && !slot2 && slot3 && slot4 && slot5) {
            return State.S7;
        } else if (!slot1 && slot2 && !slot3 && !slot4 && slot5) {
            return State.S8;
        } else if (!slot1 && slot2 && !slot3 && !slot4 && slot5) {
            return State.S9;
        } else if (!slot1 && slot2 && !slot3 && slot4 && slot5) {
            return State.S10;
        } else if (!slot1 && slot2 && !slot3 && slot4 && slot5) {
            return State.S11;
        } else if (!slot1 && slot2 && slot3 && !slot4 && slot5) {
            return State.S12;
        } else if (!slot1 && slot2 && slot3 && !slot4 && slot5) {
            return State.S13;
        } else if (!slot1 && slot2 && slot3 && slot4 && slot5) {
            return State.S14;
        } else if (!slot1 && slot2 && slot3 && slot4 && slot5) {
            return State.S15;
        } else if (slot1 && !slot2 && !slot3 && !slot4 && slot5) {
            return State.S16;
        } else if (slot1 && !slot2 && slot3 && slot4) {
            return State.S17;
        } else if (slot1 && !slot2 && !slot3 && slot4 && slot5) {
            return State.S18;
        } else if (slot1 && !slot2 && !slot3 && slot4 && slot5) {
            return State.S19;
        } else if (slot1 && !slot2 && slot3 && !slot4 && slot5) {
            return State.S20;
        } else if (slot1 && !slot2 && slot3 && !slot4 && slot5) {
            return State.S21;
        } else if (slot1 && !slot2 && slot3 && slot4 && slot5) {
            return State.S22;
        } else if (slot1 && !slot2 && slot3 && slot4 && slot5) {
            return State.S23;
        } else if (slot1 && slot2 && !slot3 && !slot4 && slot5) {
            return State.S24;
        } else if (slot1 && slot2 && !slot3 && !slot4 && slot5) {
            return State.S25;
        } else if (slot1 && slot2 && !slot3 && slot4 && slot5) {
            return State.S26;
        } else if (slot1 && slot2 && !slot3 && slot4 && slot5) {
            return State.S27;
        } else if (slot1 && slot2 && slot3 && !slot4 && slot5) {
            return State.S28;
        } else if (slot1 && slot2 && slot3 && !slot4 && slot5) {
            return State.S29;
        } else if (slot1 && slot2 && slot3 && slot4 && slot5) {
            return State.S30;
        } else if (slot1 && slot2 && slot3 && slot4 && slot5) {
            return State.S31;
        } else if (!slot1 && !slot2 && !slot3 && !slot4 && slot5) {
            return State.S0;
        } else {
            return State.S32;
        }
    }
}
