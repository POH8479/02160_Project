package hospitalmanagementsystem.users;

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
	String email;
	String phoneNumber;
	Department department;

	/**
	 * Creates a new User of the Hospital Management System
	 * @param usersName
	 * @param phone
	 */
	public User(String usersName,  String phone){
		// assign the User with a unique ID
		idCounter++;
		this.userID = "U" + Integer.toString(idCounter);
		// assign the remaining information
		this.name = usersName;
//		this.email = email; TODO
		this.phoneNumber = phone;
		this.department = Management.getInstance();
		Management.getInstance().getUserList().add(this);
	}

	/**
	 * Creates a new non generic type ofUser to the Hospital Management System
	 * @param usersName
	 * @param phone
	 * @param classString
	 */
	public User(String usersName, String phone, String classString) {
		// assign the User with a unique ID
		idCounter++;
		this.userID = classString + Integer.toString(idCounter);
		// assign the remaining information
		this.name = usersName;
//		this.email = email; TODO
		this.phoneNumber = phone;
	}

	/**
	 * This method registers a new Patient into the hospital management system
	 * and returns that Patient.
	 *
	 * @return The Patient Object that has been registered to the HMS
	 */
	public Patient registerPatient(String name, String surname, String bday, String address, String phoneNo) {
		// create a new Patient
		Patient newPatient = new Patient(name, surname, bday, address, phoneNo);

		// return the Patient
		return newPatient;
	}

	public Department getDepartment() {
		return this.department;
	}

	public String getUserID() {
		return this.userID;
	}

	public String getUserName() {
		return this.name;
	}

	public String getType() {
		return "User";
	}

	public String getAddress() {
		return this.email;
	}

	public String getNumber() {
		return this.phoneNumber;
	}
	
	public void setUserName(String newName) {
		this.name = newName;
	}
	
	public void setAddress(String newAddress) {
		this.email = newAddress;
	}
	
	public void setPhone(String newPhone) {
		this.phoneNumber = newPhone;
	}

}
