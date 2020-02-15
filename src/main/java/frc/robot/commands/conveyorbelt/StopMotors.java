/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;

public class StopMotors extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private TransferConveyorSubsystem transferSubsystem;
  private ShooterSubsystem shooterSubsystem;
  /**
   * Creates a new StopMotors.
   */
  public StopMotors(ConveyorSubsystem conveyorSubsystem, TransferConveyorSubsystem transferSubsystem, ShooterSubsystem shooterSubsystem) {
    this.conveyorSubsystem = conveyorSubsystem;
    this.transferSubsystem = transferSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    
    addRequirements(conveyorSubsystem);
    addRequirements(transferSubsystem);
    addRequirements(shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorSubsystem.moveConveyor(0);
    shooterSubsystem.moveShooter(0);
    transferSubsystem.moveTransfer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
