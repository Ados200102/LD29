package com.pixeltoad.ld29.gfx;

public class Art
{
	public Bitmap spriteSheet = new Bitmap("/sprites.png");
	public Bitmap background = new Bitmap("/background.png");

	private int width, height;
	private int[] pixels;

	public Art(int width, int height)
	{
		pixels = new int[width * height];
		this.width = width;
		this.height = height;
	}

	public void fill(int w, int h, int xOffs, int yOffs, int color)
	{
		fill(w, h, xOffs, yOffs, color, 255);
	}

	public void fill(int w, int h, int xOffs, int yOffs, int color, int alpha)
	{
		for (int y = 0; y < h; y++)
		{
			int yPos = yOffs + y;
			if (yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < w; x++)
			{
				int xPos = xOffs + x;
				if (xPos >= width || xPos < 0)
					continue;
				int bColor = pixels[xPos + yPos * width];
				pixels[xPos + yPos * width] = blendPixels(bColor, color, alpha);
			}
		}
	}

	public void drawBox(int w, int h, int xOffs, int yOffs, int color)
	{
		drawBox(w, h, xOffs, yOffs, color, 255);
	}

	public void drawBox(int w, int h, int xOffs, int yOffs, int color, int alpha)
	{
		fill(1, h, xOffs, yOffs, alpha);
		fill(w, 1, xOffs, yOffs, alpha);
		fill(w, 1, xOffs, yOffs + h - 1, alpha);
		fill(1, h, xOffs + w - 1, yOffs, alpha);
	}

	public void drawBitmap(Bitmap bitmap, int xOffs, int yOffs)
	{
		drawBitmap(bitmap, xOffs, yOffs, 255);
	}

	public void drawBitmap(Bitmap bitmap, int xOffs, int yOffs, int alpha)
	{
		for (int y = 0; y < bitmap.getHeight(); y++)
		{
			int yPos = yOffs + y;
			if (yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < bitmap.getWidth(); x++)
			{
				int xPos = xOffs + x;
				if (xPos >= width || xPos < 0)
					continue;
				int color = bitmap.getPixels()[x + y * bitmap.getWidth()];
				int a = (color >> 24) & 0xFF;
				if (alpha >= 255)
					alpha = a;
				int bColor = pixels[xPos + yPos * width];
				pixels[xPos + yPos * width] = blendPixels(bColor, color, alpha);
			}
		}
	}

	public void drawBitmap(Bitmap bitmap, int xOffs, int yOffs, float scale)
	{
		int xScale = Math.round(bitmap.getWidth() * scale);
		int yScale = Math.round(bitmap.getHeight() * scale);
		drawBitmap(bitmap, xOffs, yOffs, xScale, yScale);
	}

	public void drawBitmap(Bitmap bitmap, int xOffs, int yOffs, float scale, int alpha)
	{
		int xScale = Math.round(bitmap.getWidth() * scale);
		int yScale = Math.round(bitmap.getHeight() * scale);
		drawBitmap(bitmap, xOffs, yOffs, xScale, yScale, alpha);
	}

	public void drawBitmap(Bitmap bitmap, int xOffs, int yOffs, int w, int h)
	{
		drawBitmap(bitmap, xOffs, yOffs, w, h, 255);
	}

	public void drawBitmap(Bitmap bitmap, int xOffs, int yOffs, int w, int h, int alpha)
	{
		for (int y = 0; y < w; y++)
		{
			int yPos = yOffs + y;
			if (yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < h; x++)
			{
				int xPos = xOffs + x;
				if (xPos >= width || xPos < 0)
					continue;
				int color = bitmap.getPixels()[(int) (Math.round(x * (bitmap.getWidth() * 1.0 / w)) + Math.round(y * (bitmap.getHeight() * 1.0 / h)) * bitmap.getWidth())];
				int a = (color >> 24) & 0xFF;
				if (alpha >= 255)
					alpha = a;
				int bColor = pixels[xPos + yPos * width];
				pixels[xPos + yPos * width] = blendPixels(bColor, color, alpha);
			}
		}
	}

