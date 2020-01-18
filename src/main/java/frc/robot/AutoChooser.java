/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.*;
import java.lang.*;


/**
 * Add your docs here.
 */
public class AutoChooser {
    private SendableChooser<Position> positionChooser;
    private SendableChooser<Action> actionChooser;

    enum Position{
        MIDDLE, LEFT, RIGHT;
    }
    enum Action {
        DO_NOTHING, DEPOSIT;
    }

    public AutoChooser(){
        positionChooser = new SendableChooser<Position>();
        actionChooser = new SendableChooser<Action>();
    }
    public void AddOptions(){
        positionChooser.addOption(Position.LEFT.name(), Position.LEFT);
        positionChooser.addOption(Position.MIDDLE.name(), Position.MIDDLE);
        positionChooser.addOption(Position.RIGHT.name(), Position.RIGHT); 
        actionChooser.setDefaultOption(Action.DO_NOTHING.name(), Action.DO_NOTHING);
        actionChooser.addOption(Action.DEPOSIT.name(), Action.DEPOSIT);
    }
    public void Initialize() {
        ShuffleboardTab tab = Shuffleboard.getTab("Position");
        tab.add("Position", positionChooser);
        tab.add("Action", actionChooser);
    }
    public void Print(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(positionChooser.getSelected());
        System.out.println(actionChooser.getSelected());
    }
}
