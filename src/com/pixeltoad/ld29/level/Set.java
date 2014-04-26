package com.pixeltoad.ld29.level;

import java.util.ArrayList;

import com.pixeltoad.ld29.entity.Entity;

public class Set
{
	private static ArrayList<Set> sets = new ArrayList<Set>();

	public Entity[] entities;

	public Set(int MAX_ENTITIES)
	{
		entities = new Entity[MAX_ENTITIES];
		sets.add(this);
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
		for(Entity e : entities)
		{
			if(e != null)
				if(!e.isOnScreen(level))
					return false;
		}
		
		return true;
	}
}
