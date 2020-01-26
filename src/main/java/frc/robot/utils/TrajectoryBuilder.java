/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Constants;

/**
 * Here is an example of the TrajectoryBuilder being used
 * Trajectory trajectory = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withWaypoint(1,0).withEndPoint(2, 0, 0).build();
 * You can add as many waypoints as you want in the middle.
 */
public class TrajectoryBuilder {
    private Pose2d startPos;
    private List<Translation2d> waypoints;
    private Pose2d endPos;

    public TrajectoryBuilder() {
        waypoints = new ArrayList<Translation2d>();
    }

    public static TrajectoryBuilder start() {
        return new TrajectoryBuilder();
    }

    /**
     * Starting Pos of the Robot in meters and degrees
     * 
     * @param x the starting x position of the robot
     * @param y the starting y position of the robot
     * @param angle the starting angle of the robot
     */
    public TrajectoryBuilder withStartPosition(double x, double y, double angle) {
        startPos = new Pose2d(x, y, new Rotation2d(Math.toRadians(angle)));
        return this;
    }

    /**
     * Move the robot through a waypoint in meters
     * 
     * @param x translation
     * @param y translation
     */
    public TrajectoryBuilder withWaypoint(double x, double y) {
        waypoints.add(new Translation2d(x, y));
        return this;
    }

    /**
     * End Pos of the robot in meters and degrees
     * 
     * @param x the x position where the robot ends
     * @param y the y position where the robot ends
     * @param angle the angle when the robot ends
     */
    public TrajectoryBuilder withEndPoint(double x, double y, double angle) {
        endPos = new Pose2d(x, y, new Rotation2d(Math.toRadians(angle)));
        return this;
    }

    /**
     * Build the trajectory
     * 
     * @return the trajectory
     */
    public Trajectory build() {
        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(Constants.DRIVETRAIN_KS, Constants.DRIVETRAIN_KV, Constants.DRIVETRAIN_KA),
                Constants.DIFFERENTIAL_DRIVE_KINEMATICS, 10);

        // Create config for trajectory
        TrajectoryConfig config = new TrajectoryConfig(Constants.DRIVEAUTO_MAX_VELOCITY, Constants.DRIVEAUTO_MAX_ACCEL)
                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(Constants.DIFFERENTIAL_DRIVE_KINEMATICS).addConstraint(autoVoltageConstraint);
        
        
        return TrajectoryGenerator.generateTrajectory(startPos, waypoints, endPos, config);
    }
}
