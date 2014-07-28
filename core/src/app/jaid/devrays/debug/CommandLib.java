package app.jaid.devrays.debug;

import app.jaid.devrays.Core;
import app.jaid.devrays.graphics.DisplayUtils;
import app.jaid.devrays.items.weapons.WeaponDescriptor;
import app.jaid.devrays.ui.Hud;
import app.jaid.jtil.JTil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

/**
 * Provides static methods that get called by {@link CommandExecutor} through Java Reflection. Every command listed in
 * commands.json has a method here, every method has params String[] args and String[] flags and returns an int
 * representing a status code as specified in {@link CommandExecutor}.
 *
 * @author jaid
 */
public class CommandLib {

	private static final String OFF = "off";
	private static final String ON = "on";

	public static int coords(String[] args, String[] flags)
	{
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
		if (flags.length > 0)
		{
			if (JTil.arrayContainsIgnoreCase(flags, "keepres"))
				Gdx.graphics.setDisplayMode(Core.screenWidth, Core.screenHeight, true);
		}
		else if (Gdx.graphics.isFullscreen())
			Gdx.graphics.setDisplayMode(640, 320, false);
		else
			Gdx.graphics.setDisplayMode(DisplayUtils.getBiggestDisplayMode());

		Log.info("Display Mode is now <" + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight() + ", " + (Gdx.graphics.isFullscreen() ? "fullscreen" : "windowed") + ">.");
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int get(String[] args, String flags[])
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

	public static int track(String[] args, String flags[])
	{
		if (args[0].equalsIgnoreCase("*"))
		{
			for (CoreStat stat : CoreStat.values())
				Stats.track(stat.getName(), stat);

			return CommandExecutor.EXEC_RESULT_SUCCESS;
		}

		if (!CoreStat.contains(args[0]))
		{
			Log.error("Stat " + args[0] + " not found.");
			return CommandExecutor.EXEC_RESULT_WRONG_USAGE;
		}

		CoreStat stat = CoreStat.getByName(args[0]);

		Stats.track(stat.getName(), stat);
		return CommandExecutor.EXEC_RESULT_SUCCESS;

	}

	public static int uidebug(String[] args, String flags[])
	{
		Hud.get().setDebug(!Hud.get().getDebug(), true);
		return CommandExecutor.EXEC_RESULT_SUCCESS;
	}

	public static int weapon(String[] args, String[] flags)
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
