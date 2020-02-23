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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.ControlPanel.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.conveyorbelt.M2M3Command;
import frc.robot.commands.conveyorbelt.ShootBallAuto;
import frc.robot.commands.conveyorbelt.StateDetector;
import frc.robot.commands.conveyorbelt.StopMotors;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.DoNothing;
import frc.robot.commands.MotorSpinIntake;
import frc.robot.commands.StartIntakeCommand;
import frc.robot.commands.StopIntakeCommand;
import frc.robot.commands.WinchCommands.MoveWinch;
import frc.robot.commands.ElevatorCommands.MoveElevator;
import frc.robot.commands.ElevatorCommands.ToggleClimberPiston;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.GearSwitch;
import frc.robot.commands.drivetrain.TrajectoryFollower;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ClimberElevatorSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.commands.drivetrain.MoveBackwards;
import frc.robot.commands.drivetrain.ResetPose;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.TrajectoryBuilder;
import frc.robot.utils.diag.Diagnostics;
import frc.robot.utils.logging.LogCommandWrapper;
import frc.robot.utils.logging.Logging;
import frc.robot.utils.logging.MarkPlaceCommand;
import frc.robot.utils.logging.Logging.MessageLevel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.conveyorbelt.ShootBalls;
import frc.robot.commands.conveyorbelt.ShootStart;

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
  private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
  private final CompressorSubsystem compressorSubsystem = new CompressorSubsystem();
  private final TransferConveyorSubsystem transferConveyorSubsystem = new TransferConveyorSubsystem();
  private final ShooterSubsystem shooterSubsystem =  new ShooterSubsystem();
  private final ControlPanelSubsystem controlPanelSubsystem = new ControlPanelSubsystem();
  private final ClimberElevatorSubsystem climberElevatorSubsystem = new ClimberElevatorSubsystem();
  private final WinchSubsystem winchSubsystem = new WinchSubsystem();
  public final PowerDistPanel m_PowerDistPanel = new PowerDistPanel();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private boolean drivingEnabled = true;
  private boolean manualOverride = false;

  private static Joystick joyLeft = new Joystick(0);
  private static Joystick joyRight = new Joystick(1);
  private Joystick controller = new Joystick(2);
  private XboxController xboxController = new XboxController(2);

  private JoystickButton driverMarkPlace = new JoystickButton(joyLeft, 1); // TODO: change this based on what we use
  private JoystickButton gearSwitchLowSpeed = new JoystickButton(joyLeft, 6);
  private JoystickButton gearSwitchHighSpeed = new JoystickButton(joyRight, 11);

  public AutoChooser autoChooser = new AutoChooser();
  private JoystickButton buttonX = new JoystickButton(controller, Constants.XBOX_X_BUTTON); // Button X is the control
                                                                                            // panel rotate to position
  private JoystickButton buttonA = new JoystickButton(controller, Constants.XBOX_A_BUTTON);
  private JoystickButton buttonY = new JoystickButton(controller, Constants.XBOX_Y_BUTTON);
  private JoystickButton buttonB = new JoystickButton(controller, Constants.XBOX_B_BUTTON);

  private JoystickButton intakeBalls = new JoystickButton(controller, Constants.XBOX_LEFT_BUMPER);

  private JoystickButton xBoxLeftStick = new JoystickButton(controller, Constants.XBOX_LEFT_STICK_PRESS);
  private JoystickButton xBoxRightStick = new JoystickButton(controller, Constants.XBOX_RIGHT_STICK_PRESS);

  private JoystickButton shootBall = new JoystickButton(controller, Constants.XBOX_RIGHT_BUMPER);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    autoChooser.addOptions();
    driveTrain.setDefaultCommand(new Drive(driveTrain, controlPanelSubsystem, () -> joyLeft.getY(), () -> joyRight.getY()));
    conveyorSubsystem.setDefaultCommand(new StateDetector(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem));
    controlPanelSubsystem.setDefaultCommand(new ManualRotate(controlPanelSubsystem, () -> getXBoxRightJoyX()));

    // Configure the button bindings
    configureButtonBindings();
    autoChooser.initialize();
    climberElevatorSubsystem.setDefaultCommand(new MoveElevator(climberElevatorSubsystem, xboxController));
    winchSubsystem.setDefaultCommand(new MoveWinch(winchSubsystem, xboxController));
    
  }

  private double getXBoxRightJoyX() {
    return xboxController.getX(Hand.kRight);
  }

  public void setDrivingEnabled(boolean mode) {
    drivingEnabled = mode;
    Logging.instance().traceMessage(MessageLevel.INFORMATION, "Driving Enabled Set To: " + drivingEnabled);
  }

  public boolean getDrivingEnabled() {
    return drivingEnabled;
  }

  public static void doRumble() {
    joyLeft.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
		joyRight.setRumble(GenericHID.RumbleType.kRightRumble, 1);
  }

  public void stopRumble() {
    joyLeft.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
    joyRight.setRumble(GenericHID.RumbleType.kRightRumble, 0);
  }

  public void setManualOverride(boolean mode) {
    manualOverride = mode;
  }

  public SixWheelDriveTrainSubsystem driveTrainGetter(){
    return driveTrain;
  }

  public boolean getManualOverride() {
    return manualOverride;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Command markPlaceCommand = new MarkPlaceCommand();
    // driverMarkPlace.whenPressed(new LogCommandWrapper(markPlaceCommand, "MarkPlaceCommand")); // TODO update this button
    intakeBalls.whenPressed(new LogCommandWrapper(new StartIntakeCommand(intakeSubsystem), "StartIntakeCommand"));
    intakeBalls.whileHeld(new MotorSpinIntake(intakeSubsystem));
    intakeBalls.whenReleased(new LogCommandWrapper(new StopIntakeCommand(intakeSubsystem), "StopIntakeCommand"));
    buttonY.whenPressed(new LogCommandWrapper(new ToggleSolenoid(controlPanelSubsystem), "ToggleSolenoid"));
    buttonX.whenPressed(new RotateDegreesScheduler(controlPanelSubsystem, driveTrain, 4*360, Constants.CONTROL_PANEL_SPEED, Constants.CONTROL_PANEL_BACKWARDS_SPEED));
    buttonB.whenPressed(new RotateToColorScheduler(controlPanelSubsystem, driveTrain, Constants.CONTROL_PANEL_BACKWARDS_SPEED));
    xBoxLeftStick.and(xBoxRightStick).whenActive(new LogCommandWrapper(new ToggleClimberPiston(climberElevatorSubsystem), "ToggleClimberPiston")); //This detects if both joysticks are pressed.
    gearSwitchLowSpeed.whenPressed(new LogCommandWrapper(new GearSwitch(driveTrain, true), "GearSwitch Speed Low"));
    gearSwitchHighSpeed.whenPressed(new LogCommandWrapper(new GearSwitch(driveTrain, false), "GearSwitch Speed High"));

    shootBall.whenPressed(new LogCommandWrapper(new ShootStart()));
    shootBall.whileHeld(new ShootBalls(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem, false)); //This will start the motors at full speed when pressed down
    shootBall.whenReleased(new LogCommandWrapper(new StopMotors(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem), "Stop Conveyor Motors after shoot")); //This will stop the motors once the button is released

    //TODO: add a flush ball button

    SmartShuffleboard.putCommand("Driver", "Manual Override", new ManualOverride());
  }

  /**
   * Takes the auto mode and converts it into a command/commandgroup that will be run.
   *
   * @param autoOption the enum of the auto running
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(AutoChooser.AutoCommand autoOption, int autoDelay) {
    Trajectory[] trajectory = new Trajectory[10]; //Arbitrary number to allow as many as we want can add more if needed
    Command autoCommand; //Command that will actuall be returned in this method
    //Set up trajectories
    switch(autoOption) {
    //TODO Change the crossline auto to actually make sense, this is currently just an example
    case LEFT_DEPOSIT:
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withEndPoint(3.048, -3.1242, 0).build();
      break;
    case MIDDLE_DEPOSIT:
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withEndPoint(3.048, -1.55, 0).build();
      break;
    case RIGHT_DEPOSIT:
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withEndPoint(3.048, 1.6, 0).build();
      break;
    case RIGHT_PICKUP:
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withEndPoint(7.8, 1.6, 0).build();
      break;
    case CROSS_LINE:
      //Start at 0, 0 facing to 0, drive 2 meters forward
      trajectory[0] = TrajectoryBuilder.start().withStartPosition(0, 0, 0).withEndPoint(1.5, 0, 0).build();
      //Start where the last one ended and drive end up in the same place we started
      // trajectory[1] = TrajectoryBuilder.start().withStartPosition(2, 0, 0).withEndPoint(0, 0, 0).build();
      //Theoretically more trajectory objects could be added
      break;
    case DO_NOTHING:
      break;
    default:
      break;
    }

    //This assigns all of your trajectories to ramsete commands
    List<Command> trajectoryCommands = Arrays.stream(trajectory)
        .filter(tr -> tr != null)
        .map(tr -> new TrajectoryFollower(tr, driveTrain))
        .collect(Collectors.toList());

    //Set up the actual auto sequenece here using the inline command groups.
    switch(autoOption){
      case LEFT_DEPOSIT:
        autoCommand = new WaitCommand(autoChooser.getDelay()).andThen(trajectoryCommands.get(0)).
        andThen(new TurnToAngle(driveTrain, 0)).andThen(() 
          -> driveTrain.tankDriveVolts(0, 0)).andThen(
          new ShootBallAuto(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem).withTimeout(2));
        break;

      case MIDDLE_DEPOSIT:
        autoCommand = new WaitCommand(autoChooser.getDelay()).andThen(trajectoryCommands.get(0)).
        andThen(new TurnToAngle(driveTrain, 0)).andThen(() 
          -> driveTrain.tankDriveVolts(0, 0)).andThen(
          new ShootBallAuto(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem).withTimeout(2));
        break;

      case RIGHT_DEPOSIT:
        autoCommand = new WaitCommand(autoChooser.getDelay()).andThen(trajectoryCommands.get(0)).
        andThen(new TurnToAngle(driveTrain, 0)).andThen(() 
          -> driveTrain.tankDriveVolts(0, 0)).andThen(
          new ShootBallAuto(conveyorSubsystem, transferConveyorSubsystem, shooterSubsystem).withTimeout(2));
          break;

      case CROSS_LINE:
        //Sets the auto function to be going the first trajectory and then the second trajectory and then stopping
        autoCommand = trajectoryCommands.get(0).andThen(() -> driveTrain.tankDriveVolts(0, 0));
        break;

      case RIGHT_PICKUP:
         autoCommand = new DriveStraight(4.8, -0.5, driveTrain).andThen(new WaitCommand(0.25)).andThen(
           new ResetPose(driveTrain)).andThen(trajectoryCommands.get(0)).
           andThen(new TurnToAngle(driveTrain, 0)).andThen(
             () -> driveTrain.tankDriveVolts(0, 0));
         break;

      case DO_NOTHING:
        autoCommand = new DoNothing();
        break;

      default:
        autoCommand = new DoNothing();
        break;
    }
    return autoCommand;
  }

  public ControlPanelSubsystem getControlPanelSubsystem() {
    return controlPanelSubsystem;
  }
}
