package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import gui.controller.*;
import gui.model.Session;



public class ManagementView extends JFrame {

	private static final long serialVersionUID = 989075282041187452L;
	private ManagementController controller;
	private JTabbedPane tabbedPane;
	private JTable tblUsers;
	private JTable tblPatients;
	private JLabel lblSessionU;
	private JLabel lblSessionP;
	private JPanel userPanel;
	private JPanel patientPanel;
	
	public ManagementView(ManagementController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Management System");
		setPreferredSize(new Dimension(800, 600));
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		userPanel = new JPanel();
		patientPanel = new JPanel();
		
		// buttons
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addUser();
			}
		});
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setEnabled(false);
		btnRemoveUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeUser();
			}
		});
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addPatient();
			}
		});
		
		JButton btnRemovePatient = new JButton("Remove Patient");
		btnRemovePatient.setEnabled(false);
		btnRemovePatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removePatient();
			}
		});
		
		// toolbar
		lblSessionU = new JLabel();
		lblSessionU.setHorizontalAlignment(SwingConstants.RIGHT);
		JToolBar userToolbar = new JToolBar();
		userToolbar.add(btnAddUser);
		userToolbar.add(btnRemoveUser);
		userToolbar.add(Box.createHorizontalGlue());
		userToolbar.add(lblSessionU);
		userPanel.add(userToolbar, BorderLayout.NORTH);
		
		lblSessionP = new JLabel();
		lblSessionP.setHorizontalAlignment(SwingConstants.RIGHT);
		JToolBar patientToolbar = new JToolBar();
		patientToolbar.add(btnAddPatient);
		patientToolbar.add(btnRemovePatient);
		patientToolbar.add(Box.createHorizontalGlue());
		patientToolbar.add(lblSessionP);
		patientPanel.add(patientToolbar, BorderLayout.NORTH);
		
		// table
		tblUsers = new JTable();
		tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnRemoveUser.setEnabled((tblUsers.getSelectedRow() >= 0));
			}
		});
		userPanel.add(tblUsers,BorderLayout.CENTER);
		
		tabbedPane = new JTabbedPane();
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnRemovePatient.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		patientPanel.add(tblPatients,BorderLayout.CENTER);
		
		tabbedPane.addTab("Users", userPanel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Patients", patientPanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
		
		add(tabbedPane);
		
		//The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
        add(tabbedPane);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setTableModel(TableModel model) {
		tblUsers.setModel(model);
//		tblPatients.setModel(model);
	}

	public void setSession(Session sessionModel) {
		lblSessionU.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
		lblSessionP.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
}
