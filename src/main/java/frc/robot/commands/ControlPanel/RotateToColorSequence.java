/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
  public RotateToColorSequence(ControlPanelSubsystem controlPanelSubsystem, SixWheelDriveTrainSubsystem driveTrain, double driveBackSpeed) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(
      new MoveSolenoid(controlPanelSubsystem, Value.kForward),
      new MoveSolenoid(controlPanelSubsystem, Value.kOff),
      new WaitForSensor(controlPanelSubsystem),
      new SetDrivingEnabled(false),
      new RotateToColor(controlPanelSubsystem),
      new WaitCommand(0.1),
      new MoveBackwards(controlPanelSubsystem, driveTrain, driveBackSpeed).withTimeout(0.3),
      new MoveSolenoid(controlPanelSubsystem, Value.kReverse)
      );
  }

  @Override
  public void end(boolean interrupted) { 
    Robot.m_robotContainer.setDrivingEnabled(true);
    super.end(interrupted);
  }
}
