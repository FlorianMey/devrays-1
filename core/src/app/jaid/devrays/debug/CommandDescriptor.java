package app.jaid.devrays.debug;

public class CommandDescriptor {

	private ArgumentDescriptor[] arguments;
	private FlagDescriptor[] flags;
	private String name, description;

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
