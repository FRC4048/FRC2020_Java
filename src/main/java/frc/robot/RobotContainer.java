/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.utils.logging.LogCommandWrapper;
import frc.robot.utils.logging.MarkPlaceCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private Joystick joyLeft = new Joystick(0);
  private Joystick joyRight = new Joystick(1);
  private JoystickButton driverMarkPlace = new JoystickButton(joyLeft,1); //TODO: change this based on what we use
  public AutoChooser autoChooser = new AutoChooser();
  

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    autoChooser.addOptions();
    driveTrain.setDefaultCommand(new Drive(driveTrain, () -> joyLeft.getY(), () -> joyRight.getY()));  
    // Configure the button bindings
    configureButtonBindings();
    autoChooser.initialize();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Command markPlaceCommand = new MarkPlaceCommand();
    driverMarkPlace.whenPressed(new LogCommandWrapper(markPlaceCommand, "MarkPlaceCommand")); // TODO update this button
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.DRIVETRAIN_KS,
                                       Constants.DRIVETRAIN_KV,
                                       Constants.DRIVETRAIN_KA),
            Constants.DIFFERENTIAL_DRIVE_KINEMATICS, 10);

    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(Constants.DRIVEAUTO_MAX_VELOCITY, Constants.DRIVEAUTO_MAX_ACCEL)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.DIFFERENTIAL_DRIVE_KINEMATICS)
            .addConstraint(autoVoltageConstraint);
    // An example trajectory to follow. All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start position of the Robot
        new Pose2d(0, 0, new Rotation2d(Math.toRadians(0))),
        // Waypoints on the trajectory
        List.of(
          // new Translation2d(1, 1)
        ),        // The final position of the Robot
        new Pose2d(1.7526,1.2192,new Rotation2d(Math.toRadians(90))),
        // Pass config
        config);

      RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, driveTrain::getPose,
        new RamseteController(),
        new SimpleMotorFeedforward(Constants.DRIVETRAIN_KS, Constants.DRIVETRAIN_KV,
            Constants.DRIVETRAIN_KA),
        Constants.DIFFERENTIAL_DRIVE_KINEMATICS, driveTrain::getWheelSpeeds,
        new PIDController(Constants.DRIVETRAIN_P, Constants.DRIVETRAIN_I, Constants.DRIVETRAIN_D),
        new PIDController(Constants.DRIVETRAIN_P, Constants.DRIVETRAIN_I, Constants.DRIVETRAIN_D),
        // RamseteCommand passes volts to the callback
        driveTrain::tankDriveVolts, driveTrain);

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }
}
