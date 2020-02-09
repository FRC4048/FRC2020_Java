/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;

public class M2Command extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private final double CONVEYOR_SPEED = 0.5;
  private BallTransferState wantedState;
  private boolean force;
  private Timer timer;
  private final double DELAY = 1; 
  private Supplier<Integer> startCB;
  private Supplier<Integer> endCB;

  public M2Command(ConveyorSubsystem conveyorSubsystem, BallTransferState initState, Supplier<Integer> startCB, Supplier<Integer> endCB) {
    this(conveyorSubsystem, initState, startCB, endCB, false);
  }

  /**
   * Creates a new M2Command.
   * @param conveyorSubsystem 
   * @param initState
   * @param force <-- forces the motor run for a given time, rather than based of state
   */
  public M2Command(ConveyorSubsystem conveyorSubsystem, BallTransferState initState, Supplier<Integer> startCB, Supplier<Integer> endCB, boolean force) {
    this.conveyorSubsystem = conveyorSubsystem;
    wantedState = ConveyorStateMachine.wantedState(initState);
    this.force = force;
    this.startCB = startCB;
    this.endCB = endCB;
    addRequirements(conveyorSubsystem);
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    startCB.get(); //We add one to the command counter to signify that a command is currently scheudled
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyorSubsystem.moveConveyor(CONVEYOR_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    endCB.get(); //We subtract one from the counter to signify that the command has been completed
    conveyorSubsystem.moveConveyor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //This is here simply for case 15 where with just checking the state it thinks its immediatly done
    if(!force) {
      return ConveyorStateMachine.getState().getS2() == wantedState.getS2() && 
          ConveyorStateMachine.getState().getS3() == wantedState.getS3() &&
          ConveyorStateMachine.getState().getS4() == wantedState.getS4();
    } else {
      return timer.get() > DELAY;
    }
  }
}
