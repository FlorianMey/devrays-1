package app.jaid.jtil;

public class JTime {

	public static final String[]	unitTags;
	private static final long[]		unitLengths;
	static
	{
		long LENGTH_SECOND = 1000;
		long LENGTH_MINUTE = LENGTH_SECOND * 60;
		long LENGTH_HOUR = LENGTH_MINUTE * 60;
		long LENGTH_DAY = LENGTH_HOUR * 24;
		long LENGTH_MONTH = (int) (LENGTH_DAY * (365.25f / 12f));
		long LENGTH_YEAR = LENGTH_MONTH * 12;

		unitLengths = new long[] {1, LENGTH_SECOND, LENGTH_MINUTE, LENGTH_HOUR, LENGTH_DAY, LENGTH_MONTH, LENGTH_YEAR};
		unitTags = new String[] {"{ms}", "{s}", "{m}", "{h}", "{d}", "{mo}", "{y}"};
	}

	public static String formatUnits(String formatting, long ms)
	{
		for (int i = unitTags.length - 1; i != 0; i--)
			if (formatting.contains(unitTags[i]))
			{
				long parts = ms / unitLengths[i];
				formatting = formatting.replace(unitTags[i], String.valueOf(parts));
				// System.out.println("Tag: " + unitTags[i] + " | UnitLengths[" + i + "]: " + unitLengths[i] + " | Ms: " + ms + " | Parts: " + parts);
				ms -= parts * unitLengths[i];
			}

		return formatting;
	}

	public static boolean isOlderThan(long timestamp, long ms)
	{
		// System.out.println("isOlder = " + (System.currentTimeMillis() - timestamp) + " (" + (System.currentTimeMillis() - timestamp) / 1000 / 60 + " minutes)  >= " + ms);
		return System.currentTimeMillis() - timestamp >= ms;
	}

	public String	isFutureFormat	= "in *";
	public String	isHistoryFormat	= "* ago";
	public String	nowFormat		= "exactly now";
	public String[]	unitNames, unitNamesPlural;

	private long	timer;

	public JTime()
	{
		unitNames = new String[] {"millisecond", "second", "minute", "hour", "day", "month", "year"};
		unitNamesPlural = new String[] {"milliseconds", "seconds", "minutes", "hours", "days", "months", "years"};
	}

	public String formatDifference(final long timestamp)
	{
		return formatDifference(System.currentTimeMillis(), timestamp);
	}

	public String formatDifference(final long base, final long timestamp)
	{
		long difference = Math.abs(base - timestamp);

		if (difference == 0)
			return nowFormat;

		for (int i = 0; i != unitLengths.length; i++)
			if (i == 6 || difference < unitLengths[i + 1])
			{
				int count = Math.round(difference / unitLengths[i]);
				String unitString = count + (' ' + (count == 1 ? unitNames[i] : unitNamesPlural[i]));
				return (base < timestamp ? isFutureFormat : isHistoryFormat).replace("*", unitString);
			}

		return null;
	}

	public String formatTimerMs()
	{
		return JTil.formatDouble(getTimerMs(), 1);
	}

	public double getTimerMs()
	{
		return (System.nanoTime() - timer) / 1000000.0;
	}

	public void startTimer()
	{
		timer = System.nanoTime();
	}

}
