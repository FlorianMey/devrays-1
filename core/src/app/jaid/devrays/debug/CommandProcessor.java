package app.jaid.devrays.debug;

import com.badlogic.gdx.utils.Array;

public class CommandProcessor {

	public static Command process(String line)
	{
		line = line.trim();

		if (line.startsWith("/"))
			line = line.substring(1);

		if (!line.contains(" "))
			return new Command(line, new String[0], new String[0]);

		String command = null;
		Array<String> args = new Array<String>(8);
		Array<String> flags = new Array<String>(8);
		int segmentStart = 0;

		for (int i = 0; i != line.length(); i++)
			if (line.charAt(i) == ' ')
			{
				String segment = line.substring(segmentStart, i);

				if (command == null)
					command = segment;
				else if (segment.startsWith("-"))
					flags.add(segment.substring(1));
				else
					args.add(segment);

				segmentStart = i + 1;
			}

		String endSegment = line.substring(segmentStart, line.length());
		if (endSegment.startsWith("-"))
			flags.add(endSegment.substring(1));
		else
			args.add(endSegment);

		return new Command(command, (String[]) args.toArray(String.class), (String[]) flags.toArray(String.class));
	}
}
