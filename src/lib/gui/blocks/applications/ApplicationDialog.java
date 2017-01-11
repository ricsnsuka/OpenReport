package lib.gui.blocks.applications;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public abstract class ApplicationDialog {
	private List<String> selectedValues;
	protected JList<String> data;
	protected JDialog dialog;

	public ApplicationDialog(Frame owner, String title) {
		selectedValues = null;
		dialog = new JDialog(owner, title);
		dialog.setType(Type.POPUP);
		dialog.setResizable(false);
		dialog.setBounds(250, 250, 300, 480);
		dialog.setAlwaysOnTop(true);
		buildPanel();
	}

	protected abstract void addCurrentApplicationData(DefaultListModel<String> listModel);

	private void buildPanel() {
		JScrollPane listScroller = createScrollPane();
		
		//LAYOUT
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 0};
		gridBagLayout.rowHeights = new int[]{440, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		dialog.getContentPane().setLayout(gridBagLayout);

		JPanel mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.VERTICAL;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		dialog.getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{299, 0};
		gbl_mainPanel.rowHeights = new int[]{479, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);

		JPanel applicationPanel = new JPanel();
		GridBagConstraints gbc_applicationPanel = new GridBagConstraints();
		gbc_applicationPanel.fill = GridBagConstraints.VERTICAL;
		gbc_applicationPanel.gridx = 0;
		gbc_applicationPanel.gridy = 0;
		mainPanel.add(applicationPanel, gbc_applicationPanel);
		GridBagLayout gbl_applicationPanel = new GridBagLayout();
		gbl_applicationPanel.columnWidths = new int[]{20, 120, 120, 20, 0};
		gbl_applicationPanel.rowHeights = new int[]{20, 390, 30, 20, 0};
		gbl_applicationPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_applicationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		applicationPanel.setLayout(gbl_applicationPanel);
		
		
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		applicationPanel.add(listScroller, gbc_list);
		
		JButton btnOk = createOkButton();
		
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 2;
		applicationPanel.add(btnOk, gbc_btnOk);
		
		
	}

	private JScrollPane createScrollPane() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		addCurrentApplicationData(listModel);
		data = new JList<>(listModel);
		data.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		data.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		data.setLayoutOrientation(JList.VERTICAL);
		data.setBackground(UIManager.getColor("Panel.background"));
		data.setVisibleRowCount(16);

		JScrollPane listScroller = new JScrollPane(data);

		listScroller.setPreferredSize(new Dimension(130, 50));

		return listScroller;
	}
	
	public JDialog getDialog() {
		return this.dialog;
	}
	
	public List<String> getSelectedValues() {
		if(selectedValues == null) {
			return new ArrayList<String>();
		}
		return selectedValues;
	}
	
	private JButton createOkButton() {
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedValues = data.getSelectedValuesList();
//				dialog.getOwner().setEnabled(true);
				dialog.dispose();
				
				for(String value : selectedValues) {
					System.out.println(value);
				}
			}
		});
		return btnOk;
	}

}
