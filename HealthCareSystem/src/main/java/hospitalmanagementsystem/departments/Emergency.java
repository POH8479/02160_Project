package hospitalmanagementsystem.departments;

import java.util.ArrayList;

import hospitalmanagementsystem.*;

public class Emergency extends Department {
	// static variable single_instance of type Emergency 
    private static Emergency single_instance = null; 
    ArrayList<Patient> patientList;
    int capacity;
    
    private Emergency() {
    	this.capacity = 100;
    	patientList = new ArrayList<Patient>();
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
}
