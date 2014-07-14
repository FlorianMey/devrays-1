package app.jaid.devrays.debug;

import java.io.*;

import app.jaid.devrays.Core;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class Shell {

	private static CommandDescriptor[]	commandDescriptors;
	private static BufferedReader		inputReader	= new BufferedReader(new InputStreamReader(System.in));

	public static CommandDescriptor getCommandDescriptorByAlias(String alias)
	{
		for (CommandDescriptor descriptor : Shell.getCommandDescriptors())
			if (descriptor.getAliases() != null)
				for (String descriptorAlias : descriptor.getAliases())
					if (descriptorAlias.equalsIgnoreCase(alias))
						return descriptor;
		return null;
	}

	public static CommandDescriptor getCommandDescriptorByName(String name)
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

	public static void readInput()
	{
		try
		{
			if (inputReader.ready())
			{
				String line = inputReader.readLine();
				CommandExecutor.run(CommandProcessor.process(line));
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void setCommandLib(FileHandle... files)
	{
		Array<CommandDescriptor> descriptors = new Array<CommandDescriptor>(64);

		for (FileHandle file : files)
			descriptors.addAll(Core.getJson().fromJson(CommandDescriptor[].class, file));

		commandDescriptors = descriptors.toArray(CommandDescriptor.class);
		Log.debug("Created command library with " + commandDescriptors.length + " entries.");
	}
}
