package com.pixeltoad.ld29.menu;

import java.awt.event.KeyEvent;

import com.pixeltoad.ld29.GameComponent;
import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;

public class MainMenu extends Menu
{
	private String text = "> New Game <";
	private String copy = "©PixelToad Studios 2014";

	@Override
	public void render(Art art, GameComponent gc)
	{
		art.fill(GameComponent.getScreenWidth(), GameComponent.getScreenHeight(), 0, 0, 0x000000);
		art.drawBitmap(art.background, 0, 0);
		art.drawBitmap(art.title, (GameComponent.getScreenWidth() - art.title.getWidth()) / 2, GameComponent.getScreenHeight() / 8);
		art.drawText(text, (GameComponent.getScreenWidth() - text.length() * 8) / 2, 128, 0xFFFFFF);
		art.drawText(copy, (GameComponent.getScreenWidth() - copy.length() * 8) / 2, GameComponent.getScreenHeight() - 8, 0xFFFFFF);
	}

	@Override
	public void tick(InputHandler input, GameComponent gc)
	{
		if (input.keys[KeyEvent.VK_SPACE] || input.keys[KeyEvent.VK_ENTER])
		{
			input.keys[KeyEvent.VK_SPACE] = false;
			input.keys[KeyEvent.VK_ENTER] = false;
			gc.newGame();
		}
	}
}
