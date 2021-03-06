package gui.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import gui.model.*;
import gui.view.ManagementView;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * The Controller for the management 
 * @author Pieter O'Hearn
 *
 */
public class ManagementController {
	// Static Variables
	private static PersistenceLayer persist = new PersistenceLayer();
	
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
		JTextField phone = new JTextField(5);
		
		// create a JComboBox to check what type of user to create
		String[] typesOfUsers = { "Admin", "Doctor", "Nurse", "User" };
		JComboBox<String> type = new JComboBox<String>(typesOfUsers);
		type.setSelectedIndex(3);

		// create a JComboBox to check what Department
		String[] departmentNames = { "Emergency", "Inpatient", "Outpatient", "Management" };
		JComboBox<String> departments = new JComboBox<String>(departmentNames);
		departments.setSelectedIndex(3);
		
		// create a new JPanel and add each of the above Fields along with their corresponding Labels 
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);
		userPanel.add(type);
		userPanel.add(departments);

		// Display the JPanel using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showConfirmDialog(null, userPanel, "Please Enter the Users information", JOptionPane.OK_CANCEL_OPTION);

        // check the confirmation result
        if (confirmation == JOptionPane.OK_OPTION) {
        	// if OK was selected, create a user variable u
        	User u = null;
        	
        	//check management input of user information
        	InputController inputcheck = new InputController();
        	String error = inputcheck.checkUserInput(name.getText(), phone.getText());
        	if (error != null) {
        		view.showError(error);
        		return;
        	}
        	
        	// check what Type of user to create 
        	switch(type.getSelectedIndex()) {
        		case 0: // if Admin was selected, create a new Admin User
        			if(departments.getSelectedIndex() != 3) {
        				view.showError(String.format("%s is an invalid department.",(String)departments.getSelectedItem()));
        				return;
        			}
    	    		u = new Admin(name.getText(), phone.getText());
    	    		break;
        		case 1: // if Doctor was selected, create a new Doctor User
        			try {
        				u = new Doctor(name.getText(), phone.getText(),(String)departments.getSelectedItem());
        			} catch(IllegalArgumentException e) {
        				view.showError(e.getMessage());
        				return;
        			}
    	    		break;
        		case 2: // if Nurse was selected, create a new Nurse User
        			try {
        				u = new Nurse(name.getText(), phone.getText(),(String)departments.getSelectedItem());
        			} catch (IllegalArgumentException e) {
        				view.showError(e.getMessage());
        				return;
        			}
    	    		break;
    			default: // else create a new generic User
    				if(departments.getSelectedIndex() != 3) {
        				view.showError(String.format("%s is an invalid department.",(String)departments.getSelectedItem()));
        				return;
        			}
    				u = new User(name.getText(), phone.getText());
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
	 * Removes a patient from the HMS
	 * @param selectedRow The row number of the selected Patient
	 */
	public void removePatient(int selectedRow) {
		// check a valid row has been selected
		if (selectedRow >= 0) {
			// Find the Patient Id of the selected patient and call the removePatient method from the Patient Model
			String patientID = (String) patientModel.getValueAt(selectedRow, 0);
			patientModel.removePatient(patientID);
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
        	
        	//check management input of patient information
        	InputController inputcheck = new InputController();
        	String error = inputcheck.checkPatientInput(firstName.getText(), lastName.getText(), bDay.getText(), address.getText(), phoneNo.getText());
        	if (error != null) {
        		view.showError(error);
        		return;
        	}
        	
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
			patientModel.dischargePatient(patientID);
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
		// close the Management view and call the login method for the next user 
		view.setVisible(false);
		applicationController.login();
	}
	
	/**
	 * Edits a Users Information. An Option pane is opened with the current information prefilled.
	 * @param selectedRow The row number of the selected User in the user table
	 */
	public void editUser(int selectedRow) {
		// Create a JTextField for each input
		JTextField name = new JTextField((String) userModel.getValueAt(selectedRow, 1),10);
		JTextField phone = new JTextField((String) userModel.getValueAt(selectedRow, 4),10);
		
		// Create two arrays of Strings containing the department names
		String healthDepartments[] = {"Emergency", "Inpatient", "Outpatient", "Management"};
		String managementDepartments[] = {"Management"};
		JComboBox<String> depList;
		
		// create a new JList with one of the departments array
		if(userModel.getValueAt(selectedRow, 2).equals("Management")) {
			depList = new JComboBox<String>(managementDepartments);
		} else {
			depList = new JComboBox<String>(healthDepartments);
			
			// set the initial selected Index to the current department
			switch ((String) userModel.getValueAt(selectedRow, 2)){
				case "Emergency":
					depList.setSelectedIndex(0);
				case"Inpatient":
					depList.setSelectedIndex(1);
				case "Outpatient":
					depList.setSelectedIndex(2);
				case"Management":
					depList.setSelectedIndex(3);
			}
		}

		// create a new JPanel and add each of the above JTextFields along with their corresponding Labels 
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);
		userPanel.add(depList);

		// Display the JList using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showConfirmDialog(null, userPanel, "Update the Users information", JOptionPane.OK_CANCEL_OPTION);

        // check the confirmation result
        if (confirmation == JOptionPane.OK_OPTION) {
        	//check HS input of patient information is in correct format
        	InputController inputcheck = new InputController();
        	String error = inputcheck.checkUserInput(name.getText(), phone.getText());
        	if (error != null) {
        		view.showError(error);
        		return;
        	}
        	
        	// if OK was selected pass the input to the userModel to update
        	userModel.edit((String) userModel.getValueAt(selectedRow, 0), name.getText(), phone.getText(), (String) depList.getSelectedItem());
        	
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
        	//check HS input of patient information is in correct format
        	InputController inputcheck = new InputController();
        	String error = inputcheck.checkPatientInput(firstName.getText(), lastName.getText(), dOB.getText(), address.getText(), phoneNo.getText());
        	if (error != null) {
        		view.showError(error);
        		return;
        	}
        	
        	// if OK was selected pass the input to the patientModel to update
        	patientModel.edit((String) patientModel.getValueAt(selectedRow,0), firstName.getText(), lastName.getText(), dOB.getText(), address.getText(), phoneNo.getText());
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

	/**
	 * Searches through all the registered patients and returns the ones that match the given criteria.
	 * @param id The Patients ID
	 * @param firstName The Patients First Name
	 * @param lastName The Patients Last Name
	 * @param department The Department the Patient is admitted to
	 */
	public void patientSearch(String id, String firstName, String lastName, String department) {
		// create a new ArrayList to store the search results
		ArrayList<Patient> foundPatients = new ArrayList<Patient>();
		
		// store all the patients in one ArrayList
		ArrayList<Patient> patientsToSearch = new ArrayList<Patient>();
		patientsToSearch.addAll(Emergency.getInstance().getPatientList());
		patientsToSearch.addAll(Inpatient.getInstance().getPatientList());
		patientsToSearch.addAll(Outpatient.getInstance().getPatientList());
		
		// check all the patients in the list
		for(Patient patient : patientsToSearch) {
			// check patient matches the given criteria
			if((firstName.isEmpty() || patient.getFirstName().equals(firstName)) &&
					(lastName.isEmpty() || patient.getLastName().equals(lastName)) &&
					(id.isEmpty() || patient.getPatientID().equals(id)) &&
					 (department.isEmpty() || patient.getDepartment().equals(department))) {
				// if it does add patient to the searchedPatients list
				foundPatients.add(patient);
			}
		}
		
		// create a new Patient Search model and update the Table model
		PatientSearchModel patientSearchModel = new PatientSearchModel(foundPatients);
		this.view.setTableModel(userModel, patientSearchModel);
	}

	/**
	 * Searches through all the Users and returns the ones that match the given criteria.
	 * @param id The Users ID
	 * @param name The Users First Name
	 * @param email The Users Email Address
	 * @param department The Department the User is admitted to
	 */
	public void userSearch(String id, String name, String email, String department) {
		// create a new ArrayList to store the search results
		ArrayList<User> foundUsers = new ArrayList<User>();
		
		// store all the users in one ArrayList
		ArrayList<User> usersToSearch = new ArrayList<User>();
		usersToSearch.addAll(Emergency.getInstance().getUserList());
		usersToSearch.addAll(Inpatient.getInstance().getUserList());
		usersToSearch.addAll(Outpatient.getInstance().getUserList());
		usersToSearch.addAll(Management.getInstance().getUserList());
		
		// check all the users in the list
		for(User user : usersToSearch) {
			// check user matches the given criteria
			if((name.isEmpty() || user.getUserName().equals(name)) &&
					(email.isEmpty() || user.getEmail().equals(email)) &&
					(id.isEmpty() || user.getUserID().equals(id)) &&
					 (department.isEmpty() || user.getDepartment().equals(department))) {
				// if it does add patient to the searchedPatients list
				foundUsers.add(user);
			}
		}
		// create a new User Search model and update the Table model
		UserSearchModel userSearchModel = new UserSearchModel(foundUsers);
		this.view.setTableModel(userSearchModel, patientModel);
	}
	
	/**
	 * Resets the Patient and User Model to the original.
	 */
	public void clearSearch() {
		// retrieve the original patient and user model and set it again
		this.view.setTableModel(userModel, patientModel);
	}

	/**
	 * 
	 * @param selectedRow
	 */
	public void assignBed(int selectedRow) {
		// find the user
		Patient toBed = patientModel.findPatient((String) patientModel.getValueAt(selectedRow, 0));
		
		// create a new bed in the users department
		Department dept;
		
		// find the patients department
		switch((toBed.getDepartment()==null) ? "":toBed.getDepartment()) {
			case "Emergency":
				dept = Emergency.getInstance();
				break;
			case "Inpatient":
				dept = Inpatient.getInstance();
				break;
			default:
				this.view.showError(String.format("The %s is Department has no beds.",(toBed.getDepartment()==null) ? "No Department":toBed.getDepartment()));
				return;
		}
		
		// try set assign the user to this new bed
		try {
			// find the first free bed
			for(Bed bed : dept.getBedList()) {
				// check if free
				if(bed.getPatient() == null) {
					// assign the patient to the bed
					bed.updatePatient(toBed);
					toBed.setBed(bed.getBedID());
					
					// notify the views that data changed and break
					patientModel.fireTableDataChanged();
					break;
				}
			}
			
			// if no free bed show error
			if(toBed.getBed() == null) {
				this.view.showError(String.format("No Free Beds in the %s department",dept.getName()));
			}
		} catch(IllegalArgumentException e) {
			this.view.showError(e.getMessage());
		}
		
		// save the patient
		persist.save(toBed,toBed.getPatientID(), toBed.getDepartment());
	}

	/**
	 * Shows a pop-up with the list of beds where the Admin can add, remove and move beds within departments
	 */
	public void editBeds() {
		// create a new JPanel
		JPanel bedPanel = new JPanel();
		
		// create a JList to show the beds
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(int i = 0 ; i < Emergency.getInstance().getBedList().size() ; i++) {
			listModel.addElement((Emergency.getInstance().getBedList().get(i).getBedID()));
		}
		JList<String> bedList = new JList<String>(listModel);
		bedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bedList.setSelectedIndex(0);
		
		// create a JCombaBox to show the departments
		String departments[] = {"Emergency", "Inpatient"};
		JComboBox<String> depList = new JComboBox<String>(departments);
		depList.setSelectedIndex(0);
		depList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update the depBeds list
				DefaultListModel<String> listModel = new DefaultListModel<String>();
				if(depList.getSelectedIndex() == 0) {
					for(int i = 0 ; i < Emergency.getInstance().getBedList().size() ; i++) {
						listModel.addElement(Emergency.getInstance().getBedList().get(i).getBedID());
					}
				} else if(depList.getSelectedIndex() == 1) {
					for(int i = 0 ; i < Inpatient.getInstance().getBedList().size() ; i++) {
						listModel.addElement(Inpatient.getInstance().getBedList().get(i).getBedID());
					}
				}
				bedList.setModel(listModel);
			}
		});
		
		//  Create a ScrollPane for the Bed Panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(50, 100));
		scrollPane.setViewportView(bedList);
		bedPanel.add(scrollPane);
		bedPanel.add(depList);
		
		// store the options in an Array
		String[] options = {"Add", "Remove", "Cancel"};
		
		// Display the JList using a JOptionPane and store the confirmation result
        int confirmation = JOptionPane.showOptionDialog(null, bedPanel, "Edit the Beds", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        // check the confirmation result
        if (confirmation == JOptionPane.YES_OPTION) {
        	// if Add was selected create a new bed
        	Bed newBed = new Bed((String) depList.getSelectedItem());
        	
        	// update the beds department bed list
        	switch((String) depList.getSelectedItem()) {
	        	case "Emergency":
	        		Emergency.getInstance().addBed(newBed);
	        		break;
	        	case "Inpatient":
	        		Inpatient.getInstance().addBed(newBed);
        	}
        } else if (confirmation == JOptionPane.NO_OPTION) {
        	// if Remove was selected
        	switch((String) depList.getSelectedItem()) {
	        	case "Emergency":
	        		for(Bed bed : Emergency.getInstance().getBedList()) {
	        			if(bed.getBedID().equals(bedList.getSelectedValue())) {
	        				Emergency.getInstance().removeBed(bed);
	        				break;
	        			}
	        		}
	        		break;
	        	case "Inpatient":
	        		for(Bed bed : Emergency.getInstance().getBedList()) {
	        			if(bed.getBedID().equals(bedList.getSelectedValue())) {
	        				Emergency.getInstance().removeBed(bed);
	        				break;
	        			}
	        		}
        	}
        }
	}
}