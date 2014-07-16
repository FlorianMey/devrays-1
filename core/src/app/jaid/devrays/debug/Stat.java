package app.jaid.devrays.debug;

import app.jaid.devrays.graphics.Drawer;

public class Stat {

	private String name;
	private Object value;

	Stat(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	private String getOutput()
	{
		if (value instanceof CoreStat)
			return ((CoreStat) value).getValue();

		return String.valueOf(value);
	}

	void render(int x, int y)
	{
		Drawer.drawTextOnScreen("[GRAY]" + name + ": [WHITE]" + getOutput(), x, y);
	}

}
