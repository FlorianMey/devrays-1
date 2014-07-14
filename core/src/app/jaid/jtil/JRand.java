package app.jaid.jtil;

import java.io.File;
import java.util.*;

public class JRand {

	public static Random random = new Random();

	public static double get()
	{
		return random.nextDouble();
	}

	public static File getFile(File folder)
	{
		if (folder == null || !folder.exists() || !folder.isDirectory())
			return null;

		return getRandomEntry(folder.listFiles());
	}

	public static <T> T[] getRandomEntries(T[] array, int max)
	{
		List<T> list = new ArrayList<T>(Arrays.asList(array));
		int actualMax = Math.min(array.length, max);
		T[] result = Arrays.copyOf(array, actualMax);

		for (int i = 0; i != actualMax; i++)
		{
			int pick = random(list.size() - 1);
			result[i] = list.get(pick);
			list.remove(pick);
		}

		return result;
	}

	public static <T> T getRandomEntry(List<T> list)
	{
		return list.get(random.nextInt(list.size()));
	}

	public static <T> T getRandomEntry(T[] array)
	{
		return array[random.nextInt(array.length)];
	}

	public static double random(double max)
	{
		return random.nextDouble() * max;
	}

	public static double random(double a, double b)
	{
		double min = Math.min(a, b);
		double max = Math.max(a, b);

		return min + random.nextDouble() * (max - min);
	}

	public static int random(int max)
	{
		return random.nextInt(max + 1);
	}

	public static int random(int a, int b)
	{
		int min = Math.min(a, b);
		int max = Math.max(a, b);

		return min + random.nextInt(max - min + 1);
	}

	public static double randomRadius(double radius)
	{
		return random(-radius, radius);
	}

	public static String randomString(int length)
	{
		return randomString(length, "0123456789ABCDEF");
	}

	public static String randomString(int length, String chars)
	{
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i != length; i++)
			sb.append(chars.charAt(random.nextInt(chars.length())));

		return sb.toString();
	}

	public static <T> void shuffle(List<T> list)
	{
		Collections.shuffle(list, random);
	}

	public static <T> T[] shuffle(T[] array)
	{
		List<T> list = Arrays.asList(array);
		Collections.shuffle(list, random);
		return list.toArray(array);
	}
}
