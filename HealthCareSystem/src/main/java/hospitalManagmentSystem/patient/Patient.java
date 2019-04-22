package hospitalManagmentSystem.patient;

import java.time.LocalDate;

public class Patient {

	//INSTANCE VARIABLES
	String name;
	String surname;
	final int ID;
	LocalDate bday;
	String address;
	String phoneNo;
	Boolean deceased;
	String record;
	//Department dept;

	//CONSTRUCTOR
	public Patient(String name, String surname, LocalDate bday, String address, String phoneNo) {
		//patient info input by user
		this.name = name;
		this.surname = surname;
		this.ID = generateID();
		this.bday = bday;
		this.address = address;
		this.phoneNo = phoneNo;
		this.deceased = false;
		this.record = null;
		//this.dept = null;
	}

	/* This method generates a unique ID for a registered patient
	 * @return int value for ID
	 */
	public int generateID() {
		int idnum = 0; //NEED TO CHANGE to implement global variable but not sure how
		return idnum++;

	}

}
