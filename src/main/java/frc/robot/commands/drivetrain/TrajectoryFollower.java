/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

public class TrajectoryFollower extends RamseteCommand {
  /**
   * Creates a new TrajectoryFollower.
   */
  public TrajectoryFollower(Trajectory trajectory, SixWheelDriveTrainSubsystem driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(trajectory, driveTrain::getPose, new RamseteController(),
        new SimpleMotorFeedforward(Constants.DRIVETRAIN_KS, Constants.DRIVETRAIN_KV, Constants.DRIVETRAIN_KA),
        Constants.DIFFERENTIAL_DRIVE_KINEMATICS, driveTrain::getWheelSpeeds,
        new PIDController(Constants.DRIVETRAIN_P, Constants.DRIVETRAIN_I, Constants.DRIVETRAIN_D),
        new PIDController(Constants.DRIVETRAIN_P, Constants.DRIVETRAIN_I, Constants.DRIVETRAIN_D),
        // RamseteCommand passes volts to the callback
        driveTrain::tankDriveVolts, driveTrain);
  }
}
