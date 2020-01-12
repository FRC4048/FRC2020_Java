package frc.robot.utils.logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import edu.wpi.first.wpilibj2.command.Command;

public class ProxyFactory {
    public static Command create(Command original, String name) {
        InvocationHandler handler = new CommandLoggingProxy(original, name);

        return (Command) Proxy.newProxyInstance(original.getClass().getClassLoader(), new Class[] {Command.class}, handler);
    }
}