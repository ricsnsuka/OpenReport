package lib.managers;

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
	}

	public void removeEmail(String ... emailAddress) {
		supportDevEmails.remove(emailAddress);
	}

	public void send(String emailContent, boolean sendToSupp, String ... others) {
		//https://www.tutorialspoint.com/java/java_sending_email.htm
		//verificar se queres manter o emailContent como string



	}

}
