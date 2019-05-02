package hospitalmanagementsystem;

import java.util.Objects;
import hospitalmanagementsystem.departments.*;

/**
 * 
 * @author Jack Rodman
 *
 */

public class Patient {
	// Static Variables
	private static PersistenceLayer persist = new PersistenceLayer();

	//INSTANCE VARIABLES
	String name;
	String surname;
	String patientID;
	String bday;
	String address;
	String phoneNo;
	String deceased;
	String record;
	String dept;
	Bed bed;

	//CONSTRUCTOR
	public Patient(String name, String surname, String bday, String address, String phoneNo) {
		//patient info input by user
		this.name = name;
		this.surname = surname;
		int idCounter = persist.loadCounter() + 1;
		this.patientID = "P" + Integer.toString(idCounter);;
		persist.saveCounter(idCounter);
		this.bday = bday;
		this.address = address;
		this.phoneNo = phoneNo;
		this.deceased = "false";
		this.record = null;
		this.dept = null;
		this.bed = null;
		
		persist.save(this, this.patientID, this.dept);
	}

	public Patient() {}
	
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
			if(department == null) {
				this.dept = null;
			} else {
				this.dept = department.getName();
			}
		}
		
		// Save the new Patient
		PersistenceLayer persist = new PersistenceLayer();
		persist.save(this, this.patientID, this.dept);
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

	// Getters
	public String getFirstName() {
		return this.name;
	}

	public String getLastName() {
		return this.surname;
	}
	
	public String getpatientID() {
		return this.patientID;
	}
	
	public String getDOB() {
		return this.bday;
	}

	public String getAddress() {
		return this.address;
	}
	
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	public String getDeceased() {
		return this.deceased;
	}
	
	public String getRecord() {
		return this.record;
	}
	
	public String getDepartment() {
		return this.dept;
	}
	
	public Bed getBed() {
		return this.bed;
	}

	// Setters
	public void setFirstName(String firstName) {
		this.name = firstName;
	}

	public void setLastName(String lastName) {
		this.surname = lastName;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	
	public void setDOB(String dOB) {
		this.bday = dOB;
		
	}
	
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void setPhoneNo(String phone) {
		this.phoneNo = phone;
	}

	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}
	
	/**
	 * Updates a patients medical record
	 * @param data
	 */
	public void setRecord(String data) {
		if(!Objects.equals(this.record,null)) {
			this.record = this.record + "\n" + data;
		} else {
			this.record = data;
		}
	}
	
	public void setDepartment(String department) {
		this.dept = department;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}
}
