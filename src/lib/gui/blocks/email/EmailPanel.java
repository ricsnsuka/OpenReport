package lib.gui.blocks.email;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.controller.EmailController;
import lib.exceptions.OpenReportException;

/**
 * EmailPanel.java Fourth panel block in the GUI. Interface to setup the
 * developer who will receive the reports via email.
 * 
 * @author rnsuka
 *
 */
public class EmailPanel extends JPanel {

	private static final long serialVersionUID = -5145767768404568681L;

	private static final String label = "Send to";

	private JCheckBox sendToSupport;
	private JButton editEmails;
	private JTextField otherEmails;

	/**
	 * Add listeners to this block elements
	 * 
	 * @param emailController
	 * @param frame
	 */
	private void addListeners(EmailController emailController, JFrame frame) {
		editEmails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmailDialog dialog = new EmailDialog(frame, emailController);
				frame.setEnabled(false);
				dialog.setVisible(true);
			}
		});

		sendToSupport.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				emailController.setEmailToSupportTeam(sendToSupport.isSelected());
			}
		});

		otherEmails.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!"".equals(otherEmails.getText().trim())) {
					splitEmails();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}

			private void splitEmails() {
				String[] ret = otherEmails.getText().split(";");
				try {
					for (String email : ret) {
						emailController.addEmailReceiver(email);
					}
				} catch (OpenReportException e) {
					System.err.println(e.getMessage());
					otherEmails.requestFocus();
				}
			}
		});
	}

	/**
	 * Panel builder
	 * 
	 * @param frame
	 * @param emailController
	 */
	private void buildFrame(JFrame frame, EmailController emailController) {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), EmailPanel.label, TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_emailSetup = new GridBagConstraints();
		gbc_emailSetup.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailSetup.insets = new Insets(0, 0, 5, 0);
		gbc_emailSetup.gridx = 0;
		gbc_emailSetup.gridy = 4;
		frame.getContentPane().add(this, gbc_emailSetup);
		GridBagLayout gbl_emailSetup = new GridBagLayout();
		gbl_emailSetup.columnWidths = new int[] { 15, 85, 30, 50, 140, 10, 0 };
		gbl_emailSetup.rowHeights = new int[] { 20, 0 };
		gbl_emailSetup.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_emailSetup.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gbl_emailSetup);

		sendToSupport = new JCheckBox("Support DEVs");
		sendToSupport.setSelected(true);
		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.anchor = GridBagConstraints.CENTER;
		gbc_checkbox.insets = new Insets(0, 0, 0, 5);
		gbc_checkbox.gridx = 1;
		gbc_checkbox.gridy = 0;
		add(sendToSupport, gbc_checkbox);

		editEmails = new JButton("");
		editEmails.setIcon(new ImageIcon(EmailDialog.class.getResource("/resources/img/settings.png")));
		editEmails.setMargin(new Insets(0, 0, 0, 0));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 2;
		gbc_btnEdit.gridy = 0;
		add(editEmails, gbc_btnEdit);

		JPanel emailOthersText = new JPanel();
		GridBagConstraints gbc_emailOthersText = new GridBagConstraints();
		gbc_emailOthersText.insets = new Insets(0, 0, 0, 0);
		gbc_emailOthersText.anchor = GridBagConstraints.CENTER;
		gbc_emailOthersText.gridx = 4;
		gbc_emailOthersText.gridy = 0;
		add(emailOthersText, gbc_emailOthersText);
		GridBagLayout gbl_emailOthersText = new GridBagLayout();
		gbl_emailOthersText.columnWidths = new int[] { 35, 5, 100, 0 };
		gbl_emailOthersText.rowHeights = new int[] { 20, 0 };
		gbl_emailOthersText.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_emailOthersText.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		emailOthersText.setLayout(gbl_emailOthersText);

		JLabel others = new JLabel("Others");
		GridBagConstraints gbc_Others = new GridBagConstraints();
		gbc_Others.gridwidth = 2;
		gbc_Others.insets = new Insets(0, 0, 0, 0);
		gbc_Others.gridx = 0;
		gbc_Others.gridy = 0;
		emailOthersText.add(others, gbc_Others);

		otherEmails = new JTextField();
		GridBagConstraints gbc_emailOthers = new GridBagConstraints();
		gbc_emailOthers.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailOthers.gridx = 2;
		gbc_emailOthers.gridy = 0;
		emailOthersText.add(otherEmails, gbc_emailOthers);
		otherEmails.setColumns(25);
		others.setLabelFor(otherEmails);

		addListeners(emailController, frame);
	}

	/**
	 * Panel builder
	 * 
	 * @param frame
	 * @param emailController
	 */
	public void build(JFrame frame, EmailController emailController) {
		buildFrame(frame, emailController);
	}

	/**
	 * Validates the block
	 * @return
	 */
	public boolean validatePanel() {
		return sendToSupport.isSelected() || (otherEmails != null && !otherEmails.equals(""));
	}

}
