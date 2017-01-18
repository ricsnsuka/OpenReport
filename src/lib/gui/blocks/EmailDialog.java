package lib.gui.blocks;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lib.fileparser.XMLParser;
import lib.gui.EmailRow;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.UIManager;

public class EmailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	protected ArrayList<String> dialogData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmailDialog dialog = new EmailDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void createEmailRows(JPanel owner) {
		for(String value : this.dialogData) {
			EmailRow row = new EmailRow();
			row.setText(value);
			owner.add(row);
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmailDialog() {
		
		dialogData = new ArrayList<>();
		addCurrentDialogApplicationData();
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 240, 140, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{20, 150, 30, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel panel = new JPanel();
			panel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 2;
			gbc_panel.insets = new Insets(0, 0, 0, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			getContentPane().add(panel, gbc_panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			
			createEmailRows(panel);
		
			JScrollBar scrollBar = new JScrollBar();
			GridBagConstraints gbc_scrollBar = new GridBagConstraints();
			gbc_scrollBar.insets = new Insets(0, 0, 5, 5);
			gbc_scrollBar.gridx = 3;
			gbc_scrollBar.gridy = 1;
			getContentPane().add(scrollBar, gbc_scrollBar);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			getContentPane().add(panel, gbc_panel);
			//			GridBagLayout gbl_panel = new GridBagLayout();
			//			gbl_panel.columnWidths = new int[]{130, 30, 0};
			//			gbl_panel.rowHeights = new int[]{0, 0};
			//			gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			//			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			//			panel.setLayout(gbl_panel);
			{
				textField = new JTextField(20);
				//				GridBagConstraints gbc_textField = new GridBagConstraints();
				//				gbc_textField.gridwidth = 2;
				//				gbc_textField.insets = new Insets(0, 0, 0, 5);
				//				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				//				gbc_textField.gridx = 0;
				//				gbc_textField.gridy = 0;
				//				GridBagConstraints gbc_textField = new GridBagConstraints();
				//				gbc_textField.insets = new Insets(0, 0, 0, 5);
				//				gbc_textField.gridx = 0;
				//				gbc_textField.gridy = 0;
				panel.add(textField);

			}
			{

				panel.setBackground(textField.getBackground());
				panel.setBorder(textField.getBorder());
				textField.setBorder(null);
			}
			JButton btnAdd = new JButton("Add");
			//				GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			//				gbc_btnAdd.gridx = 2;
			//				gbc_btnAdd.gridy = 0;
			btnAdd.setContentAreaFilled(false);
			btnAdd.setMargin(new Insets(0,0,0,0));
			//				textField.add(btnAdd);
			panel.add(btnAdd);
		}
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.gridwidth = 2;
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 0, 5);
		gbc_contentPanel.gridx = 2;
		gbc_contentPanel.gridy = 3;
		getContentPane().add(contentPanel, gbc_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.setToolTipText("Save changes");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setToolTipText("Discard changes");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	protected void addCurrentDialogApplicationData() {
		XMLParser parser = new XMLParser("src\\resources\\SupEmailAddresses.xml");
		for(String email : parser.getNodeValue("emailTo")) {
			dialogData.add(email);
		}
	}
	
	

}
