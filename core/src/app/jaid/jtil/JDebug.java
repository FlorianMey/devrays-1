package app.jaid.jtil;

public class JDebug {

	private static final int DEFAULT_STACK_ELEMENTS = 5;
	private static final boolean SHOW_CAUSES = true;
	private static final boolean SHOW_LINE_NUMBER = true;
	private static final boolean SHOW_METHOD_NAME = true;
	private static final String STACK_ELEMENTS_SEPERATOR = " \u2190 ";
	private static final String STACK_PREFIX = " @ ";
	private static final String STACKS_SEPERATOR = " <caused by> ";

	public static String formatException(Throwable exception)
	{
		return formatException(exception, DEFAULT_STACK_ELEMENTS);
	}

	public static String formatException(Throwable exception, int stackElements)
	{
		StringBuilder out = new StringBuilder(512);

		String message = getMessage(exception);

		out.append(exception.getClass().getSimpleName());

		if (message != null && !message.isEmpty() && !message.equals("null"))
			out.append(": " + message);

		out.append(STACK_PREFIX);

		StackTraceElement[] stack = exception.getStackTrace();

		int i = 0;
		int end = stackElements;
		boolean first = true;

		while (i != end && stack.length > i)
		{
			boolean isSourceHidden = stack[i].getFileName() == null;
			out.append(first ? "" : STACK_ELEMENTS_SEPERATOR);

			out.append(isSourceHidden ? stack[i].getClassName() : stack[i].getFileName().replace(".java", ""));

			if (SHOW_METHOD_NAME && !stack[i].getMethodName().equals("<init>"))
			{
				out.append('.');
				out.append(stack[i].getMethodName());
			}

			if (SHOW_LINE_NUMBER && !isSourceHidden && stack[i].getLineNumber() > 0)
				out.append(isSourceHidden ? "" : ":" + stack[i].getLineNumber());

			first = false;
			i++;
		}

		if (stack.length > stackElements)
			out.append(STACK_ELEMENTS_SEPERATOR + (stack.length - stackElements) + " more");

		if (SHOW_CAUSES && exception.getCause() != null)
			out.append(STACKS_SEPERATOR + formatException(exception.getCause(), stackElements));

		return out.toString();
	}

	public static String getMessage(Throwable exception)
	{
		return exception.getMessage();
	}
}
