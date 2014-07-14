package app.jaid.devrays.debug;

import app.jaid.jtil.JTil;

import com.badlogic.gdx.utils.Array;

public class CommandLib {

	private static final String OFF = "off";
	private static final String ON = "on";

	public static int coords(String[] args, String[] flags)
	{
		Log.debug("akk");

		if (args.length == 0)
			return CommandExecutor.EXEC_RESULT_TOO_FEW_ARGUMENTS;

		if (args[0].equalsIgnoreCase(ON))
			DebugFlags.drawCoords = true;
		if (args[0].equalsIgnoreCase(OFF))
			DebugFlags.drawCoords = false;
		if (args[0].equalsIgnoreCase("alpha") && args.length > 1)
			DebugFlags.showCoordsAlpha = Float.valueOf(args[1]);

		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int help(String[] args, String flags[])
	{
		if (args.length == 0)
		{
			Array<String> availableCommands = new Array<String>(64);

			for (CommandDescriptor descriptor : Shell.getCommandDescriptors())
				availableCommands.add(descriptor.getName());

			Log.info("Available commands: " + availableCommands.toString(", "));
			return CommandExecutor.EXEC_RESULT_SUCCESS;
		}

		CommandDescriptor descriptor = Shell.getCommandDescriptorByName(args[0]);

		if (descriptor == null)
		{
			Log.error("Cannot display documentation for unknown command " + args[0] + ".");
			return CommandExecutor.EXEC_RESULT_EXCEPTION;
		}

		Log.info("--- " + descriptor.getName() + (descriptor.getAliases() != null && descriptor.getAliases().length > 0 ? " (or " + JTil.explode(descriptor.getAliases(), " / ") + ")" : "") + " ---");

		if (descriptor.getDescription() != null)
			Log.info(descriptor.getDescription());

		if (descriptor.getArguments() != null && descriptor.getArguments().length > 0)
		{
			Log.info("--- Arguments (" + descriptor.getArguments().length + ") ---");
			for (ArgumentDescriptor argument : descriptor.getArguments())
				Log.info(argument.toString());
		}

		if (descriptor.getFlags() != null && descriptor.getFlags().length > 0)
		{
			Log.info("--- Flags (" + descriptor.getFlags().length + ") ---");
			for (FlagDescriptor flag : descriptor.getFlags())
				Log.info(flag.toString());
		}

		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}
}
