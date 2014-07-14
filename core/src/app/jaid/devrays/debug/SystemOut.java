package app.jaid.devrays.debug;

import com.badlogic.gdx.utils.Array;

public class SystemOut implements Printing {

	Array<LogContext> contexts = new Array<LogContext>();

	public SystemOut() {
		contexts.addAll(LogContext.INFO, LogContext.DEBUG, LogContext.WARNING, LogContext.ERROR);
	}

	@Override
	public Array<LogContext> getContexts()
	{
		return contexts;
	}

	@Override
	public boolean isDisplayingContextInfo()
	{
		return true;
	}

	@Override
	public void print(String message, LogContext context)
	{
		(context == LogContext.ERROR ? System.err : System.out).println(message);
	}

}
