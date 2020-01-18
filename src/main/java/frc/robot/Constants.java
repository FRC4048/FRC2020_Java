/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int MOTOR_LEFT1_ID = 1;
    public static final int MOTOR_LEFT2_ID = 2;
    public static final int MOTOR_RIGHT1_ID = 3;
    public static final int MOTOR_RIGHT2_ID = 4;

    public static final int[] DRIVE_ENCODER_LEFT_ID = {2,3};
    public static final int[] DRIVE_ENCODER_RIGHT_ID = {0,1};
    
    public static final double DRIVETRAIN_KS = 0.644;
    public static final double DRIVETRAIN_KV = 0.566;
    public static final double DRIVETRAIN_KA = 0.142;

    public static final DifferentialDriveKinematics DIFFERENTIAL_DRIVE_KINEMATICS = new DifferentialDriveKinematics(2.1569939645342884);
}
