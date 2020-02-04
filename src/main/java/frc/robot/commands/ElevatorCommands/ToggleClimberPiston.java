/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ToggleClimberPiston extends CommandBase {
    private ElevatorSubsystem climber;
    private boolean isActivated;

    /**
     * Creates a new ToggleClimberPiston.
     */
    public ToggleClimberPiston(ElevatorSubsystem climber, boolean isActivated) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.climber = climber;
        this.isActivated = isActivated;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (isActivated) {
            if (climber.getPistonState() && climber.getBottomSwitch()) {
                climber.retractPiston();
            } else {
                climber.extendPiston();
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
        return true;
    }
}
