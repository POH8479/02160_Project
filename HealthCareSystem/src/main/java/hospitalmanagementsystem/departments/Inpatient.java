package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

/**
 * 
 * @author Kun
 *
 */

public class Inpatient extends Department {
	// static variable single_instance of type Inpatient 
    private static Inpatient single_instance = null; 
    ArrayList<Patient> patientList;
    ArrayList<Bed> bedList;
    ArrayList<User> userList;
    int capacity;
    
    private Inpatient() {
    	this.capacity = 100;
    	patientList = new ArrayList<Patient>();
    	bedList = new ArrayList<Bed>();
    	userList = new ArrayList<User>();
    }
	
    // static method to create instance of Inpatient class 
    public static Inpatient getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Inpatient();
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
		return "Inpatient";
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
