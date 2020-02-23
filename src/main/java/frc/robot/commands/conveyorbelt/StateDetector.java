/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.logging.LogCommandWrapper;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.ShooterSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.balltransfer.BallTransferState;
import frc.robot.subsystems.balltransfer.ConveyorStateMachine;

public class StateDetector extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private TransferConveyorSubsystem transferSubsystem;
  private final double TIMEOUT = 2; // seconds
  private final double TRANSFER_LOW_SPEED = 0.8;

  /**
   * Creates a new StateDetector.
   */
  public StateDetector(ConveyorSubsystem conveyorSubsystem, TransferConveyorSubsystem transferSubsystem, ShooterSubsystem shooterSubsystem) {
    this.conveyorSubsystem = conveyorSubsystem;
    this.transferSubsystem = transferSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    conveyorSubsystem.setRunStateMachine(true);

    addRequirements(transferSubsystem);
    addRequirements(shooterSubsystem);
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
    BallTransferState state = ConveyorStateMachine.getState();
    
    /*
     * This logic is based off of this spreadsheet
     * https://docs.google.com/spreadsheets/d/1z1pBTtl-
     * SJKcTKWXfU3sdGcWpE97y6huKPtR8nkVKk4/edit?usp=drivesdk
     * 
     * The reason we are checking if the command is avaliable is because the default command is not 
     * being interupted fast enough normally. What this checks is if any other command in the system
     * is being used and if it is we will not try to schedule any commands.
     */
    if (!conveyorSubsystem.getRunStateMachine()) {
      transferSubsystem.moveTransfer(0);
    } else if (TransferConveyorSubsystem.getSlot5() && conveyorSubsystem.isCommandAvalible()) { //We will only move motors if there is a ball in the stager
      createCommand(conveyorSubsystem, transferSubsystem, shooterSubsystem, state).withTimeout(TIMEOUT).schedule();
    } else if (IntakeSubsystem.getIsRunning() && ConveyorStateMachine.getState() != BallTransferState.S31) { //This is static so we don't have to take an instance of the intake subsystem in this default command
      transferSubsystem.moveTransfer(TRANSFER_LOW_SPEED);
    } else if (!IntakeSubsystem.getIsRunning()) {
      transferSubsystem.moveTransfer(0);
    }
  }

  public static Command createCommand(ConveyorSubsystem conveyorSubsystem, TransferConveyorSubsystem transferSubsystem, ShooterSubsystem shooterSubsystem, BallTransferState state) {
    switch (state) {
    case S0:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S1:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state));
    case S2:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S3:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S4:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S5:
      return new LogCommandWrapper(new M2Command(conveyorSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M2Command");
    case S6:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S7:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S8:
      return new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem, transferSubsystem, shooterSubsystem, state), "M1M2M3Command");
    case S9:
      return new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem, transferSubsystem, shooterSubsystem, state), "M1M2M3Command");
    case S10:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S11:
      return new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem, transferSubsystem, shooterSubsystem, state), "M1M2M3Command");
    case S12:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S13:
      return new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem, transferSubsystem, shooterSubsystem, state), "M1M2M3Command");
    case S14:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S15:
      return new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem, transferSubsystem, shooterSubsystem, state, true), "M1M2M3Command");
    case S16:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S17:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S18:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S19:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S20:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S21:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S22:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S23:
      return new LogCommandWrapper(new M2M3Command(conveyorSubsystem, transferSubsystem, state), "M2M3Command");
    case S24:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");//The spreadsheet says something different
    case S25:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S26:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S27:
      conveyorSubsystem.setRunStateMachine(false);
      return new LogCommandWrapper(new StopMotors(conveyorSubsystem, transferSubsystem, shooterSubsystem), "StopMotors");
    case S28:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S29:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");//The spreadsheet is saying stop
    case S30:
      return new LogCommandWrapper(new M3Command(transferSubsystem, state, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded), "M3Command");
    case S31:
      conveyorSubsystem.setRunStateMachine(false);
      return new LogCommandWrapper(new StopMotors(conveyorSubsystem, transferSubsystem, shooterSubsystem), "StopMotors");
    case S32:
    default:
      conveyorSubsystem.setRunStateMachine(false);
      return new LogCommandWrapper(new StopMotors(conveyorSubsystem, transferSubsystem, shooterSubsystem), "StopMotors");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
