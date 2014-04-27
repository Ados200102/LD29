package com.pixeltoad.ld29.entity;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public class CoinEntity extends Entity
{
	public CoinEntity(int x, int y)
	{
		super(x, y, 8, 8);
	}
	
	int frame;

	@Override
	public void render(Art art, Level level)
	{
		frame++;
		frame %= (4 * 6);
		
		art.drawTile(art.spriteSheet, getX(), getY(), 13 + frame / 6, 8);
	}
	
	@Override
	public void tick(InputHandler input, Level level)
	{
		move(0, (int) -Math.abs(level.speed));
	}

}
