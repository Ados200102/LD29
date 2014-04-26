package com.pixeltoad.ld29;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.gfx.Bitmap;

public class GameComponent extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 128;
	private static final int HEIGHT = 174;
	private static final int SCALE = 4;

	private boolean running;
	private Thread thread;

	private Bitmap bitmap;
	private Art art;

	private BufferedImage img;
	private int[] pixels;

	public GameComponent()
	{
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		art = new Art(WIDTH, HEIGHT);
		
		try
		{
			bitmap = new Bitmap("/sprites.png");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}

	public synchronized void start()
	{
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop()
	{
		if (!running)
			return;
		running = false;
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		int frames = 0;

		double unprocessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;

		requestFocus();

		while (running)
		{
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0)
				passedTime = 0;
			if (passedTime > 100000000)
				passedTime = 100000000;

			unprocessedSeconds += passedTime / 1000000000.0;

			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick)
			{
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % 60 == 0)
				{
					System.out.println(frames + " fps");
					lastTime += 1000;
					frames = 0;
				}
			}

			if (ticked)
			{
				render();
				frames++;
			} else
			{
				try
				{
					Thread.sleep(1);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public void tick()
	{
	}

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		art.fill(WIDTH, HEIGHT, 0, 0, 0x000000);
		art.drawBitmap(bitmap, 0, 0, 0.25f);
		art.fill(WIDTH, HEIGHT, 0, 0, 0xFFFFFF, 126);
		
		for(int y = 0; y < art.getHeight(); y++)
		{
			for(int x = 0; x < art.getWidth(); x++)
			{
				pixels[x + y * WIDTH] = art.getPixels()[x + y * art.getWidth()]; 
			}
		}
		
		Graphics g = bs.getDrawGraphics();

		int ww = WIDTH * SCALE;
		int hh = HEIGHT * SCALE;
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(img, (getWidth() - ww) / 2, (getHeight() - hh) / 2, ww, hh, null);

		g.dispose();
		bs.show();
	}

	public int[] getPixels()
	{
		return pixels;
	}
}
