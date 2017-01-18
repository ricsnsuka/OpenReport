package lib.gui.blocks;

import java.awt.FlowLayout;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	
	private class EmailDialog  {
		private final String dialogTitle = "Support DEVs Emails";
		
		private final JPanel contentPanel = new JPanel();
		private JDialog emailDialog ;
		
		public EmailDialog(JFrame owner) {
			emailDialog = new JDialog(owner, dialogTitle);
			emailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
		private void buildDialog() {
			emailDialog.setBounds(100, 100, 450, 300);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{10, 240, 140, 10, 0};
			gridBagLayout.rowHeights = new int[]{10, 60, 60, 60, 20, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			emailDialog.getContentPane().setLayout(gridBagLayout);
			{
				JList list = new JList();
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.gridheight = 3;
				gbc_list.insets = new Insets(0, 0, 5, 5);
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.gridx = 1;
				gbc_list.gridy = 1;
				emailDialog.getContentPane().add(list, gbc_list);
			}
			{
				JButton btnAdd = new JButton("Add...");
				GridBagConstraints gbc_btnAdd = new GridBagConstraints();
				gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
				gbc_btnAdd.gridx = 2;
				gbc_btnAdd.gridy = 1;
				emailDialog.getContentPane().add(btnAdd, gbc_btnAdd);
			}
			{
				JButton btnRemove = new JButton("Remove");
				GridBagConstraints gbc_btnRemove = new GridBagConstraints();
				gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
				gbc_btnRemove.gridx = 2;
				gbc_btnRemove.gridy = 2;
				emailDialog.getContentPane().add(btnRemove, gbc_btnRemove);
			}
			{
				JButton btnEditXML = new JButton("Edit XML");
				GridBagConstraints gbc_btnEditFile = new GridBagConstraints();
				gbc_btnEditFile.insets = new Insets(0, 0, 5, 5);
				gbc_btnEditFile.gridx = 2;
				gbc_btnEditFile.gridy = 3;
				emailDialog.getContentPane().add(btnEditXML, gbc_btnEditFile);
			}
			contentPanel.setLayout(new FlowLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			GridBagConstraints gbc_contentPanel = new GridBagConstraints();
			gbc_contentPanel.fill = GridBagConstraints.BOTH;
			gbc_contentPanel.insets = new Insets(0, 0, 0, 5);
			gbc_contentPanel.gridx = 2;
			gbc_contentPanel.gridy = 4;
			emailDialog.getContentPane().add(contentPanel, gbc_contentPanel);
			{
				JPanel buttonPane = new JPanel();
				contentPanel.add(buttonPane);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				{
					JButton okButton = new JButton("OK");
					okButton.setToolTipText("Save changes");
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					emailDialog.getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setToolTipText("Discard changes");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
	
	
	
}
