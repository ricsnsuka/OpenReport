package lib.gui.blocks.applications;

import java.awt.Frame;

import javax.swing.DefaultListModel;

import lib.fileparser.XMLParser;

public final class ActiveQuoteDialog extends ApplicationDialog {
	
	public ActiveQuoteDialog(Frame owner) {
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
