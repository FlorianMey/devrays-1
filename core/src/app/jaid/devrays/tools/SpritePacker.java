package app.jaid.devrays.tools;

import java.io.File;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class SpritePacker {

	public static void main(String args[])
	{
		System.out.println(new File("../..").getAbsolutePath());
		TexturePacker.process("../../image-source/world", "textures", "world");
	}
}
