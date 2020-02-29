package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.balltransfer.TransferConveyorSubsystem;

public class ReverseIntake extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private TransferConveyorSubsystem transferConveyorSubsystem;

    public ReverseIntake(IntakeSubsystem intakeSubsystem, TransferConveyorSubsystem transferConveyorSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.transferConveyorSubsystem = transferConveyorSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intakeSubsystem.spinMotor(-1);
        transferConveyorSubsystem.moveTransfer(-1);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.spinMotor(0);
        transferConveyorSubsystem.moveTransfer(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
