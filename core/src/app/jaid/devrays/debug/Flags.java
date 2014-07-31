package app.jaid.devrays.debug;

import app.jaid.jtil.JTil;

public class Flags {

	private String[] flags;

	public Flags(String[] flags)
	{
		this.flags = flags;
	}

	public boolean has(String flag)
	{
		if (flags == null)
			return false;

		return JTil.arrayContainsIgnoreCase(flags, flag);
	}

	public int size()
	{
		return flags.length;
	}

}
