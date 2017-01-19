package lib.email;

import java.util.ArrayList;

import lib.fileparser.EmailXMLParser;

public class EmailManager extends ArrayList<String> {
	
	private static final long serialVersionUID = 1L;
	
	private EmailXMLParser parser;

	public EmailManager() {
		super();
		load();
	}
	
	private void load() {
		parser = new EmailXMLParser();
		for(String emailAddress : parser.getNodeValue("emailTo")) {
			add(emailAddress);
		}
	}
	
	public void addEmail(String emailAddress) {
		add(emailAddress);
//		update();
	}
	
	public void removeEmail(int index) {
		remove(index);
//		update();
	}
	
	public void send() {
		//TODO: to be implemented
	}
	
}
