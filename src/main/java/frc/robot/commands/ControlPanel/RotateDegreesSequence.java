/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.drivetrain.MoveBackwards;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RotateDegreesSequence extends SequentialCommandGroup {
  /**
   * Creates a new RotateDegreesSequence.
   */
  ControlPanelSubsystem controlPanelSubsystem;
  SixWheelDriveTrainSubsystem driveTrain;
  double driveBackSpeed;
  public RotateDegreesSequence(ControlPanelSubsystem controlPanelSubsystem, SixWheelDriveTrainSubsystem driveTrain, double degreesTurn, double driveBackSpeed, double speed) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    this.controlPanelSubsystem = controlPanelSubsystem;
    this.driveTrain = driveTrain;
    this.driveBackSpeed = driveBackSpeed;
    controlPanelSubsystem.setWaitSensorTimeout(false);
    
    addCommands (
      new MoveSolenoid(controlPanelSubsystem, true),
      new WaitForSensor(controlPanelSubsystem),
      new RotateDegrees(controlPanelSubsystem, degreesTurn, speed).withTimeout(Constants.CONTROL_PANEL_ROTATE_DEGREES_TIMEOUT),
      new MoveBackwards(controlPanelSubsystem, driveTrain, driveBackSpeed).withTimeout(2),
      new MoveSolenoid(controlPanelSubsystem, false)
      );
  }

  @Override
    public void initialize() {
    super.initialize();
  }

  @Override
  public boolean isFinished() {
    if (controlPanelSubsystem.getWaitSensorTimeout()) {
      controlPanelSubsystem.movePiston(false);
      return true;
    } else {
      return super.isFinished();
    } 
  }
}
