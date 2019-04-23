package hospitalmanagementsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.departments.Management;

/**
 * 
 * @author Jack Rodman
 *
 */

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
	Bed bed;

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
		this.bed = null;
	}

	/**
	 * Updates a patient's department to enable admitting or moving a patient to a new department
	 * @param department
	 * @throws IllegalAccessException
	 */
	public void updateDepartment(Department department) throws IllegalAccessException {
		// check if department is Management
		if( department instanceof Management) {
			throw new IllegalAccessException("Patients can not be assigned to the Managment Department");
		}
		//otherwise update department
		else {
			this.dept = department;
		}
	}

	/**
	 * Updates a patient's assigned bed in case the patient is admitted or moved.
	 * @param newbed
	 * @throws IllegalAccessException
	 */
	public void updateBed(Bed newbed) throws IllegalAccessException {
		//check if bed is empty
		if(newbed.getPatient() == null) {
			throw new IllegalAccessException("This bed is already occupied");
		}
		//otherwise update bed
		else {
			this.bed = newbed;
			//this.bed.addPatient(this); Is this step unnecessary?
		}
	}

	/**
	 * Retrieves a patients medical record if it exists
	 * @return
	 */
	public String getRecord() {
		//returns a notification of no entry if nothing has been recorded yet
		if(this.record == null) {
			return "Patient does not have record entry";
		}
		return this.record;
	}

	/**
	 * Updates a patients medical record
	 * @param data
	 */
	public void updateRecord(String data) {
		this.record = data;
	}

	/**
	 * This method creates a hashtable to be able to retrieve the patient info accessible by a user
	 * @return patientInfo hashtable
	 */
	public Hashtable<String, String> getPatientInfo() {
		//create hashtable for easier organisation of patient data
		Hashtable<String, String> patientInfo = new Hashtable<String, String>();
		
		//load all parameters into hashtable
		patientInfo.put("Name", this.name);
		patientInfo.put("Surname", this.surname);
		patientInfo.put("Patient ID", Integer.toString(this.patientID));
		//string formatting for patient birth date parameter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String bdayString = bday.format(formatter);
		patientInfo.put("Birth Date", bdayString);
		patientInfo.put("Address", this.address);
		patientInfo.put("Phone Number", this.phoneNo);
		//patientInfo.put("Deceased", "false");
		//patientInfo.put("Department", this.dept.name);
		patientInfo.put("Bed number", this.bed.getBedID());
		
		return patientInfo;
	}

	/**
	 * This method retreives the bed object that is currently occupied by the patient
	 * @return bed
	 */
	public Object getBed() {
		//if null is returned, patient has not yet been admitted to a bed
		return this.bed;
	}

	/**
	 * This method retrieves a patients unique ID number
	 * @return ID number
	 */
	public int getPatientId() {
		return this.patientID;
	}

	/**
	 * This method notifies the user if the patient is alive or dead
	 * @return true if a patient is deceased and false if a patient is alive
	 */
	public boolean getDeceased() {
		return this.deceased;
	}

	/**
	 * This method retrieves the department that a patient belongs to
	 * @return Department object if admitted or null if department has not yet been assigned
	 */
	public Object getDepartment() {
		return this.dept;
	}
}
