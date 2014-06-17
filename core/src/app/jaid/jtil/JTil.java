package app.jaid.jtil;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.Map.Entry;

public class JTil {

	private static DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(new Locale("en")));
	private static final String nullFallback = "(null)";

	public static String explode(List<? extends Object> list, String seperator) {
		return explode(list, seperator, seperator);
	}

	public static String explode(List<? extends Object> list, String seperator, String lastSeperator) {
		if (list == null) {
			return nullFallback;
		}

		StringBuilder ret = new StringBuilder();
		int i = 0;
		for (Object object : list) {
			ret.append((i == 0 ? "" : i == list.size() - 1 ? lastSeperator : seperator) + String.valueOf(object));
			i++;
		}

		return ret.toString();
	}

	public static String explode(Object[] array, String seperator) {
		if (array == null) {
			return nullFallback;
		}

		StringBuilder ret = new StringBuilder();
		boolean first = true;
		for (Object object : array) {
			ret.append((first ? "" : seperator) + String.valueOf(object));
			first = false;
		}

		return ret.toString();
	}

	public static String fillString(int length, char content) {
		char[] array = new char[length];
		Arrays.fill(array, content);
		return new String(array);
	}

	public static String formatBytes(long bytes) {
		int thresh = 1000;
		double size = bytes;

		if (bytes < thresh) {
			return bytes + " B";
		}

		int unit = -1;
		do {
			size /= thresh;
			unit++;
		} while (size >= thresh);

		return formatDouble(size, 2) + ' ' + new String[] { "kB", "MB", "GB", "TB" }[unit];
	}

	public static String formatCommaDigits(double value, int commaDigits) {
		decimalFormat.setMaximumFractionDigits(commaDigits);
		return decimalFormat.format(value);
	}

	public static String formatDouble(double value) {
		return value == (int) value ? String.valueOf((int) value) : String.valueOf(value);
	}

	public static String formatDouble(double value, int commaDigits) {
		return formatDouble(trimCommaDigits(value, commaDigits));
	}

	public static int getInt(double value) {
		return (int) Math.round(value);
	}

	public static boolean getIntIndex(int[] ints, int needle) {
		return Arrays.binarySearch(ints, needle) >= 0;
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}

		return null;
	}

	public static <T, E> List<T> getKeysByValue(Map<T, E> map, E value) {
		List<T> keys = new ArrayList<T>();
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				keys.add(entry.getKey());
			}
		}

		return keys;
	}

	public static int[] intListToArray(List<Integer> list) {
		int[] ret = new int[list.size()];
		int i = 0;
		for (Integer e : list) {
			ret[i++] = e.intValue();
		}
		return ret;
	}

	public static boolean isPrintableChar(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return !Character.isISOControl(c) && block != null && block != Character.UnicodeBlock.SPECIALS;
	}

	public static float moveTo(float value, float change, float target) {
		return value > target ? value - change < target ? target : value - change : value + change > target ? target : value + change;
	}

	public static float normalize(float value, float change) {
		return moveTo(value, change, 0);
	}

	public static double trimCommaDigits(double value, int commaDigits) {
		return Double.valueOf(formatCommaDigits(value, commaDigits));
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> flattenMap(Object map) {
		return flattenMap((HashMap<? extends Object, ? extends Object>) map, null);
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> flattenMap(HashMap<? extends Object, ? extends Object> map, String node) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (Object key : map.keySet()) {
			Object value = map.get(key);
			String resultKey = node == null ? String.valueOf(key) : node + '.' + String.valueOf(key);

			if (value instanceof HashMap) {
				result.putAll(flattenMap((HashMap<? extends Object, ? extends Object>) value, resultKey));
			} else {
				result.put(resultKey, value);
			}
		}
		return result;
	}
}
