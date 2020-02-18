/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.drivetrain.MoveBackwards;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RotateToColorSequence extends SequentialCommandGroup {
  /**
   * Creates a new RotateToColorSequence.
   */
  ControlPanelSubsystem controlPanelSubsystem;
  SixWheelDriveTrainSubsystem driveTrain;
  double driveBackSpeed;
  public RotateToColorSequence(ControlPanelSubsystem controlPanelSubsystem, SixWheelDriveTrainSubsystem driveTrain, double driveBackSpeed) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.driveTrain = driveTrain;
    this.driveBackSpeed = driveBackSpeed;

    addCommands(
      new MoveSolenoid(controlPanelSubsystem, true),
      new WaitForSensor(controlPanelSubsystem),
      new SetDrivingEnabled(false),
      new RotateToColor(controlPanelSubsystem).withTimeout(Constants.CONTROL_PANEL_ROTATE_TO_COLOR_TIMEOUT),
      new WaitCommand(0.1),
      new MoveBackwards(controlPanelSubsystem, driveTrain, driveBackSpeed).withTimeout(0.3),
      new MoveSolenoid(controlPanelSubsystem, false)
      );
  }
  @Override
    public void initialize() {
    super.initialize();
  }

  @Override
  public void end(boolean interrupted) {
    Robot.m_robotContainer.setDrivingEnabled(true);
    if (controlPanelSubsystem.getWaitSensorTimeout()) {
      controlPanelSubsystem.movePiston(false);
      super.end(true);
    } else {
      super.end(interrupted);
    }
  }

  @Override
  public boolean isFinished() {
    if (controlPanelSubsystem.getWaitSensorTimeout()) {
      return true;
    } else {
      return super.isFinished();
    }
  }
}

