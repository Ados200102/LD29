package com.pixeltoad.ld29.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLGen
{
	public static void main(String[] args)
	{
		try
		{
			String[] img = listFiles(XMLGen.class.getProtectionDomain().getCodeSource().getLocation().getFile() + "../res/sets/");
			if (img != null)
			{
				saveXML("sets", img);
			}
		} catch (ParserConfigurationException | IOException | TransformerException e)
		{
			e.printStackTrace();
		}
	}

	public static String[] listFiles(String dir)
	{

		File directory = new File(dir);

		if (!directory.isDirectory())
		{
			System.out.println("No directory provided");
			return null;
		}

		//create a FilenameFilter and override its accept-method
		FilenameFilter filefilter = new FilenameFilter()
		{

			public boolean accept(File dir, String name)
			{
				//if the file extension is .txt return true, else false
				return name.endsWith(".png");
			}
		};

		String[] filenames = directory.list(filefilter);

		return filenames;
	}

	public static void saveXML(String name, String[] images) throws ParserConfigurationException, IOException, TransformerException
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("sets");
		doc.appendChild(rootElement);

		for (int i = 0; i < images.length; i++)
		{
			Element set = doc.createElement("set");
			rootElement.appendChild(set);

			String image = images[i];
			BufferedImage img = ImageIO.read(XMLGen.class.getResourceAsStream("/sets/" + image));
			int width = img.getWidth();
			int height = img.getHeight();
			int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

			set.setAttribute("id", "" + i);
			set.setAttribute("height", height + "");

			for (int x = 0; x < width; x++)
			{
				for (int y = 0; y < height; y++)
				{
					int color = pixels[x + y * width];
					if (color != 0xFFFF00FF)
					{
						Element obj = doc.createElement("entity");
						set.appendChild(obj);

						if (color == 0xFFFFD800)
							obj.setAttribute("entity", "coin");
						if (color == 0xFF404040)
							obj.setAttribute("entity", "solid");
						if (color == 0xff000000)
							obj.setAttribute("entity", "end");

						obj.setAttribute("x", "" + x);
						obj.setAttribute("y", "" + y);
					}
				}
			}

		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		String loc = XMLGen.class.getProtectionDomain().getCodeSource().getLocation().getFile() + "../res/" + name + ".xml";
		StreamResult result = new StreamResult(new File(loc));

		transformer.transform(source, result);

		System.out.println("File saved to " + new File(loc).getCanonicalPath());
	}
}
