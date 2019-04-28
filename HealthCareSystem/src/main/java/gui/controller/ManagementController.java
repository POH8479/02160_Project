package gui.controller;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.model.*;
import gui.view.ManagementView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * The Controller for the management 
 * @author Pieter O'Hearn
 *
 */
public class ManagementController {
	// Instance variables
	private UserModel userModel;
	private PatientModel patientModel;
	private Session sessionModel;
	private ManagementView view;
	private ApplicationController applicationController;

	/**
	 * The Constructor for the Management Controller. Sets the instance variables 
	 * @param session The current session
	 * @param appController The main Application Controller
	 */
	public ManagementController(Session session, ApplicationController appController) {
		// set the instance variables
		this.userModel = session.getUserModel();
		this.patientModel = session.getPatientModel();
		this.sessionModel = session;
		this.applicationController = appController;
	}
	
	/**
	 * Displays the Management view.
	 */
	public void display() {
		// set the visibility of the management view to true
		view.setVisible(true);
	}

	/**
	 * Sets the management view and TableModel and Session of the view.  
	 * @param view
	 */
	public void setView(ManagementView view) {
		// save the ManagementView as the instance variable view and set the Table model and session 
		this.view = view;
		this.view.setTableModel(userModel, patientModel);
		this.view.setSession(sessionModel);
	}

	/**
	 * Adds a User to the HMS. An Option pane is opened where the new users information can be inputed.
	 */
	public void addUser() {
		// Create a JTextField for each input
		JTextField name = new JTextField(5);
		JTextField address = new JTextField(10);
		JTextField phone = new JTextField(5);
		
		// create a JComboBox to check what type of user to create
		String[] typesOfUsers = { "Admin", "Doctor", "Nurse", "User" };
		JComboBox<String> type = new JComboBox<String>(typesOfUsers);
		type.setSelectedIndex(3);

		// create a new JPanel and add each of the above Fields along with their corresponding Labels 
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Address:"));
		userPanel.add(address);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);
		userPanel.add(type);

		// Display the JPanel using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showConfirmDialog(null, userPanel, "Please Enter the Users information", JOptionPane.OK_CANCEL_OPTION);

        // check the confirmation result
        if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected, create a user variable u
        	User u;
        	
        	// check what Type of user to create 
        	switch(type.getSelectedIndex()) {
        		case 0: // if Admin was selected, create a new Admin User
    	    		u = new Admin(name.getText(), address.getText(), phone.getText());
    	    		break;
        		case 1: // if Doctor was selected, create a new Doctor User
    	    		u = new Doctor(name.getText(), address.getText(), phone.getText(),null);
    	    		break;
        		case 2: // if Nurse was selected, create a new Nurse User
    	    		u = new Nurse(name.getText(), address.getText(), phone.getText(),null);
    	    		break;
    			default: // else create a new generic User
	    		u = new User(name.getText(), address.getText(), phone.getText());
        	}
        	
