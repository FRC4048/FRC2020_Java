/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;

public class TurnToAngle extends CommandBase {
  /**
   * Creates a new TurnToAngle.
   */
  private final double MIN_ANGLE = -180;
  private final double MAX_ANGLE = 180;
  private final double ANGLE_THRESHOLD = 2;
  private final double MAX_SPEED = 0.2;
  private final double MIN_SPEED = 0.1;
  private SixWheelDriveTrainSubsystem driveTrain;
  private double angle;
  private double currAngle;

  public TurnToAngle(SixWheelDriveTrainSubsystem driveTrain, int angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    angle = Math.max(angle, MIN_ANGLE);
    angle = Math.min(angle, MAX_ANGLE);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currAngle = driveTrain.getAngle();
    double angleError = Math.abs(currAngle - angle);
    double speed = 0.0;

    if (Math.abs(angle - currAngle) < ANGLE_THRESHOLD) {
      speed = 0.0;
    } else {
      if (Math.abs(currAngle - angle) > 180) {
        if (currAngle > angle)
          angle += 360;
        else
          angle -= 360;
      }
      // 180 is the maximum error
      angleError = angle - currAngle;

      speed = (angleError / 180) * (MAX_SPEED - MIN_SPEED);
      if (angleError < 0)
        speed -= MIN_SPEED;
      else
        speed += MIN_SPEED;
      if (Math.abs(angle - currAngle) < ANGLE_THRESHOLD)
        speed = 0;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(currAngle - angle) <= ANGLE_THRESHOLD;
  }
}
