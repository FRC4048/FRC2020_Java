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

public class ShootBallAuto extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private TransferConveyorSubsystem transferConveyorSubsystem;
  private ShooterSubsystem shooterSubsystem;
  /**
   * Creates a new ShootBallAuto.
   */
  public ShootBallAuto(ConveyorSubsystem conveyorSubsystem, TransferConveyorSubsystem transferConveyorSubsystem, ShooterSubsystem shooterSubsystem) {
    this.conveyorSubsystem = conveyorSubsystem;
    this.transferConveyorSubsystem = transferConveyorSubsystem;
    this.shooterSubsystem = shooterSubsystem;

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
    conveyorSubsystem.moveConveyor(1); 
    shooterSubsystem.moveShooter(1);
    transferConveyorSubsystem.moveTransfer(1); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ConveyorStateMachine.getState() == BallTransferState.S0;
  }
}
