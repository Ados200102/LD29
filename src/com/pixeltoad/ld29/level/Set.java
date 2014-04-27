package com.pixeltoad.ld29.level;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pixeltoad.ld29.entity.CoinEntity;
import com.pixeltoad.ld29.entity.EndEntity;
import com.pixeltoad.ld29.entity.Entity;
import com.pixeltoad.ld29.entity.SoildEntity;

public class Set
{
	public Entity[] entities;
	public int height;

	private static Document doc;
	private static NodeList nodeList;

	public static void init()
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(Set.class.getResourceAsStream("/sets.xml"));
		} catch (SAXException | IOException | ParserConfigurationException e)
		{
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();

		if (doc.getDocumentElement().getNodeName() == "sets")
		{
			nodeList = doc.getDocumentElement().getChildNodes();
		} else
		{
			System.err.println("Error: Sets file is not a vaild Sets XML file!");
		}
	}

	public static Set getSet(int id)
	{
		Set set = new Set();

		Node node = nodeList.item(id);

		set.height = new Integer(node.getAttributes().getNamedItem("height").getNodeValue());

		NodeList nnm = node.getChildNodes();

		set.entities = new Entity[nnm.getLength()];

		for (int j = 0; j < nnm.getLength(); j++)
		{
			NamedNodeMap n = nnm.item(j).getAttributes();

			int x = new Integer(n.getNamedItem("x").getNodeValue());
			int y = new Integer(n.getNamedItem("y").getNodeValue());

			String ent = n.getNamedItem("entity").getNodeValue();

			if (ent.contains("solid"))
				set.entities[j] = new SoildEntity(x * 8, y * 8);
			if (ent.contains("coin"))
				set.entities[j] = new CoinEntity(x * 8, y * 8);
			if (ent.contains("end"))
				set.entities[j] = new EndEntity(x * 8, y * 8);
		}

		return set;
	}

	public static int maxSets()
	{
		return doc.getElementsByTagName("set").getLength();
	}

	public boolean started = false;
}
