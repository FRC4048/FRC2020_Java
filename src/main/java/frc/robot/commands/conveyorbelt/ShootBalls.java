/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyorbelt;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;

public class ShootBalls extends CommandBase {
  private ConveyorSubsystem conveyorSubsystem;
  /**
   * Creates a new ShootBalls.
   */
  public ShootBalls(ConveyorSubsystem conveyorSubsystem) {
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
    conveyorSubsystem.moveConveyor(1);
    conveyorSubsystem.moveShooter(1);
    conveyorSubsystem.moveStager(1); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorSubsystem.moveConveyor(0);
    conveyorSubsystem.moveShooter(0);
    conveyorSubsystem.moveStager(0); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; //TODO make this end when the operator let goes of the shoot button.
  }
}
