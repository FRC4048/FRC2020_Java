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

public class M1M2M3Command extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private final double STAGER_SPEED = 0.5;
  private final double CONVEYOR_SPEED = 0.5;
  private final double SHOOTER_SPEED = 0.5;
  private State initState;
  /**
   * Creates a new M1M2M3M4.
   */
  public M1M2M3Command(ConveyorSubsystem conveyorSubsystem, State initState) {
    this.conveyorSubsystem = conveyorSubsystem;
    this.initState = initState;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyorSubsystem); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyorSubsystem.moveShooter(SHOOTER_SPEED);
    conveyorSubsystem.moveStager(STAGER_SPEED);
    conveyorSubsystem.moveConveyor(CONVEYOR_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorSubsystem.moveShooter(0);
    conveyorSubsystem.moveStager(0);
    conveyorSubsystem.moveConveyor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return conveyorSubsystem.getState() == ConveyorStateMachine.wantedState(initState);
  }
}
