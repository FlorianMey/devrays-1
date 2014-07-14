package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.DebugFlags;
import app.jaid.devrays.entity.*;
import app.jaid.devrays.geo.Point;
import app.jaid.devrays.graphics.Drawer;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.mobs.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class Environment {

	private static final int INITIAL_CAPACITY_BULLETS = 255;
	private static final int INITIAL_CAPACITY_MOBS = 255;

	private Array<Bullet> bullets = new Array<Bullet>(false, INITIAL_CAPACITY_BULLETS);
	private Array<Mob> mobs = new Array<Mob>(false, INITIAL_CAPACITY_MOBS);
	Player player;

	public Array<Bullet> getBullets()
	{
		return bullets;
	}

	public Array<Mob> getMobs()
	{
		return mobs;
	}

	@SuppressWarnings("unchecked")
	public void render()
	{
		renderEntities(mobs, bullets);

		if (DebugFlags.drawCoords)
			for (int x = -100; x != 100; x++)
				for (int y = -100; y != 100; y++)
				{
					Color color = new Color(x == 0 || y == 0 ? Color.BLUE : Color.DARK_GRAY);

					if (x % 10 == 0 && y % 10 == 0)
						color.lerp(Color.WHITE, 0.5f);

					color.a = DebugFlags.showCoordsAlpha;
					Core.getBatch().setColor(color);
					Core.getBatch().draw(Media.getSprite("pixel"), x - 0.1f, y - 0.1f, 0.2f, 0.2f);
					Core.getBatch().setColor(Color.WHITE);
				}

	}

	@SuppressWarnings("unchecked")
	private void renderEntities(Array<? extends Entity>... entityGroups)
	{
		for (Array<? extends Entity> entityGroup : entityGroups)
			for (Entity entity : entityGroup)
				entity.render();
	}

	@SuppressWarnings("unchecked")
	private void renderEntityShapes(Array<? extends Entity>... entityGroups)
	{
		for (Array<? extends Entity> entityGroup : entityGroups)
			for (Entity entity : entityGroup)
				entity.renderShapes();
	}

	@SuppressWarnings("unchecked")
	private void renderEntityText(Array<? extends Entity>... entityGroups)
	{
		for (Array<? extends Entity> entityGroup : entityGroups)
			for (Entity entity : entityGroup)
				entity.renderText();
	}

	@SuppressWarnings("unchecked")
	public void renderShapes()
	{
		renderEntityShapes();
	}

	@SuppressWarnings("unchecked")
	public void renderText()
	{
		if (DebugFlags.drawCoords)
		{
			for (int x = -100; x != 100; x++)
			{
				Point screenPoint = Point.worldPointToScreenPoint(x, 0);
				Drawer.drawTextOnWorld(String.valueOf(x), screenPoint.x, Core.screenHeight / 2);
			}

			for (int y = -100; y != 100; y++)
			{
				Point screenPoint = Point.worldPointToScreenPoint(0, y);
				Drawer.drawTextOnWorld(String.valueOf(y), Core.screenWidth / 2, screenPoint.y);
			}
		}

		renderEntityText(mobs, bullets);
	}

	@SuppressWarnings("unchecked")
	public void update()
	{
		updateEntites(mobs, bullets);
	}

	@SuppressWarnings("unchecked")
	private void updateEntites(Array<? extends Entity>... entityGroups)
	{
		for (Array<? extends Entity> entityGroup : entityGroups)
			for (Entity entity : entityGroup)
				if (!entity.update())
					((Array<Entity>) entityGroup).removeValue(entity, true);
	}
}
