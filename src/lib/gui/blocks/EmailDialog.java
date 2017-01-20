package lib.gui.blocks;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import lib.gui.EmailRow;
import lib.structs.OpenReportsCache;

public class EmailDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String dialogTitle = "Support DEV Email Addresses";

	private final JPanel contentPanel = new JPanel();

	private boolean addMode;

	/**
	 * Create the dialog.
	 */
	public EmailDialog(JFrame owner, OpenReportsCache cache) {
		super(owner, EmailDialog.dialogTitle);
		addMode = false;
		buildPanel(cache);
	}

	private void buildPanel(OpenReportsCache cache) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(getOwner().getX()+getOwner().getWidth(), getOwner().getY(), 450, 320);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 240, 140, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 170, 30, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);


		//email list panel
		JPanel emailListPanel = new JPanel();
		GridBagConstraints gbc_emailListPanel = new GridBagConstraints();
		gbc_emailListPanel.gridwidth = 2;
		gbc_emailListPanel.fill = GridBagConstraints.BOTH;
		gbc_emailListPanel.gridx = 1;
		gbc_emailListPanel.gridy = 1;
		getContentPane().add(emailListPanel, gbc_emailListPanel);
		GridBagLayout gbl_emailListPanel = new GridBagLayout();
		gbl_emailListPanel.columnWidths = new int[]{350, 30, 0};
		gbl_emailListPanel.rowHeights = new int[]{145, 0};
		gbl_emailListPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_emailListPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		emailListPanel.setLayout(gbl_emailListPanel);

		JScrollPane emailListScrollPane = new JScrollPane();

		JPanel emailList = new JPanel();
		emailList.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		emailList.setLayout(new GridLayout(0, 1, 0, 0));

		createEmailRows(emailList, cache);

		emailListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		emailListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		emailListScrollPane.setViewportView(emailList);

		GridBagConstraints gbc_emailListScrollPane = new GridBagConstraints();
		gbc_emailListScrollPane.gridwidth = 2;
		gbc_emailListScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_emailListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_emailListScrollPane.gridx = 0;
		gbc_emailListScrollPane.gridy = 0;
		emailListPanel.add(emailListScrollPane, gbc_emailListScrollPane);


		//Add email text field + buttons
		JPanel addEmailPanel = new JPanel();
		GridBagConstraints gbc_addEmailPanel = new GridBagConstraints();
		gbc_addEmailPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_addEmailPanel.gridwidth = 2;
		gbc_addEmailPanel.anchor = GridBagConstraints.NORTH;
		gbc_addEmailPanel.gridx = 1;
		gbc_addEmailPanel.gridy = 2;
		getContentPane().add(addEmailPanel, gbc_addEmailPanel);
		GridBagLayout gbl_addEmailPanel = new GridBagLayout();
		gbl_addEmailPanel.columnWidths = new int[]{190, 15, 0};
		gbl_addEmailPanel.rowHeights = new int[]{15, 0};
		gbl_addEmailPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_addEmailPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		addEmailPanel.setLayout(gbl_addEmailPanel);

		JTextField addEmailText = new JTextField(225);
		addEmailText.setEditable(false);
		addEmailText.setBackground(Color.WHITE);
		GridBagConstraints gbc_addEmailText = new GridBagConstraints();
		gbc_addEmailText.fill = GridBagConstraints.BOTH;
		gbc_addEmailText.gridx = 0;
		gbc_addEmailText.gridy = 0;
		addEmailPanel.add(addEmailText, gbc_addEmailText);


		addEmailPanel.setBackground(addEmailText.getBackground());
		addEmailPanel.setBorder(addEmailText.getBorder());
		addEmailText.setBorder(null);
		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(EmailDialog.class.getResource("/resources/img/add.png")));
		addListenerToAddButton(btnAdd, addEmailText, emailList, cache);
		btnAdd.setMargin(new Insets(0,0,0,0));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.VERTICAL;
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		addEmailPanel.add(btnAdd, gbc_btnAdd);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.gridwidth = 2;
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 2;
		gbc_contentPanel.gridy = 3;
		getContentPane().add(contentPanel, gbc_contentPanel);


		//Ok and Cancel Buttons panel
		JPanel buttonPane = new JPanel();
		contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton okButton = createOkButton();
		okButton.setToolTipText("Save changes");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setToolTipText("Discard changes");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		addOnCloseListener();
	}


	private void createEmailRows(JPanel owner, OpenReportsCache cache) {
		for(String value : cache.getEmailList()) {
			EmailRow row = new EmailRow();
			row.setText(value);
			owner.add(row);
		}
	}

	private void addOnCloseListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				getOwner().setEnabled(true);
				dispose();
			}
		});
	}

	private void addListenerToAddButton(JButton button, JTextField textfield, JPanel viewPanel, OpenReportsCache cache) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addMode) {
					//TODO: Triggers EmailManager addEmail

					textfield.setText("");
					if(validateField(textfield)) {
						cache.getEmailList().addEmail(textfield.getText());
						viewPanel.removeAll();
						createEmailRows(viewPanel, cache);
						viewPanel.getRootPane().revalidate();
					} else {
						JOptionPane.showMessageDialog(viewPanel, "The email address is invalid.");
					}
					addMode = false;
					button.setIcon(new ImageIcon(EmailDialog.class.getResource("/resources/img/add.png")));
					textfield.setEditable(false);
				} else {
					addMode = true;
					button.setIcon(new ImageIcon(EmailDialog.class.getResource("/resources/img/accept.png")));
					textfield.setEditable(true);
					textfield.requestFocus();

				}
			}
			private boolean validateField(JTextField textfield) {
				return !textfield.getText().isEmpty() && textfield.getText() != null;
			}
		});
	}

	private JButton createOkButton() {
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getOwner().setEnabled(true);
				dispose();
			}
		});
		return btnOk;
	}


}
