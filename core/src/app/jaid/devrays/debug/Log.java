package app.jaid.devrays.debug;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.jaid.devrays.io.SystemIO;
import app.jaid.devrays.ui.Console;
import app.jaid.jtil.JDebug;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.utils.Array;

/**
 * Provides static methods to broadcast log lines, supposed to be used everywhere in the code. Contains a set of
 * printers (see {@link Printing}, by default a {@link Console} printer and a {@link SystemIO} printer) that all get
 * called when a log line gets broadcasted. Every log entry is assigned to a {@link LogContext} and printers tell Log
 * what LogContexts they want to listen to with {@link Printing#getContexts}.
 *
 * @author jaid
 */
public class Log {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("[HH:mm:ss.SSS] ");
	private static final Array<Printing> printers = new Array<Printing>(8);

	public static void chat(String message)
	{
		log(message, LogContext.CHAT);
	}

	public static void debug(Object message)
	{
		log(String.valueOf(message), LogContext.DEBUG);
	}

	public static void error(String message)
	{
		log(message, LogContext.HUMAN_ERROR);
	}

	public static void exception(String message)
	{
		log(message, LogContext.EXCEPTION);
	}

	public static void exception(String message, Exception exception)
	{
		log(message + ": " + JDebug.formatException(exception), LogContext.EXCEPTION);
	}

	public static void info(String message)
	{
		log(message, LogContext.INFO);
	}

	public static void log(String message, LogContext context)
	{
		message = message.replace("[", "[[");

		for (Printing printer : printers)
			if (printer.getContexts().contains(context, true))
				printer.print(printer.isDisplayingContextInfo() ? dateFormat.format(new Date()) + "<" + context.name().charAt(0) + "> " + message : message, context);
	}

	public static void registerPrinter(Printing printing)
	{
		printers.add(printing);
		log("Registered printer " + printing.getClass().getSimpleName() + " printing " + JTil.explode(printing.getContexts().toArray(LogContext.class), ", ", " and ") + ".", LogContext.DEBUG);
	}

	public static void warn(String message)
	{
		log(message, LogContext.WARNING);
	}

}
