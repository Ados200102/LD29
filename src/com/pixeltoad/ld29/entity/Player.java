package com.pixeltoad.ld29.entity;

import java.awt.event.KeyEvent;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.geom.Rectangle;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.level.Level;

public class Player extends Entity
{
	Rectangle left;
	Rectangle right;
	Rectangle up;
	Rectangle down;

	private int score;

	public Player(int x, int y)
	{
		super(x, y, 8, 16);
		left = new Rectangle(x - 1, y, 1, 16);
		right = new Rectangle(x + 8, y, 1, 16);
		down = new Rectangle(x, y + 16, 8, 1);
		up = new Rectangle(x, y - 1, 8, 1);
	}

	int frame;

	@Override
	public void render(Art art, Level level)
	{
		frame++;
		frame %= 14;

		art.drawTile(art.spriteSheet, getX(), getY(), 6 + frame / 2, 8);
		art.drawTile(art.spriteSheet, getX(), getY() + 8, 6 + frame / 2 + 32, 8);

		//		getHitBox().render(art);
		//		left.render(art);
		//		right.render(art);
		//		down.render(art);
		//		up.render(art);
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

		if (left)
			xx--;
		if (right)
			xx++;
		if (up)
			yy--;
		if (down)
			yy++;

		if (move2(xx * 2, yy * 2, level))
			move(xx * 2, yy * 2);;
	}

	public void hit(Entity e, Level level)
	{
		if (e instanceof CoinEntity)
		{
			score++;
			level.removeEntity(e);
		}
	}

	public void touch(Entity e, Level level)
	{
		if (e instanceof CoinEntity)
		{
			score++;
			level.removeEntity(e);
		}
	}

	public boolean move2(int x, int y, Level level)
	{
		int xx = getX() + x;
		int yy = getY() + y;

		boolean left = (x < 0);
		boolean right = (x > 0);
		boolean down = (y > 0);
		boolean up = (y < 0);

		for (Entity e : level.entities)
		{
			if (e != null && !(e instanceof RenderEntity))
			{
				if (left)
				{
					this.left.setX(xx);
					if (this.left.intersects(e.getHitBox()))
					{
						System.out.println("Entity " + e + " touched the left of player");
						touch(e, level);
						if (e.isSolid())
							return false;
					}
				}
				if (right)
				{
					this.right.setX(xx + getWidth());
					if (this.right.intersects(e.getHitBox()))
					{
						System.out.println("Entity " + e + " touched the right of player");
						touch(e, level);
						if (e.isSolid())
							return false;
					}
				}
				if (down)
				{
					this.down.setY(yy + getHeight());
					if (this.down.intersects(e.getHitBox()))
					{
						System.out.println("Entity " + e + " touched the bottom of player");
						hit(e, level);
						if (e.isSolid())
							return false;
					}
				}
				if (up)
				{
					this.up.setY(yy);
					if (this.up.intersects(e.getHitBox()))
					{
						System.out.println("Entity " + e + " touched the top of player");
						touch(e, level);
						if (e.isSolid())
							return false;
					}
				}
				if (this.getHitBox().intersects(e.getHitBox()))
				{
					touch(e, level);
				}
			}
		}

		this.left.setX(getX() - 1);
		this.left.setY(getY());
		this.right.setX(getX() + 8);
		this.right.setY(getY());
		this.down.setX(getX());
		this.down.setY(getY() + 16);
		this.up.setX(getX());
		this.up.setY(getY() - 1);

		return true;
	}

	public int getScore()
	{
		return score;
	}
}
