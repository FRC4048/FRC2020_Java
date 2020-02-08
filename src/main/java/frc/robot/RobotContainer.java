/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.ControlPanel.ManualRotate;
import frc.robot.commands.ControlPanel.RotateDegrees;
import frc.robot.commands.ControlPanel.RotateToColor;
import frc.robot.commands.ControlPanel.ToggleElevator;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.TrajectoryFollower;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.TrajectoryBuilder;
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
  private final SixWheelDriveTrainSubsystem driveTrain = new SixWheelDriveTrainSubsystem();
  private CompressorSubsystem compressorSubsystem = new CompressorSubsystem();
  private ControlPanelSubsystem controlPanelSubsystem = new ControlPanelSubsystem();

  private Joystick joyLeft = new Joystick(0);
  private Joystick joyRight = new Joystick(1);
  private JoystickButton driverMarkPlace = new JoystickButton(joyLeft,1); //TODO: change this based on what we use
  public AutoChooser autoChooser = new AutoChooser();
  private XboxController controller = new XboxController(2);
  private JoystickButton buttonX = new JoystickButton(controller, Constants.XBOX_X_BUTTON); //Button X is the control panel rotate to position 
  private JoystickButton buttonA = new JoystickButton(controller, Constants.XBOX_A_BUTTON);
  private JoystickButton buttonY = new JoystickButton(controller, Constants.XBOX_Y_BUTTON);
  private JoystickButton buttonB = new JoystickButton(controller, Constants.XBOX_B_BUTTON);


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    autoChooser.addOptions();
    driveTrain.setDefaultCommand(new Drive(driveTrain, () -> joyLeft.getY(), () -> joyRight.getY()));

    controlPanelSubsystem.setDefaultCommand(new ManualRotate(controlPanelSubsystem, () -> getXBoxRightJoyX()));
    // Configure the button bindings
    configureButtonBindings();
    autoChooser.initialize();
  }

  private double getXBoxRightJoyX(){
    return controller.getX(GenericHID.Hand.kRight);
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
    SmartShuffleboard.putCommand("Control Panel", "Toggle Elevator", new ToggleElevator(controlPanelSubsystem));
    SmartShuffleboard.putCommand("Control Panel", "RotateClockwiseDegrees", new RotateDegrees(controlPanelSubsystem, 4*360, Constants.CONTROL_PANEL_SPEED));
    SmartShuffleboard.putCommand("Control Panel", "RotateCountrerClockwiseDegrees", new RotateDegrees(controlPanelSubsystem, 4*360, -Constants.CONTROL_PANEL_SPEED));
    SmartShuffleboard.putCommand("Control Panel", "Color Rotate", new RotateToColor(controlPanelSubsystem));
    buttonA.whenPressed(new ToggleElevator(controlPanelSubsystem));
    //buttonX.whenPressed(new RotateDegrees(controlPanelSubsystem, 4*360, Constants.CONTROL_PANEL_SPEED));
    //buttonB.whenPressed(new RotateToColor(controlPanelSubsystem));
    buttonX.whenPressed(new LogCommandWrapper(new RotateDegrees(controlPanelSubsystem, 4*360, Constants.CONTROL_PANEL_SPEED), "RotateDegrees"));
    buttonB.whenPressed(new LogCommandWrapper(new RotateToColor(controlPanelSubsystem),"RotatetoColor"));
  }

  /**
   * Takes the auto mode and converts it into a command/commandgroup that will be run.
   * 
   * @param autoOption the enum of the auto running
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(AutoChooser.AutoCommand autoOption) {
    Trajectory[] trajectory = new Trajectory[10]; //Arbitrary number to allow as many as we want can add more if needed
    Command autoCommand; //Command that will actuall be returned in this method

    //Set up trajectories
    switch(autoOption) {
    //TODO Change the crossline auto to actually make sense, this is currently just an example
    case CROSS_LINE:
      //Start at 0, 0 facing to 0, drive 2 meters forward
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withWaypoint(1, 0).withEndPoint(2, 0, 0).build();   
      //Start where the last one ended and drive end up in the same place we started
      trajectory[1] = TrajectoryBuilder.start().withStartPosition(2, 0, 0).withEndPoint(0, 0, 0).build(); 
      //Theoretically more trajectory objects could be added
      break;
    default:
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withEndPoint(0, 0, 0).build(); //Do nothing?
      break;
    }
    
    //This assigns all of your trajectories to ramsete commands
    List<Command> trajectoryCommands = Arrays.stream(trajectory)
        .filter(tr -> tr != null)
        .map(tr -> new TrajectoryFollower(tr, driveTrain))
        .collect(Collectors.toList());

    //Set up the actual auto sequenece here using the inline command groups.
    switch(autoOption){
    case CROSS_LINE:
      //Sets the auto function to be going the first trajectory and then the second trajectory and then stopping
      autoCommand = trajectoryCommands.get(0).andThen(trajectoryCommands.get(1).andThen(() -> driveTrain.tankDriveVolts(0, 0)));
      break;
    default:
      autoCommand = trajectoryCommands.get(0).andThen(() -> driveTrain.tankDriveVolts(0, 0));
      break;
    }
    
    return autoCommand;
  }
}
