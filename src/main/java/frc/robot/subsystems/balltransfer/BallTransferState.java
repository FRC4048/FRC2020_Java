
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.balltransfer;

/**
 * https://docs.google.com/spreadsheets/d/1z1pBTtl-SJKcTKWXfU3sdGcWpE97y6huKPtR8nkVKk4/edit?usp=drivesdk
 */
public enum BallTransferState {
    S0(false, false, false, false, false), S1(false, false, false, false, true), 
    S2(false, false, false, true, false), S3(false, false, false, true, true),
    S4(false, false, true, false, false), S5(false, false, true, false, true),
    S6(false, false, true, true, false), S7(false, false, true, true, true),
    S8(false, true, false, false, false), S9(false, true, false, false, true),
    S10(false, true, false, true, false), S11(false, true, false, true, true),
    S12(false, true, true, false, false), S13(false, true, true, false, true),
    S14(false, true, true, true, false), S15(false, true, true, true, true),
    S16(true, false, false, false, false), S17(true, false, false, false, true), 
    S18(true, false, false, true, false), S19(true, false, false, true, true), 
    S20(true, false, true, false, false), S21(true, false, true, false, true),
    S22(true, false, true, true, false), S23(true, false, true, true, true), 
    S24(true, true, false, false, false), S25(true, true, false, false, true), 
    S26(true, true, false, true, false), S27(true, true, false, true, true), 
    S28(true, true, true, false, false), S29(true, true, true, false, true), 
    S30(true, true, true, true, false), S31(true, true, true, true, true), S32();

    private boolean s1;
    private boolean s2;
    private boolean s3;
    private boolean s4;
    private boolean s5;

    /**
     * BallTransferState
     * 
     * @param s1 slot1 DIO
     * @param s2 slot2 DIO
     * @param s3 slot3 DIO
     * @param s4 slot4 DIO
     * @param s5 slot5 DIO
     */
    BallTransferState(boolean s1, boolean s2, boolean s3, boolean s4, boolean s5) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    /**
     * Default case is only for S32 where the sensors are irrelivant and it means we must shoot
     */
    BallTransferState() {
        
    }

    public boolean getS1() {
        return s1;
    }
    
    public boolean getS2() {
        return s2;
    }
    
    public boolean getS3() {
        return s3;
    }
    
    public boolean getS4() {
        return s4;
    }
    
    public boolean getS5() {
        return s5;
    }
}
