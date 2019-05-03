package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

/**
 * 
 */
public class Management implements Department {
	// Static Variables
    private static Management single_instance = null; 
    
    // Instance Variables
    private ArrayList<User> userList;
    
    /**
     * 
     */
	private Management() {
		userList = new ArrayList<User>();
	}
	
    /**
     * 
     * @return
     */
    public static Management getInstance() { 
        if (single_instance == null) {
            single_instance = new Management();
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
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}

	/**
	 * 
	 * @param patient
	 */
	public void removePatient(Patient patient) {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}
	
	/**
	 * 
	 * @param bed
	 */
	public void addBed(Bed bed) {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}

	/**
	 * 
	 * @param bed
	 */
	public void removeBed(Bed bed) {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}

	// GETTER METHODS
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return "Management";
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Bed> getBedList(){
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Patient> getPatientList() {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
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
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Beds.");
	}

	/**
	 * 
	 * @param patients
	 */
	public void setPatientList(ArrayList<Patient> patients) {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("The Management Department has No Patients.");
	}

	/**
	 * 
	 * @param users
	 */
	public void setUserList(ArrayList<User> users) {
		this.userList = users;
	}
}
