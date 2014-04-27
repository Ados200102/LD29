package com.pixeltoad.ld29.level;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pixeltoad.ld29.entity.CoinEntity;
import com.pixeltoad.ld29.entity.Entity;
import com.pixeltoad.ld29.entity.SoildEntity;

public class Set
{
	private Entity[] entities;

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
			nodeList = doc.getElementsByTagName("set");
		} else
		{
			System.err.println("Error: Sets file is not a vaild Sets XML file!");
		}
	}

	public static Set getSet(int id)
	{
		Set set = new Set();
		
		Node node = nodeList.item(id);

		NodeList nnm = node.getChildNodes();

		for (int j = 0; j < nnm.getLength(); j++)
		{
			NamedNodeMap n = nnm.item(j).getAttributes();
			
			set.entities = new Entity[n.getLength()];
			
			for (int l = 0; l < n.getLength(); l++)
			{
				int x = new Integer(n.getNamedItem("x").getNodeValue());
				int y = new Integer(n.getNamedItem("y").getNodeValue());
				
				String ent = n.getNamedItem("entity").getNodeValue();
				
				if(ent == "solid")
					set.entities[l] = new SoildEntity(x, y, 18);
				if(ent == "coin")
					set.entities[l] = new CoinEntity(x, y);
			}
		}
		
		return set;
	}
	
	public static int maxSets()
	{
		return doc.getElementsByTagName("set").getLength();
	}
	
	public boolean isDone()
	{
		for(Entity e : entities)
		{
			if(e != null)
				return false;
		}
		
		return true;
	}
}