	public void drawTile(Bitmap bitmap, int xOffs, int yOffs, int tile, int tileSize)
	{
		drawTile(bitmap, xOffs, yOffs, tile, tileSize, 255);
	}

	public void drawTile(Bitmap bitmap, int xOffs, int yOffs, int tile, int tileSize, int alpha)
	{
		int xTile = tile % (bitmap.getWidth() / tileSize);
		int yTile = tile / (bitmap.getWidth() / tileSize);
		int tOffs = xTile * tileSize + yTile * tileSize * bitmap.getWidth();

		for (int y = 0; y < tileSize; y++)
		{
			int yPos = y + yOffs;
			if (yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < tileSize; x++)
			{
				int xPos = x + xOffs;
				if (xPos >= width || xPos < 0)
					continue;
				int color = bitmap.getPixels()[x + y * bitmap.getWidth() + tOffs];
				int a = (color >> 24) & 0xFF;
				if (alpha >= 255)
					alpha = a;
				int bColor = pixels[xPos + yPos * width];
				pixels[xPos + yPos * width] = blendPixels(bColor, color, a);
			}
		}
	}

	public int blendPixels(int backgroundColor, int pixelToBlendColor, int alpha)
	{

		if (alpha >= 255)
			return pixelToBlendColor;
		if (alpha <= 0)
			return backgroundColor;

		int alpha_blend = alpha;

		int alpha_background = 256 - alpha_blend;

		int rr = backgroundColor & 0xff0000;
		int gg = backgroundColor & 0xff00;
		int bb = backgroundColor & 0xff;

		int r = (pixelToBlendColor & 0xff0000);
		int g = (pixelToBlendColor & 0xff00);
		int b = (pixelToBlendColor & 0xff);

		r = ((r * alpha_blend + rr * alpha_background) >> 8) & 0xff0000;
		g = ((g * alpha_blend + gg * alpha_background) >> 8) & 0xff00;
		b = ((b * alpha_blend + bb * alpha_background) >> 8) & 0xff;

		return 0xff000000 | r | g | b;
	}

	public static String chars = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
			"0123456789.,!?'\"-+=/\\%()<>:;    ";

	public void drawText(String text, int xOffs, int yOffs, int color)
	{
		drawText(text, xOffs, yOffs, color, 255);
	}

	public void drawText(String text, int xOffs, int yOffs, int color, int alpha)
	{
		String[] lines = text.split("\n");

		for (int i = 0; i < lines.length; i++)
		{
			String line = lines[i].trim();
			
			drawString(line, xOffs, yOffs + i * 10, color, alpha);
		}
	}

	private void drawString(String text, int xOffs, int yOffs, int color, int alpha)
	{
		text = text.toUpperCase();
		for (int i = 0; i < text.length(); i++)
		{
			int ix = chars.indexOf(text.charAt(i));
			if (ix >= 0)
			{
				drawTile(spriteSheet, xOffs + i * 8, yOffs, 30 * 32 + ix, 8, color, alpha);
			}
		}
	}

	private void drawTile(Bitmap bitmap, int xOffs, int yOffs, int tile, int tileSize, int color, int alpha)
	{
		int xTile = tile % (bitmap.getWidth() / tileSize);
		int yTile = tile / (bitmap.getWidth() / tileSize);
		int tOffs = xTile * tileSize + yTile * tileSize * bitmap.getWidth();

		for (int y = 0; y < tileSize; y++)
		{
			int yPos = y + yOffs;
			if (yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < tileSize; x++)
			{
				int xPos = x + xOffs;
				if (xPos >= width || xPos < 0)
					continue;
				int mcolor = bitmap.getPixels()[x + y * bitmap.getWidth() + tOffs];
				int a = (mcolor >> 24) & 0xFF;
				if (a == 255)
					pixels[xPos + yPos * width] = color;
			}
		}
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public int[] getPixels()
	{
		return pixels;
	}
}
