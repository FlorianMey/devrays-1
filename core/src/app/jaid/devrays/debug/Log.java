package app.jaid.devrays.debug;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.jaid.jtil.JDebug;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.utils.Array;

public class Log {

	private static final SimpleDateFormat	dateFormat	= new SimpleDateFormat("[HH:mm:ss.SSS] ");
	private static final Array<Printing>	printers	= new Array<Printing>(8);

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
		log(message, LogContext.ERROR);
	}

	public static void error(String message, Exception exception)
	{
		log(message + ": " + JDebug.formatException(exception), LogContext.ERROR);
	}

	public static void info(String message)
	{
		log(message, LogContext.INFO);
	}

	private static void log(String message, LogContext context)
	{
		for (Printing printer : printers)
			if (printer.getContexts().contains(context, true))
				printer.print(printer.isDisplayingContextInfo() ? dateFormat.format(new Date()) + "<" + context.name().charAt(0) + "> " + message : message, context);
	}

	public static void registerPrinter(Printing printing)
	{
		printers.add(printing);
		log("Registered printer " + printing.getClass().getSimpleName() + " printing " + JTil.explode(printing.getContexts().toArray(LogContext.class), ", ", " and ") + ".", LogContext.DEBUG);
	}

}
