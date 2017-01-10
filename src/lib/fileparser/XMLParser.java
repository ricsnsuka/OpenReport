package lib.fileparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	private final String filepath;
	private Document document;


	public XMLParser(String filepath) {
		this.filepath = filepath;
		loadDocument();
	}


	public ArrayList<String> getAttributeValue(String nodeName, String attr) {
		ArrayList<String> list = new ArrayList<>();
		NodeList nList = document.getElementsByTagName(nodeName);
		Node node, nodeAttr;
		NamedNodeMap attribute;
		for(int i = 0; i < nList.getLength(); i++) {
			node = nList.item(i);
			NodeList nList2;
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				nList2 = ((Element) node).getChildNodes();
				for(int j = 0; j < nList2.getLength(); j++) {
					if(nList2.item(j).getNodeType() == Node.ELEMENT_NODE) {
						attribute = nList2.item(j).getAttributes();
						nodeAttr = attribute.getNamedItem(attr);
						list.add(nodeAttr.getTextContent());
					}
				}
			}
		}
		return list;

	}

	public boolean exists(String nodeName, String content) {
		NodeList nList = document.getElementsByTagName(nodeName);
		//This can and should be easily replaced
		int j;
		String textContent;

		for(int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nList2 = ((Element) node).getChildNodes();
				j = 0;
				while(j < nList2.getLength()) {
					if(nList2.item(j).getNodeType() == Node.ELEMENT_NODE) {
						textContent = nList2.item(j).getTextContent();
						if(content.contains(textContent)) {
							return true;
						}
					}
					j++;
				}
			}
		}
		return false;
	}

	public void addInfoToXML(String node, String content) {
		Element root = document.getDocumentElement();
		NodeList nList = root.getChildNodes();
		int index = getIndex(nList, node);
		if(index == -1)
			return;
		Element element = document.createElement(node);

		element.setTextContent(content);
		nList.item(index).appendChild(element);

		rewriteDocument();
	}
	
	private void loadDocument() {
		try{ 
			File file = new File(filepath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			document = db.parse(file);


		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}catch( ParserConfigurationException ex) {
			//LOG
		}catch(SAXException ex) {		
			//LOG
		}
	}

	protected Document getDocument() {
		return this.document;
	}

	protected void rewriteDocument() {
		try{
			document.normalize();

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			StreamResult result = new StreamResult(filepath);
			DOMSource source = new DOMSource(document);

			transformer.transform(source, result);
		}catch(TransformerException ex) {

		}
	}

	protected int getIndex(NodeList nList, String nodeTagName) {
		int i = 0;
		while(i < nList.getLength()) {
			if(nList.item(i).getNodeName().equals(nodeTagName))
				return i;
			i++;
		}
		return -1;
	}


}
