/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.PowerDistPanel;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.commands.ClimberCommands.MoveWinch;
import frc.robot.commands.ElevatorCommands.MoveElevator;
import frc.robot.commands.ElevatorCommands.ToggleClimberPiston;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.TrajectoryFollower;
import frc.robot.subsystems.WinchSubsystem;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.ClimberElevatorSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.TrajectoryBuilder;
import frc.robot.utils.logging.LogCommandWrapper;
import frc.robot.utils.logging.MarkPlaceCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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

  private ClimberElevatorSubsystem climberElevatorSubsystem = new ClimberElevatorSubsystem();
  private WinchSubsystem winchSubsystem = new WinchSubsystem();

  public PowerDistPanel m_PowerDistPanel = new PowerDistPanel();


  private Joystick joyLeft = new Joystick(0);
  private Joystick joyRight = new Joystick(1);
  private JoystickButton driverMarkPlace = new JoystickButton(joyLeft,1); //TODO: change this based on what we use
  public AutoChooser autoChooser = new AutoChooser();

  private XboxController xboxController = new XboxController(2);
  private JoystickButton xBoxLeftStick = new JoystickButton(xboxController, Constants.XBOX_LEFT_STICK_PRESS);
  private JoystickButton xBoxRightStick = new JoystickButton(xboxController, Constants.XBOX_RIGHT_STICK_PRESS);


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    autoChooser.addOptions();
    driveTrain.setDefaultCommand(new Drive(driveTrain, () -> joyLeft.getY(), () -> joyRight.getY()));  
    // Configure the button bindings
    configureButtonBindings();
    autoChooser.initialize();
    climberElevatorSubsystem.setDefaultCommand(new MoveElevator(climberElevatorSubsystem, xboxController));
    winchSubsystem.setDefaultCommand(new MoveWinch(winchSubsystem, xboxController));
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
    xBoxLeftStick.and(xBoxRightStick).whenActive(new LogCommandWrapper(new ToggleClimberPiston(climberElevatorSubsystem), "ToggleClimberPiston")); //This detects if both joysticks are pressed.
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
