package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ControlPanelSubsystem;

public class RotateToColor extends SequentialCommandGroup {
    ControlPanelSubsystem controlPanelSubsystem;

    public RotateToColor(ControlPanelSubsystem controlPanelSubsystem, int directions){
        SingleSwitchColor[] rotationQueue = new SingleSwitchColor[Math.abs(directions)];
        for(int i = 0; i < Math.abs(directions) - 1; i++){
            rotationQueue[i] = new SingleSwitchColor(controlPanelSubsystem, directions >= 0, false);
        }
        rotationQueue[rotationQueue.length-1] = new SingleSwitchColor(controlPanelSubsystem, directions >= 0, true);

        addCommands(rotationQueue);
    }
}