package com.pixeltoad.ld29;

import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.gfx.Bitmap;
import com.pixeltoad.ld29.level.Level;

public class Game
{
	private int width, height;

	public InputHandler input;
	public Bitmap bitmap = new Bitmap("/background.png");
	public Bitmap tileSheet = new Bitmap("/sprites.png");
	public Art art;

	public Level level;

	public Game(GameComponent gameComponent, int width, int height)
	{
		this.width = width;
		this.height = height;

		input = new InputHandler(gameComponent);
		art = new Art(width, height);

		level = new Level(width, height);
	}

	public void tick()
	{
		if (level != null)
			level.tick(input);
	}

	public void render()
	{
		art.fill(width, height, 0, 0, 0xFF000000);
		if (level != null)
			level.render(art);
		
		art.drawText("Test \n test1", 10, 10, 0xFFFFFF);
	}
}
