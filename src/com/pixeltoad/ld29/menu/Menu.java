package com.pixeltoad.ld29.menu;

import com.pixeltoad.ld29.GameComponent;
import com.pixeltoad.ld29.InputHandler;
import com.pixeltoad.ld29.gfx.Art;

public abstract class Menu
{
	public abstract void render(Art art, GameComponent gc);
	public abstract void tick(InputHandler input, GameComponent gc);
}
