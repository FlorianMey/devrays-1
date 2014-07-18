package app.jaid.devrays.debug;

import app.jaid.jtil.JTil;

/**
 * Runnable command instance that has been converted from String (see {@link CommandProcessor#process}) into a main
 * command, a String[] arguments and a String[] flags. Can be passed to {@link CommandExecutor#run} to be executed.
 * 
 * @author jaid
 */
public class Command {

	private String[] args, flags;
	private String command;

	public Command(String command, String[] args, String[] flags)
	{
		this.command = command;
		this.args = args;
		this.flags = flags;
	}

	public String[] getArguments()
	{
		return args;
	}

	public String getCommand()
	{
		return command;
	}

	public String[] getFlags()
	{
		return flags;
	}

	@Override
	public String toString()
	{
		return "<" + command + (args.length > 0 ? ": " + JTil.explode(args, ", ") : "") + (flags.length > 0 ? " (" + JTil.explode(flags, " ") + ")" : "") + ">";
	}

}
