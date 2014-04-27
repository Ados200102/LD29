package com.pixeltoad.ld29;

import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.gfx.Bitmap;
import com.pixeltoad.ld29.level.Level;

public class Game
{
	private GameComponent gc;
	
	private int width, height;

	public Level level;

	public Game(GameComponent gameComponent, int width, int height)
	{
		this.width = width;
		this.height = height;

		level = new Level(width, height);
		
		gc = gameComponent;
	}

	public void tick()
	{
		if (level != null)
			level.tick(gc.input);
	}

	public void render()
	{
		gc.art.fill(width, height, 0, 0, 0xFF000000);
		if (level != null)
			level.render(gc.art);
	}
}
