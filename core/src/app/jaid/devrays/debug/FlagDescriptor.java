package app.jaid.devrays.debug;

public class FlagDescriptor {

	private String name, description;

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "-" + name + (description != null ? ": " + description : "");
	}

}
