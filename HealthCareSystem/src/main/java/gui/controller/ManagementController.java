package gui.controller;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.model.PatientModel;
import gui.model.Session;
import gui.model.UserModel;
import gui.view.ManagementView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Outpatient;
import hospitalmanagementsystem.users.Admin;
import hospitalmanagementsystem.users.Doctor;
import hospitalmanagementsystem.users.HealthStaff;
import hospitalmanagementsystem.users.Nurse;
import hospitalmanagementsystem.users.User;

public class ManagementController {

	private UserModel userModel;
	private PatientModel patientModel;
	private Session sessionModel;
	private ManagementView view;
	private ApplicationController applicationController;

	public ManagementController(Session session, ApplicationController appController) {
		this.userModel = session.getUserModel();
		this.patientModel = session.getPatientModel();
		this.sessionModel = session;
		this.applicationController = appController;
	}
	public void display() {
		view.setVisible(true);
	}

	public void setView(ManagementView view) {
		this.view = view;
		this.view.setTableModel(userModel, patientModel);
		this.view.setSession(sessionModel);
	}

	public void addUser() {
		// capture the information
		JTextField name = new JTextField(5);
		JTextField address = new JTextField(10);
		JTextField phone = new JTextField(5);
		JTextField type = new JTextField(5);

		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Address:"));
		userPanel.add(address);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);
		userPanel.add(new JLabel("Type of User:"));
		userPanel.add(type);

        int confirmation = JOptionPane.showConfirmDialog(null, userPanel,
	               "Please Enter the Users information", JOptionPane.OK_CANCEL_OPTION);

        if (confirmation == JOptionPane.OK_OPTION) {
        	User u;
        	switch(type.getText()) {
        		case "Admin":
        			// create a new admin
    	    		u = new Admin(name.getText(), address.getText(), phone.getText());
    	    		break;
        		case "Doctor":
        			// create a new doctor
    	    		u = new Doctor(name.getText(), address.getText(), phone.getText(),null);
    	    		break;
        		case "Nurse":
        			// create a new nurse
    	    		u = new Nurse(name.getText(), address.getText(), phone.getText(),null);
    	    		break;
    			default:
				// create a new user
	    		u = new User(name.getText(), address.getText(), phone.getText());
        	}
        	userModel.addNewUser(u);
        }

	}
	public void removeUser(int selectedRow) {
		// remove the user from the user list
		if (selectedRow >= 0) {
			String userID = (String) userModel.getValueAt(selectedRow, 0);
			userModel.removeUser(userID,(Admin) sessionModel.getUser());
		}
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
	public void assignDepartment(int selectedRow) {
		String departments[] = {"Emergency", "Inpatient", "Outpatient"};
		
		JList<String> depList = new JList<String>(departments);
		depList.setSelectedIndex(0); 
		
		int confirmation = JOptionPane.showConfirmDialog(null, depList, "Select a department", JOptionPane.OK_CANCEL_OPTION);
		
		if (confirmation == JOptionPane.OK_OPTION) {
        	//
			String userID = (String) userModel.getValueAt(selectedRow, 0);
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
			
			userModel.updateDepartment(userID, dept);
		}
	}
	public void editUser(int selectedRow) {
		// capture the information
		JTextField name = new JTextField((String) userModel.getValueAt(selectedRow, 1),10);
		JTextField address = new JTextField((String) userModel.getValueAt(selectedRow, 3),20);
		JTextField phone = new JTextField((String) userModel.getValueAt(selectedRow, 4),10);

		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Address:"));
		userPanel.add(address);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);

        int confirmation = JOptionPane.showConfirmDialog(null, userPanel,
	               "Update the Users information", JOptionPane.OK_CANCEL_OPTION);

        if (confirmation == JOptionPane.OK_OPTION) {
        	userModel.edit((String) userModel.getValueAt(0, selectedRow), name.getText(), address.getText(), phone.getText());
        }
	}
	public void editPatient(int selectedRow) {
		JTextField firstName = new JTextField((String) userModel.getValueAt(selectedRow, 1),10);
		JTextField lastName = new JTextField((String) userModel.getValueAt(selectedRow, 2),10);
		JTextField address = new JTextField((String) userModel.getValueAt(selectedRow, 5),20);
		JTextField phoneNo = new JTextField((String) userModel.getValueAt(selectedRow, 4),10);
		JTextField dOB = new JTextField((String) userModel.getValueAt(selectedRow, 6),10);

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
