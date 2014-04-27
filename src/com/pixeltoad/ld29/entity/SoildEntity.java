package com.pixeltoad.ld29.entity;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public class SoildEntity extends Entity
{
	private int frame;
	
	public SoildEntity(int x, int y, int frame)
	{
		super(x, y, 8, 8);
	}
	
	@Override
	public void render(Art art, Level level)
	{
		art.drawTile(art.spriteSheet, getX(), getY(), 18 + (frame / 6), 8);
	}
	
	public boolean isSolid()
	{
		return true;
	}
	
	@Override
	public void tick(InputHandler input, Level level)
	{
		move(0, (int) -Math.abs(level.speed));
	}

}
