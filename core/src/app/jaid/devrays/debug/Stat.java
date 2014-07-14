package app.jaid.devrays.debug;

import app.jaid.devrays.graphics.Drawer;

public class Stat {

	private String name;
	private Object value;

	Stat(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	void render(int x, int y)
	{
		Drawer.drawTextOnScreen("[GRAY]" + name + ": [WHITE]" + String.valueOf(value), x, y);
	}

}
