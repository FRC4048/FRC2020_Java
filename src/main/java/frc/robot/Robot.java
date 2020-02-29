/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utils.LimeLightVision;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.utils.SmartShuffleboard;
import frc.robot.utils.diag.Diagnostics;
import frc.robot.utils.logging.Logging;
import frc.robot.utils.logging.Logging.MessageLevel;
import frc.robot.commands.*;
import frc.robot.commands.ControlPanel.MoveSolenoid;
import frc.robot.commands.drivetrain.DriveStraightWithGyro;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  public static RobotContainer m_robotContainer;
  private static Diagnostics diagnostics;
  private static LimeLightVision limelight;;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    diagnostics = new Diagnostics();
    m_robotContainer = new RobotContainer();
     limelight = new LimeLightVision();
     limelight.setLedOff();
     limelight.setStream(2);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
//    SmartShuffleboard.put("Driver", "MANUAL OVERRIDE ENABLED", m_robotContainer.getManualOverride());
   Logging.instance().writeAllData();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */

  @Override
  public void disabledInit() {
    Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "-----------DISABLED----------");
  }

  @Override
  public void disabledPeriodic() {
    SmartShuffleboard.put("Drive", "Distance", Math.abs((m_robotContainer.driveTrainGetter().getLeftEncoderDistance() + m_robotContainer.driveTrainGetter().getRightEncoderDistance())/2));
    SmartShuffleboard.put("Driver", "AutoCommandVerify", m_robotContainer.autoChooser.getAutonomousCommand(m_robotContainer.autoChooser.getPosition(), m_robotContainer.autoChooser.getAction())
                                                                                                           + " " + m_robotContainer.autoChooser.getDelay());
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */

  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "-----------AUTO INIT----------");
    Logging.instance().writeAllTitles();
    // schedule the autonomous command (example)
    final StringBuilder gameInfo = new StringBuilder();
    gameInfo.append("Match Number=");
		gameInfo.append(DriverStation.getInstance().getMatchNumber());
		gameInfo.append(", Alliance Color=");
		gameInfo.append(DriverStation.getInstance().getAlliance().toString());
		gameInfo.append(", Match Type=");
		gameInfo.append(DriverStation.getInstance().getMatchType().toString());
    Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, gameInfo.toString());
    new MoveSolenoid(m_robotContainer.getControlPanelSubsystem(), Value.kForward).schedule();
    frc.robot.AutoChooser.AutoCommand getAutoCommand = m_robotContainer.autoChooser.getAutonomousCommand(m_robotContainer.autoChooser.getPosition(),
                                                       m_robotContainer.autoChooser.getAction());
    Command autonomousCommand = m_robotContainer.getAutonomousCommand(getAutoCommand, m_robotContainer.autoChooser.getDelay());
    Logging.instance().traceMessage(MessageLevel.INFORMATION, "AutoCommand is: " + getAutoCommand.toString() + " " + m_robotContainer.autoChooser.getDelay());
    m_robotContainer.driveTrainGetter().resetGyro();
    m_robotContainer.driveTrainGetter().resetOdodemtry(new Pose2d(0, 0, new Rotation2d(0)));
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "-----------TELEOP INIT----------");
    Logging.instance().writeAllTitles();
    new MoveSolenoid(m_robotContainer.getControlPanelSubsystem(), Value.kForward).schedule();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    diagnostics.reset();
  }

  /**
   * This function is called periodically during test mode.
   */
  
  @Override
  public void testPeriodic() {
    diagnostics.refresh();
  }

  public static Diagnostics getDiagnostics() {
    return diagnostics;
  }
}
