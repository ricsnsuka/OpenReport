package lib.email;

import java.util.ArrayList;
import java.util.regex.Pattern;

import lib.fileparser.EmailXMLParser;

public class EmailManager extends ArrayList<String> {
	
	private static final long serialVersionUID = 1L;
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@opengi.co.uk", Pattern.CASE_INSENSITIVE);
	
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
		parser.addInfoToXML("Developer", emailAddress);
//		update();
	}
	
	public void removeEmail(String emailAddress) {
		remove(emailAddress);
		parser.removeInfoFromXML("Developer", emailAddress);
//		update();
	}
	
	public void send() {
		//TODO: to be implemented
	}
	
	public static boolean validateEmailAddress(String address) {
		return VALID_EMAIL_ADDRESS_REGEX.matcher(address).matches();
		
	}
	
}
