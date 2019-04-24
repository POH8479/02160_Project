package hospitalmanagementsystem;

import hospitalmanagementsystem.departments.*;

/**
 * The bed has a unique final ID, patient and department where it belongs.
 * A bed can only lay one patient and belong to one department.
 * @author Pieter O'Hearn
 */
public class Bed {
	// Static Variables
	public static int IDCounter = 0;

	// Instance Variables
	final String bedID;
	Department department;
	Patient patient;
	
	/**
	 * Creates a new instance of a Bed with no Patient or department
	 * @param dep
	 */
	public Bed() {
		// give the new bed a unique ID
		IDCounter++;
		this.bedID = "B" + Integer.toString(IDCounter);
		
		// assign other variables
		this.department = null;
		this.patient = null;
		
	}
	
	/**
	 * Creates a new instance of a Bed with no Patient but a specified department
	 * @param dep
	 */
	public Bed(Department dep) {
		// give the new bed a unique ID
		IDCounter++;
		this.bedID = "B" + Integer.toString(IDCounter);
		
		// assign other variables
		this.department = dep;
		this.patient = null;
		
	}

	/**
	 * Adds a Patient to the bed
	 * @param patient
	 * @throws IllegalArgumentException
	 */
	public void addPatient(Patient patient) throws IllegalArgumentException {
		// check if the bed is full
		if(this.patient != null) {
			// if full throw IllegalArgumentException
			throw new IllegalArgumentException(String.format("Bed %s is already occupied", this.bedID));
		} else if(!patient.getPatientInfo().get("Department").equals(this.department.getName())) {
			// if bed is in wrong department throw IllegalArgumentException
			throw new IllegalArgumentException(String.format("Bed %s is in a different Department to %s", this.bedID, patient.getPatientInfo().get("First Name")));
		} else {
			// if empty and in the same department update patient
			this.patient = patient;
		}

	}

	/**
	 * returns the patientID of the patient that is currently occupying the bed or null if empty.
	 * @return the patient ID
	 */
	public String getPatient() {
		// check if null
		if(this.patient == null) {
			return null;
		}
		
		// if not null return patient ID
		return this.patient.getPatientInfo().get("Patient ID");
	}

	/**
	 * return the unique bed ID as a string
	 * @return the bed ID
	 */
	public String getBedID() {
		return this.bedID;
	}

	public Object getDepartment() {
		return this.department;
	}
	
	public void removePatient() {
		this.patient = null;
	}

	// kun's comment
}