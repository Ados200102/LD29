package com.pixeltoad.ld29;

import javax.swing.JFrame;

public class Main
{	
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Beneath the Surface");
		GameComponent game = new GameComponent();
		
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		game.start();
	}
}
