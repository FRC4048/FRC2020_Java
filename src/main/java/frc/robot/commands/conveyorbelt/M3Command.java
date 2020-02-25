/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem.S5RequiredSensor;

public class M3Command extends CommandBase {

  private final double STAGER_SPEED = 0.7;
  private TransferConveyorSubsystem transferConveyorSubsystem;
  private S5RequiredSensor s5RequiredSensor;
  private BallTransferState wantedState;
  private Supplier<Integer> startCB;
  private Supplier<Integer> endCB;

  public M3Command(TransferConveyorSubsystem transferConveyorSubsystem, BallTransferState initState, Supplier<Integer> startCB, Supplier<Integer> endCB) {
    this(transferConveyorSubsystem, initState, S5RequiredSensor.UPPER, startCB, endCB);
  }

  /**
   * Creates a new M3Command.
   * @param transferConveyorSubsystem
   * @param initState The initial state the state machine is at
   * @param s5RequiredSensor whether to count the upper or lower M5 sensor (so that we don't have to do 64 states in the machine)
   * @param startCB the callback to call when starting the command (to make sure init is happenning only once
   * @param endCB the callback to call when ending the command (to make sure init is happenning only once
   */
  public M3Command(TransferConveyorSubsystem transferConveyorSubsystem, BallTransferState initState, S5RequiredSensor s5RequiredSensor, Supplier<Integer> startCB, Supplier<Integer> endCB) {
    this.transferConveyorSubsystem = transferConveyorSubsystem;
    this.s5RequiredSensor = s5RequiredSensor;
    addRequirements(transferConveyorSubsystem);
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
    transferConveyorSubsystem.moveTransfer(STAGER_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transferConveyorSubsystem.moveTransfer(0);
    endCB.get(); //We subtract one from the counter to signify that the command has been completed
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ConveyorStateMachine.getState(s5RequiredSensor).getS5() == wantedState.getS5();
  }
}
