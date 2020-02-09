/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.balltransfer;

/**
 * Add your docs here.
 */
public class ConveyorStateMachine {

    /**
     * Gets the state of the robot passed in and returns the wanted state that it
     * can move to next.
     * 
     * @param state
     * @return the watned to move to state based on the state passed in
     */
    public static BallTransferState wantedState(BallTransferState state) {
        switch (state) {
        case S0:
            return BallTransferState.S1;
        case S1:
            return BallTransferState.S2;
        case S2:
            return BallTransferState.S3;
        case S3:
            return BallTransferState.S6;
        case S4:
            return BallTransferState.S8;
        case S5:
            return BallTransferState.S9;
        case S6:
            return BallTransferState.S7;
        case S7:
            return BallTransferState.S14;
        case S8:
            return BallTransferState.S16;
        case S9:
            return BallTransferState.S18;
        case S10:
            return BallTransferState.S11;
        case S11:
            return BallTransferState.S22;
        case S12:
            return BallTransferState.S13;
        case S13:
            return BallTransferState.S26;
        case S14:
            return BallTransferState.S15;
        case S15:
            return BallTransferState.S30;
        case S16:
            return BallTransferState.S17;
        case S17:
            return BallTransferState.S18;
        case S18:
            return BallTransferState.S19;
        case S19:
            return BallTransferState.S22;
        case S20:
            return BallTransferState.S21;
        case S21:
            return BallTransferState.S26;
        case S22:
            return BallTransferState.S23;
        case S23:
            return BallTransferState.S30;
        case S24:
            return BallTransferState.S25;
        case S25:
            return BallTransferState.S32;
        case S26:
            return BallTransferState.S27;
        case S27:
            return BallTransferState.S32;
        case S28:
            return BallTransferState.S29;
        case S29:
            return BallTransferState.S32;
        case S30:
            return BallTransferState.S31;
        case S31:
            return BallTransferState.S32;
        case S32:
            return BallTransferState.S32;
        default:
            return BallTransferState.S32; // This is just saying we must shoot
        }
    }

    /**
     * Gets the current state of the subsystem
     * 
     * @return The state
     */
    public static BallTransferState getState(boolean slot1, boolean slot2, boolean slot3, boolean slot4, boolean slot5) {
        if (!slot1 && !slot2 && !slot3 && !slot4 && slot5) {
            return BallTransferState.S1;
        } else if (!slot1 && !slot2 && !slot3 && slot4 && !slot5) {
            return BallTransferState.S2;
        } else if (!slot1 && !slot2 && !slot3 && slot4 && slot5) {
            return BallTransferState.S3;
        } else if (!slot1 && !slot2 && slot3 && !slot4 && !slot5) {
            return BallTransferState.S4;
        } else if (!slot1 && !slot2 && slot3 && !slot4 && slot5) {
            return BallTransferState.S5;
        } else if (!slot1 && !slot2 && slot3 && slot4 && !slot5) {
            return BallTransferState.S6;
        } else if (!slot1 && !slot2 && slot3 && slot4 && slot5) {
            return BallTransferState.S7;
        } else if (!slot1 && slot2 && !slot3 && !slot4 && !slot5) {
            return BallTransferState.S8;
        } else if (!slot1 && slot2 && !slot3 && !slot4 && slot5) {
            return BallTransferState.S9;
        } else if (!slot1 && slot2 && !slot3 && slot4 && !slot5) {
            return BallTransferState.S10;
        } else if (!slot1 && slot2 && !slot3 && slot4 && slot5) {
            return BallTransferState.S11;
        } else if (!slot1 && slot2 && slot3 && !slot4 && !slot5) {
            return BallTransferState.S12;
        } else if (!slot1 && slot2 && slot3 && !slot4 && slot5) {
            return BallTransferState.S13;
        } else if (!slot1 && slot2 && slot3 && slot4 && !slot5) {
            return BallTransferState.S14;
        } else if (!slot1 && slot2 && slot3 && slot4 && slot5) {
            return BallTransferState.S15;
        } else if (slot1 && !slot2 && !slot3 && !slot4 && !slot5) {
            return BallTransferState.S16;
        } else if (slot1 && !slot2 && !slot3 && !slot4 && slot5) {
            return BallTransferState.S17;
        } else if (slot1 && !slot2 && !slot3 && slot4 && !slot5) {
            return BallTransferState.S18;
        } else if (slot1 && !slot2 && !slot3 && slot4 && slot5) {
            return BallTransferState.S19;
        } else if (slot1 && !slot2 && slot3 && !slot4 && !slot5) {
            return BallTransferState.S20;
        } else if (slot1 && !slot2 && slot3 && !slot4 && slot5) {
            return BallTransferState.S21;
        } else if (slot1 && !slot2 && slot3 && slot4 && !slot5) {
            return BallTransferState.S22;
        } else if (slot1 && !slot2 && slot3 && slot4 && slot5) {
            return BallTransferState.S23;
        } else if (slot1 && slot2 && !slot3 && !slot4 && !slot5) {
            return BallTransferState.S24;
        } else if (slot1 && slot2 && !slot3 && !slot4 && slot5) {
            return BallTransferState.S25;
        } else if (slot1 && slot2 && !slot3 && slot4 && !slot5) {
            return BallTransferState.S26;
        } else if (slot1 && slot2 && !slot3 && slot4 && slot5) {
            return BallTransferState.S27;
        } else if (slot1 && slot2 && slot3 && !slot4 && !slot5) {
            return BallTransferState.S28;
        } else if (slot1 && slot2 && slot3 && !slot4 && slot5) {
            return BallTransferState.S29;
        } else if (slot1 && slot2 && slot3 && slot4 && !slot5) {
            return BallTransferState.S30;
        } else if (slot1 && slot2 && slot3 && slot4 && slot5) {
            return BallTransferState.S31;
        } else if (!slot1 && !slot2 && !slot3 && !slot4 && !slot5) {
            return BallTransferState.S0;
        } else {
            return BallTransferState.S32;
        }

    }

    public static BallTransferState getState() {
        return getState(ShooterSubsystem.getSlot1(), ConveyorSubsystem.getSlot2(), ConveyorSubsystem.getSlot3(), ConveyorSubsystem.getSlot4(), TransferConveyorSubsystem.getSlot5());
    }
}
