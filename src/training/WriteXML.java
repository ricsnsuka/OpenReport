package training;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lib.structs.InfoLogData;
import lib.structs.LogData;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

public class WriteXML {

	public WriteXML() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		getInfo();
	}


	public static void getInfo() throws Exception {
		//FICHEIRO XML ONDE VAI BUSCAR OS HITS
		String filepath = "src\\resources\\test.xml";
		File file = new File(filepath);

		JaroWinkler algorithm = new JaroWinkler();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbFactory.newDocumentBuilder();
		Document doc = db.parse(file);

		doc.getDocumentElement().normalize();

		InfoLogData info = new InfoLogData("Oct 01, 2016 3:59:03 PM", "Can't stop the feeling!");

		NodeList nList = doc.getElementsByTagName("INFOS");

		for(int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nList2 = ((Element) node).getChildNodes();
				int j = 0;
				while(j < nList2.getLength() ) {
					if(nList2.item(j).getNodeType() == Node.ELEMENT_NODE) {
						
						double similarity = algorithm.getSimilarity(info.getSeverityInfo(), nList2.item(j).getTextContent());
						System.out.println("Comparing strings:: " + info.getSeverityInfo() + " with " + nList2.item(j).getTextContent() + ":: " + similarity);
//						if(info.getSeverityInfo().contains(nList2.item(j).getTextContent())) {
						
						if(similarity > 0.4) {
							System.out.println("YES YOU CAN!");
							return;
						}
					}
					j++;
				}
				addInfotoXML(doc, info);

			}


		}
		doc.normalize();
		DOMSource source = new DOMSource(doc);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult("src\\resources\\test.xml");
		transformer.transform(source, result);

		//FICHEIRO LOG ONDE VAI ANALISAR A INFORMACAO
	}

	public static void addInfotoXML(Document doc, LogData logData) throws Exception {

		Element root = doc.getDocumentElement();

		NodeList nList = root.getChildNodes();
		int index = getIndex(nList, "INFOS");

		Element element = doc.createElement("Info");
		element.setTextContent(logData.getSeverityInfo());

		nList.item(index).appendChild(element);


	}

	private static int getIndex(NodeList nList, String nodeTagName) {
		int i = 0;
		while(i < nList.getLength()) {
			if(nList.item(i).getNodeName().equals(nodeTagName))
				return i;
			i++;
		}
		return -1;
	}

}
