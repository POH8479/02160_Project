package hospitalmanagementsystem.users;

import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.*;

/**
 * The Doctor Class represents an Doctor User who extends the HealthStaff Interface and 
 * has the second level of access in the System.
 * @author Jack Rodman
 */
public class Doctor extends User implements HealthStaff{
	// STATIC VARIABLES
	private static PersistenceLayer persist = new PersistenceLayer();
	
	// INSTANCE VARIABLES
	private String department;

	// CONSTRUCTORS
	/**
	 * Creates a new Doctor of the Hospital Management.
	 * @param usersName The new Users Name
	 * @param phone The new Users Phone Number
	 * @param department The new Users initial Department
	 */
	public Doctor(String usersName, String phone, String department) {
		super(usersName, phone, "D");
		
		//assign department based on input
		switch(department==null?"null":department) {
		  case "Emergency":
			  this.department = department;
			  Emergency.getInstance().addUser(this);
		    break;
		  case "Inpatient":
			  this.department = department;
			  Inpatient.getInstance().addUser(this);
		    break;
		  case "Outpatient":
			  this.department = department;
			  Outpatient.getInstance().addUser(this);
		    break;
		  case "null":
			  this.department = null;
		    break;
		  default:
		    throw new IllegalArgumentException(String.format("%s is an invalid department.",department));
		}

		// Save the new User
		persist.save(this, this.getUserID(), this.department);
	}

	/**
	 * Empty Constructor for the Persistence Layer.
	 */
	public Doctor() {}
	
	// METHODS
	/**
	 * Moves the HealthStaff User from their current department to a new one.
	 * @param department The new department
	 */
	public void moveDepartment(String department) {
		// update the department lists
		switch(department) {
			case "Emergency":
				Emergency.getInstance().getUserList().remove(this);
				Emergency.getInstance().getUserList().add(this);
			case "Outpatient":
				Outpatient.getInstance().getUserList().remove(this);
				Outpatient.getInstance().getUserList().add(this);
			case "Inpatient":
				Inpatient.getInstance().getUserList().remove(this);
				Inpatient.getInstance().getUserList().add(this);
		}
		
		// delete the xml user file from the current department
		persist.delete(this.userID, this.department);

		// change the department variable
		this.department = department;

		// Save the updated User
		persist.save(this, this.userID, this.department);
	}

	// GETTER METHODS
	@Override
	public String getType() {
		return "Doctor";
	}

	@Override
	public String getDepartment() {
		return this.department;
	}
	
	// SETTER METHODS
	@Override
	public void setDepartment(String newDepartment) {
		this.department = newDepartment;
	}
}