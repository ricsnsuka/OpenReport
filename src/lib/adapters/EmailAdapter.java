package lib.adapters;

import java.util.ArrayList;

public class EmailAdapter {
	private boolean toSupport;
	private ArrayList<String> receivers;
	
	public EmailAdapter() {
		
	}
	
	public boolean isToSupport() {
		return toSupport;
	}
	public void setToSupport(boolean toSupport) {
		this.toSupport = toSupport;
	}
	public ArrayList<String> getReceivers() {
		return receivers;
	}
	public void setReceivers(ArrayList<String> receivers) {
		this.receivers = receivers;
	}
	
}
