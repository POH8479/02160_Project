package hospitalmanagementsystem.users;

import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.Management;

/**
 * A User of the Hospital Management System. They have the lowest privileges of
 * all the users but can still see a Users basic information and register the
 * patient into the Hospital Management System.
 *
 * @author Pieter O'Hearn
 */
public class User {
	// STATIC VARIABLES
	private static PersistenceLayer persist = new PersistenceLayer();

	// INSTANCE VARIABLES
	protected String name;
	protected String userID;
	protected String email;
	protected String phoneNumber;
	private String department;

	// CONSTRUCTORS
	/**
	 * Creates a new User of the Hospital Management System
	 * @param usersName
	 * @param phone
	 */
	public User(String usersName,  String phone){
		// assign the User with a unique ID
		int idCounter = persist.loadCounter() + 1;
		this.userID = "U" + Integer.toString(idCounter);
		
		// assign the remaining information
		this.name = usersName;
		this.email = this.userID + "@kapjak.com";
		this.phoneNumber = phone;
		this.department = Management.getInstance().getName();
		Management.getInstance().addUser(this);

		// Save the new User
		persist.save(this, this.userID, this.department);
		persist.saveCounter(idCounter);
	}

	/**
	 * An Empty Constructor for the Persistence Layer
	 */
	public User() {}

	/**
	 * Creates a new non generic type ofUser to the Hospital Management System
	 * @param usersName
	 * @param phone
	 * @param classString
	 */
	public User(String usersName, String phone, String classString) {
		// assign the User with a unique ID
		int idCounter = persist.loadCounter() + 1;
		this.userID = classString + Integer.toString(idCounter);
		persist.saveCounter(idCounter);
		
		// assign the remaining information
		this.name = usersName;
		this.email = this.userID + "@kapjak.com";
		this.phoneNumber = phone;
		this.email = this.userID + "@kapjak.com";
	}

	// METHODS
	/**
	 * Edits the Users information(Name and phone number).
	 * @param newName The new name of the User
	 * @param newPhone The new phone number of the User
	 */
	public void editUser(String newName, String newPhone) {
		// set the users
		this.setUserName(newName);
		this.setNumber(newPhone);
		
		// Save the updated User
		persist.delete(this.userID, this.getDepartment());
		persist.save(this, this.userID, this.getDepartment());
	}

	// GETTER METHODS
	/**
	 * Returns the User Class, "User" in this case.
	 * @return String "User"
	 */
	public String getType() {
		return "User";
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return this.name;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserID() {
		return this.userID;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumber() {
		return this.phoneNumber;
	}

	/**
	 * 
	 * @return
	 */
	public String getDepartment() {
		return this.department;
	}

	// SETTER METHODS
	/**
	 * 
	 * @param newName
	 */
	public void setUserName(String newName) {
		this.name = newName;
	}

	/**
	 * 
	 * @param newID
	 */
	public void setUserID(String newID) {
		this.userID = newID;
	}

	/**
	 * 
	 * @param newEmail
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	/**
	 * 
	 * @param newPhone
	 */
	public void setNumber(String newPhone) {
		this.phoneNumber = newPhone;
	}

	/**
	 * 
	 * @param newDepartment
	 */
	public void setDepartment(String newDepartment) {
		this.department = newDepartment;
	}
}
