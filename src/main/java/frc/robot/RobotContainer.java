/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import frc.robot.commands.ControlPanel.Deploy;
import frc.robot.commands.ControlPanel.RotateCount;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ControlPanel;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.logging.LogCommandWrapper;
import frc.robot.utils.logging.MarkPlaceCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();
  public final ControlPanel controlPanel = new ControlPanel();
  private final CompressorSubsystem compressor = new CompressorSubsystem();
  private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);
  private Joystick joyLeft = new Joystick(0);
  private Joystick joyRight = new Joystick(1);
  private JoystickButton driverMarkPlace = new JoystickButton(joyLeft,1); //TODO: change this based on what we use

  private XboxController controller = new XboxController(2); //TODO: Change this to what we use.
  private JoystickButton spinTest = new JoystickButton(controller, 3);

  public AutoChooser autoChooser = new AutoChooser();
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    autoChooser.addOptions();
    driveTrain.setDefaultCommand(new Drive(driveTrain, () -> joyLeft.getY(), () -> joyRight.getY()));  
    // Configure the button bindings
    configureButtonBindings();
    autoChooser.initialize();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Command markPlaceCommand = new MarkPlaceCommand();
    driverMarkPlace.whenPressed(new LogCommandWrapper(markPlaceCommand, "MarkPlaceCommand")); //TODO update this button
    spinTest.whenPressed(new RotateCount(controlPanel));
  }

  public void shuffleboardDebug(){
    SmartShuffleboard.putCommand("Control Panel", "Extend", new Deploy(controlPanel, true));
    SmartShuffleboard.putCommand("Control Panel", "Retract", new Deploy(controlPanel, false));
    SmartShuffleboard.putCommand("Control Panel", "Rotate Count", new RotateCount(controlPanel));

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
  //}
}
