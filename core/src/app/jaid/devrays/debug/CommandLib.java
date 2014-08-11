package app.jaid.devrays.debug;

import java.io.BufferedReader;
import java.io.IOException;

import app.jaid.devrays.Core;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.graphics.DisplayUtils;
import app.jaid.devrays.graphics.Gfx;
import app.jaid.devrays.items.WeaponDescriptor;
import app.jaid.devrays.ui.Hud;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

/**
 * Provides static methods that get called by {@link CommandExecutor} through Java Reflection. Every command listed in
 * commands.json has a method here, every method has params String[] args and String[] flags and returns an int
 * representing a status code as specified in {@link CommandExecutor}.
 *
 * @author jaid
 */
public class CommandLib {

	public static int coords(String[] args, Flags flags)
	{
		if (args[0].equalsIgnoreCase(CommandExecutor.BOOLEAN_ON))
			DebugFlags.drawCoords = true;
		if (args[0].equalsIgnoreCase(CommandExecutor.BOOLEAN_OFF))
			DebugFlags.drawCoords = false;
		if (args[0].equalsIgnoreCase("alpha") && args.length > 1)
			DebugFlags.showCoordsAlpha = Float.valueOf(args[1]);

		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int dmgeffect(String[] args, Flags flags)
	{
		Gfx.setHudStrength(Float.valueOf(args[0]));

		if (args.length > 1)
			Gfx.setHudAngle(Angle.fromDegrees(Float.valueOf(args[1])));

		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int echo(String[] args, Flags flags)
	{
		Log.log(args[0], args.length > 1 ? LogContext.valueOf(args[1].toUpperCase()) : LogContext.INFO);
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int exit(String[] args, Flags flags)
	{
		Gdx.app.exit();
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int fullscreen(String[] args, Flags flags)
	{
		if (flags.has("keepres"))
			Gdx.graphics.setDisplayMode(Core.screenWidth, Core.screenHeight, true);
		else if (Gdx.graphics.isFullscreen())
			Gdx.graphics.setDisplayMode(640, 320, false);
		else
			Gdx.graphics.setDisplayMode(DisplayUtils.getBiggestDisplayMode());

		Log.info("Display Mode is now <" + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight() + ", " + (Gdx.graphics.isFullscreen() ? "fullscreen" : "windowed") + ">.");
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int get(String[] args, Flags flags)
	{
		if (args.length == 0)
		{
			Log.info("Available stats: " + JTil.explode(CoreStat.values(), ", "));
			return CommandExecutor.EXEC_RESULT_SUCCESS;
		}

		if (!CoreStat.contains(args[0]))
		{
			Log.error("Stat " + args[0] + " not found.");
			return CommandExecutor.EXEC_RESULT_WRONG_USAGE;
		}

		CoreStat stat = CoreStat.getByName(args[0]);

		Log.info(stat.getName() + ": " + stat.getValue());
		return CommandExecutor.EXEC_RESULT_SUCCESS;

	}

	public static int help(String[] args, Flags flags)
	{
		if (args.length == 0)
		{
			Array<String> availableCommands = new Array<String>(64);

			for (CommandDescriptor descriptor : Shell.getCommandDescriptors())
				availableCommands.add(descriptor.getName());

			availableCommands.sort();
			Log.info("Available commands: " + availableCommands.toString(", "));
			return CommandExecutor.EXEC_RESULT_SUCCESS;
		}

		CommandDescriptor descriptor = Shell.getCommandDescriptor(args[0]);

		if (descriptor == null)
		{
			Log.exception("Cannot display documentation for unknown command " + args[0] + ".");
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

	public static int run(String[] args, Flags flags) throws IOException
	{
		FileHandle scriptFile = flags.has("internal") ? Gdx.files.internal("meta/" + args[0]) : Core.getDataFile("scripts/" + args[0]);

		if (!scriptFile.exists())
		{
			scriptFile = flags.has("internal") ? Gdx.files.internal("meta/" + args[0] + ".devrcmd") : Core.getDataFile("scripts/" + args[0] + ".devrcmd");

			if (!scriptFile.exists())
			{
				Log.error("File " + scriptFile + " does not exist.");
				return CommandExecutor.EXEC_RESULT_WRONG_USAGE;
			}
		}

		BufferedReader reader = new BufferedReader(scriptFile.reader());
		int successfulRuns = 0, failedRuns = 0;

		String line = null;
		while ((line = reader.readLine()) != null)
		{
			if (line.isEmpty())
				continue;

			if (flags.has("log"))
				Log.info(">> " + line);

			if (CommandExecutor.run(line) == CommandExecutor.EXEC_RESULT_SUCCESS)
				successfulRuns++;
			else
				failedRuns++;
		}
		reader.close();

		if (successfulRuns + failedRuns > 0)
			Log.info("Executed script " + scriptFile.name() + " (" + successfulRuns + " successful" + (failedRuns != 0 ? ", " + failedRuns + " failed" : "") + ").");
		else
			Log.info("Script " + scriptFile.name() + " does not contain executable commands.");

		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int say(String[] args, Flags flags)
	{
		Hud.getConsole().say(args[0]);
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int track(String[] args, Flags flags)
	{
		if (args[0].equalsIgnoreCase("*"))
		{
			for (CoreStat stat : CoreStat.values())
				StatsTracker.get().track(stat.getName(), stat);

			return CommandExecutor.EXEC_RESULT_SUCCESS;
		}

		if (!CoreStat.contains(args[0]))
		{
			Log.error("Stat " + args[0] + " not found.");
			return CommandExecutor.EXEC_RESULT_WRONG_USAGE;
		}

		CoreStat stat = CoreStat.getByName(args[0]);

		StatsTracker.get().track(stat.getName(), stat);
		return CommandExecutor.EXEC_RESULT_SUCCESS;

	}

	public static int uidebug(String[] args, Flags flags)
	{
		Hud.get().setDebug(!Hud.get().getDebug(), true);
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int weapon(String[] args, Flags flags)
	{

		WeaponDescriptor weaponDescriptor = WeaponDescriptor.getAll().get(Integer.parseInt(args[0]));

		Log.info("--- Weapon " + weaponDescriptor.getName() + " ---");
		Log.info("Frequency: every " + JTil.formatDouble(weaponDescriptor.getShootFrequency(), 2) + "s (" + JTil.formatDouble(weaponDescriptor.getShootsPerMinute()) + "/min)");
		Log.info("Damage: " + JTil.formatDouble(weaponDescriptor.getDamage()) + (weaponDescriptor.getDamageVariation() != 0 ? " (vary: " + JTil.formatDouble(weaponDescriptor.getDamageVariation(), 2) + ")" : ""));
		Log.info("DPS: " + JTil.formatDouble(1 / weaponDescriptor.getShootFrequency() * weaponDescriptor.getDamage(), 2));
		Log.info("Bullet Speed: " + JTil.formatDouble(weaponDescriptor.getBulletSpeed()) + (weaponDescriptor.getBulletSpeedVariation() != 0 ? " (vary: " + JTil.formatDouble(weaponDescriptor.getBulletSpeedVariation(), 2) + ")" : ""));
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}
}
