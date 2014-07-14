package app.jaid.devrays.debug;

public class FlagDescriptor {

	private String name, description;

	@Override
	public String toString()
	{
		return "-" + name + (description != null ? ": " + description : "");
	}

}
