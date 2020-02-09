/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;

public class M1Command extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private final double SHOOTER_SPEED = 0.25;
  private BallTransferState wantedState;
  
  /**
   * Creates a new M1Command.
   */
  public M1Command(ShooterSubsystem shooterSubsystem, BallTransferState initState) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
    wantedState = ConveyorStateMachine.wantedState(initState);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterSubsystem.moveShooter(SHOOTER_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.moveShooter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ConveyorStateMachine.getState().getS1() == wantedState.getS1();
  }
}
