package app.jaid.devrays.debug;

import java.lang.reflect.Method;

public class CommandExecutor {

	public static final int EXEC_RESULT_BROKEN_LIB = 5;
	public static final int EXEC_RESULT_COMMAND_NOT_FOUND = 4;
	public static final int EXEC_RESULT_EXCEPTION = 6;
	public static final int EXEC_RESULT_NO_PERMISSION = 3;
	public static final int EXEC_RESULT_NOT_NOW = 2;
	public static final int EXEC_RESULT_SUCCESS = 0;
	public static final int EXEC_RESULT_TOO_FEW_ARGUMENTS = 1;

	public static int run(Command command)
	{
		long start = System.currentTimeMillis();
		String cmd = command.getCommand();
		String[] args = command.getArguments();
		String[] flags = command.getFlags();
		int status;

		CommandDescriptor descriptor = Shell.getCommandDescriptor(cmd);

		if (descriptor == null)
		{
			Log.error("Command " + cmd + " not found.");
			return EXEC_RESULT_COMMAND_NOT_FOUND;
		}

		try
		{
			Method method = CommandLib.class.getMethod(cmd, new Class[] { String[].class, String[].class });

			if (!method.getReturnType().equals(Integer.TYPE))
			{
				Log.error("The method does not return a primitive integer and cannot be executed.");
				return EXEC_RESULT_BROKEN_LIB;
			}

			status = (int) method.invoke(null, new Object[] { args, flags });
		} catch (NoSuchMethodException e)
		{
			Log.error("The command library is broken and cannot run " + cmd + ".");
			return EXEC_RESULT_BROKEN_LIB;
		} catch (Exception e)
		{
			Log.error("Exception occured", e);
			return EXEC_RESULT_EXCEPTION;
		}

		Log.debug("Ran command " + command + " in " + (System.currentTimeMillis() - start) + " ms.");

		return status;
	}
}
