package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

public class Management extends Department {
	private final ArrayList<Patient> emptyList = null;
	// static variable single_instance of type Management 
    private static Management single_instance = null; 
    ArrayList<User> userList;
    
    private Management() {
    	userList = new ArrayList<User>();
    }
	
    // static method to create instance of Management class 
    public static Management getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Management();
        }
  
        return single_instance; 
    }
    
    public String getName() {
		return "Management";
	}
    
	public ArrayList<Patient> getPatientList() {
		return this.emptyList;
	}
}
