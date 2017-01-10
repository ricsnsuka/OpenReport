package lib.gui.blocks.applications;

import java.awt.Frame;

import javax.swing.DefaultListModel;

import lib.fileparser.XMLParser;

public final class eBrokerDialog extends ApplicationDialog{
	
	public eBrokerDialog(Frame owner) {
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
