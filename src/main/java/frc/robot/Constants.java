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
    public static final int PCM_CAN_ID = 10;
    public static final int MOTOR_LEFT1_ID = 1;
    public static final int MOTOR_LEFT2_ID = 2;
    public static final int MOTOR_RIGHT1_ID = 3;
    public static final int MOTOR_RIGHT2_ID = 4;
    public static final int WINCH_MOTOR_CAN = 7;
    public static final int CLIMBER_MOTOR_CAN = 11;

    public static final int[] DRIVE_ENCODER_LEFT_ID = {2,3};
    public static final int[] DRIVE_ENCODER_RIGHT_ID = {0,1};

    public static final int CLIMBER_SOLENOID = 0;

    public static final int CLIMBER_SWITCH_CHANNEL = 8;

    public static final double DRIVETRAIN_KS = 0.803;
    public static final double DRIVETRAIN_KV = 2.3;
    public static final double DRIVETRAIN_KA = 0.584;

    public static final double DRIVE_ENCODER_DISTANCE_PER_PULSE = 0.00303832381;

    public static final double DRIVETRAIN_P = 7;
    public static final double DRIVETRAIN_I = 0;
    public static final double DRIVETRAIN_D = 0;

    public static final double DRIVEAUTO_MAX_VELOCITY = 1;
    public static final double DRIVEAUTO_MAX_ACCEL = 0.6;

    public static final DifferentialDriveKinematics DIFFERENTIAL_DRIVE_KINEMATICS = new DifferentialDriveKinematics(0.5136427252077864);

    public static final int XBOX_A_BUTTON = 1;
    public static final int XBOX_B_BUTTON = 2;
    public static final int XBOX_X_BUTTON = 3;
    public static final int XBOX_Y_BUTTON = 4;
    public static final int XBOX_LEFT_BUMPER = 5;
    public static final int XBOX_RIGHT_BUMPER = 6;
    public static final int XBOX_BACK_BUTTON = 7;
    public static final int XBOX_START_BUTTON = 8;
    public static final int XBOX_LEFT_STICK_PRESS = 9;
    public static final int XBOX_RIGHT_STICK_PRESS = 10;
}
