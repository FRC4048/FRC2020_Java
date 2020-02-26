package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TestLog;
import org.junit.Test;
import frc.robot.utils.logging.LogCommandWrapper;


public class ProxyTest {

//    @Test
    public void testProxy() {
        Command testLogCommand = new TestLog().withTimeout(2);
        Command commandWrapper = new LogCommandWrapper(testLogCommand, "TestLogCommand"); 
        commandWrapper.initialize();
        commandWrapper.end(false);
        commandWrapper.isFinished();
    }    
}