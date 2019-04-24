package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

public abstract class Department {
  	String ID;
	String name;
	List<User> userList = new ArrayList<User>();

	protected void addUser(User user) {
		userList.add(user);
	}

	protected void removeUser(User user) {
		userList.remove(user);
	}

	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub	
	}

	public void removePatient(Patient patient) {
		// TODO Auto-generated method stub	
	}
	
	public Hashtable<String, String> getPatientList() {
		// TODO Auto-generated method stub
		return null;
	}
}
