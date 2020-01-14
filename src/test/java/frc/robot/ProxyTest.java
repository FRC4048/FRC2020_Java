package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TestLog;
import org.junit.Test;
import frc.robot.utils.logging.ProxyFactory;

public class ProxyTest {

    @Test
    public void testProxy() {
        Command testLogCommand = new TestLog().withTimeout(2);
        Command proxyCommand = (Command) ProxyFactory.create(testLogCommand, "TestLog"); 
        proxyCommand.initialize();
        proxyCommand.end(false);
        proxyCommand.isFinished();
    }    
}