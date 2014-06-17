package app.jaid.jtil;

public class JGeo {

	public static float getSignedDegrees(float angle) {
		float newAngle = angle;

		while (newAngle <= -180) {
			newAngle += 360;
		}

		while (newAngle > 180) {
			newAngle -= 360;
		}

		return newAngle;
	}

	public static float angleDifference(float a, float b) {
		return 180 - Math.abs(Math.abs(a + 180 - b - 180) - 180);
	}

	public static float mergeAngles(float baseAngle, float plusAngle, float influence) {
		float difference = ((baseAngle - plusAngle + 180 + 360) % 360) - 180;
		float angle = (360 + plusAngle + (difference * 0.5f)) % 360;
		return getSignedDegrees(angle);
	}

	public static float changeAngle(float baseAngle, float targetAngle, float change) {
		return 0f;
	}

	public static int getClosestAngleDirection(float baseAngle, float targetAngle) {
		return 1;
	}

}
