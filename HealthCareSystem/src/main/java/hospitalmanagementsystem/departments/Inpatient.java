package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

public class Inpatient extends Department {
	// static variable single_instance of type Inpatient 
    private static Inpatient single_instance = null; 

    private Inpatient() {
    	this.name = "Inpatient";
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
    public void addPatient(Patient patient) {
		patientList.add(patient);
	}
	
	public void removePatient(Patient patient) {
		patientList.remove(patient);
	
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	public void removeUser(User user) {
		userList.remove(user);
	}
	
	//Getters		
	public String getName() {
			return "Inpatient";
		}
		
	public ArrayList<Bed> getBedList(){
			return this.bedList;
		}
		
	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	public ArrayList<User> getUserList() {
		return this.userList;
	}
		
	//Setters
	public void setName(String Name) {
		this.name = Name;
	}
		
	public void setBedList(ArrayList<Bed> beds) {
		this.bedList = beds;
	}
		
	public void setPatientList(ArrayList<Patient> patients) {
		this.patientList = patients;
	}
		
	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}

}
