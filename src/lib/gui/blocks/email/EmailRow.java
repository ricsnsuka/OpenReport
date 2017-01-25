package lib.gui.blocks.email;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

	private JButton deleteButton;
	private boolean isDummy;

	/**
	 * Create the panel.
	 */

	public EmailRow() {
		isDummy = false;
	}

	public EmailRow(boolean dummy) {
		isDummy = dummy;
	}


	public void build(JPanel owner) {
		buildRow(owner);
	}

	private void buildRow(JPanel owner) {

		setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		textField.setEnabled(false);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("focus gained");
			}
		});

		textField.setBackground(owner.getBackground());
		textField.setBorder(new LineBorder(UIManager.getColor("TextField[Disabled].textForeground"), 1, true));
		add(textField, BorderLayout.CENTER);
		textField.setColumns(28);
		textField.setEditable(false);

		setBackground(textField.getBackground());
		setBorder(textField.getBorder());
		textField.setBorder(null);

		if(!isDummy) {
			deleteButton = new JButton("");
			deleteButton.setIcon(new ImageIcon(EmailRow.class.getResource("/resources/img/delete.png")));
			deleteButton.setMargin(new Insets(0,0,0,0));
			add(deleteButton, BorderLayout.EAST);
		}

	}

	public void setText(String text) {
		this.textField.setText(text);
	}

	public String getText() {
		return textField.getText();
	}

	protected JButton getDeleteButton() {
		return this.deleteButton;
	}
	
	protected boolean isDummy() {
		return this.isDummy;
	}


}
