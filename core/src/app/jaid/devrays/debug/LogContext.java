package app.jaid.devrays.debug;

import com.badlogic.gdx.graphics.Color;

public enum LogContext {

	BROADCAST, CHAT, DEBUG, ERROR, GUILD, INFO, SUCCESS, TEAM, WARNING;

	public static Color getColor(LogContext context)
	{
		switch (context)
		{
			case BROADCAST:
				return Color.PINK;
			case DEBUG:
				return Color.GRAY;
			case ERROR:
				return Color.RED;
			case GUILD:
				return Color.PURPLE;
			case INFO:
				return Color.YELLOW;
			case SUCCESS:
				return Color.GREEN;
			case TEAM:
				return Color.GREEN;
			case WARNING:
				return Color.ORANGE;
		}

		return Color.WHITE;
	}
}
