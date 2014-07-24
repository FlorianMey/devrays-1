package app.jaid.devrays.debug;

import app.jaid.devrays.io.Media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

/**
 * Core/utils class for command system. Fetches meta information about the command library from JSON files and stores
 * that as static fields here.
 *
 * @author jaid
 */
public class Shell {

	private static Array<CommandDescriptor> commandDescriptors = Media.loadJsonArray(CommandDescriptor.class, Gdx.files.internal("meta/commands.json"));
	private static Array<ConsoleShortcut> consoleShortcuts = Media.loadJsonArray(ConsoleShortcut.class, Gdx.files.internal("meta/shortcuts.json"));

	public static CommandDescriptor getCommandDescriptor(String name)
	{
		for (CommandDescriptor descriptor : Shell.getCommandDescriptors())
			if (descriptor.getName().equalsIgnoreCase(name))
				return descriptor;

		return null;
	}

	public static Array<CommandDescriptor> getCommandDescriptors()
	{
		return commandDescriptors;
	}

	public static ConsoleShortcut getShortcut(String from)
	{
		for (ConsoleShortcut shortcut : consoleShortcuts)
			if (shortcut.getFrom().equalsIgnoreCase(from))
				return shortcut;

		return null;
	}
}
