package lib.fileparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lib.structs.LogType;

public class XMLParser {
	private static final String filepath = "src\\resources\\test.xml";
	private HashMap<LogType, String> tagNames;
	private Document document;


	public XMLParser() {
		tagNames = new HashMap<>();
		initTagNames();
		loadDocument();
	}

	private void initTagNames() {
		tagNames.put(LogType.INFO, "INFOS");
		tagNames.put(LogType.SEVERE, "SEVERES");
		tagNames.put(LogType.WARNING, "WARNINGS");
	}

	private void loadDocument() {
		try{ 
			File file = new File(filepath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			document = db.parse(file);


		}catch(IOException ex) {
			//LOG
		}catch( ParserConfigurationException ex) {
			//LOG
		}catch(SAXException ex) {		
			//LOG
		}
	}

	public boolean exists(String severityInfo, LogType type) {
		NodeList nList = document.getElementsByTagName(tagNames.get(type));
		for(int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nList2 = ((Element) node).getChildNodes();
				int j = 0;
				while(j < nList2.getLength()) {
					if(severityInfo.contains(nList2.item(j).getTextContent())) {
						return true;
					}
					j++;
				}
			}
		}
		return false;
	}

	public void addInfoToXML(String severityInfo) {
		Element root = document.getDocumentElement();
		NodeList nList = root.getChildNodes();
		int index = getIndex(nList, "INFOS");
		Element element = document.createElement("Info");
		
		
		element.setTextContent(severityInfo);
		nList.item(index).appendChild(element);
		
		rewriteDocument();
	}

	private void rewriteDocument() {
		try{
			document.normalize();
			
			DOMSource source = new DOMSource(document);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult(filepath);
			
			transformer.transform(source, result);
		}catch(TransformerException ex) {

		}
	}
	
	private int getIndex(NodeList nList, String nodeTagName) {
		int i = 0;
		while(i < nList.getLength()) {
			if(nList.item(i).getNodeName().equals(nodeTagName))
				return i;
			i++;
		}
		return -1;
	}


}
