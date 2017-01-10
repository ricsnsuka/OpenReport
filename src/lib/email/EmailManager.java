package lib.email;

import java.util.ArrayList;

public class EmailManager {
	private ArrayList<String> supportDevEmails;
	
	public EmailManager() {
		load();
	}
	
	private void load() {
		
	}
	
	public void addEmail(String name, String emailAddress) {
		supportDevEmails.add(emailAddress);
		update();
	}
	
	public void removeEmail(int index) {
		supportDevEmails.remove(index);
		update();
	}
	
	public void send() {
		//TODO: to be implemented
	}
	
	private void update() {
		
	}
}
