package lib.gui.blocks.applications;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import lib.fileparser.XMLParser;

public final class ActiveQuoteDialog extends ApplicationDialog {
	
	public ActiveQuoteDialog(JFrame owner) {
		super(owner, "ActiveQuote Servers");
	}
	
	@Override
	protected void addCurrentApplicationData(DefaultListModel<String> listModel) {
		XMLParser parser = new XMLParser("src\\resources\\applications.xml");
		for(String eBrokerSite : parser.getAttributeValue("activequote", "serverNumber")) {
			listModel.addElement(eBrokerSite);
		}
	}

}
