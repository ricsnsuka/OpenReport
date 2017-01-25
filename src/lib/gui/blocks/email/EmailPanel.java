package lib.gui.blocks.email;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.adapters.EmailAdapter;
import lib.structs.OpenReportsCache;

public class EmailPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5145767768404568681L;

	private static final String label = "Send to";

	private EmailAdapter emailAdapter;

	private JCheckBox sendToSupport;
	private JButton editEmails;
	private String[] otherEmails;


	public EmailPanel() {
		emailAdapter = new EmailAdapter();
	}
	
	public void build(JFrame frame, OpenReportsCache cache) {
		buildFrame(frame, cache);
	}

	private void buildFrame(JFrame frame, OpenReportsCache cache) {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), EmailPanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_emailSetup = new GridBagConstraints();
		gbc_emailSetup.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailSetup.insets = new Insets(0, 0, 5, 0);
		gbc_emailSetup.gridx = 0;
		gbc_emailSetup.gridy = 4;
		frame.getContentPane().add(this, gbc_emailSetup);
		GridBagLayout gbl_emailSetup = new GridBagLayout();
		gbl_emailSetup.columnWidths = new int[]{10, 87, 75, 15, 275, 0, 0};
		gbl_emailSetup.rowHeights = new int[] {22, 0};
		gbl_emailSetup.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_emailSetup.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gbl_emailSetup);



		sendToSupport = new JCheckBox("Support DEVs");
		sendToSupport.setSelected(true);
		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.anchor = GridBagConstraints.NORTHWEST;
		gbc_checkbox.insets = new Insets(0, 0, 0, 0);
		gbc_checkbox.gridx = 1;
		gbc_checkbox.gridy = 0;
		add(sendToSupport, gbc_checkbox);

		editEmails = new JButton("");
		editEmails.setIcon(new ImageIcon(EmailDialog.class.getResource("/resources/img/settings.png")));
		editEmails.setMargin(new Insets(0,0,0,0));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 2;
		gbc_btnEdit.gridy = 0;
		add(editEmails, gbc_btnEdit);

		JPanel emailOthersText = new JPanel();
		GridBagConstraints gbc_emailOthersText = new GridBagConstraints();
		gbc_emailOthersText.insets = new Insets(0, 0, 0, 5);
		gbc_emailOthersText.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailOthersText.gridx = 4;
		gbc_emailOthersText.gridy = 0;
		add(emailOthersText, gbc_emailOthersText);
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
		emailOthers.setColumns(40);
		Others.setLabelFor(emailOthers);

		addListeners(frame, cache);
	}

	private void addListeners(JFrame frame, OpenReportsCache cache) {
		editEmails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmailDialog dialog = new EmailDialog(frame, cache);
				frame.setEnabled(false);
				dialog.setVisible(true);
			}
		});

		sendToSupport.addItemListener(new ItemListener() {
			@Override
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
