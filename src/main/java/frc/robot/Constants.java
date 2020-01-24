/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int PDP_CAN_ID = 0;
    public static final int MOTOR_LEFT1_ID = 1;
    public static final int MOTOR_LEFT2_ID = 2;
    public static final int MOTOR_RIGHT1_ID = 3;
    public static final int MOTOR_RIGHT2_ID = 4;

    //PDP Ports
    public static final int PDP_STEERING_FR = 8;
    public static final int PDP_STEERING_FL = 11;
    public static final int PDP_STEERING_BL = 4;
    public static final int PDP_STEERING_BR = 7;
    public static final int PDP_DRIVE_FR = 0;
    public static final int PDP_DRIVE_FL = 3;
    public static final int PDP_DRIVE_BL = 13;
    public static final int PDP_DRIVE_BR = 15;
}
