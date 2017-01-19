package lib.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class EmailRow extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -415668857912541349L;
	
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EmailRow() {
		buildRow();

	}
	
	private void buildRow() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		setBackground(textField.getBackground());
		setBorder(textField.getBorder());
		textField.setBorder(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(EmailRow.class.getResource("/resources/img/delete.png")));
		btnNewButton.setMargin(new Insets(0,0,0,0));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
	}
	
	public void setText(String text) {
		this.textField.setText(text);
	}

}
