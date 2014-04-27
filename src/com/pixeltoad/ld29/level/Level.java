package com.pixeltoad.ld29.level;

import java.util.Random;

import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.entity.CoinEntity;
import com.pixeltoad.ld29.entity.Entity;
import com.pixeltoad.ld29.entity.Player;
import com.pixeltoad.ld29.entity.RenderEntity;
import com.pixeltoad.ld29.gfx.Art;

public class Level
{
	private Random random = new Random();
	public int width, height;
	public Entity[] entities = new Entity[1024];
	public Player player;

	public final int MAX_SPEED = 12;

	public float speed = 1;
	public int distance;

	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		player = new Player(width / 2 - 4, 32);
		spawnEntity(new CoinEntity(8, 16));
	}

	public void render(Art art)
	{
		art.drawBitmap(art.background, 0, -scroll);
		art.drawBitmap(art.background, 0, -scroll + height);

		for (int y = 0; y < (height / 8) + 4; y++)
		{
			art.drawTile(art.spriteSheet, 0, y * 8 + -scroll2, 32 + (y % 2 == 0 ? 2 : 0), 8);
			art.drawTile(art.spriteSheet, width - 8, y * 8 + -scroll2, 33 + (y % 2 == 0 ? 2 : 0), 8);
		}

		for (Entity e : entities)
		{
			if (e instanceof RenderEntity)
			{
				e.render(art, this);
			}
		}
		
		for (Entity e : entities)
		{
			if (e != null && !(e instanceof RenderEntity))
			{
				e.render(art, this);
			}
		}

		player.render(art, this);

		art.drawText("Distance: " + distance / 8 + "M\n" + "Speed: " + (int) speed + "\n" + "Score: " + player.getScore(), 2, 2, 0xFFFFFF);
	}

	private int scroll, scroll2;

	public void tick(InputHandler input)
	{
		scroll += speed;
		scroll2 += speed;

		scroll %= height;
		scroll2 %= 16;

		distance += speed;

		if (speed < MAX_SPEED)
		{
			speed = (distance / 8 / 1000.0f) + 1;
			speed = Math.min(speed, MAX_SPEED);
		}

		for (int i = 0; i < entities.length; i++)
		{
			Entity e = entities[i];

			if (e != null)
			{
				if (e.getY() + e.getHeight() < 0)
				{
					removeEntity(e);
				}

				e.tick(input, this);
			}
		}

		if (random.nextInt(64) < 2)
		{
			spawnEntity(new RenderEntity(random.nextInt(width - 8), height + 1, random.nextInt(6)));
			spawnEntity(new CoinEntity(random.nextInt(width - 8), height + 1));
		}

		generate();

		player.tick(input, this);
	}

	public int getFreeEntityId()
	{
		for (int i = 0; i < entities.length; i++)
		{
			if (entities[i] == null)
				return i;
		}

		return -1;
	}

	public void spawnEntity(Entity entity)
	{
		int id = getFreeEntityId();
		if (id >= 0)
		{
			entity.id = id;
			entities[id] = entity;
		}
	}

	public void removeEntity(Entity entity)
	{
		entities[entity.id] = null;
	}

	public void generate()
	{
	}
}
