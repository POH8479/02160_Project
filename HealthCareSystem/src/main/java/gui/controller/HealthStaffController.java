package gui.controller;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.model.PatientModel;
import gui.model.PatientSearchModel;
import gui.model.Session;
import gui.view.HealthStaffView;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.HealthStaff;

/**
 * The Controller for the HealthStaff Users view of the HMS GUI.
 * @author Pieter O'Hearn
 *
 */
public class HealthStaffController {
	// Instance variables
	private PatientModel patientModel;
	private Session sessionModel;
	private HealthStaffView view;
	private ApplicationController applicationController;

	/**
	 * The Constructor, initialises the instance variables patientModel, sessionModel and applicationController.
	 * @param session The current Session
	 * @param appController The applicationController
	 */
	public HealthStaffController(Session session, ApplicationController appController) {
		// initialise the instance variables
		this.patientModel = session.getPatientModel();
		this.sessionModel = session;
		this.applicationController = appController;
	}
	
	/**
	 * Displays the HealthStaff view Window.
	 */
	public void display() {
		// set the HealthStaffView to Visible
		view.setVisible(true);
	}

	/**
	 * Initialises the health staff view.
	 * @param view The Health Staff View
	 */
	public void setView(HealthStaffView view) {
		// store the provided view and set the table model and session
		this.view = view;
		this.view.setTableModel(patientModel);
		this.view.setSession(sessionModel);
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
        	
        	//check HS input of patient information is in correct format
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
	 * logs the user out of the HMS.
	 */
	public void logOut() {
		// close the HealthStaff view and call the login method for the next user
		view.setVisible(false);
		applicationController.login();
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
        	patientModel.edit((String) patientModel.getValueAt(selectedRow, 0), firstName.getText(), lastName.getText(), dOB.getText(), address.getText(), phoneNo.getText());
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
		this.view.setTableModel(patientSearchModel);
	}

	/**
	 * Resets the Patient Model to the original.
	 */
	public void clearSearch() {
		// retrieve the original patient model and set it again
		this.view.setTableModel(patientModel);
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
		PersistenceLayer persist = new PersistenceLayer();
		persist.save(toBed,toBed.getPatientID(), toBed.getDepartment());
	}
}