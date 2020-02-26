/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.utils.SmartShuffleboard;

public class TurnToAngle extends CommandBase {
  /**
   * Creates a new TurnToAngle.
   */
  private final double ANGLE_THRESHOLD = 2;
  private final double MAX_SPEED = 0.3;
  private final double MIN_SPEED = 0.2;   
  private final int SLOWDOWN_ANGLE = 45;
  private SixWheelDriveTrainSubsystem driveTrain;
  private double requiredAngle;
  private double currAngle;
  private double speed = 0.0;

  public TurnToAngle(SixWheelDriveTrainSubsystem driveTrain, int requiredAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.requiredAngle = requiredAngle;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currAngle = driveTrain.getAngle() * -1;
    double angleError = requiredAngle - currAngle;

    if (Math.abs(angleError) > 180) {
      if (currAngle > requiredAngle)
        requiredAngle += 360;
      else
        requiredAngle -= 360;
    }

    angleError = requiredAngle - currAngle;

    if (Math.abs(angleError) <= ANGLE_THRESHOLD) {
      speed = 0.0;
    } else {
      if (Math.abs(angleError) > SLOWDOWN_ANGLE)
        speed = MAX_SPEED;
      else
        speed = (MAX_SPEED - MIN_SPEED) * (Math.abs(angleError)/SLOWDOWN_ANGLE) + MIN_SPEED;
    }

    if (requiredAngle < currAngle){
      driveTrain.drive(-Math.abs(speed), Math.abs(speed), false);
    } 
    else if (currAngle < requiredAngle){
      driveTrain.drive(Math.abs(speed), -Math.abs(speed), false);
    }
    if (Constants.ENABLE_DEBUG == true){
      SmartShuffleboard.put("Turn", "Right speed", -speed);
      SmartShuffleboard.put("Turn", "Left speed", speed);
      SmartShuffleboard.put("Turn", "Error", angleError);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(currAngle - requiredAngle) <= ANGLE_THRESHOLD;
  }
}
