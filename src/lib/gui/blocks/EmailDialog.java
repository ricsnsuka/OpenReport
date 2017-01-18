package lib.gui.blocks;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

public class EmailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

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

	/**
	 * Create the dialog.
	 */
	public EmailDialog() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 240, 140, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 60, 60, 60, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JList list = new JList();
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.gridheight = 3;
			gbc_list.insets = new Insets(0, 0, 5, 5);
			gbc_list.fill = GridBagConstraints.BOTH;
			gbc_list.gridx = 1;
			gbc_list.gridy = 1;
			getContentPane().add(list, gbc_list);
		}
		{
			JButton btnAdd = new JButton("Add...");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
			gbc_btnAdd.gridx = 2;
			gbc_btnAdd.gridy = 1;
			getContentPane().add(btnAdd, gbc_btnAdd);
		}
		{
			JButton btnRemove = new JButton("Remove");
			GridBagConstraints gbc_btnRemove = new GridBagConstraints();
			gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
			gbc_btnRemove.gridx = 2;
			gbc_btnRemove.gridy = 2;
			getContentPane().add(btnRemove, gbc_btnRemove);
		}
		{
			JButton btnEditXML = new JButton("Edit XML");
			GridBagConstraints gbc_btnEditFile = new GridBagConstraints();
			gbc_btnEditFile.insets = new Insets(0, 0, 5, 5);
			gbc_btnEditFile.gridx = 2;
			gbc_btnEditFile.gridy = 3;
			getContentPane().add(btnEditXML, gbc_btnEditFile);
		}
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 0, 5);
		gbc_contentPanel.gridx = 2;
		gbc_contentPanel.gridy = 4;
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

}
