/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;

public class ReverseAll extends CommandBase {
  private TransferConveyorSubsystem transferConveyorSubsystem;
  private ConveyorSubsystem conveyorSubsystem;
  private ShooterSubsystem shooterSubsystem;
  
  /**
   * Creates a new ReverseAll.
   */
  public ReverseAll(TransferConveyorSubsystem transferConveyorSubsystem, ConveyorSubsystem conveyorSubsystem, ShooterSubsystem shooterSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.conveyorSubsystem = conveyorSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    this.transferConveyorSubsystem = transferConveyorSubsystem;

    addRequirements(conveyorSubsystem, shooterSubsystem, transferConveyorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyorSubsystem.moveConveyor(-1);
    shooterSubsystem.moveShooter(-1);
    transferConveyorSubsystem.moveTransfer(-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorSubsystem.moveConveyor(0);
    shooterSubsystem.moveShooter(0);
    transferConveyorSubsystem.moveTransfer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
