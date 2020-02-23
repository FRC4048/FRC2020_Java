/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;

public class M1Command extends CommandBase {
  private ShooterSubsystem shooterSubsystem;
  private final double SHOOTER_SPEED = 0.1;
  private BallTransferState wantedState;
  private Supplier<Integer> startCB;
  private Supplier<Integer> endCB;

  /**
   * Creates a new M1Command.
   */
  public M1Command(ShooterSubsystem shooterSubsystem, BallTransferState initState, Supplier<Integer> startCB, Supplier<Integer> endCB) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
    wantedState = ConveyorStateMachine.wantedState(initState);
    this.startCB = startCB;
    this.endCB = endCB;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startCB.get(); //We add one to the command counter to signify that a command is currently scheudled
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
    endCB.get(); //We subtract one from the counter to signify that the command has been completed
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ConveyorStateMachine.getState().getS1() == wantedState.getS1();
    // return false;
  }
}
