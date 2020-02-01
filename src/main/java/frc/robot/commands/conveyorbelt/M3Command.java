/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ConveyorStateMachine.State;
import frc.robot.subsystems.ConveyorStateMachine;

public class M3Command extends CommandBase {
  private final double STAGER_SPEED = 0.5;
  private ConveyorSubsystem conveyorSubsystem;
  private State initState;
  /**
   * Creates a new M3Command.
   */
  public M3Command(ConveyorSubsystem conveyorSubsystem, State initState) {
    this.conveyorSubsystem = conveyorSubsystem;
    addRequirements(conveyorSubsystem);
    this.initState = initState;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyorSubsystem.moveStager(STAGER_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorSubsystem.moveStager(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return conveyorSubsystem.getState() == ConveyorStateMachine.wantedState(initState);
  }
}
