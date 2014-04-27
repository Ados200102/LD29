package com.pixeltoad.ld29.menu;

import com.pixeltoad.ld29.GameComponent;
import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;

public class GameOverMenu extends Menu
{

	@Override
	public void render(Art art, GameComponent gc)
	{
		art.fill(GameComponent.getScreenWidth(), GameComponent.getScreenHeight(), 0, 0, 0x000000);
	}

	@Override
	public void tick(InputHandler input, GameComponent gc)
	{
	}
}
