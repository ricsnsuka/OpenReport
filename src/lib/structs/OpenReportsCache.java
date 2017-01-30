package lib.structs;

import lib.email.EmailManager;

public class OpenReportsCache {
	private EmailManager emailList;
	
	public OpenReportsCache() {
		init();
	}
	
	private void init() {
		emailList = new EmailManager();
	}
	
	public EmailManager getEmailList()  {
		return this.emailList;
	}
	
	
	
	
}
