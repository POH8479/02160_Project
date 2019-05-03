package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

/**
 * 
 */
public class Outpatient implements Department {
	// Static Variables 
    private static Outpatient single_instance = null; 
    
    // Instance Variables
    private ArrayList<User> userList;
    private ArrayList<Patient> patientList;
    
    /**
     * 
     */
    private Outpatient() {
    	patientList = new ArrayList<Patient>();
    	userList = new ArrayList<User>();
    }
	
    /**
     * 
     * @return
     */
    public static Outpatient getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Outpatient();
        }
  
        return single_instance; 
    }

    /**
	 * 
	 * @param User
	 */
	public void addUser(User user) {
		userList.add(user);
	}

	/**
	 * 
	 * @param User
	 */
	public void removeUser(User user) {
		userList.remove(user);
	}

	/**
	 * 
	 * @param patient
	 */
	public void addPatient(Patient patient) {
		patientList.add(patient);
	}

	/**
	 * 
	 * @param patient
	 */
	public void removePatient(Patient patient) {
		patientList.remove(patient);
	}
	
	/**
	 * 
	 * @param bed
	 */
	public void addBed(Bed bed) {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	/**
	 * 
	 * @param bed
	 */
	public void removeBed(Bed bed) {
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	// GETTER METHODS
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return "Outpatient";
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Bed> getBedList(){
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<User> getUserList() {
		return this.userList;
	}

	// SETTER  METHODS
	
	/**
	 * 
	 * @param beds
	 */
	public void setBedList(ArrayList<Bed> beds) {
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	/**
	 * 
	 * @param patients
	 */
	public void setPatientList(ArrayList<Patient> patients) {
		this.patientList = patients;
	}

	/**
	 * 
	 * @param users
	 */
	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}
}
