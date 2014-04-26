package com.pixeltoad.ld29.entity;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.geom.Rectangle;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public abstract class Entity
{
	private int x, y, width, height;
	public int id;
	private Rectangle hitBox;
	
	public Entity(int x, int y, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public abstract void render(Art art, Level level);
	public abstract void tick(InputHandler input, Level level);
	
	public void move(int x, int y)
	{
		this.x += x;
		this.y += y;
		hitBox.setX(getX());
		hitBox.setY(getY());
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
		hitBox.setX(getX());
		hitBox.setY(getY());
	}
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Rectangle getHitBox()
	{
		return hitBox;
	}

	public int getId()
	{
		return id;
	}
}
