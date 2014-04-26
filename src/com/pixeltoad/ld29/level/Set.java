package com.pixeltoad.ld29.level;

import java.util.ArrayList;

import com.pixeltoad.ld29.entity.Entity;
import com.pixeltoad.ld29.entity.RenderEntity;

public class Set
{
	private static ArrayList<Set> sets = new ArrayList<Set>();

	private static Set testSet = new Set(2).addEntityToSet(new RenderEntity(10, 1, 7)).addEntityToSet(new RenderEntity(10, 10, 8));

	private Entity[] entities;
	private Entity[] clone;
	
	public Set(int MAX_ENTITIES)
	{
		entities = new Entity[MAX_ENTITIES];
		sets.add(this);
	}
	
	public Entity[] getEntitiez()
	{
		return entities;
	}

	
	public Entity[] getEntities()
	{
		clone = entities.clone();
		return clone;
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

	public Set addEntityToSet(Entity entity)
	{
		int id = getFreeEntityId();
		if (id >= 0)
		{
			entity.id = id;
			entities[id] = entity;
		}

		return this;
	}

	public boolean isDone(Level level)
	{
		for (Entity e : clone)
		{
			if (e != null)
				if (!e.isOnScreen(level))
					return false;
		}

		return true;
	}

	public static ArrayList<Set> getSets()
	{
		return sets;
	}
	
	public static Set getSet(int id)
	{
		return sets.get(id);
	}
}
