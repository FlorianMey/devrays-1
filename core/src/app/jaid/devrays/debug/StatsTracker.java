package app.jaid.devrays.debug;

import java.util.HashMap;
import java.util.Map;

import app.jaid.devrays.ui.Hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Set of {@link Stat} instances that get rendered every frame in a screen corner.
 */
public class StatsTracker extends Table {

	private static StatsTracker statsTracker = new StatsTracker();

	public static StatsTracker get()
	{
		return statsTracker;
	}

	private final Map<String, Stat> stats = new HashMap<String, Stat>();

	public StatsTracker()
	{
		super(Hud.oldSkin);
	}

	public void track(String name, Object value)
	{
		if (stats.containsKey(name))
		{
			Log.warn("Can't add stat " + name + " twice.");
			return;
		}

		Label nameLabel = new Label(name, Hud.oldSkin);
		nameLabel.setColor(Color.GRAY);
		Stat stat = new Stat(value);

		stats.put(name, stat);
		add(nameLabel).right().spaceRight(10);
		add(stat).left();
		row();
	}

	public void update()
	{
		for (Cell<?> cell : getCells())
			if (cell.getActor() instanceof Stat)
				((Stat) cell.getActor()).update();
	}
}