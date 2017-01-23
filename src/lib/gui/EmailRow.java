package lib.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

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
	}
	
	public void build(JPanel owner) {
		buildRow(owner);
	}
	
	private void buildRow(JPanel owner) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("focus gained");
			}
		});
		textField.setBackground(owner.getBackground());
		textField.setBorder(new LineBorder(UIManager.getColor("TextField[Disabled].textForeground"), 1, true));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setEditable(false);
		textField.setEnabled(false);
		
		setBackground(textField.getBackground());
		setBorder(textField.getBorder());
		textField.setBorder(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouse clicked");
				textField.requestFocus();
			}
		});
		btnNewButton.setIcon(new ImageIcon(EmailRow.class.getResource("/resources/img/delete.png")));
		btnNewButton.setMargin(new Insets(0,0,0,0));
		btnNewButton.setEnabled(false);
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
