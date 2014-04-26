package com.pixeltoad.ld29.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bitmap
{
	private BufferedImage image;
	private int width, height;
	private int[] pixels;
	
	public Bitmap(String file)
	{
		try
		{
			image = ImageIO.read(Bitmap.class.getResourceAsStream(file));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int[] getPixels()
	{
		return pixels;
	}
}
