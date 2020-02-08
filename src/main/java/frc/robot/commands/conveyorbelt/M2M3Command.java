/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.balltransfer.ConveyorSubsystem;
import frc.robot.subsystems.balltransfer.BallTransferState;

public class M2M3Command extends ParallelCommandGroup {
  private final double DELAY = 0.5;

  public M2M3Command(ConveyorSubsystem conveyorSubsystem, BallTransferState initState) {
    addCommands(
      new M3Command(conveyorSubsystem, initState),
      new SequentialCommandGroup(
        new WaitCommand(DELAY),
        new M2Command(conveyorSubsystem, initState)  
      )
    );
  }
}
