package com.pixeltoad.ld29.entity;

import java.awt.event.KeyEvent;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;

public class Player extends Entity
{
	public float upForce; //Much creative

	public Player(int x, int y)
	{
		super(x, y, 8, 8);
	}

	@Override
	public void render(Art art)
	{
		getHitBox().render(art);
	}

	int xx, yy;

	@Override
	public void tick(InputHandler input)
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
			if(upForce <= 0)
			{
				upForce = 20f;
			}
		if(down)
			yy++;
		
		if(upForce > 0)
		{
			upForce -= (upForce * 0.5);
			if(upForce < 0.1)
				upForce = 0;
		}
		
		move(xx * 2, yy - (int)upForce);
	}
}
