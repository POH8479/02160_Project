package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

/**
 * A Singleton Class Which represents the Management Department at the Hospital.
 * @author Karoline Østergaard
 * @author Pieter O'Hearn
 */
public class Management implements Department {
	// Static Variables
    private static Management single_instance = null; 
    
    // Instance Variables
    private ArrayList<User> userList;
    
    /**
     * The Private Constructor for the Management class.
     */
	private Management() {
		userList = new ArrayList<User>();
	}
	
    /**
     * Returns the saved instance of the Management Class or creates a one if one has not yet been created.
     * @return The Management Instance
     */
    public static Management getInstance() { 
        if (single_instance == null) {
            single_instance = new Management();
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
	 * Throws an Unsupported Operation Exception as there are no Patients in the Management Department.
	 * @param patient The patient to add
	 * @throws UnsupportedOperationException
	 */
	public void addPatient(Patient patient) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}

	/**
	 * Throws an Unsupported Operation Exception as there are no Patients in the Management Department.
	 * @param patient The patient to remove
	 * @throws UnsupportedOperationException
	 */
	public void removePatient(Patient patient) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Management Department.
	 * @param bed The bed to add
	 * @throws UnsupportedOperationException
	 */
	public void addBed(Bed bed) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}

	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Management Department.
	 * @param bed The bed to remove
	 * @throws UnsupportedOperationException
	 */
	public void removeBed(Bed bed) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}

	// GETTER METHODS
	public String getName() {
		return "Management";
	}
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Management Department.
	 * @throws UnsupportedOperationException
	 */
	public ArrayList<Bed> getBedList() throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Patients in the Management Department.
	 * @throws UnsupportedOperationException
	 */
	public ArrayList<Patient> getPatientList() throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}
	
	public ArrayList<User> getUserList() {
		return this.userList;
	}

	// SETTER  METHODS
	
	/**
	 * Throws an Unsupported Operation Exception as there are no Beds in the Management Department.
	 * @param beds The Bed List
	 * @throws UnsupportedOperationException
	 */
	public void setBedList(ArrayList<Bed> beds) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}

	/**
	 * Throws an Unsupported Operation Exception as there are no Patients in the Management Department.
	 * @param patients The Patient List
	 * @throws UnsupportedOperationException
	 */
	public void setPatientList(ArrayList<Patient> patients) throws UnsupportedOperationException {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}

	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}
}
