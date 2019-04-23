package hospitalmanagementsystem.users;

import java.time.LocalDate;
import java.util.Hashtable;

import hospitalmanagementsystem.Patient;

/**
 * A User of the Hospital Management System. They have the lowest privileges of
 * all the users but can still see a Users basic information and register the
 * patient into the Hospital Management System.
 *
 * @author Pieter O'Hearn
 */
public class User {
	// Static Variables
	public static int IDCounter = 0;

	// Instance Variables
	String name;
	final int userID;
	String address;
	String phoneNumber;

	/**
	 * Creates a new User of the Hospital Management System
	 */
	public User(String usersName, String usersAddress, String phone){
		// assign the User with a unique ID
		IDCounter++;
		this.userID = IDCounter;
		// assign the remaining information
		this.name = usersName;
		this.address = usersAddress;
		this.phoneNumber = phone;
	}

// TODO Should This return a Dictionary for easy lookup????
	/**
	 * Returns the Users Info as a String in the format
	 *
	 * @return String
	 */
	public Hashtable<String,String> getUserInfo() {
		// create a Hash table and add the Users info
		Hashtable<String,String> info = new Hashtable<String,String>();
		info.put("Name", this.name);
		info.put("User ID", Integer.toString(this.userID));
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
	// TODO Finish this method
	/**
	 * returns the patients basic data (Name, )
	 *
	 * @param patient The
	 * @return The patients basic data in String format
	 */
	public String getPatientData(Patient patient) {
		// create a String Builder
		StringBuilder sb = new StringBuilder();

		// append the Name
		sb.append("Name: ");
		sb.append(name);
		// append the ID
		sb.append("\nUser ID: ");
		sb.append(userID);
		// append the Address
		sb.append("\nAddress: ");
		sb.append(address);
		// append the phoneNumber
		sb.append("\nPhone Number: ");
		sb.append(phoneNumber);

		// convert sb to String and return
		return sb.toString();
	}
}
