package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

/**
 * 
 * @author Kun
 *
 */

public abstract class Department {
	String ID;
	String name;
	ArrayList<Bed> bedList;
	ArrayList<User> userList;
	ArrayList<Patient> patientList;

	public abstract void addUser(User User);

	public void removeUser(User User) {
		userList.remove(User);
	}

	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	public abstract void removePatient(Patient patient);
	
	public abstract String getName();
	
	public abstract ArrayList<Patient> getPatientList();
	
	public abstract ArrayList<User> getUserList();
	
}
