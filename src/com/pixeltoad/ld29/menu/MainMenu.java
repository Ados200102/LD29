package com.pixeltoad.ld29.menu;

import com.pixeltoad.ld29.GameComponent;
import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;

public class MainMenu extends Menu
{

	@Override
	public void render(Art art, GameComponent gc)
	{
		art.fill(GameComponent.getScreenWidth(), GameComponent.getScreenHeight(), 0, 0, 0x000000);
		art.drawBitmap(art.background, 0, 0);
		art.fill(art.title.getWidth() * 2, art.title.getHeight() * 2, 0, 0, 0xFFFFFF);
		art.drawBitmap(art.title, 0, 0);
	}

	@Override
	public void tick(InputHandler input, GameComponent gc)
	{
	}
}
