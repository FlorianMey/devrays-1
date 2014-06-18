package app.jaid.jtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

public class JFiles {

	public static String getDirectory(File file)
	{
		return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separatorChar));
	}

	public static String getExtension(File file)
	{
		String name = file.getName();
		return name.substring(name.lastIndexOf('.') + 1, name.length());
	}

	public static File getFolder(String... path)
	{
		File folder = new File(JTil.explode(path, "/"));

		if (!folder.exists())
			folder.mkdirs();

		return folder;
	}

	public static File getNextFree(File folder, String basename)
	{
		int i = 0;
		File file;
		while ((file = new File(folder, basename + "_" + String.format("%03d", ++i))).exists())
			;

		return file;
	}

	public static String getTitle(File file)
	{
		String name = file.getName();

		int index = name.lastIndexOf(".");

		if (index == -1)
			return name;

		return name.substring(0, index);
	}

	public static List<File> listFiles(File folder)
	{
		List<File> files = new ArrayList<File>();

		for (File file : folder.listFiles())
			if (file.isFile())
				files.add(file);

		return files;
	}

	public static String read(File file)
	{
		try
		{
			return Charset.defaultCharset().decode(ByteBuffer.wrap(Files.readAllBytes(file.toPath()))).toString();
		} catch (Exception e)
		{
			return "";
		}
	}

	public static String readOrCreate(File file)
	{
		try
		{
			if (!file.exists())
				file.createNewFile();

			return read(file);
		} catch (Exception e)
		{
			return "";
		}
	}

	public static void write(File file, String content)
	{
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(file);
			writer.print(content);
			writer.close();
		} catch (FileNotFoundException e)
		{}
	}

	public static void writeLine(File file, String line)
	{
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true))))
		{
			out.println(new SimpleDateFormat("[dd.MM.yyyy HH:mm:ss] ").format(new Date()) + line);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
