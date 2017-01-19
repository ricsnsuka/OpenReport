package lib.fileparser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class EmailXMLParser extends XMLParser {
	private static final String filepath = "src\\resources\\SupEmailAddresses.xml";
	private final String parentNodeName = "Developer";
	
	public EmailXMLParser() {
		super(filepath);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addInfoToXML(String node, String content) {
		if(node == null || content == null)
			return;
		Element root = getDocument().getDocumentElement();
		NodeList nList = root.getChildNodes();
		int index = getIndex(nList, parentNodeName);
		if(index == -1)
			return;
		Element element = getDocument().createElement(node);

		element.setTextContent(content);
		nList.item(index).appendChild(element);

		rewriteDocument();
	}

}
