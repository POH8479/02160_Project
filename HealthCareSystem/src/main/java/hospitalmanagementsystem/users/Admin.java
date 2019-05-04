package hospitalmanagementsystem.users;

import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.*;

/**
 *
 * @author Pieter O'Hearn
 */
public class Admin extends User implements HealthStaff{
	// STATIC VARIABLES
	private static PersistenceLayer persist = new PersistenceLayer();
	
	// INSTANCE VARIABLES
	private String department;

	// CONSTRUCTORS
	/**
	 * Creates a new Admin of the Hospital Management
	 */
	public Admin(String usersName, String phone) {
		// call the super
		super(usersName, phone, "A");

		// set the department to Management
		this.department = Management.getInstance().getName();
		Management.getInstance().addUser(this);

		// Save the new User
		persist.save(this, this.userID, this.department);
	}
	
	/**
	 * Empty Constructor for the Persistence Layer.
	 */
	public Admin() {}

	// METHODS
	public void moveDepartment(String department) {
		// throw an UnsupportedOperationException
		throw new UnsupportedOperationException("Admin Users Cannot Move Departments.");
	}

	// GETTER METHODS
	/**
	 * 
	 */
	@Override
	public String getDepartment() {
		return this.department;
	}

	/**
	 * 
	 */
	@Override
	public String getType() {
		return "Admin";
	}
	
	// SETTER METHODS
	/**
	 * 
	 */
	@Override
	public void setDepartment(String newDepartment) {
		this.department = newDepartment;
	}
}