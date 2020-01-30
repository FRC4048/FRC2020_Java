/* package frc.robot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.logging.Logging;

/*
 *   MotorStall object should be instantiated in init() method of a command
 *   isFinished() should call isStalled() to determine if stalled for longer than allowed
 

public class MotorUtils {
	public static final double DEFAULT_TIMEOUT = 0.15;
	private double timeout;
	private double time;
	final int PDPChannel;
	final double currentThreshold;
	private PowerDistributionPanel pdp;

	public MotorUtils(PowerDistributionPanel pdp, int PDPPort, double currentThreshold)
	{
		this.pdp = pdp;
		this.timeout = DEFAULT_TIMEOUT;
		this.PDPChannel = PDPPort;
		this.currentThreshold = currentThreshold;
		time = Timer.getFPGATimestamp();
	}
	
	public MotorUtils(PowerDistributionPanel pdp, int PDPPort, double currentThreshold, double timeout)
	{
		this(pdp, PDPPort, currentThreshold);
		this.timeout = timeout;
	}
	
	public boolean isStalled()
	{
		final double currentValue = pdp.getCurrent(PDPChannel);
		final double now = Timer.getFPGATimestamp();
		
		if (currentValue < currentThreshold)
		{
			time = now;
			System.out.println(pdp.getCurrent(PDPChannel))

		}
		else
		{
			DriverStation.reportError("Motor stall, PDP Channel=" + PDPChannel, false);
			if (now - time > timeout)
			{
				Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "Motor stall, PDP channel =" + PDPChannel);
				return true;
			}
		}
		return false;
	}
}
*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.logging.Logging;

/*
 *   MotorStall object should be instantiated in init() method of a command
 *   isFinished() should call isStalled() to determine if stalled for longer than allowed
 */

public class MotorUtils {
	public static final double DEFAULT_TIMEOUT = 0.15;
	private double timeout;
	private double time;
	final int PDPChannel;
	final double currentThreshold;
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	
	public MotorUtils(int PDPPort, double currentThreshold)
	{
		this.timeout = DEFAULT_TIMEOUT;
		this.PDPChannel = PDPPort;
		this.currentThreshold = currentThreshold;
		time = Timer.getFPGATimestamp();
	}
	
	public MotorUtils(int PDPPort, double currentThreshold, double timeout)
	{
		this(PDPPort, currentThreshold);
		this.timeout = timeout;
	}
	
	public boolean isStalled()
	{
		final double currentValue = pdp.getCurrent(PDPChannel);
		final double now = Timer.getFPGATimestamp();
		
		if (currentValue < currentThreshold)
		{
			time = now;
			
		}
		else
		{
			DriverStation.reportError("Motor stall, PDP Channel=" + PDPChannel, false);
			final double timeStalled = now - time;

			if (timeStalled > timeout)
			{
				Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "Motor stall, PDP channel =" + PDPChannel);
				return true;
			}
			
		}
		return false;
	}
}

