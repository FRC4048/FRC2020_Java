
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.logging.Logging;

public class CompressorSubsystem extends SubsystemBase {

    public Compressor compressor;
    private Relay relay; 
    private DigitalInput input;

    public CompressorSubsystem() {
        compressor = new Compressor(Constants.PCM_CAN_ID);
        compressor.setClosedLoopControl(false);
        compressor.start();
        relay = new Relay(Constants.COMPRESSOR_RELAY, Relay.Direction.kForward); 
        input = new DigitalInput(Constants.COMPRESSOR_PRESSURE);
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

    public Relay.Value getRelay() {
        return relay.get();
    }

    public boolean getDIO() {
        return input.get();
    }
    

    public void setRelay(){
        relay.set(Relay.Value.kOn);
    }

    public void resetRelay(){
        relay.set(Relay.Value.kOff);
    }
}