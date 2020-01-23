/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.utils.logging.Logging;

/**
 * Add your docs here.
 */
public class PowerDistributionPanel {
    public static PowerDistributionPanel pdp;

    public Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {

        @Override
        protected void addAll() {
            add("Total Voltage", pdp.getVoltage());
            add("Total Current", pdp.getTotalCurrent());
            add("FR Steer", pdp.getCurrent(Constants.PDP_STEERING_FR));
            add("FL Steer", pdp.getCurrent(Constants.PDP_STEERING_FL));
            add("RL Steer", pdp.getCurrent(Constants.PDP_STEERING_BL));
            add("RR Steer", pdp.getCurrent(Constants.PDP_STEERING_BR));
            add("FR Drive", pdp.getCurrent(Constants.PDP_DRIVE_FR));
            add("FL Drive", pdp.getCurrent(Constants.PDP_DRIVE_FL));
            add("RL Drive", pdp.getCurrent(Constants.PDP_DRIVE_BL));
            add("RR Drive", pdp.getCurrent(Constants.PDP_DRIVE_BR));
        }
    };
    
}

