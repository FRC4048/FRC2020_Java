package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.utils.ColorSensor.ColorValue;

public class SingleSwitchColor extends CommandBase {
    private ControlPanelSubsystem controlPanelSubsystem;
    private ColorValue initialColor;
    private boolean direction;
    private boolean brake;
    private final double SPEED = 0.7; //TODO: Change to what we use.

    /**
     *
     * @param controlPanelSubsystem
     * @param direction this determines whether to spin left or right.
     * @param brake this determines whether the motor should stop after executing.
     */
    public SingleSwitchColor(ControlPanelSubsystem controlPanelSubsystem, boolean direction, boolean brake){
        this.controlPanelSubsystem = controlPanelSubsystem;
        this.direction = direction;
        this.brake = brake;
    }

    @Override
    public void initialize() {
        initialColor = controlPanelSubsystem.getCurrentColor();
    }

    @Override
    public void execute() {
        if(direction){
            controlPanelSubsystem.rotateWithSpeed(SPEED);
        } else{
            controlPanelSubsystem.rotateWithSpeed(-SPEED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        if(brake){
            controlPanelSubsystem.stopRotation();
        }
    }

    @Override
    public boolean isFinished() {
        return controlPanelSubsystem.getCurrentColor() != initialColor;
    }
}
