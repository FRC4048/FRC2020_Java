/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.utils.logging.Logging;

/**
 * Add your docs here.
 */
public class MotorUtils {
    public static final double DEFAULT_TIMEOUT = 0.15;
    private double timeout;
    private double time;
    final int PDPChannel;
    final double currentThreshold;

    public MotorUtils(int PDPPort, double currentThreshold) {
        this.timeout = DEFAULT_TIMEOUT;
        this.PDPChannel = PDPPort;
        this.currentThreshold = currentThreshold;
        time = Timer.getFPGATimestamp();
    }

    public MotorUtils(int PDPPort, double currentThreshold, double timeout) {
        this(PDPPort, currentThreshold);
        this.timeout = timeout;
    }

    public boolean isStalled() {
        final double currentValue = Robot.m_robotContainer.m_PowerDistPanel.getPDP().getCurrent(PDPChannel);
        final double now = Timer.getFPGATimestamp();

        if (currentValue < currentThreshold) {
            time = now;
        } else {
            DriverStation.reportError("Motor stall, PDP Channel=" + PDPChannel, false);
            if (now - time > timeout) {
                Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "Motor stall, PDP channel =" + PDPChannel);
                return true;
            }
        }
        return false;
    }
}
