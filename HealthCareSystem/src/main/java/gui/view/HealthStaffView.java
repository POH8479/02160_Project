package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import gui.controller.HealthStaffController;
import gui.model.Session;

public class HealthStaffView extends JFrame {

	private static final long serialVersionUID = -5921945552466222819L;
	private HealthStaffController controller;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane patientPane;
	
	public HealthStaffView(HealthStaffController controller) {
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Management System");
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// edit
				controller.editPatient(tblPatients.getSelectedRow());
			}
		});
		
		JButton btnRegisterPatient = new JButton("Register Patient");
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
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDischargePatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnAdmitPatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnEdit.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnRecord.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		
		patientPane = new JScrollPane(tblPatients);
		add(patientPane);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setTableModel(TableModel patientModel) {
		tblPatients.setModel(patientModel);
	}

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
}
