package com.pixeltoad.ld29.entity;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public class EndEntity extends Entity
{
	public EndEntity(int x, int y)
	{
		super(x, y, 8, 8);
	}

	@Override
	public void render(Art art, Level level)
	{
	}

	boolean gen = false;
	
	@Override
	public void tick(InputHandler input, Level level)
	{
		move(0, (int) -Math.abs(level.speed));
		if(getY() <= level.height && !gen){
			level.generate();
			gen = true;
		}
	}
}
