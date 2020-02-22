/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;

public class ShootBalls extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private TransferConveyorSubsystem transferConveyorSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private boolean isFlush;
  private double rightTriggerVaue;
  private final double SHOOTER_SPEED = 1;
  private final double CONVEYOR_SPEED = 1;
  private final double TRANSFER_SPEED = 1;

  /**
   * Creates a new ShootBalls.
   * 
   * @param direction the direction that the balls will be shooting out of
   */
  public ShootBalls(ConveyorSubsystem conveyorSubsystem, TransferConveyorSubsystem transferConveyorSubsystem, ShooterSubsystem shooterSubsystem, boolean isFlush, double rightTriggerValue) {
    this.conveyorSubsystem = conveyorSubsystem;
    this.transferConveyorSubsystem = transferConveyorSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    this.isFlush = isFlush;
    this.rightTriggerVaue = rightTriggerVaue;

    addRequirements(conveyorSubsystem);
    addRequirements(transferConveyorSubsystem);
    addRequirements(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!isFlush) {
      conveyorSubsystem.moveConveyor(CONVEYOR_SPEED); 
      shooterSubsystem.moveShooter(SHOOTER_SPEED);
      transferConveyorSubsystem.moveTransfer(TRANSFER_SPEED); 
    } else if (rightTriggerVaue > 0.3){
      conveyorSubsystem.moveConveyor(-CONVEYOR_SPEED); 
      shooterSubsystem.moveShooter(-SHOOTER_SPEED);
      transferConveyorSubsystem.moveTransfer(-TRANSFER_SPEED);  
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
