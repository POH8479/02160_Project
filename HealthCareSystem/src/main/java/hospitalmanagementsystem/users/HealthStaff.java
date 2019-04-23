package hospitalmanagementsystem.users;

import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Department;

/**
 * A Health Staff is a User who is medically working with patients.
 * The methods allow HealthStaff to admit and discharge Patients, assign them a
 * Bed and to access and update their medical record.
 *
 * @author Pieter O'Hearn
 */
public interface HealthStaff {
	/**
	 * Admits a given patient to a given department, updating the patients
	 * department variable and the departments patient list.
	 *
	 * @param patient The Patient that is being admitted
	 * @param department The department to admit the patient to
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public void admitPatient(Patient patient, Department department) throws Exception;

	/**
	 * Discharges the given patient from their department, removing the patient
	 * from the Departments patient list and the patients department variable
	 *
	 * @param patient The Patient that is being discharged
	 * @throws Exception 
	 */
	public void dischargePatient(Patient patient) throws Exception;

	/**
	 * Assigns the given Patient a Bed in the department, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @return The Bed the patient is assigned to
	 */
	public Bed assignBed(Patient patient, Bed bed);

	/**
	 * Returns the medical data of the given patient as a String
	 *
	 * @param patient The Patient whose medical data is being requested
	 * @return A string of the patients medical data
	 */
	public String getMedicalData(Patient patient);

	/**
	 * This method updates the given Patients medical record. The data String
	 * is appended to the Patients medical data variable.
	 *
	 * @param patient The Patient whose medical data is being edited
	 * @param data The new medical data
	 * @return A string of the patients updated medical data
	 */
	public String editMedicalData(Patient patient, String data);
}
