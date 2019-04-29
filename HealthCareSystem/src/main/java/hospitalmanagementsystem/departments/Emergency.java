package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

public class Emergency extends Department {
	// static variable single_instance of type Emergency 
    private static Emergency single_instance = null; 
    ArrayList<Patient> patientList;
    ArrayList<User> userList;
    int capacity;
    
    private Emergency() {
    	this.capacity = 100;
    	patientList = new ArrayList<Patient>();
    	userList = new ArrayList<User>();
    }
	
    // static method to create instance of Emergency class 
    public static Emergency getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Emergency();
        }
  
        return single_instance; 
    }
	
	public void addPatient(Patient Patient) {
		patientList.add(Patient);
	}
	
	public void removePatient(Patient Patient) {
		patientList.remove(Patient);
	
	}
	
	public String getName() {
		return "Emergency";
	}
	
	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	public ArrayList<User> getUserList() {
		return this.userList;
	}
	
	public void addUser(User User) {
		userList.add(User);
	}
}
