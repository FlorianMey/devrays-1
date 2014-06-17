package app.jaid.devrays.math;

public class Angle {

	public static final Angle	ANGLE_EAST			= fromDegrees(90);
	public static final Angle	ANGLE_NORTH			= fromDegrees(0);
	public static final Angle	ANGLE_SOUTH			= fromDegrees(180);
	public static final Angle	ANGLE_WEST			= fromDegrees(270);
	public static final float	MAX_DEGREES			= 360;
	public static final float	MAX_DEGREES_SIGNED	= 180;
	public static final float	MAX_RADIANS			= (float) (Math.PI * 2);
	public static final float	PI					= (float) Math.PI;

	public static float degreesToRadians(float degrees)
	{
		return (float) Math.toRadians(degrees);
	}

	public static Angle fromDegrees(double degrees)
	{
		return new Angle(degreesToRadians((float) degrees));
	}

	public static Angle fromDegrees(float degrees)
	{
		return new Angle(degreesToRadians(degrees));
	}

	public static Angle fromRadians(double radians)
	{
		return new Angle((float) radians);
	}

	public static Angle fromRadians(float radians)
	{
		return new Angle(radians);
	}

	public static float radiansToDegrees(float radians)
	{
		return (float) Math.toDegrees(radians);
	}

	private float	radians;

	private Angle(float radians) {
		this.radians = radians;
		ensureRadiansInBounds();
	}

	public String debugInfo()
	{
		return this + " (toDirection(8) = " + toDirection(8) + ", toDirectionSigned(8) = " + toDirectionSigned(8) + ", snapToGrid(8) = " + snapToGrid(8) + ")";
	}

	private void ensureRadiansInBounds()
	{
		radians = radians % MAX_RADIANS;
	}

	public float getDegrees()
	{
		return radiansToDegrees(radians);
	}

	public float getDifferenceTo(Angle other)
	{
		if (radians == other.radians)
			return 0;

		float max = Math.max(radians, other.radians);
		float min = Math.min(radians, other.radians);

		float positiveDifference = max - min;
		float negativeDifference = MAX_RADIANS - max + min;

		return Math.min(positiveDifference, negativeDifference);
	}

	public float getRadians()
	{
		return radians;
	}

	public int getShortestRotateDirection(Angle other)
	{
		if (radians == other.radians)
			return 0;

		float max = Math.max(radians, other.radians);
		float min = Math.min(radians, other.radians);

		float positiveDifference = max - min;
		return positiveDifference > PI ? -1 : 1;
	}

	public float getSignedDegrees()
	{
		return getDegrees() - 180;
	}

	public Angle invert()
	{
		return Angle.fromRadians(radians + PI);
	}

	public Angle mixWith(Angle other)
	{
		if (getShortestRotateDirection(other) == 1) // If way between angles doesn't intersect 0, use simple math to calculate mid
			return Angle.fromRadians((radians + other.radians) / 2);
		else
		{
			float radiansIncreased = radians + MAX_RADIANS; // Else temporarily increase before getting mid to get rid of seamlessness between 0 and PI*2
			float otherRadiansIncreased = other.radians + MAX_RADIANS;
			return Angle.fromRadians((radiansIncreased + otherRadiansIncreased) / 2 - PI);
		}
	}

	public Angle rotateByDegrees(Angle target, float degrees)
	{
		return rotateByDegrees(target, degreesToRadians(degrees));
	}

	public Angle rotateByRadians(Angle target, float radians)
	{
		return null;
	}

	public Angle snapToGrid(int steps)
	{
		float gridSpace = MAX_RADIANS / steps;
		return Angle.fromRadians(Math.round(radians / gridSpace) * gridSpace);
	}

	// not in use
	public int toDirection(int steps)
	{
		return Math.round(radians / (MAX_RADIANS / steps));
	}

	// not in use
	public int toDirectionSigned(int steps)
	{
		int direction = toDirection(steps);
		return steps % 2 == 0 && direction == steps / 2 ? -direction : direction;
	}

	@Override
	public String toString()
	{
		return "(" + getDegrees() + "°, " + getSignedDegrees() + "° signed, " + getRadians() + " rad)";
	}

}