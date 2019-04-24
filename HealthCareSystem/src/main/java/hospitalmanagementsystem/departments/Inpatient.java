package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import hospitalmanagementsystem.*;

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
    int capacity;
    
    private Inpatient() {
    	this.capacity = 100;
    	patientList = new ArrayList<Patient>();
    	bedList = new ArrayList<Bed>();
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

}
