package lib.gui;

import java.awt.BorderLayout;
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
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("focus gained");
			}
		});
		setLayout(new BorderLayout(0, 0));
		
		textField.setBackground(owner.getBackground());
		textField.setBorder(new LineBorder(UIManager.getColor("TextField[Disabled].textForeground"), 1, true));
		add(textField, BorderLayout.CENTER);
		textField.setColumns(28);
		textField.setEditable(false);
		
		setBackground(textField.getBackground());
		setBorder(textField.getBorder());
		textField.setBorder(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouse clicked");
				textField.requestFocus();
			}
		});
		btnNewButton.setIcon(new ImageIcon(EmailRow.class.getResource("/resources/img/delete.png")));
		btnNewButton.setMargin(new Insets(0,0,0,0));
		add(btnNewButton, BorderLayout.EAST);
		
	}
	
	public void setText(String text) {
		this.textField.setText(text);
	}

}
