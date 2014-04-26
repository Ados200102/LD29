package com.pixeltoad.ld29;

import java.util.Random;

import com.pixeltoad.ld29.entity.Player;
import com.pixeltoad.ld29.gfx.Art;
import com.pixeltoad.ld29.gfx.Bitmap;

public class Game
{
	private int width, height;
	
	public InputHandler input;
	public Bitmap bitmap = new Bitmap("/background.png");
	public Bitmap tileSheet = new Bitmap("/sprites.png");
	public Art art;
	
	public Player player;
	
	public Game(GameComponent gameComponent, int width, int height)
	{
		this.width = width;
		this.height = height;

		input = new InputHandler(gameComponent);
		art = new Art(width, height);
		
		player = new Player(0,0);
	}
	
	int scroll;
	
	int rx;
	
	Random r = new Random();
	
	public void tick()
	{
		player.tick(input);
		scroll += 2;
		if(scroll > height){
			scroll = 0;
			rx = r.nextInt(width - 16); 
		}
	}

	public void render()
	{
		art.fill(width, height, 0, 0, 0xFF000000);
		player.render(art);
	}
}
