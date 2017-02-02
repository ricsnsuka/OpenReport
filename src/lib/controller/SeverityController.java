package lib.controller;

import javax.swing.JFrame;

import lib.adapters.SeverityTypeAdapter;
import lib.gui.blocks.severitytype.SeverityTypeFieldName;
import lib.gui.blocks.severitytype.SeverityTypePanel;
import lib.structs.ReportConfig;

public class SeverityController {
	private SeverityTypePanel panel;
	private SeverityTypeAdapter adapter;


	public SeverityController(ReportConfig config, SeverityTypePanel panel, SeverityTypeAdapter adapter) {
		this.panel = panel;
		this.adapter = adapter;
		config.setSeverityTypes(adapter);
	}

	public void buildPanel(JFrame frame) {
		this.panel.build(frame, this);
	}

	public void changeCheckboxValue(String field, boolean value) {
		switch(field) {
		case SeverityTypeFieldName.ALL:
			this.adapter.setAllTypes(value);
			break;
		case SeverityTypeFieldName.SEVERE:
			this.adapter.setSevere(value);
			break;
		case SeverityTypeFieldName.INFO:
			this.adapter.setInfo(value);
			break;
		case SeverityTypeFieldName.WARNING:
			this.adapter.setWarning(value);
			break;
		default:
			break;
		}
	}
	
	public boolean getCurrentValue(String field) {
		switch(field) {
		case SeverityTypeFieldName.ALL:
			return this.adapter.isAllTypes();
		case SeverityTypeFieldName.SEVERE:
			return this.adapter.isSevere();
		case SeverityTypeFieldName.INFO:
			return this.adapter.isInfo();
		case SeverityTypeFieldName.WARNING:
			return this.adapter.isWarning();
		default:
			break;
		}
		return false;
	}
	
	public boolean checkAllChecksSelected() {
		return this.adapter.isSevere() && this.adapter.isInfo() && this.adapter.isWarning();
	}

	public boolean validatePanel() {
		return this.adapter.isSevere() || this.adapter.isInfo() || this.adapter.isWarning();
	}
}
