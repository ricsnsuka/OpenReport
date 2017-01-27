package lib.gui.blocks.applications;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.exceptions.OpenReportException;
import lib.gui.blocks.applications.specific.ActiveQuote4PowerplaceApplicationPanel;
import lib.gui.blocks.applications.specific.ActiveQuoteApplicationPanel;
import lib.gui.blocks.applications.specific.EBrokerApplicationPanel;
import lib.gui.blocks.applications.specific.EnrinchmentHUBApplicationPanel;
import lib.gui.blocks.applications.specific.HostedListServiceApplicationPanel;
import lib.gui.blocks.applications.specific.OpenClientCheckApplicationPanel;
import lib.gui.blocks.applications.specific.OpenCostumerPortalApplicationPanel;
import lib.gui.blocks.applications.specific.OpenDataWarehouseApplicationPanel;
import lib.gui.blocks.applications.specific.OpenQuoteApplicationPanel;
import lib.gui.blocks.applications.specific.OpenUnitMeterApplicationPanel;
import lib.gui.blocks.applications.specific.QuoteGenerationServiceApplicationPanel;
import lib.gui.blocks.applications.specific.RTEDeployerApplicationPanel;

public class ApplicationPanelFactory {
	
	public static ApplicationPanel generateApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, String label, int gridy) throws OpenReportException {
		ApplicationPanel applicationPanel;
		switch(label) {
		case ApplicationsPanel.eBrokerAppliaction:
			applicationPanel = new EBrokerApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.activeQuoteApplication:
			applicationPanel = new ActiveQuoteApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.openQuoteApplication:
			applicationPanel = new OpenQuoteApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.openCostumerPortalApplication:
			applicationPanel = new OpenCostumerPortalApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.enrichmentHUBApplication:
			applicationPanel = new EnrinchmentHUBApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.hostedListServiceApplication:
			applicationPanel = new HostedListServiceApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.openClientCheckApplication:
			applicationPanel = new OpenClientCheckApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.openDataWarehouseApplication:
			applicationPanel = new OpenDataWarehouseApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.openUnitMeterApplication:
			applicationPanel = new OpenUnitMeterApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.rteDeployerApplication:
			applicationPanel = new RTEDeployerApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.quoteGenerationServiceApplication:
			applicationPanel = new QuoteGenerationServiceApplicationPanel(controller, frame, panel, gridy);
			break;
		case ApplicationsPanel.activeQuote4PowerplaceApplication:
			applicationPanel = new ActiveQuote4PowerplaceApplicationPanel(controller, frame, panel, gridy);
			break;
		default:
			throw new OpenReportException("Application doesn't exist");
		}
		return applicationPanel;
	}
	
}
