package com.pixeltoad.ld29.menu;

import java.awt.event.KeyEvent;

import com.pixeltoad.ld29.GameComponent;
import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;

public class GameOverMenu extends Menu
{

	@Override
	public void render(Art art, GameComponent gc)
	{
		art.drawBitmap(art.background, 0, 0);
		String score = "Score: " + gc.score;
		String distance = "Distance: " + gc.distance;
		art.drawText(score, (GameComponent.getScreenWidth() - score.length() * 8) / 2, 108, 0xFFFFFF);
		art.drawText(distance, (GameComponent.getScreenWidth() - distance.length() * 8) / 2, 100, 0xFFFFFF);
		art.drawText("> Main Menu <", (GameComponent.getScreenWidth() - "> Main Menu <".length() * 8) / 2, 180, 0xFFFFFF);
	}

	@Override
	public void tick(InputHandler input, GameComponent gc)
	{
		if (input.keys[KeyEvent.VK_ENTER] || input.keys[KeyEvent.VK_SPACE])
		{
			input.keys[KeyEvent.VK_SPACE] = false;
			input.keys[KeyEvent.VK_ENTER] = false;
			gc.setMenu(new MainMenu());
		}
	}
}
