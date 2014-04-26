package com.pixeltoad.ld29.geom;

import com.pixeltoad.ld29.gfx.Art;

public class Rectangle
{
	private int x, y, width, height;

	public Rectangle(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public boolean intersects(Rectangle other)
	{
		if ((x > (other.x + other.width)) || ((x + width) < other.x))
		{
			return false;
		}
		if ((y > (other.y + other.height)) || ((y + height) < other.y))
		{
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "[Rectangle "+width+"x"+height+"]";
	}
	
	public void render(Art art)
	{
		art.drawBox(width, height, x, y, 0xFFFFFFF);
	}
}
