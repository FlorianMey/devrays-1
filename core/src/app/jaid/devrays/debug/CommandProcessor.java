package app.jaid.devrays.debug;

import app.jaid.jtil.JTil;

import com.badlogic.gdx.utils.Array;

public class CommandProcessor {

	public static String checkCommand(Command command, CommandDescriptor descriptor)
	{
		if (command.getArguments().length < descriptor.getMinimumArguments())
			return "Too few arguments.";

		if (command.getArguments().length > (descriptor.getArguments() == null ? 0 : descriptor.getArguments().length))
			return "Too many arguments.";

		for (int i = 0; i != command.getArguments().length; i++)
		{
			ArgumentDescriptor argDescription = descriptor.getArguments()[i];

			String arg = command.getArguments()[i];
			String type = argDescription.getType();

			if (!validateArgument(arg, type))
				return "Argument #" + (i + 1) + " \"" + arg + "\" is no valid " + type + ".";

			if (argDescription.getAllowedValues() != null && !JTil.arrayContainsIgnoreCase(argDescription.getAllowedValues(), arg))
				return "\"" + arg + "\" is not listed in possible values of argument #" + (i + 1);
		}

		if (command.getFlags().length != 0 && descriptor.getFlags() == null)
			return "That command does not accept any flags.";

		for (String flag : command.getFlags())
			if (!descriptor.hasFlag(flag))
				return "Flag \"" + flag + "\" does not exist for that command.";

		return null;
	}

	public static String checkCommand(String line)
	{
		Command command = process(line);
		CommandDescriptor descriptor = Shell.getCommandDescriptor(command.getCommand());

		if (descriptor == null)
			return null;

		return checkCommand(command, descriptor);
	}

	public static PreprocessedCommand preprocess(String line)
	{
		Command command = process(line);
		CommandDescriptor descriptor = Shell.getCommandDescriptor(command.getCommand());

		String error = checkCommand(command, descriptor);

		return new PreprocessedCommand(descriptor, new String[0], null);
	}

	public static Command process(String line)
	{
		line = line.trim();

		if (line.startsWith("/"))
			line = line.substring(1);

		if (!line.contains(" "))
			return new Command(line, new String[0], new String[0]);

		String command = null;
		Array<String> args = new Array<String>(8);
		Array<String> flags = new Array<String>(8);
		int segmentStart = 0;

		for (int i = 0; i != line.length(); i++)
			if (line.charAt(i) == ' ')
			{
				String segment = line.substring(segmentStart, i);

				if (command == null)
					command = segment;
				else if (segment.startsWith("-"))
					flags.add(segment.substring(1));
				else
					args.add(segment);

				segmentStart = i + 1;
			}

		String endSegment = line.substring(segmentStart, line.length());
		if (endSegment.startsWith("-"))
			flags.add(endSegment.substring(1));
		else
			args.add(endSegment);

		return new Command(command, (String[]) args.toArray(String.class), (String[]) flags.toArray(String.class));
	}

	private static boolean validateArgument(String arg, String type)
	{
		switch (type)
		{
			case "String":
				return true;
			case "Float":
				return validateFloatArgument(arg);
		}

		return false;
	}

	private static boolean validateFloatArgument(String arg)
	{
		try
		{
			Float.parseFloat(arg);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
}
