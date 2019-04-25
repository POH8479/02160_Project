package hospitalmanagementsystem.users;

import java.time.LocalDate;
import java.util.Hashtable;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.departments.Management;

/**
 * A User of the Hospital Management System. They have the lowest privileges of
 * all the users but can still see a Users basic information and register the
 * patient into the Hospital Management System.
 *
 * @author Pieter O'Hearn
 */
public class User {
	// Static Variables
	public static int idCounter = 0;

	// Instance Variables
	String name;
	final String userID;
	String address;
	String phoneNumber;
	Department department;

	/**
	 * Creates a new User of the Hospital Management System
	 */
	public User(String usersName, String usersAddress, String phone){
		// assign the User with a unique ID
		idCounter++;
		this.userID = "U" + Integer.toString(idCounter);
		// assign the remaining information
		this.name = usersName;
		this.address = usersAddress;
		this.phoneNumber = phone;
		this.department = Management.getInstance();
		Management.getInstance().getUserList().add(this);
	}

	public User(String usersName, String usersAddress, String phone, String classString) {
		// assign the User with a unique ID
		idCounter++;
		this.userID = classString + Integer.toString(idCounter);
		// assign the remaining information
		this.name = usersName;
		this.address = usersAddress;
		this.phoneNumber = phone;
	}

	/**
	 * Returns the Users Info as a String in the format
	 *
	 * @return String
	 */
	public Hashtable<String,String> getUserInfo() {
		// create a Hash table and add the Users info
		Hashtable<String,String> info = new Hashtable<String,String>();
		info.put("Name", this.name);
		info.put("User ID", this.userID);
		info.put("Address", this.address);
		info.put("Phone Number", this.phoneNumber);
		

		// return info hash table
		return info;
	}

	/**
	 * This method registers a new Patient into the hospital management system
	 * and returns that Patient.
	 *
	 * @return The Patient Object that has been registered to the HMS
	 */
	public Patient registerPatient(String name, String surname, LocalDate bday, String address, String phoneNo) {
		// create a new Patient
		Patient newPatient = new Patient(name, surname, bday, address, phoneNo);

		// return the Patient
		return newPatient;
	}
	
	/**
	 * returns the patients basic data (Name, )
	 *
	 * @param patient The
	 * @return The patients basic data in String format
	 */
	public Hashtable<String,String> getPatientData(Patient patient) {
		// return the patients info
		return patient.getPatientInfo();
	}

	public Department getDepartment() {
		return this.department;
	}

}
