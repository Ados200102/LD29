package com.pixeltoad.ld29;

import com.pixeltoad.ld29.level.Level;

public class Game
{
	public GameComponent gc;
	
	private int width, height;

	public Level level;

	public Game(GameComponent gameComponent, int width, int height)
	{
		this.width = width;
		this.height = height;

		level = new Level(width, height, this);
		
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
