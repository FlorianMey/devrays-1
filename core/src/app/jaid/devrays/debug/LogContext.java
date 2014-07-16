package app.jaid.devrays.debug;

import com.badlogic.gdx.graphics.Color;

public enum LogContext {

	BROADCAST, CHAT, DEBUG, EXCEPTION, GUILD, HUMAN_ERROR, INFO, SUCCESS, TEAM, WARNING;

	public Color getColor()
	{
		switch (this)
		{
			case BROADCAST:
				return Color.PINK;
			case DEBUG:
				return Color.GRAY;
			case EXCEPTION:
				return Color.RED;
			case HUMAN_ERROR:
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
