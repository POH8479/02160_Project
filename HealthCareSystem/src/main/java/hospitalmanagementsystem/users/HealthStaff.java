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
	 * Moves the HealthStaff User from their current department to a new one.
	 * @param department The new department
	 */
	public void moveDepartment(String department);
}
