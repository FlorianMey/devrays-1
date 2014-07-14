package app.jaid.devrays.debug;

public class ArgumentDescriptor {

	private boolean essential;
	private String name, type, description;

	public String getDescription()
	{
		return description;
	}

	public String getName()
	{
		return name;
	}

	public String getType()
	{
		return type;
	}

	public boolean isEssential()
	{
		return essential;
	}

	@Override
	public String toString()
	{
		return (!essential ? "*Optional " : "") + type + " " + name + (description != null ? ": " + description : "");
	}
}
