package hospitalmanagementsystem;

import java.util.Hashtable;
import java.util.Objects;

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
	final String patientID;
	String bday;
	String address;
	String phoneNo;
	Boolean deceased;
	String record;
	Department dept;
	Bed bed;

	//CONSTRUCTOR
	public Patient(String name, String surname, String bday, String address, String phoneNo) {
		//patient info input by user
		this.name = name;
		this.surname = surname;
		idnum++;
		this.patientID = "P" + Integer.toString(idnum);;
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
	 * @throws IllegalArgumentException
	 */
	public void updateDepartment(Department department) throws IllegalArgumentException {
		// check if department is Management
		if( department instanceof Management) {
			throw new IllegalArgumentException("Patients can not be assigned to the Managment Department");
		} else { //otherwise update department
			this.dept = department;
		}
	}
  
	/**
	 * Updates a patient's assigned bed in case the patient is admitted or moved.
	 * @param newbed
	 * @throws IllegalArgumentException
	 */
	public void updateBed(Bed newbed) throws IllegalArgumentException {
		//check if bed is empty
		if(newbed.getPatient() != null) {
			throw new IllegalArgumentException(String.format("Bed %s is already occupied", newbed.getBedID()));
		}
		//check if bed is not in same department as patient
		else if(!Objects.equals(newbed.getDepartment(),this.dept)) {
			throw new IllegalArgumentException(String.format("Bed %s is in a different department to %s", newbed.getBedID(), this.name));
		}
		//otherwise update bed
		else {
			this.bed = newbed;
		}
  }

	/**
	 * Retrieves a patients medical record if it exists
	 * @return
	 */
	public String getRecord() {
		return this.record;
	}

	/**
	 * Updates a patients medical record
	 * @param data
	 */
	public void updateRecord(String data) {
		if(this.record != null) {
			this.record = this.record + "\n" + data;
		} else {
			this.record = data;
		}
		
	}

	/**
	 * This method creates a hashtable to be able to retrieve the patient info accessible by a user
	 * @return patientInfo hashtable
	 */
	public Hashtable<String, String> getPatientInfo() {
		//create hashtable for easier organisation of patient data
		Hashtable<String, String> patientInfo = new Hashtable<String, String>();
		
		//load all parameters into hashtable
		patientInfo.put("First Name", this.name);
		patientInfo.put("Last Name", this.surname);
		patientInfo.put("Patient ID", this.patientID);
		patientInfo.put("Birth Date", this.bday);
		patientInfo.put("Address", this.address);
		patientInfo.put("Phone Number", this.phoneNo);
		patientInfo.put("Deceased", "false");
		
		if(this.dept == null) {
			patientInfo.put("Department", "None");
		} else {
			patientInfo.put("Department", this.dept.getName());
		}
		
		if(this.bed == null) {
			patientInfo.put("Bed ID", "None");
		} else {
			patientInfo.put("Bed ID", this.bed.getBedID());
		}
		
		
		return patientInfo;
	}

	/**
	 * This method retrieves the bed object that is currently occupied by the patient
	 * @return bed
	 */
	public Bed getBed() {
		//if null is returned, patient has not yet been admitted to a bed
		return this.bed;
	}

	/**
	 * This method retrieves a patients unique ID number
	 * @return ID number
	 */
	public String getPatientId() {
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
	public Department getDepartment() {
		return this.dept;
	}

	public String getFirstName() {
		return this.name;
	}

	public String getLastName() {
		return this.surname;
	}

	public String getNumber() {
		return this.phoneNo;
	}

	public String getAddress() {
		return this.address;
	}

	public String getDOB() {
		return this.bday;
	}
	
}
