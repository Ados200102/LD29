package com.pixeltoad.ld29.level;

import java.util.Random;

import com.pixeltoad.ld29.Game;
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

	public Game game;
	public Set set;

	public Level(int width, int height, Game game)
	{
		this.width = width;
		this.height = height;
		this.game = game;
		player = new Player(width / 2 - 4, 32);
		spawnEntity(new CoinEntity(8, 16));
		Set.init();
		generate();
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
		art.fill(80, 20, width - 88, 8, 0x606060);
		art.fill((int)((player.getHealth() * 1.0 / player.MAX_HEALTH) * 78), 18, width - 87, 9, 0xFf6060);
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
		}

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
		spawnEntity(entity, false, 0);
	}

	public void spawnEntity(Entity entity, boolean flag, int sid)
	{
		int id = getFreeEntityId();
		if (id >= 0)
		{
			entity.id = id;
			if (flag)
			{
				entity.sid = sid;
			}
			entities[id] = entity;
		}
	}

	public void removeEntity(Entity entity)
	{
		entities[entity.id] = null;
		if (entity.sid >= 0 && entity.sid < set.entities.length)
			set.entities[entity.sid] = null;
	}

	public void generate()
	{
		System.out.println("Generate is called!");
		set = Set.getSet(random.nextInt(Set.maxSets()));
		for (int i = 0; i < set.entities.length; i++)
		{
			Entity e = set.entities[i];

			e.move(0, height + 10);
			spawnEntity(e, true, i);
		}
	}
}
