package app.jaid.jtil;

public class JDebug {

	public static String formatException(Exception exception)
	{
		return formatException(exception, 5);
	}

	public static String formatException(Exception exception, int stackElements)
	{
		String stackTrace = "";
		String message = exception.getMessage();
		StackTraceElement[] stack = exception.getStackTrace();

		int i = 0;
		int end = stackElements;
		boolean first = true;

		while (i != end && stack.length > i)
		{
			boolean isSourceHidden = stack[i].getFileName() == null;
			stackTrace += (first ? "" : " @ ") + (isSourceHidden ? stack[i].getClassName() : stack[i].getFileName().replace(".java", "")) + "." + stack[i].getMethodName() + (isSourceHidden ? "" : ":" + stack[i].getLineNumber());
			first = false;
			i++;
		}

		return exception.getClass().getSimpleName() + (message != null && !message.isEmpty() ? "" : ": " + exception.getMessage()) + " " + stackTrace;
	}
}
