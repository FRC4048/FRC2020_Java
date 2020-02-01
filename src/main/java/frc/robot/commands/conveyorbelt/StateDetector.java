/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ConveyorSubsystem.State;
import frc.robot.utils.logging.LogCommandWrapper;

public class StateDetector extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  private final double TIMEOUT = 0.5; // seconds

  /**
   * Creates a new StateDetector.
   */
  public StateDetector(ConveyorSubsystem conveyorSubsystem) {
    this.conveyorSubsystem = conveyorSubsystem;
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
    State state = conveyorSubsystem.getState();
    if (conveyorSubsystem.getSlot5()) {
      switch (state) {
      case S0:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S1:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S2:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S3:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S4:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S5:
        new LogCommandWrapper(new M2Command(conveyorSubsystem), "M2Command").withTimeout(TIMEOUT).schedule();
        break;
      case S6:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S7:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S8:
        new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem), "M1M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S9:
        new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem), "M1M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S10:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S11:
        new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem), "M1M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S12:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S13:
        new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem), "M1M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S14:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S15:
        new LogCommandWrapper(new M1M2M3Command(conveyorSubsystem), "M1M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S16:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S17:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S18:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S19:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S20:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S21:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S22:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S23:
        new LogCommandWrapper(new M2M3Command(conveyorSubsystem), "M2M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S24:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S25:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S26:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S27:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S28:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S29:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S30:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S31:
        new LogCommandWrapper(new M3Command(conveyorSubsystem), "M3Command").withTimeout(TIMEOUT).schedule();
        break;
      case S32:
        break;
      default:
        break;
      }
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
