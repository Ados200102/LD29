package com.pixeltoad.ld29.entity;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public class RenderEntity extends Entity
{
	private int tile, ydir;

	public RenderEntity(int x, int y, int tile, int ydir)
	{
		super(x, y, 8, 8);
		this.tile = tile;
		this.ydir = ydir;
	}

	@Override
	public void render(Art art, Level level)
	{
		art.drawTile(art.spriteSheet, getX(), getY(), tile, 8);
	}

	@Override
	public void tick(InputHandler input, Level level)
	{
		move(0, ydir);
		if (getY() > level.height)
		{
			level.removeEntity(this);
		}
	}

}
