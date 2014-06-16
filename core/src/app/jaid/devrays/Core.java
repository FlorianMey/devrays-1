package app.jaid.devrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class Core {
	public static boolean	debug;
	public static float		delta;
	public static float		speed;
	public static long		time;

	public static void tick()
	{
		delta = Gdx.graphics.getDeltaTime() * speed;
		time = TimeUtils.millis();
	}

}
