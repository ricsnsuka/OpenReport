package lib.structs;

import java.util.ArrayList;
import java.util.HashMap;

import lib.email.EmailManager;

public class OpenReportsCache {
	private EmailManager emailList;
	private HashMap<String, ArrayList<String>> storedXMLInformation;
	
	public OpenReportsCache() {
		init();
	}
	
	private void init() {
		emailList = new EmailManager();
	}
	
	public EmailManager getEmailList()  {
		return this.emailList;
	}

	public void setStoredXMLInformation(HashMap<String, ArrayList<String>> xmlInformation) {
		this.storedXMLInformation = xmlInformation;
	}
	
	public ArrayList<String> getXMLNodeInformation(String nodeName) {
		return this.storedXMLInformation.get(nodeName);
	}
	
	
	
	
}
