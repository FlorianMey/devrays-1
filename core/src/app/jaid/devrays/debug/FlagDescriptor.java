package app.jaid.devrays.debug;

/**
 * Information about a flag that is member of {@link CommandDescriptor}. Gets fetched from commands.json in
 * {@link Shell#setCommandLib}.
 *
 * @author jaid
 */
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
