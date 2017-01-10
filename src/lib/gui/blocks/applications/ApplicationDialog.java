package lib.gui.blocks.applications;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	protected JList<String> list;
	protected JDialog dialog;

	public ApplicationDialog(Frame owner, String title) {
		selectedValues = null;
		dialog = new JDialog(owner, title);
		dialog.setBounds(250, 250, 300, 480);
		dialog.setAlwaysOnTop(true);
		buildPanel();
	}

	protected abstract void addCurrentApplicationData(DefaultListModel<String> listModel);

	private void buildPanel() {
		//LAYOUT
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{433, 0};
		gridBagLayout.rowHeights = new int[]{269, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		dialog.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		dialog.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{283, 0};
		gbl_panel.rowHeights = new int[]{268, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{20, 112, 133, 0};
		gbl_panel_1.rowHeights = new int[]{20, 380, 20, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JScrollPane listScroller = createScrollPane();
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		panel_1.add(listScroller, gbc_list);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedValues = list.getSelectedValuesList();
				dialog.getOwner().setEnabled(true);
				dialog.dispose();
				
				for(String value : selectedValues) {
					System.out.println(value);
				}
			}
		});
		
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 2;
		panel_1.add(btnOk, gbc_btnOk);
		
		
	}

	private JScrollPane createScrollPane() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		addCurrentApplicationData(listModel);
		list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBackground(UIManager.getColor("Panel.background"));
		list.setVisibleRowCount(16);

		JScrollPane listScroller = new JScrollPane(list);

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

}
