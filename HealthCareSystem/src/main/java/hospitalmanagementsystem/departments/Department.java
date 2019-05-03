package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

public interface Department {

	/**
	 * 
	 * @param User
	 */
	public void addUser(User User);

	/**
	 * 
	 * @param User
	 */
	public void removeUser(User User);

	/**
	 * 
	 * @param patient
	 */
	public void addPatient(Patient patient);

	/**
	 * 
	 * @param patient
	 */
	public void removePatient(Patient patient);
	
	/**
	 * 
	 * @param bed
	 */
	public void addBed(Bed bed);

	/**
	 * 
	 * @param bed
	 */
	public void removeBed(Bed bed);
	

	// GETTER METHODS
	
	/**
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Bed> getBedList();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Patient> getPatientList();
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<User> getUserList();
	
	// SETTER METHODS
	
	/**
	 * 
	 * @param beds
	 */
	public void setBedList(ArrayList<Bed> beds);
	
	/**
	 * 
	 * @param patients
	 */
	public void setPatientList(ArrayList<Patient> patients);
	
	/**
	 * 
	 * @param users
	 */
	public void setUserList(ArrayList<User> users);
}
