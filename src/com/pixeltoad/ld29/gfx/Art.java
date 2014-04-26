package com.pixeltoad.ld29.gfx;

public class Art
{
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
			if(yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < w; x++)
			{
				int xPos = xOffs + x;
				if(xPos >= width || xPos < 0)
					continue;
				int bColor = pixels[xPos + yPos * width];
				pixels[xPos + yPos * width] = blendPixels(bColor, color, alpha);
			}
		}
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
			if(yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < bitmap.getWidth(); x++)
			{
				int xPos = xOffs + x;
				if(xPos >= width || xPos < 0)
					continue;
				int color = bitmap.getPixels()[x + y * bitmap.getWidth()];
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
			if(yPos >= height || yPos < 0)
				continue;
			for (int x = 0; x < h; x++)
			{
				int xPos = xOffs + x;
				if(xPos >= width || xPos < 0)
					continue;
				int color = bitmap.getPixels()[(int) (Math.round(x * (bitmap.getWidth() * 1.0 / w)) + Math.round(y * (bitmap.getHeight() * 1.0 / h)) * bitmap.getWidth())];
				int bColor = pixels[xPos + yPos * width];
				pixels[xPos + yPos * width] = blendPixels(bColor, color, alpha);
			}
		}
	}
	
	public int blendPixels(int backgroundColor, int pixelToBlendColor, int alpha) {
		
		if(alpha >= 255)
			return pixelToBlendColor;
		if(alpha <= 0)
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
