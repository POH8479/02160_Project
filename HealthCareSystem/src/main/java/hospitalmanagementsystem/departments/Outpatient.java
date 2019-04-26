package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

public class Outpatient extends Department {
	// static variable single_instance of type Outpatient 
    private static Outpatient single_instance = null; 
    ArrayList<Patient> patientList;
    ArrayList<User> userList;
    int capacity;
    
    private Outpatient() {
    	this.capacity = 100;
    	patientList = new ArrayList<Patient>();
    	userList = new ArrayList<User>();
    }
	
    // static method to create instance of Outpatient class 
    public static Outpatient getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Outpatient();
        }
  
        return single_instance; 
    }

	public void addPatient(Patient patient) {
		if(patient != null) {
			patientList.add(patient);
		}
	}

	public void removePatient(Patient patient) {
		patientList.remove(patient);

	}
	
	public String getName() {
		return "Outpatient";
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