        	// add the new user to the User Model
        	userModel.addNewUser(u);
        }
	}
	
	/**
	 * Removes a user from the HMS
	 * @param selectedRow The row number of the selected User
	 */
	public void removeUser(int selectedRow) {
		// check a valid row has been selected
		if (selectedRow >= 0) {
			// Find the User Id of the selected user and call the removeUser method from the User Model
			String userID = (String) userModel.getValueAt(selectedRow, 0);
			userModel.removeUser(userID,(Admin) sessionModel.getUser());
		}
	}
	
	/**
	 * Adds a new Patient to the HMS. An Option pane is opened where the new Patients information can be inputed.
	 */
	public void addPatient() {
		// Create a JTextField for each input
		JTextField firstName = new JTextField(5);
		JTextField lastName = new JTextField(5);
		JTextField bDay = new JTextField(5);
		JTextField address = new JTextField(5);
		JTextField phoneNo = new JTextField(5);
		
		// create a new JPanel and add each of the above JTextFields along with their corresponding Labels 
		JPanel patientPanel = new JPanel();
		patientPanel.add(new JLabel("First Name:"));
		patientPanel.add(firstName);
		patientPanel.add(new JLabel("Last Name:"));
		patientPanel.add(lastName);
		patientPanel.add(new JLabel("Date of Birth:"));
		patientPanel.add(bDay);
		patientPanel.add(new JLabel("Address:"));
		patientPanel.add(address);
		patientPanel.add(new JLabel("Phone Number:"));
		patientPanel.add(phoneNo);
		
		// Display the JPanel using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showConfirmDialog(null, patientPanel, "Please Enter the Patients information", JOptionPane.OK_CANCEL_OPTION);

        // check the confirmation result
        if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected, create a new patient with the provided information and add to the patient Model
        	Patient p = new Patient(firstName.getText(), lastName.getText(), bDay.getText(), address.getText(), phoneNo.getText());
        	patientModel.addNewPatient(p);
        }
	}
	
	/**
	 * Discharges a patient from their department
	 * @param selectedRow The row number of the selected Patient
	 */
	public void dischargePatient(int selectedRow) {
		// check a valid row has been selected
		if (selectedRow >= 0) {
			// if so get the patients id and call the dischargePatient method form the patient model
			String patientID = (String) patientModel.getValueAt(selectedRow, 0);
			patientModel.dischargePatient(patientID,(HealthStaff) sessionModel.getUser());
		}
	}
	
	/**
	 * Admits a Patient to a department
	 * @param selectedRow The row number of the selected Patient
	 */
	public void admitPatient(int selectedRow) {
		// Create an array of Strings containing the department names
		String departments[] = {"Emergency", "Inpatient", "Outpatient"};
		
		// create a new JList with the departments array and set the initial selected Index to 0
		JList<String> depList = new JList<String>(departments);
		depList.setSelectedIndex(0); 
		
		// Display the JList using a JOptionPane and store the confirmation result
		int confirmation = JOptionPane.showConfirmDialog(null, depList, "Select a department", JOptionPane.OK_CANCEL_OPTION);
		
		// check the confirmation result
		if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected retrieve the selected patients ID and create a Department variable
			String patientID = (String) patientModel.getValueAt(selectedRow, 0);
			Department dept;
			
			// check the selected value of the department list
			switch (depList.getSelectedValue()) {
				// if Emergency is selected set the department variable to the Emergency Instance
				case "Emergency":
					dept = Emergency.getInstance();
					break;
				// if Inpatient is selected set the department variable to the Inpatient Instance
				case "Inpatient":
					dept = Inpatient.getInstance();
					break;
				// Otherwise Outpatient must be selected so set the department variable to the Outpatient Instance
				default:
					dept = Outpatient.getInstance();
			}
			
			// try and admit the patient to this department
			try {
				patientModel.admitPatient(patientID, (HealthStaff) sessionModel.getUser(), dept);
			} catch (IllegalAccessException e) {
				// Show error dialog "Can not admit a patient to the Management department."
				view.showError(e.getMessage());
			} catch (IllegalArgumentException e) {
				// Show error dialog "Can not admit a patient who is already admitted to a department."
				view.showError(e.getMessage());
			}
        }
	}
	
	/**
	 * logs the user out of the HMS.
	 */
	public void logOut() {
		// close the HealthStaff view and call the login method for the next user 
		view.setVisible(false);
		applicationController.login();
		
	}
	
	/**
	 * Assigns a department to the selected User.
	 * @param selectedRow The row number of the selected User
	 */
	public void assignDepartment(int selectedRow) {
		// Create an array of Strings containing the department names
		String departments[] = {"Emergency", "Inpatient", "Outpatient"};
		
		// create a new JList with the departments array and set the initial selected Index to 0
		JList<String> depList = new JList<String>(departments);
		depList.setSelectedIndex(0); 
		
		// Display the JList using a JOptionPane and store the confirmation result
		int confirmation = JOptionPane.showConfirmDialog(null, depList, "Select a department", JOptionPane.OK_CANCEL_OPTION);
		
		// check the confirmation result
		if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected retrieve the selected Users ID and create a Department variable
			String userID = (String) userModel.getValueAt(selectedRow, 0);
			Department dept;
			
			// check the selected value of the department list
			switch (depList.getSelectedValue()) {
				// if Emergency is selected set the department variable to the Emergency Instance
				case "Emergency":
					dept = Emergency.getInstance();
					break;
				// if Inpatient is selected set the department variable to the Inpatient Instance
				case "Inpatient":
					dept = Inpatient.getInstance();
					break;
				// Otherwise Outpatient must be selected so set the department variable to the Outpatient Instance
				default:
					dept = Outpatient.getInstance();
			}
			
			// admit the user to the department
			userModel.updateDepartment(userID, dept);
		}
	}
	
	/**
	 * Edits a Users Information. An Option pane is opened with the current information prefilled.
	 * @param selectedRow The row number of the selected User in the user table
	 */
	public void editUser(int selectedRow) {
		// Create a JTextField for each input
		JTextField name = new JTextField((String) userModel.getValueAt(selectedRow, 1),10);
		JTextField address = new JTextField((String) userModel.getValueAt(selectedRow, 3),20);
		JTextField phone = new JTextField((String) userModel.getValueAt(selectedRow, 4),10);

		// create a new JPanel and add each of the above JTextFields along with their corresponding Labels 
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Address:"));
		userPanel.add(address);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);

		// Display the JList using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showConfirmDialog(null, userPanel, "Update the Users information", JOptionPane.OK_CANCEL_OPTION);

     // check the confirmation result
        if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected pass the input to the userModel to update
        	userModel.edit((String) userModel.getValueAt(0, selectedRow), name.getText(), address.getText(), phone.getText());
        	
        	// update the Session label
        	view.setSession(this.sessionModel);
        }
	}
	
	/**
	 * Edits a patients information, opening an option pane with the current information prefilled.
	 * @param selectedRow The row number of the selected patient in the patient table
	 */
	public void editPatient(int selectedRow) {
		// Create a JTextField for each input
		JTextField firstName = new JTextField((String) patientModel.getValueAt(selectedRow, 1),10);
		JTextField lastName = new JTextField((String) patientModel.getValueAt(selectedRow, 2),10);
		JTextField address = new JTextField((String) patientModel.getValueAt(selectedRow, 5),20);
		JTextField phoneNo = new JTextField((String) patientModel.getValueAt(selectedRow, 4),10);
		JTextField dOB = new JTextField((String) patientModel.getValueAt(selectedRow, 6),10);

		// create a new JPanel and add each of the above JTextFields along with their corresponding Labels 
		JPanel patientPanel = new JPanel();
		patientPanel.add(new JLabel("First Name:"));
		patientPanel.add(firstName);
		patientPanel.add(new JLabel("Last Name:"));
		patientPanel.add(lastName);
		patientPanel.add(new JLabel("Date of Birth:"));
		patientPanel.add(dOB);
		patientPanel.add(new JLabel("Address:"));
		patientPanel.add(address);
		patientPanel.add(new JLabel("Phone Number:"));
		patientPanel.add(phoneNo);

		// Display the JList using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showConfirmDialog(null, patientPanel, "Update the Users information", JOptionPane.OK_CANCEL_OPTION);

     // check the confirmation result
        if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected pass the input to the patientModel to update
        	patientModel.edit((String) patientModel.getValueAt(0, selectedRow), firstName.getText(), lastName.getText(), dOB.getText(), address.getText(), phoneNo.getText());
        }
	}
	
	/**
	 * Opens the Patient Record Window by calling the RecordController.
	 * @param selectedRow The row number of the selected patient in the patient table
	 */
	public void showRecord(int selectedRow) {
		// find the patient and save as a local variable
		Patient p = patientModel.findPatient((String) patientModel.getValueAt(selectedRow, 0)); 
		
		// close the HealthStaff view and start the patient record controller 
		this.view.setVisible(false);
		this.applicationController.record(this.sessionModel, p);
	}
}