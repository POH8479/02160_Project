package hospitalmanagmentsystem.patient;

import java.time.LocalDate;

public class Patient {
	// Static Variables
	public static int idnum = 0;

	//INSTANCE VARIABLES
	String name;
	String surname;
	final int patientID;
	LocalDate bday;
	String address;
	String phoneNo;
	Boolean deceased;
	String record;
	Department dept;

	//CONSTRUCTOR
	public Patient(String name, String surname, LocalDate bday, String address, String phoneNo) {
		//patient info input by user
		this.name = name;
		this.surname = surname;
		idnum++;
		this.patientID = idnum;
		this.bday = bday;
		this.address = address;
		this.phoneNo = phoneNo;
		this.deceased = false;
		this.record = null;
		this.dept = null;
	}

	/** This method generates a unique ID for a registered patient
	 * @return int value for ID
	 */
	@Deprecated
	public int generateID() {
		int idnum = 0; //NEED TO CHANGE to implement global variable but not sure how
		return idnum++;

	}

	/**
	 * Description
	 *
	 * @param department
	 * @throws AccessDeniedException
	 */
	public void updateDepartment(Department department) {
		// check if department is Managment
		if( department instanceof Managment) {
			throw new AccessDeniedException("Patients can not be assigned to the Managment Department");
		}
	}
}
