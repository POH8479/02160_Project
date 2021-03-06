package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.User;

/**
 * A Singleton Class Which represents the Outpatient Department at the Hospital.
 * @author Karoline Østergaard
 * @author Pieter O'Hearn
 */
public class Outpatient implements Department {
	// Static Variables 
    private static Outpatient single_instance = null; 
    
    // Instance Variables
    private ArrayList<User> userList;
    private ArrayList<Patient> patientList;
    
    /**
     * The Private Constructor for the Outpatient class.
     */
    private Outpatient() {
    	patientList = new ArrayList<Patient>();
    	userList = new ArrayList<User>();
    }
	
    /**
     * Returns the saved instance of the Outpatient Class or creates a one if one has not yet been created.
     * @return The Outpatient Instance
     */
    public static Outpatient getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Outpatient();
        }
  
        return single_instance; 
    }

    /**
	 * Adds a User to the Department.
	 * @param User The User to add
	 */
	public void addUser(User user) {
		userList.add(user);
	}

	/**
	 * Removes a user from the Department.
	 * @param User The user to remove
	 */
	public void removeUser(User user) {
		userList.remove(user);
	}

	/**
	 * Adds a Patient to the Department.
	 * @param patient The patient to add
	 */
	public void addPatient(Patient patient) {
		patientList.add(patient);
	}

	/**
	 * Removes a Patient to the Department.
	 * @param patient The patient to remove
	 */
	public void removePatient(Patient patient) {
		patientList.remove(patient);
	}
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Outpatient Department.
	 * @param bed The bed to add
	 * @throws UnsupportedOperationException
	 */
	public void addBed(Bed bed) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Outpatient Department.
	 * @param bed The bed to remove
	 * @throws UnsupportedOperationException
	 */
	public void removeBed(Bed bed) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	// GETTER METHODS
	public String getName() {
		return "Outpatient";
	}
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Outpatient Department.
	 * @throws UnsupportedOperationException
	 */
	public ArrayList<Bed> getBedList() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	public ArrayList<Patient> getPatientList() {
		return this.patientList;
	}
	
	public ArrayList<User> getUserList() {
		return this.userList;
	}

	// SETTER  METHODS
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Outpatient Department.
	 * @param beds The Bed List
	 * @throws UnsupportedOperationException
	 */
	public void setBedList(ArrayList<Bed> beds) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("The Outpatient Department has No Beds.");
	}

	public void setPatientList(ArrayList<Patient> patients) {
		this.patientList = patients;
	}

	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}
}