package com.pixeltoad.ld29.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.AudioStream;

public class Sound
{
	private AudioStream as;

	public static Sound music = loadSound("/loop.wav");
	public static Sound coin = loadSound("/coin.wav");

	public static Sound loadSound(String fileName)
	{
		Sound sound = new Sound();
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			sound.clip = clip;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return sound;
	}

	private Clip clip;

	public void play()
	{
		try
		{
			if (clip != null)
			{
				new Thread()
				{
					public void run()
					{
						synchronized (clip)
						{
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void loop()
	{
		clip.setLoopPoints(5, clip.getFrameLength() - 5);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopLoop()
	{
		clip.loop(0);
	}

	public void stop()
	{
		clip.stop();
	}
}
