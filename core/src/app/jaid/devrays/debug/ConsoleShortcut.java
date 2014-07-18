package app.jaid.devrays.debug;

import app.jaid.devrays.ui.Console;

/**
 * Information about a shortcut for the user input in {@link Console}. May replace the "from" value of an
 * ConsoleShortcut instance with its "to" value as soon as the user presses Space while a Console has input focus. Gets
 * fetched at start from shortcuts.json in {@link Shell#setShortcuts}.
 *
 * @author jaid
 */
public class ConsoleShortcut {

	private String from, to;

	public String getFrom()
	{
		return from;
	}

	public String getTo()
	{
		return to;
	}
}
