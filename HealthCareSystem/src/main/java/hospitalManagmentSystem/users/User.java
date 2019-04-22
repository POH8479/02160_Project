package hospitalmanagmentsystem.users;

/**
 * A User of the Hospital Managment System. They have the lowest privileges of
 * all the users but can still see a Users basic information and register the
 * patient into the Hospital Managment System.
 *
 * @author Pieter O'Hearn
 */
public class User {
	// Static Variables
	static int IDCounter = 0;

	// Instance Variables
	String name;
	int final id;
	String address;
	int phoneNumber;

	/*
	 * Creates a new User of the Hospital Managment System
	 */
	public User(String usersName, String usersAddress, int phone){
		// assign the User with a unique ID
		IDCounter++;
		this.id = IDCounter;
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
	public String getUserInfo() {
		// create a String Builder
		StringBuilder sb = new StringBuilder();

		// append the Name
		sb.append("Name: ");
		sb.append(Name);
		// append the ID
		sb.append("\nID: ");
		sb.append(id);
		// append the Address
		sb.append("\nAddress: ");
		sb.append(Address);
		// append the phoneNumber
		sb.append("\nPhone Number: ");
		sb.append(phoneNumber);

		// convert sb to Strng and return
		return sb.toString()
	}

	/**
	 * This method registers a new Patient into the hospital managment system
	 * and returns that Patient.
	 *
	 * @return The Patient Object that has been registered to the HMS
	 */
	public Patient registerPatient(String patientName, String patientAddress, int patientPhone) {
		// create a new Patient
		Patient newPatient = new Patient(patientName, patientAddress, patientPhone);

		// return the Patient
		return newPatient;
	}
}
