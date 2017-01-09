package lib.fileparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lib.others.MutableString;
import lib.structs.LogEntryType;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

public class XMLParser {
	private static final String filepath = "src\\resources\\test.xml";
	private HashMap<LogEntryType, String> tagNames;
	private Document document;


	public XMLParser() {
		tagNames = new HashMap<>();
		initTagNames();
		loadDocument();
	}

	private void initTagNames() {
		tagNames.put(LogEntryType.INFO, "INFOS");
		tagNames.put(LogEntryType.SEVERE, "SEVERES");
		tagNames.put(LogEntryType.WARNING, "WARNINGS");
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

	public boolean exists(LogEntryType type, String severityInfo, MutableString mutable) {
		NodeList nList = document.getElementsByTagName(tagNames.get(type));
		//This can and should be easily replaced
		JaroWinkler algorithm = new JaroWinkler();
		double similarity;
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
						if(severityInfo.contains(textContent)) {
							mutable.setString(textContent);
							return true;
						}
						similarity = algorithm.getSimilarity(severityInfo, textContent);
						if(similarity > 0.93) {
							mutable.setString(textContent);
							return true;
						}
					}
					j++;
				}
			}
		}
		return false;
	}

	public boolean addInfoToXML(LogEntryType type, String severityInfo) {
		Element root = document.getDocumentElement();
		NodeList nList = root.getChildNodes();
		int index = getIndex(nList, tagNames.get(type));
		if(index == -1)
			return false;

		try {
			Element element = document.createElement(type.toString());


			element.setTextContent(severityInfo);
			nList.item(index).appendChild(element);

			rewriteDocument();
		}catch(TransformerException ex)  {
			return false;
		}
		return true;
	}

	private void rewriteDocument() throws TransformerException {
		document.normalize();

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		StreamResult result = new StreamResult(filepath);
		DOMSource source = new DOMSource(document);

		transformer.transform(source, result);
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
