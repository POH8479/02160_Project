package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.*;

/**
 * This Interface represents a Department in the Hospital.
 * @author Pieter O'Hearn
 */
public interface Department {

	/**
	 * Adds a User to the Department.
	 * @param User The user to add
	 */
	public void addUser(User User);

	/**
	 * Removes a user from the Department.
	 * @param User The user to remove
	 */
	public void removeUser(User User);

	/**
	 * Adds a Patient to the Department.
	 * @param patient The patient to add
	 */
	public void addPatient(Patient patient);

	/**
	 * Removes a Patient to the Department.
	 * @param patient The patient to remove
	 */
	public void removePatient(Patient patient);
	
	/**
	 * Adds a Bed to the Department.
	 * @param bed The bed to add
	 */
	public void addBed(Bed bed);

	/**
	 * Removes a Bed to the Department.
	 * @param bed The bed to remove
	 */
	public void removeBed(Bed bed);
	

	// GETTER METHODS
	public String getName();
	
	public ArrayList<Bed> getBedList();
	
	public ArrayList<Patient> getPatientList();
	
	public ArrayList<User> getUserList();
	
	// SETTER METHODS
	public void setBedList(ArrayList<Bed> beds);
	
	public void setPatientList(ArrayList<Patient> patients);
	
	public void setUserList(ArrayList<User> users);
}