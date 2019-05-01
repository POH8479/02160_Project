package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

public class Management extends Department {
	// static variable single_instance of type Management 
    private static Management single_instance = null; 
    
    private Management() {
    	this.name = "Emergency";
    	patientList = new ArrayList<Patient>();
    	userList = new ArrayList<User>();
    	bedList = new ArrayList<Bed>();
    }
	
    // static method to create instance of Management class 
    public static Management getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Management();
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
			return "Emergency";
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
