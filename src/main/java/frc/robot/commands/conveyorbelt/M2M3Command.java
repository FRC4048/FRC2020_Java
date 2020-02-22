/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.command.WaitForChildren;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;
import frc.robot.utils.logging.LogCommandWrapper;
import frc.robot.utils.logging.Logging;
import frc.robot.utils.logging.Logging.MessageLevel;
import frc.robot.subsystems.balltransfer.BallTransferState;

public class M2M3Command extends SequentialCommandGroup {
  private final double DELAY = 1;

  public M2M3Command(ConveyorSubsystem conveyorSubsystem, TransferConveyorSubsystem transferConveyorSubsystem, BallTransferState initState) {
    addCommands(
      new LogCommandWrapper(new M3Command(transferConveyorSubsystem, initState, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded)),
      new SequentialCommandGroup(
        // new WaitCommand(DELAY),
        new LogCommandWrapper(new M2Command(conveyorSubsystem, initState, conveyorSubsystem::commandStarted, conveyorSubsystem::commandEnded))        
      )
    );
  }
}
