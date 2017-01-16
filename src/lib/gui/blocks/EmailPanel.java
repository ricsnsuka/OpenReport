package lib.gui.blocks;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lib.adapters.EmailAdapter;
import lib.email.EmailManager;
import lib.gui.ErrorDialog;

public class EmailPanel {
	private EmailManager emailManager;
	private EmailAdapter emailAdapter;
	
	private JCheckBox sendToSupport;
	private JButton editEmails;
	private String[] otherEmails;
	
	
	public EmailPanel(JFrame frame) {
		emailAdapter = new EmailAdapter();
		buildFrame(frame);
	}
	
	private void buildFrame(JFrame frame) {
		Panel emailSetup = new Panel();
		GridBagConstraints gbc_emailSetup = new GridBagConstraints();
		gbc_emailSetup.insets = new Insets(0, 0, 5, 0);
		gbc_emailSetup.gridx = 0;
		gbc_emailSetup.gridy = 0;
		frame.getContentPane().add(emailSetup, gbc_emailSetup);
		GridBagLayout gbl_emailSetup = new GridBagLayout();
		gbl_emailSetup.columnWidths = new int[]{0, 51, 87, 75, 15, 275, 0, 0};
		gbl_emailSetup.rowHeights = new int[] {22, 0};
		gbl_emailSetup.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_emailSetup.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		emailSetup.setLayout(gbl_emailSetup);

		Label sendTo = new Label("Send to");
		GridBagConstraints gbc_sendTo = new GridBagConstraints();
		gbc_sendTo.anchor = GridBagConstraints.NORTHWEST;
		gbc_sendTo.insets = new Insets(0, 0, 0, 5);
		gbc_sendTo.gridx = 1;
		gbc_sendTo.gridy = 0;
		emailSetup.add(sendTo, gbc_sendTo);

		sendToSupport = new JCheckBox("Support DEVs");
		sendToSupport.setSelected(true);
		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.anchor = GridBagConstraints.NORTHWEST;
		gbc_checkbox.insets = new Insets(0, 0, 0, 5);
		gbc_checkbox.gridx = 2;
		gbc_checkbox.gridy = 0;
		emailSetup.add(sendToSupport, gbc_checkbox);

		editEmails = new JButton("Edit...");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 3;
		gbc_btnEdit.gridy = 0;
		emailSetup.add(editEmails, gbc_btnEdit);

		JPanel emailOthersText = new JPanel();
		GridBagConstraints gbc_emailOthersText = new GridBagConstraints();
		gbc_emailOthersText.insets = new Insets(0, 0, 0, 5);
		gbc_emailOthersText.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailOthersText.gridx = 5;
		gbc_emailOthersText.gridy = 0;
		emailSetup.add(emailOthersText, gbc_emailOthersText);
		GridBagLayout gbl_emailOthersText = new GridBagLayout();
		gbl_emailOthersText.columnWidths = new int[]{35, 5, 150, 0};
		gbl_emailOthersText.rowHeights = new int[]{22, 0};
		gbl_emailOthersText.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_emailOthersText.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		emailOthersText.setLayout(gbl_emailOthersText);

		JLabel Others = new JLabel("Others");
		GridBagConstraints gbc_Others = new GridBagConstraints();
		gbc_Others.gridwidth = 2;
		gbc_Others.fill = GridBagConstraints.VERTICAL;
		gbc_Others.insets = new Insets(0, 0, 0, 5);
		gbc_Others.gridx = 0;
		gbc_Others.gridy = 0;
		emailOthersText.add(Others, gbc_Others);

		TextField emailOthers = new TextField();
		GridBagConstraints gbc_emailOthers = new GridBagConstraints();
		gbc_emailOthers.gridx = 2;
		gbc_emailOthers.gridy = 0;
		emailOthersText.add(emailOthers, gbc_emailOthers);
		emailOthers.setColumns(35);
		Others.setLabelFor(emailOthers);
		
		addListeners(frame);
	}
	
	private void addListeners(JFrame frame) {
		editEmails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErrorDialog.showErrorDialog(frame, "This will show a dialog to edit the emails.");
			}
		});
		
		sendToSupport.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				emailAdapter.setToSupport(sendToSupport.isSelected());
				System.out.println("Send to support updated :)");

			}
		});
		
		
	}
	
	public boolean validatePanel() {
		return sendToSupport.isSelected() || (otherEmails != null && !otherEmails.equals(""));
	}
	
	
	
}
