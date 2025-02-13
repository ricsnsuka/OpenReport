package lib.fileparser;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lib.structs.LogEntryType;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

public final class ReportXMLParser extends XMLParser {


	private static final String filepath = "src\\resources\\test.xml";
	private HashMap<LogEntryType, String> tagNames;

	public ReportXMLParser() {
		super(filepath);
		tagNames = new HashMap<>();
		initTagNames();
	}
	
	public ReportXMLParser(String filepath) {
		super(filepath);
		tagNames = new HashMap<>();
		initTagNames();
	}

	@Override
	public void initXmlInformation() {
		xmlInformation.put(LogEntryType.SEVERE.name(), new ArrayList<>());
		xmlInformation.put(LogEntryType.INFO.name(), new ArrayList<>());
		xmlInformation.put(LogEntryType.WARNING.name(), new ArrayList<>());
	}

	public String exists(LogEntryType type, String severityInfo) {
		NodeList nList = getDocument().getElementsByTagName(tagNames.get(type));
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
						if(!"".equals(textContent) && severityInfo.contains(textContent)) {
							return textContent;
						}
						similarity = algorithm.getSimilarity(severityInfo, textContent);
						//TODO make the similarity value configurable
						if(similarity > 0.93) {
							return textContent;
						}
					}
					j++;
				}
			}
		}
		return null;
	}

	private void initTagNames() {
		tagNames.put(LogEntryType.INFO, "INFOS");
		tagNames.put(LogEntryType.SEVERE, "SEVERES");
		tagNames.put(LogEntryType.WARNING, "WARNINGS");
	}

	@Override
	protected synchronized void addInfoToXML(String node, String content) {
		if(node == null || content == null)
			return;
		Element root = getDocument().getDocumentElement();
		NodeList nList = root.getChildNodes();
		int index = getIndex(nList, getNodeName(node));
		if(index == -1)
			return;
		Element element = getDocument().createElement(node);

		element.setTextContent(content);
		nList.item(index).appendChild(element);

		rewriteDocument();
	}

	private String getNodeName(String type) {
		switch(type) {
		case "INFO":
			return tagNames.get(LogEntryType.INFO);
		case "SEVERE":
			return tagNames.get(LogEntryType.SEVERE);
		case "WARNING":
			return tagNames.get(LogEntryType.WARNING);
		default:
			return null;
		}
	}
}
