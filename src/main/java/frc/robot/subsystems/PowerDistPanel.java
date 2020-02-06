/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.utils.logging.Logging;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/**
 * Add your docs here.
 */
public class PowerDistPanel extends SubsystemBase{
    private PowerDistributionPanel pdp;

    public PowerDistPanel() {
        pdp = new PowerDistributionPanel(Constants.PDP_CAN_ID);
	}

    public Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {

        @Override
        protected void addAll() {

            add("Total Voltage", pdp.getVoltage());
            add("Total Current", pdp.getTotalCurrent());
            add("Drive R1", pdp.getCurrent(Constants.PDP_DRIVE_R1));
            add("Drive R2", pdp.getCurrent(Constants.PDP_DRIVE_R2));
            add("Drive L1", pdp.getCurrent(Constants.PDP_DRIVE_L1));
            add("Drive L2", pdp.getCurrent(Constants.PDP_DRIVE_L2));
            
        }
    };

    public PowerDistributionPanel getPDP() {
        return pdp;
    }
    public void periodic() {
	}
}
