package frc.robot.utils.logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

public class CommandLoggingProxy implements InvocationHandler {

    private Object target;
    private final String ident;
	private final Set<String> requirements = new TreeSet<String>();

    public CommandLoggingProxy(Object target, final String ident) {
        this.target = target;
        this.ident = ident;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log("Invoking " + method.getName());
        return method.invoke(target, args);
    }

    private void log(final String text) {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" ");
		sb.append(ident);
		// TODO: Cache the string value for requirements to save on creation every time the log message is called
		Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, sb.toString(), requirements.toString(), text);
	}

}