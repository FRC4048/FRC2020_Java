
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.logging.Logging;

public class CompressorSubsystem extends SubsystemBase {

    private Compressor compressor;

    public CompressorSubsystem() {
        compressor = new Compressor(Constants.PCM_CAN_ID);
        compressor.setClosedLoopControl(true);
    }
    @Override
    public void periodic() {
    }


    public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(this.getClass()) {
        protected void addAll() {
            add("Pressure Switch", getPressureSwitch());
            add("Current", getCurrent());
        }
    };

    public boolean getPressureSwitch() {
        return compressor.getPressureSwitchValue();
    }

    public double getCurrent() {
        return compressor.getCompressorCurrent();
    }

}