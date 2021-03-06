package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.Core;
import app.jaid.devrays.debug.DebugFlags;
import app.jaid.devrays.entity.*;
import app.jaid.devrays.io.Media;
import app.jaid.devrays.mobs.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

/**
 * Singleton that contains interactive objects ({@link Bullet}s, {@link Mob}s...) and the map.
 *
 * @author jaid
 */
public class Environment {

	private static final int INITIAL_CAPACITY_BULLETS = 255;
	private static final int INITIAL_CAPACITY_MOBS = 255;

	public static Environment get()
	{
		return IngameScreen.getEnvironment();
	}

	private Array<Bullet> bullets = new Array<Bullet>(false, INITIAL_CAPACITY_BULLETS);
	private Array<Mob> mobs = new Array<Mob>(false, INITIAL_CAPACITY_MOBS);

	private Player player;

	// is add and not set, because player will be an array in the future
	void addPlayer(Player player)
	{
		this.player = player;
	}

	public Array<Bullet> getBullets()
	{
		return bullets;
	}

	public Entity[] getCollisions(Entity entity, @SuppressWarnings("unchecked") Array<? extends Entity>... entityGroups)
	{
		Array<Entity> collisions = new Array<Entity>(4);

		for (Array<? extends Entity> entityGroup : entityGroups)
			for (Entity collidingEntity : entityGroup)
				if (entity != collidingEntity && entity.getHitbox().intersects(collidingEntity.getHitbox()))
					collisions.add(collidingEntity);

		return collisions.toArray(Entity.class);
	}

	public Array<Mob> getMobs()
	{
		return mobs;
	}

	public Player getPlayer()
	{
		return player;
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
					Core.getBatch().draw(Media.getSprite("pixel"), x - 0.05f, y - 0.05f, 0.1f, 0.1f);
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
			{
				entity.renderShapes();

				if (DebugFlags.drawHitboxes && entity.getHitbox() != null)
					entity.getHitbox().renderWorldBounds(Color.RED);
			}
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
		renderEntityShapes(mobs, bullets);
	}

	@SuppressWarnings("unchecked")
	public void renderText()
	{
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
