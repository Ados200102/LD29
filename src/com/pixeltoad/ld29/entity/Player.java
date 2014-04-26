package com.pixeltoad.ld29.entity;

import java.awt.event.KeyEvent;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public class Player extends Entity
{
	public Player(int x, int y)
	{
		super(x, y, 8, 16);
	}
	
	int frame;
	
	@Override
	public void render(Art art, Level level)
	{	
		frame++;
		frame %= 14;
		
		art.drawTile(art.spriteSheet, getX(), getY() - 8, 6 + frame / 2, 8);
		art.drawTile(art.spriteSheet, getX(), getY(), 6 + frame / 2 + 32, 8);
	}

	int xx, yy;

	@Override
	public void tick(InputHandler input, Level level)
	{
		boolean left = input.keys[KeyEvent.VK_LEFT];
		boolean right = input.keys[KeyEvent.VK_RIGHT];
		boolean up = input.keys[KeyEvent.VK_UP];
		boolean down = input.keys[KeyEvent.VK_DOWN];
		
		xx = 0;
		yy = 0;
		
		if(left)
			xx--;
		if(right)
			xx++;
		if(up)
			yy--;
		if(down)
			yy++;
		
		move(xx * 2, yy * 2);
	}
}
