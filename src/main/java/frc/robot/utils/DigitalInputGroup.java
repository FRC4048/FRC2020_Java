/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class DigitalInputGroup {
    private DigitalInput sensor1;
    private DigitalInput sensor2;

    /**
     * A wrapper for 2 digital inputs that combines them into 1 object
     * 
     * @param sensor1
     * @param sensor2
     */
    public DigitalInputGroup(DigitalInput sensor1, DigitalInput sensor2) {
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
    }

    /**
     * Gets the current state of the group
     * 
     * @return the state
     */
    public boolean get() {
        return sensor1.get() || sensor2.get(); //TODO: need to figure out if this is true of false broken
    }
}
