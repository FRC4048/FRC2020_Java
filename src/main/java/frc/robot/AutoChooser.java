/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutonomousCommands.AutoCrossLine;
import edu.wpi.first.wpilibj.shuffleboard.*;

/**
 * Add your docs here.
 */
public class AutoChooser {
    private SendableChooser<Position> positionChooser;
    private SendableChooser<Action> actionChooser;
    //position at beginning of match
    enum Position{
        MIDDLE, LEFT, RIGHT;
    }
    //all actions driver choose at beginning of match
    enum Action {
        DO_NOTHING, DEPOSIT, CROSS_LINE, PICKUP;
    }
    //all commmands during autonomous
    enum AutoCommand {
        LEFT_DEPOSIT, RIGHT_DEPOSIT, MIDDLE_DEPOSIT, LEFT_PICKUP, MIDDLE_PICKUP, RIGHT_PICKUP,
        DO_NOTHING, CROSS_LINE;
    }


    public AutoChooser(){
        positionChooser = new SendableChooser<Position>();
        actionChooser = new SendableChooser<Action>();
    }
    public void addOptions(){
        positionChooser.addOption(Position.LEFT.name(), Position.LEFT);
        positionChooser.addOption(Position.MIDDLE.name(), Position.MIDDLE);
        positionChooser.addOption(Position.RIGHT.name(), Position.RIGHT); 
        actionChooser.setDefaultOption(Action.DO_NOTHING.name(), Action.DO_NOTHING);
        actionChooser.addOption(Action.DEPOSIT.name(), Action.DEPOSIT);
        actionChooser.addOption(Action.CROSS_LINE.name(), Action.CROSS_LINE);
        actionChooser.addOption(Action.PICKUP.name(), Action.PICKUP);

    }
    public void initialize() {
        ShuffleboardTab tab = Shuffleboard.getTab("Driver");
        tab.add("Autonomous Position", positionChooser);
        tab.add("Autonomous Action", actionChooser);
    }
    public void print(){
        System.out.println(positionChooser.getSelected());
        System.out.println(actionChooser.getSelected());
    }

    public Action getAction(){
        return actionChooser.getSelected();
    }

    public Position getPosition (){
        return positionChooser.getSelected();
    }
   
    public AutoCommand getAutonomousCommand( Position p, Action a){

        if (a == Action.DO_NOTHING){
            return AutoCommand.DO_NOTHING;
        }

        else if (a == Action.CROSS_LINE){
            return AutoCommand.CROSS_LINE;
        }
        else if (a == Action.DEPOSIT){
            if (p == Position.LEFT){
                return AutoCommand.LEFT_DEPOSIT;
            }   
            else if (p == Position.MIDDLE){
                return AutoCommand.MIDDLE_DEPOSIT;
            }
            else if (p == Position.RIGHT){
                return AutoCommand.RIGHT_DEPOSIT;
            }
        }
        else if (a == Action.PICKUP){
            if (p == Position.LEFT){
                return AutoCommand.LEFT_DEPOSIT;
            }   
            else if (p == Position.MIDDLE){
                return AutoCommand.MIDDLE_DEPOSIT;
            }
            else if (p == Position.RIGHT){
                return AutoCommand.RIGHT_DEPOSIT;
            }
        }
        return AutoCommand.CROSS_LINE;
    }
}
