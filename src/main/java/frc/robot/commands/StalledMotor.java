/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.robot.subsystems.PowerDistPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SixWheelDriveTrainSubsystem;
import frc.robot.subsystems.StallMotorTest;
import frc.robot.utils.MotorUtils; 
import frc.robot.Constants;

public class StalledMotor extends CommandBase {
  private final StallMotorTest stallMotor;
  private final PowerDistPanel pdp = new PowerDistPanel();
  private final MotorUtils driveStall = new MotorUtils(pdp.getPDP(), 0, 5.0, 2.0);
  private Timer time = new Timer();
  /**
   * Creates a new StalledMotor.
   */

  public StalledMotor(StallMotorTest stallMotor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(stallMotor);
    this.stallMotor = stallMotor;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    stallMotor.drive(0.3);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    stallMotor.drive(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(driveStall.isStalled()){
      return true;

    }

    if(time.hasPeriodPassed(5.0)){
      return true;
    }

    return false;
    
  }
}
