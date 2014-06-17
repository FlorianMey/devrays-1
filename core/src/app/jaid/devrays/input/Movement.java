package app.jaid.devrays.input;

import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.math.Angle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Movement {

	private static int directionsToAngle(int moveX, int moveY)
	{
		if (moveY == 1 && moveX == 1)
			return -3;
		if (moveX == 1 && moveY == -1)
			return -1;
		if (moveY == -1 && moveX == -1)
			return 1;
		if (moveX == -1 && moveY == 1)
			return 3;

		if (moveY == 1)
			return -4;
		if (moveX == 1)
			return -2;
		if (moveY == -1)
			return 0;
		if (moveX == -1)
			return 2;

		return Integer.MIN_VALUE;
	}

	public static int getMoveX()
	{
		return Gdx.input.isKeyPressed(Keys.D) ? 1 : Gdx.input.isKeyPressed(Keys.A) ? -1 : 0;
	}

	public static int getMoveY()
	{
		return Gdx.input.isKeyPressed(Keys.W) ? 1 : Gdx.input.isKeyPressed(Keys.S) ? -1 : 0;
	}

	public static void push(Mob mob, float speed)
	{
		int moveX = getMoveX();
		int moveY = getMoveY();

		if (moveX == 0 && moveY == 0)
			return;

		mob.push(Angle.fromDegrees(directionsToAngle(moveX, moveY) * 45), speed);
	}

}
