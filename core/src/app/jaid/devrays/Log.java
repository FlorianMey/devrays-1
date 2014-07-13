package app.jaid.devrays;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static final SimpleDateFormat	dateFormat	= new SimpleDateFormat("[HH:mm:ss.SSS] ");

	public static void debug(Object message)
	{
		log(String.valueOf(message), LogContext.DEBUG);
	}

	public static void info(String message)
	{
		log(message, LogContext.INFO);
	}

	public static void log(String message, LogContext context)
	{
		String output = dateFormat.format(new Date()) + "<" + context.name().charAt(0) + "> " + message;
		(context == LogContext.ERROR ? System.err : System.out).println(output);
	}

}
