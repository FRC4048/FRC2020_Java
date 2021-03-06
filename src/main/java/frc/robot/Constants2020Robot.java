/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * Add your docs here.
 */
interface Constants2020Robot {
    //Global Constant
    public static final boolean ENABLE_DEBUG = false;

    //CAN ID
    public static final int PDP_CAN_ID = 0;
    public static final int MOTOR_LEFT1_ID = 1;
    public static final int MOTOR_LEFT2_ID = 2;
    public static final int MOTOR_RIGHT1_ID = 3;
    public static final int MOTOR_RIGHT2_ID = 4;

    public static final int CONTROL_PANEL_CAN_ID = 5;
    public static final int SHOOTER_MOTOR_ID = 6;
    public static final int CONVEYOR_MOTOR_ID = 7;
    public static final int TRANSFER_MOTOR_ID = 8;
    public static final int INTAKE_MOTOR_ID = 9;
    public static final int CLIMBER_ELEVATOR_ID = 11;
    public static final int CLIMBER_WINCH_ID = 12;
    public static final int PCM_CAN_ID = 10;
    public static final int PIGEON_CAN_ID = 13;

    //PDP
    public static final int PDP_DRIVE_L1 = 13;
    public static final int PDP_DRIVE_L2 = 15;
    public static final int PDP_DRIVE_R1 = 0;
    public static final int PDP_DRIVE_R2 = 1;
    public static final int PDP_ELEVATOR = 8;


    //DIO
    public static final int[] DRIVE_ENCODER_LEFT_ID = {0,1};
    public static final int[] DRIVE_ENCODER_RIGHT_ID = {2,3};
    public static final int CONTROL_PANEL_SENSOR_ID = 5;
    public static final int INTAKE_SENSOR = 6;
    public static final int SLOT1_A_ID = 11;
    public static final int SLOT1_B_ID = 10;
    public static final int SLOT2_A_ID = 12;
    public static final int SLOT2_B_ID = 13;
    public static final int SLOT3_A_ID = 7;
    public static final int SLOT3_B_ID = 15;
    public static final int SLOT4_A_ID = 4;
    public static final int SLOT4_B_ID = 17;
    public static final int SLOT5_LOWER = 9;
    public static final int SLOT5_UPPER = 8;
    public static final int COMPRESSOR_PRESSURE = 14;

    //Relay
    public static final int COMPRESSOR_RELAY = 0; 

    //PCM
    public static final int INTAKE_PISTON_ID = 0;
    public static final int CLIMBER_PISTON_ID = 2;
    public static final int DRIVE_TRAIN_GEARSWITCH_ID = 3;
    public static final int[] CONTROL_PANEL_PISTON_ID = {1,4};

    //DRIVETRAIN CONSTANTS
    public static final double DRIVETRAIN_KS = 0.748;
    public static final double DRIVETRAIN_KV = 2.19;
    public static final double DRIVETRAIN_KA = 0.268;
    public static final double DRIVE_ENCODER_DISTANCE_PER_PULSE = 0.00157490545;
    public static final double DRIVETRAIN_P = 7;
    public static final double DRIVETRAIN_I = 0;
    public static final double DRIVETRAIN_D = 0;
    public static final double DRIVEAUTO_MAX_VELOCITY = 1;
    public static final double DRIVEAUTO_MAX_ACCEL = 0.6;
    public static final DifferentialDriveKinematics DIFFERENTIAL_DRIVE_KINEMATICS = new DifferentialDriveKinematics(0.559);

    //CONTROL PANEL CONTANTS
    public static final double CONTROL_PANEL_DEGREES_TO_TICKS = 520/360.0;
    public static final double CONTROL_PANEL_SPEED = 0.5;
    public static final double CONTROL_PANEL_COLOR_SPEED = 0.25;
    public static final double CONTROL_PANEL_BACKWARDS_SPEED = 1;
    public static final double CONTROL_PANEL_ROTATE_DEGREES_TIMEOUT = 6;
    public static final double CONTROL_PANEL_ROTATE_TO_COLOR_TIMEOUT = 3;
    public static final double CONTROL_PANEL_WAIT_SENSOR_TIMEOUT = 5;
    public static final int CONTROL_PANEL_UNKNOWN_LIMIT = 100;

    //OI
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

    //ROBOT MOVEMENTS
    public static final double ELEVATOR_SPEED = 0.5;
    public static final double CLIMBER_WINCH_SPEED = 1;
}
