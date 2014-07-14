package app.jaid.devrays.debug;

public class CommandDescriptor {

	private String[]				aliases;
	private ArgumentDescriptor[]	arguments;
	private FlagDescriptor[]		flags;
	private String					name, description;

	public String[] getAliases()
	{
		return aliases;
	}

	public ArgumentDescriptor[] getArguments()
	{
		return arguments;
	}

	public String getDescription()
	{
		return description;
	}

	public FlagDescriptor[] getFlags()
	{
		return flags;
	}

	public String getName()
	{
		return name;
	}

}
