package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import java.util.List;

import hospitalmanagementsystem.*;

public class Outpatient extends Department {
	// static variable single_instance of type Outpatient 
    private static Outpatient single_instance = null; 
    ArrayList<Patient> patientList;
    int capacity;
    
    private Outpatient() {
    	this.capacity = 100;
    	patientList = new ArrayList<Patient>();
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
		patientList.add(patient);
	}

	public void removePatient(Patient patient) {
		patientList.remove(patient);

	}
}
