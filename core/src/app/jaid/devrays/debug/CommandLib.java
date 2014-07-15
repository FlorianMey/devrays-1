package app.jaid.devrays.debug;

import app.jaid.devrays.Core;
import app.jaid.devrays.graphics.DisplayUtils;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.Gdx;
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

	public static int exit(String[] args, String[] flags)
	{
		Gdx.app.exit();
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int fullscreen(String[] args, String[] flags)
	{
		if (args.length > 0)
		{
			if (JTil.arrayContainsIgnoreCase(flags, "keepres"))
				Gdx.graphics.setDisplayMode(Core.screenWidth, Core.screenHeight, !Gdx.graphics.isFullscreen());
		}
		else if (Gdx.graphics.isFullscreen())
			Gdx.graphics.setDisplayMode(640, 320, false);
		else
			Gdx.graphics.setDisplayMode(DisplayUtils.getBiggestDisplayMode());

		Log.info("Display Mode is now " + "TODO" + ".");
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

		CommandDescriptor descriptor = Shell.getCommandDescriptor(args[0]);

		if (descriptor == null)
		{
			Log.error("Cannot display documentation for unknown command " + args[0] + ".");
			return CommandExecutor.EXEC_RESULT_EXCEPTION;
		}

		Log.info("--- Command " + descriptor.getName() + " ---");

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
