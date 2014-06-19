package app.jaid.devrays.screen.ingame;

import app.jaid.devrays.entity.Bullet;

import com.badlogic.gdx.utils.Array;

public class Environment {

	public static void addBullet(Bullet bullet)
	{
		IngameScreen.getEnvironment().bullets.add(bullet);
	}

	Array<Bullet>	bullets	= new Array<Bullet>();

	public void render()
	{
		for (Bullet bullet : bullets)
			bullet.render();
	}

	public void update()
	{
		for (Bullet bullet : bullets)
			bullet.update();
	}
}
