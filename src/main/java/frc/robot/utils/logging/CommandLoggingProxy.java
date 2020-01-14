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
        System.out.println(method.getName());
        switch(method.getName()) {
            case "initialize":
                log("initialize");
                return method.invoke(target, args);
            case "end":
                log("end");
                return method.invoke(target, args);
            case "isFinished":
                Boolean result = (Boolean) method.invoke(target, args);
                if(result) { 
                    log("isFinished");
                }
                return result;
            case "isTimedOut":
                Boolean timedResult = (Boolean) method.invoke(target, args);
                if(timedResult) {
                    log("isTimedOut");
                }
                return timedResult;
            default:
                log("Invoking " + method.getName());
                return method.invoke(target, args);
        }
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