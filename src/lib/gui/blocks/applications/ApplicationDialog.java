package lib.gui.blocks.applications;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import lib.structs.Application;

public class ApplicationDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3886417716378033869L;
	
	private List<Application> selectedValues;
	private ArrayList<Application> defaultValues;

	protected JList<String> data;

	public ApplicationDialog(JFrame owner, String title, ArrayList<Application> defaultValues) {
		super(owner, title);

		selectedValues = null;
		this.defaultValues = defaultValues;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(owner.getX()+owner.getWidth(), owner.getY(), 300, 480);
		buildPanel();
	}

	protected DefaultListModel<String> createApplicationListModel() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(Application value : this.defaultValues) {
			listModel.addElement(value.getName());
		}
		return listModel;
	}

	private void buildPanel() {
		JScrollPane listScroller = createScrollPane();

		//LAYOUT
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 0};
		gridBagLayout.rowHeights = new int[]{440, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JPanel mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.VERTICAL;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{300, 0};
		gbl_mainPanel.rowHeights = new int[]{480, 0};
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
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.fill = GridBagConstraints.CENTER;
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 2;
		applicationPanel.add(btnOk, gbc_btnOk);

		addOnCloseListener();

	}

	private JScrollPane createScrollPane() {
		DefaultListModel<String> listModel = createApplicationListModel();
		data = new JList<>(listModel);
		data.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		data.setSelectionModel(new DefaultListSelectionModel() 
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1304236373523189960L;

			@Override
			public void setSelectionInterval(int index0, int index1) 
			{
				if(data.isSelectedIndex(index0)) 
				{
					data.removeSelectionInterval(index0, index1);
				}
				else 
				{
					data.addSelectionInterval(index0, index1);
				}
			}
		});
		data.setLayoutOrientation(JList.VERTICAL);
		data.setBackground(UIManager.getColor("Panel.background"));
		data.setVisibleRowCount(16);

		JScrollPane listScroller = new JScrollPane(data);

		listScroller.setPreferredSize(new Dimension(130, 50));

		return listScroller;
	}


	public List<Application> getSelectedValues() {
		if(selectedValues == null) {
			return new ArrayList<Application>();
		}
		return selectedValues;
	}

	private JButton createOkButton() {
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getSelectedValuesList();
				getOwner().setEnabled(true);
				dispose();

			}
		});
		return btnOk;
	}
	
	private void getSelectedValuesList() {
		selectedValues = new ArrayList<>();
		for(String value : data.getSelectedValuesList()) {
			selectedValues.add(new Application(getTitle(), value));
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

}
