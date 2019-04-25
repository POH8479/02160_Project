package gui.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.model.PatientModel;
import gui.model.Session;
import gui.model.UserModel;
import gui.view.ManagementView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.User;

public class ManagementController {

	private UserModel userModel;
	private PatientModel patientModel;
	private Session sessionModel;
	private ManagementView view;

	public ManagementController(UserModel userM, PatientModel patientM, Session session) {
		this.userModel = userM;
		this.patientModel = patientM;
		this.sessionModel = session;
	}
	public void display() {
		view.setVisible(true);
	}

	public void setView(ManagementView view) {
		this.view = view;
		this.view.setTableModel(userModel);
		this.view.setSession(sessionModel);
	}

	public void addUser() {
		// capture the information
		JTextField name = new JTextField(7);
		JTextField address = new JTextField(14);
		JTextField phone = new JTextField(7);

		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name:"));
		userPanel.add(name);
		userPanel.add(new JLabel("Address:"));
		userPanel.add(address);
		userPanel.add(new JLabel("Phone Number:"));
		userPanel.add(phone);

        int confirmation = JOptionPane.showConfirmDialog(null, userPanel,
	               "Please Enter the Users information", JOptionPane.OK_CANCEL_OPTION);

        if (confirmation == JOptionPane.OK_OPTION) {
        	// create a new user
    		User u = new User(name.getText(), address.getText(), phone.getText());
        	userModel.addNewUser(u);
        }

	}
	public void removeUser() {
		// TODO Auto-generated method stub
		
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
	
	public void removePatient() {
		// TODO Auto-generated method stub
		
	}
}
