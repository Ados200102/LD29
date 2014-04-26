package com.pixeltoad.ld29;

import com.pixeltoad.ld29.gfx.Art;

public class Game
{
	private int width, height;
	
	public InputHandler input;
	public Art art;

	public Game(GameComponent gameComponent, int width, int height)
	{
		this.width = width;
		this.height = height;

		input = new InputHandler(gameComponent);
		art = new Art(width, height);
	}

	public void tick()
	{
	}

	public void render()
	{
		art.fill(width, height, 0, 0, 0x000000);
	}
}
