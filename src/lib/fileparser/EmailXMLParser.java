package lib.fileparser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class EmailXMLParser extends XMLParser {
	private static final String filepath = "src\\resources\\SupEmailAddresses.xml";
	private final String parentNodeName = "Developers";
	
	public EmailXMLParser() {
		super(filepath);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addInfoToXML(String node, String content) {
		Node developers = getDocument().getElementsByTagName(parentNodeName).item(0);
		
		Element newDeveloper = getDocument().createElement(node);
		newDeveloper.setAttribute("name", getFirstName(content) + " " + getLastName(content));
		
		Element emailTo = getDocument().createElement("emailTo");
		emailTo.appendChild(getDocument().createTextNode(content));
		newDeveloper.appendChild(emailTo);
		
		developers.appendChild(newDeveloper);
		
		rewriteDocument();
	}
	
	public void removeInfoFromXML(String node, String content) {
		NodeList nList = getDocument().getElementsByTagName(node);
		int j;
		for(int i = 0; i < nList.getLength(); i++) {
			Node currentNode = nList.item(i);
			if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nList2 = ((Element) currentNode).getChildNodes();
				j = 0;
				while(j < nList2.getLength()) {
					Node sibling = nList2.item(j);
					if(sibling.getNodeType() == Node.ELEMENT_NODE) {
						if(content.contains(sibling.getTextContent())) {
							currentNode.removeChild(sibling);
							currentNode.getParentNode().removeChild(currentNode);
						}
					}
					j++;
				}
			}
		}
		rewriteDocument();
	}
	
	private String getFirstName(String emailAddress) {
		return emailAddress.split("\\.")[0];
	}
	
	private String getLastName(String emailAddress) {
		return emailAddress.split("\\.")[1].split("@")[0];
	}
}
