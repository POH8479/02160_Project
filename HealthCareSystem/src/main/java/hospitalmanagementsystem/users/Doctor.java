package hospitalmanagementsystem.users;

import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.HMS;
import hospitalmanagementsystem.Patient;

/**
 * 
 * @author jackrodman
 *
 */
public class Doctor extends User implements HealthStaff{
	// Static variables
	static int idCounter;
	
	//INSTANCE VARIABLES
	Department department;
	final String doctorID;
	//String specialty; TODO

	public Doctor(String usersName, String usersAddress, String phone, String department) {
		super(usersName, usersAddress, phone);
		//assign department based on input
		this.department = HMS.getDepartment(department);
		
		idCounter++;
		doctorID = "D" + Integer.toString(idCounter);
	}

	/**
	 * Admits a given patient to a given department, updating the patients
	 * department variable and the departments patient list.
	 *
	 * @param patient The Patient that is being admitted
	 * @param department The department to admit the patient to
	 * @throws IllegalAccessException 
	 */
	public void admitPatient(Patient patient, Department department) throws IllegalAccessException {
		// if department is Management then throw an exception
		if(department instanceof Management) {
			throw new IllegalAccessException();
		} else {
			// Update the patients department variable
			patient.updateDepartment(department);

			// Add the Patient to the departments patient list
			department.addPatient(patient);
		}
	}

	/**
	 * Discharges the given patient from their department, removing the patient
	 * from the Departments patient list and the patients department variable
	 *
	 * @param patient The Patient that is being discharged
	 */
  public void dischargePatient(Patient patient) throws IllegalAccessException{
		// Update the patients department variable
		patient.updateDepartment(null);

		// remove the Patient from the departments patient list
		department.removePatient(patient);
	}

	/**
	 * Assigns the given Patient a Bed in the department, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @return The Bed the patient is assigned to
	 */
	public Bed assignBed(Patient patient, Bed bed) throws IllegalAccessException{
		// Update the patients Bed variable
		patient.updateBed(bed);

		// Add the Patient to the beds patient variable
		bed.addPatient(patient);

		return bed;
	}

	/**
	 * Returns the medical data of the given patient as a String
	 *
	 * @param patient The Patient whose medical data is being requested
	 * @return A string of the patients medical data
	 */
	public String getMedicalData(Patient patient) {
		// request the updated record and return it
		return patient.getRecord();
	}

	/**
	 * This method updates the given Patients medical record. The data String
	 * is appended to the Patients medical data variable.
	 *
	 * @param patient The Patient whose medical data is being edited
	 * @param data The new medical data
	 * @return A string of the patients updated medical data
	 */
	public String editMedicalData(Patient patient, String data) {
		// append the data to the patients medical data
		patient.updateRecord(data);

		// request the updated record and return it
		return patient.getRecord();
	}
}
