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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import gui.controller.*;
import gui.model.Session;



public class ManagementView extends JFrame {

	private static final long serialVersionUID = -4500084641006846370L;
	private ManagementController controller;
	private JTabbedPane tabbedPane;
	private JTable tblUsers;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane userPane;
	private JScrollPane patientPane;
	
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
		
		// buttons
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setEnabled(false);
		btnAddUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addUser();
			}
		});
		
		JButton btnAssignDept = new JButton("Assign Department");
		btnAssignDept.setEnabled(false);
		btnAssignDept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.assignDepartment(tblUsers.getSelectedRow());
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// edit
				if(tabbedPane.getSelectedIndex() == 0 && tblUsers.getSelectedRow() >= 0) {
					// edit the selected User
					controller.editUser(tblUsers.getSelectedRow());
				}
				if(tabbedPane.getSelectedIndex() == 1 && tblPatients.getSelectedRow() >= 0) {
					// edit the selected Patient
					controller.editPatient(tblPatients.getSelectedRow());
				}
			}
		});
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setEnabled(false);
		btnRemoveUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeUser(tblUsers.getSelectedRow());
			}
		});
		
		JButton btnRegisterPatient = new JButton("Register Patient");
		btnRegisterPatient.setEnabled(false);
		btnRegisterPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addPatient();
			}
		});
		
		JButton btnAdmitPatient = new JButton("Admit Patient");
		btnAdmitPatient.setEnabled(false);
		btnAdmitPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.admitPatient(tblPatients.getSelectedRow());
			}
		});
		
		JButton btnDischargePatient = new JButton("Discharge Patient");
		btnDischargePatient.setEnabled(false);
		btnDischargePatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.dischargePatient(tblPatients.getSelectedRow());
			}
		});
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.logOut();
			}
		});
		
		JButton btnRecord = new JButton("Record");
		btnRecord.setEnabled(false);
		btnRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				controller.showRecord(tblPatients.getSelectedRow());
			}
		});
		
		
		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnAddUser);
		toolbar.add(btnAssignDept);
		toolbar.add(btnRemoveUser);
		toolbar.add(btnRegisterPatient);
		toolbar.add(btnAdmitPatient);
		toolbar.add(btnDischargePatient);
		toolbar.add(btnEdit);
		toolbar.add(btnRecord);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(btnLogOut);
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// table
		tabbedPane = new JTabbedPane();
		
		tblUsers = new JTable();
		tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnRemoveUser.setEnabled((tblUsers.getSelectedRow() >= 0));
				btnAssignDept.setEnabled(((tblUsers.getSelectedRow() >= 0) && (((String) tblUsers.getValueAt(tblUsers.getSelectedRow(), 0)).charAt(0) != 'U') && (((String) tblUsers.getValueAt(tblUsers.getSelectedRow(), 0)).charAt(0) != 'A')));
				btnEdit.setEnabled((tabbedPane.getSelectedIndex() == 0 && tblUsers.getSelectedRow() >= 0));
			}
		});
		
		
		userPane = new JScrollPane(tblUsers);
		
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDischargePatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnAdmitPatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnEdit.setEnabled((tabbedPane.getSelectedIndex() == 1 && tblPatients.getSelectedRow() >= 0));
				btnRecord.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				// 
				if(tabbedPane.getSelectedIndex() == 0) {
					btnRegisterPatient.setEnabled(false);
					btnAdmitPatient.setEnabled(false);
					btnDischargePatient.setEnabled(false);
					btnRecord.setEnabled(false);
					btnAddUser.setEnabled(true);
				} else if(tabbedPane.getSelectedIndex() == 1) {
					btnAddUser.setEnabled(false);
					btnRemoveUser.setEnabled(false);
					btnRegisterPatient.setEnabled(true);
				}
				
			}
		});
		
		
		patientPane = new JScrollPane(tblPatients);
		
		tabbedPane.addTab("Users", userPane);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Patients", patientPane);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
		
		add(tabbedPane);
		
		//The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
        add(tabbedPane);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setTableModel(TableModel userModel,TableModel patientModel) {
		tblUsers.setModel(userModel);
		tblPatients.setModel(patientModel);
	}

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
}
