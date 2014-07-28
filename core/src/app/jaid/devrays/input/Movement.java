package app.jaid.devrays.input;

import app.jaid.devrays.entity.Mob;
import app.jaid.devrays.geo.Angle;
import app.jaid.devrays.mobs.Player;

/**
 * Collection of static methods that help to translate keypresses into entity (mainly {@link Player}) movements.
 *
 * @author jaid
 */
public class Movement {

	public static boolean up, down, left, right;

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
		return right ? 1 : left ? -1 : 0;
	}

	public static int getMoveY()
	{
		return up ? 1 : down ? -1 : 0;
	}

	public static void push(Mob mob, float speed)
	{
		int moveX = getMoveX();
		int moveY = getMoveY();

		if (moveX == 0 && moveY == 0)
			return;

		mob.push(Angle.fromDegrees(directionsToAngle(moveX, moveY) * 45), speed);
	}

	public static void reset()
	{
		up = false;
		down = false;
		left = false;
		right = false;
	}

}
