/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutonomousCommands.AutoCrossLine;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;

/**
 * Add your docs here.
 */
public class AutoChooser {
    private SendableChooser<Position> positionChooser;
    private SendableChooser<Action> actionChooser;
    private NetworkTableEntry delayEntry;
    AutoCommand autonomousCommand;
    //position at beginning of match
    enum Position{
        MIDDLE, LEFT, RIGHT;
    }
    //all actions driver choose at beginning of match
    enum Action {
        DO_NOTHING, DEPOSIT, CROSS_LINE, PICKUP, FEED;
    }
    //all commmands during autonomous
    enum AutoCommand {
        LEFT_DEPOSIT, RIGHT_DEPOSIT, MIDDLE_DEPOSIT, RIGHT_PICKUP,
        DO_NOTHING, CROSS_LINE, FEED_LEFT, FEED_RIGHT;
    }
 
    public AutoChooser(){
        positionChooser = new SendableChooser<Position>();
        actionChooser = new SendableChooser<Action>();
        AutoCommand autonomousCommand = getAutonomousCommand(getPosition(), getAction());
    }
    public void addOptions(){
        positionChooser.addOption(Position.LEFT.name(), Position.LEFT);
        positionChooser.addOption(Position.MIDDLE.name(), Position.MIDDLE);
        positionChooser.addOption(Position.RIGHT.name(), Position.RIGHT); 
        actionChooser.setDefaultOption(Action.CROSS_LINE.name(), Action.CROSS_LINE);
        actionChooser.addOption(Action.DEPOSIT.name(), Action.DEPOSIT);
        actionChooser.addOption(Action.PICKUP.name(), Action.PICKUP);
        actionChooser.addOption(Action.FEED.name(), Action.FEED);
        actionChooser.addOption(Action.DO_NOTHING.name(), Action.DO_NOTHING);

    }
    public void initialize() {
        ShuffleboardTab tab = Shuffleboard.getTab("Driver");
        tab.add("Autonomous Position", positionChooser);
        tab.add("Autonomous Action", actionChooser);
        delayEntry = tab.add("Delay", 0).getEntry();
    }

    public Action getAction(){
        if(actionChooser.getSelected() != null){
            return actionChooser.getSelected();
        } else{
            return Action.DO_NOTHING;
        }
    }

    public Position getPosition(){
        if(positionChooser.getSelected() != null){
            return positionChooser.getSelected();
        } else{
            return Position.MIDDLE;
        }
    }

    public int getDelay(){
        int delay = delayEntry.getNumber(0).intValue();
        if(autonomousCommand == AutoCommand.DO_NOTHING
            || autonomousCommand == AutoCommand.RIGHT_PICKUP 
            || autonomousCommand == AutoCommand.CROSS_LINE){
            return 0;
        }
        if(delay <= 6 && delay > 0){
            return delay;
        }else{
            return 0;
        }
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
                //Don't want to pickup from trench when we are not on the right
                return AutoCommand.LEFT_DEPOSIT;
            }   
            else if (p == Position.MIDDLE){
                return AutoCommand.MIDDLE_DEPOSIT;
            }
            else if (p == Position.RIGHT){
                return AutoCommand.RIGHT_PICKUP;
            }
        }
        else if (a == Action.FEED){
            if (p == Position.LEFT){
                return AutoCommand.FEED_LEFT;
            }
            else if (p == Position.MIDDLE){
                return AutoCommand.DO_NOTHING;
            }
            else if (p == Position.RIGHT){
                return AutoCommand.FEED_RIGHT;
            }
        }
        return AutoCommand.CROSS_LINE;
    }
}