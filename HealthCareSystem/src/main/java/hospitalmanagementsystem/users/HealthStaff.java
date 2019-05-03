package hospitalmanagementsystem.users;

/**
 * A Health Staff is a User who is medically working with patients.
 * The methods allow HealthStaff to admit and discharge Patients, assign them a
 * Bed and to access and update their medical record.
 *
 * @author Pieter O'Hearn
 */
public interface HealthStaff {

	/**
	 * This method returns the department of the HealthStaff User
	 * @return a department object
	 */
	public String getDepartment();
	
	/**
	 * Moves the HealthStaff User to another department.
	 * @param department The name of the new Department
	 */
	public void moveDepartment(String department);
}
