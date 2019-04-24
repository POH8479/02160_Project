package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

public abstract class Department {
	String ID;
	String name;
	ArrayList<User> userList = new ArrayList<User>();

	public void addUser(User User) {
		userList.add(User);
	}

	public void removeUser(User User) {
		userList.remove(User);
	}

	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	public void removePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract String getName();
	
	public abstract ArrayList<Patient> getPatientList();
	
}
