package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

/**
 * The View for the HealthStaff Users
 * @author Pieter O'Hearn
 *
 */
public class HealthStaffView extends JFrame {
	// Static Variables
	private static final long serialVersionUID = -5921945552466222819L;
	
	// Instance Variables
	private HealthStaffController controller;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane patientPane;
	
	/**
	 * Constructor for the HealthStaff View sets up the view by calling the initGUI() method.
	 * @param controller The HealthStaff Controller
	 */
	public HealthStaffView(HealthStaffController controller) {
		// set the HealthStaffView and call initGUI
		this.controller = controller;
		initGUI();
	}
	
	/**
	 * Initialises the Graphical User Interface
	 */
	private void initGUI() {
		// set the Default Close Operation, Tile and Window Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Management System");
		setPreferredSize(new Dimension(800, 600));
		
		// BUTTONS
		// Create a new button "Edit" with an Action Listener and set Enabled to false
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked edit the selected patient
				controller.editPatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Register Patient" with an Action Listener
		JButton btnRegisterPatient = new JButton("Register Patient");
		btnRegisterPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked add a new patient
				controller.addPatient();
			}
		});
		
		// Create a new button "Admit Patient" with an Action Listener and set Enabled to false
		JButton btnAdmitPatient = new JButton("Admit Patient");
		btnAdmitPatient.setEnabled(false);
		btnAdmitPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked admit a new Patient
				controller.admitPatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Discharge Patient" with an Action Listener and set Enabled to false
		JButton btnDischargePatient = new JButton("Discharge Patient");
		btnDischargePatient.setEnabled(false);
		btnDischargePatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked discharge the selected patient
				controller.dischargePatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Log Out" with an Action Listener
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When selected, Log Out
				controller.logOut();
			}
		});
		
		// Create a new button "Record" with an Action Listener and set Enabled to false
		JButton btnRecord = new JButton("Record");
		btnRecord.setEnabled(false);
		btnRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When selected show the selected patients medical record
				controller.showRecord(tblPatients.getSelectedRow());
			}
		});
		
		
		// TOOLBAR
		// create a new label and set Horizontal Alignment right
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// create a new JToo;Bar and add all buttons and the above label
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnRegisterPatient);
		toolbar.add(btnAdmitPatient);
		toolbar.add(btnDischargePatient);
		toolbar.add(btnEdit);
		toolbar.add(btnRecord);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(btnLogOut);
		toolbar.add(lblSession);
		
		// Place the toolbar on the top of the page
		add(toolbar, BorderLayout.NORTH);
		
		// TABLES
		// create a table for the patients with an Selection Listener and set the Selection mode to single selection
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// When a row is selected enable the following buttons
				btnDischargePatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnAdmitPatient.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnEdit.setEnabled((tblPatients.getSelectedRow() >= 0));
				btnRecord.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		
		// add the table to a new JScrollPane and add to the HealthStaff Frame
		patientPane = new JScrollPane(tblPatients);
		add(patientPane);
		
		// Pack the content to the window and place in the window in the center of the screen
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Sets the table model for the Patients table
	 * @param patientModel The table model
	 */
	public void setTableModel(TableModel patientModel) {
		// set the model of the table patient
		tblPatients.setModel(patientModel);
	}

	/**
	 * Sets session label with current Users name.
	 * @param sessionModel the Session User
	 */
	public void setSession(Session sessionModel) {
		// set the text of the lblSession
		lblSession.setText("<html>" + sessionModel.getUser().getUserName() + " <i>(" + sessionModel.getUser().getType()+ ")</i></html>");
	}

	/**
	 * Shows an Error Dialog with a given message
	 * @param message The given message
	 */
	public void showError(String errorMessage) {
		// Show an Error message in a new JOptionPane
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
