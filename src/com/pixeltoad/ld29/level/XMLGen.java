package com.pixeltoad.ld29.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLGen
{
	public static void main(String[] args)
	{
		
	}
	
	public static void saveXML(String name, String image) throws ParserConfigurationException, IOException
	{
		BufferedImage img = ImageIO.read(XMLGen.class.getResourceAsStream(image));
		int width = img.getWidth();
		int height = img.getHeight();
		int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("sets");
		doc.appendChild(rootElement);
		
		Element set = doc.createElement("set");
		rootElement.appendChild(set);
		
		for(int x =0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				int color = pixels[x + y * width];
				if(color != 0xFF00FF)
				{
					if(color == 0xFFD800)
					{
						//Coin
					}
					
					if(color == 0x404040)
					{
						//Solid
					}
				}
			}
		}
	}
}
