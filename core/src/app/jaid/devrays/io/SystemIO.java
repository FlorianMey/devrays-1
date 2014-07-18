package app.jaid.devrays.io;

import java.io.*;

import app.jaid.devrays.debug.*;

import com.badlogic.gdx.utils.Array;

/**
 * Wrapper for native shell Devrays is started in. Is able to read input lines and passes them to
 * {@link CommandExecutor#run(String)}. Also acts as printer and outputs development related log entries to native
 * shell.
 * 
 * @author jaid
 */
public class SystemIO implements Printing {

	private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

	public static void readInput()
	{
		try
		{
			if (inputReader.ready())
			{
				String line = inputReader.readLine();
				CommandExecutor.run(line);
			}
		} catch (IOException e)
		{
			Log.exception("Could not read from native shell", e);
		}
	}

	Array<LogContext> contexts = new Array<LogContext>();

	public SystemIO() {
		contexts.addAll(LogContext.INFO, LogContext.DEBUG, LogContext.WARNING, LogContext.EXCEPTION);
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
		(context == LogContext.EXCEPTION ? System.err : System.out).println(message);
	}

}
