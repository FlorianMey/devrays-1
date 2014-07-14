package app.jaid.devrays.debug;

import app.jaid.jtil.JTil;

public class Command {

	private String[]	args, flags;
	private String		command;

	public Command(String command, String[] args, String[] flags) {
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
