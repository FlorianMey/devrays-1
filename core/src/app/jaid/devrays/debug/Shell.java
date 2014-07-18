package app.jaid.devrays.debug;

import app.jaid.devrays.Core;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

/**
 * Core/utils class for command system. Fetches meta information about the command library from JSON files and stores
 * that as static fields here.
 * 
 * @author jaid
 */
public class Shell {

	private static CommandDescriptor[] commandDescriptors;
	private static ConsoleShortcut[] consoleShortcuts;

	public static CommandDescriptor getCommandDescriptor(String name)
	{
		for (CommandDescriptor descriptor : Shell.getCommandDescriptors())
			if (descriptor.getName().equalsIgnoreCase(name))
				return descriptor;

		return null;
	}

	public static CommandDescriptor[] getCommandDescriptors()
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

	public static void setCommandLib(FileHandle... files)
	{
		Array<CommandDescriptor> descriptors = new Array<CommandDescriptor>(64);

		for (FileHandle file : files)
			descriptors.addAll(Core.getJson().fromJson(CommandDescriptor[].class, file));

		commandDescriptors = descriptors.toArray(CommandDescriptor.class);
		Log.debug("Created command library with " + commandDescriptors.length + " entries.");
	}

	public static void setShortcuts(FileHandle... files)
	{
		Array<ConsoleShortcut> shortcuts = new Array<ConsoleShortcut>(64);

		for (FileHandle file : files)
			shortcuts.addAll(Core.getJson().fromJson(ConsoleShortcut[].class, file));

		consoleShortcuts = shortcuts.toArray(ConsoleShortcut.class);
		Log.debug("Registered " + consoleShortcuts.length + " console shortcuts.");
	}
}
