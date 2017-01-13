package lib.gui.blocks.applications;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import lib.fileparser.XMLParser;

public final class eBrokerDialog extends ApplicationDialog{
	
	public eBrokerDialog(JFrame owner) {
		super(owner, "eBroker Sites");
	}

	@Override
	protected void addCurrentApplicationData(DefaultListModel<String> listModel) {
		XMLParser parser = new XMLParser("src\\resources\\applications.xml");
		for(String eBrokerSite : parser.getAttributeValue("eBroker", "name")) {
			listModel.addElement(eBrokerSite);
		}
	}
	
}
