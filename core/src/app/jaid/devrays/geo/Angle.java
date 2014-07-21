package app.jaid.devrays.geo;

import app.jaid.jtil.JTil;

/**
 * Wrapper for a float angle value that contains sweet util functions and unit system conversions.
 *
 * @author jaid
 */
public class Angle {

	public static final Angle ANGLE_EAST = fromDegrees(90);
	public static final Angle ANGLE_NORTH = fromDegrees(0);
	public static final Angle ANGLE_SOUTH = fromDegrees(180);
	public static final Angle ANGLE_WEST = fromDegrees(270);
	public static final float MAX_DEGREES = 360;
	public static final float MAX_DEGREES_SIGNED = 180;
	public static final float MAX_RADIANS = (float) (Math.PI * 2);
	public static final float PI = (float) Math.PI;

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

	private float radians;

	public Angle()
	{

	}

	private Angle(float radians)
	{
		setRadians(radians);
	}

	public String debugInfo()
	{
		return this + " (toDirection(8) = " + toDirection(8) + ", toDirectionSigned(8) = " + toDirectionSigned(8) + ", snapToGrid(8) = " + snapToGrid(8) + ")";
	}

	/**
	 * Forces radians to become a float between 0 and MAX_RADIANS. The direction itself is not affected, just what
	 * number it is represented as.
	 */
	private void ensureRadiansInBounds()
	{
		radians = radians % MAX_RADIANS;

		if (radians < 0)
			radians += MAX_RADIANS;
	}

	public float getDegrees()
	{
		return radiansToDegrees(radians);
	}

	public float getDegreesDifferenceTo(Angle other)
	{
		return radiansToDegrees(getRadiansDifferenceTo(other));
	}

	public float getRadians()
	{
		return radians;
	}

	public float getRadiansDifferenceTo(Angle other)
	{
		if (radians == other.radians)
			return 0;

		float max = Math.max(radians, other.radians);
		float min = Math.min(radians, other.radians);

		float positiveDifference = max - min;
		float negativeDifference = MAX_RADIANS - max + min;

		return Math.min(positiveDifference, negativeDifference);
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
		if (getShortestRotateDirection(other) == 1) // If the way between angles does not intersect 0, use simple math
			// to calculate mid
			return Angle.fromRadians((radians + other.radians) / 2);
		else
		{
			float radiansIncreased = radians + MAX_RADIANS; // Else temporarily increase before getting mid to get rid
			// of seamlessness between 0 and PI*2
			float otherRadiansIncreased = other.radians + MAX_RADIANS;
			return Angle.fromRadians((radiansIncreased + otherRadiansIncreased) / 2 - PI);
		}
	}

	public Angle moveTo(Angle target, float change)
	{
		float radius = JTil.moveTo(radians + MAX_RADIANS, change, target.radians + MAX_RADIANS) - MAX_RADIANS;
		return Angle.fromRadians(radius);
	}

	public Angle rotateByDegrees(float degreesRotate)
	{
		return rotateByDegrees(degreesToRadians(degreesRotate));
	}

	public Angle rotateByRadians(float radiansRotate)
	{
		return Angle.fromRadians(radians + radiansRotate);
	}

	public void setDegrees(float degrees)
	{
		setRadians(degreesToRadians(degrees));
	}

	public void setRadians(float radians)
	{
		this.radians = radians;
		ensureRadiansInBounds();
	}

	public void setTo(Angle other)
	{
		radians = other.radians;
	}

	public Angle snapToGrid(int steps)
	{
		float gridSpace = MAX_RADIANS / steps;
		return Angle.fromRadians(Math.round(radians / gridSpace) * gridSpace);
	}

	public String toAdvancedString()
	{
		return "(" + getDegrees() + "°, " + getSignedDegrees() + "° signed, " + getRadians() + " rad)";
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
		return JTil.formatDouble(getDegrees(), 2) + "°";
	}

}