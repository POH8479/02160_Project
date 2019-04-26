package gui.controller;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.model.PatientModel;
import gui.model.Session;
import gui.view.UserView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Outpatient;
import hospitalmanagementsystem.users.HealthStaff;

public class UserController {

	private PatientModel patientModel;
	private Session sessionModel;
	private UserView view;
	private ApplicationController applicationController;

	public UserController(Session session, ApplicationController appController) {
		this.patientModel = session.getPatientModel();
		this.sessionModel = session;
		this.applicationController = appController;
	}
	
	public void display() {
		view.setVisible(true);
	}

	public void setView(UserView view) {
		this.view = view;
		this.view.setTableModel(patientModel);
		this.view.setSession(sessionModel);
	}
	
	public void addPatient() {
		// capture the information
		JTextField firstName = new JTextField(5);
		JTextField lastName = new JTextField(5);
		JTextField bDay = new JTextField(5);
		JTextField address = new JTextField(5);
		JTextField phoneNo = new JTextField(5);
		
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
		

        int confirmation = JOptionPane.showConfirmDialog(null, patientPanel, "Please Enter the Patients information", JOptionPane.OK_CANCEL_OPTION);

        if (confirmation == JOptionPane.OK_OPTION) {
        	// create a new user
        	Patient p = new Patient(firstName.getText(), lastName.getText(), bDay.getText(), address.getText(), phoneNo.getText());
        	patientModel.addNewPatient(p);
        }
	}
	
	public void dischargePatient(int selectedRow) {
		// get the patients id and send to the patient model
		if (selectedRow >= 0) {
			String patientID = (String) patientModel.getValueAt(selectedRow, 0);
			patientModel.dischargePatient(patientID,(HealthStaff) sessionModel.getUser());
		}
		
	}
	
	public void admitPatient(int selectedRow) {
		String departments[] = {"Emergency", "Inpatient", "Outpatient"};
		
		JList<String> depList = new JList<String>(departments);
		depList.setSelectedIndex(0); 
		
		int confirmation = JOptionPane.showConfirmDialog(null, depList, "Select a department", JOptionPane.OK_CANCEL_OPTION);
		
		if (confirmation == JOptionPane.OK_OPTION) {
        	//
			String patientID = (String) patientModel.getValueAt(selectedRow, 0);
			Department dept;
			
			// find the department
			switch (depList.getSelectedValue()) {
				case "Emergency":
					dept = Emergency.getInstance();
					break;
				case "Inpatient":
					dept = Inpatient.getInstance();
					break;
				default:
					dept = Outpatient.getInstance();
			}
			try {
				patientModel.admitPatient(patientID, (HealthStaff) sessionModel.getUser(), dept);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
	}
	
	public void logOut() {
		// 
		view.setVisible(false);
		applicationController.login();
		
	}
	
	public void editPatient(int selectedRow) {
		JTextField firstName = new JTextField((String) patientModel.getValueAt(selectedRow, 1),10);
		JTextField lastName = new JTextField((String) patientModel.getValueAt(selectedRow, 2),10);
		JTextField address = new JTextField((String) patientModel.getValueAt(selectedRow, 5),20);
		JTextField phoneNo = new JTextField((String) patientModel.getValueAt(selectedRow, 4),10);
		JTextField dOB = new JTextField((String) patientModel.getValueAt(selectedRow, 6),10);

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

        int confirmation = JOptionPane.showConfirmDialog(null, patientPanel,
	               "Update the Users information", JOptionPane.OK_CANCEL_OPTION);

        if (confirmation == JOptionPane.OK_OPTION) {
        	patientModel.edit((String) patientModel.getValueAt(0, selectedRow), firstName.getText(), lastName.getText(), dOB.getText(), address.getText(), phoneNo.getText());
        }
	}
	
	public void showRecord(int selectedRow) {
		// 
		Patient p = patientModel.findPatient((String) patientModel.getValueAt(selectedRow, 0)); 
		
		this.view.setVisible(false);
		this.applicationController.record(this.sessionModel, p);
	}

}
