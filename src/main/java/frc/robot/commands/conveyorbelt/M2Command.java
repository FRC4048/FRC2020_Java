/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;

public class M2Command extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private final double CONVEYOR_SPEED = 0.5;
  private BallTransferState wantedState;
  /**
   * Creates a new M2Command.
   */
  public M2Command(ConveyorSubsystem conveyorSubsystem, BallTransferState initState) {
    this.conveyorSubsystem = conveyorSubsystem;
    wantedState = ConveyorStateMachine.wantedState(initState);
    addRequirements(conveyorSubsystem);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyorSubsystem.moveConveyor(CONVEYOR_SPEED);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorSubsystem.moveConveyor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ConveyorStateMachine.getState().getS2() == wantedState.getS2() && 
        ConveyorStateMachine.getState().getS3() == wantedState.getS3() &&
        ConveyorStateMachine.getState().getS4() == wantedState.getS4();
  }
}
