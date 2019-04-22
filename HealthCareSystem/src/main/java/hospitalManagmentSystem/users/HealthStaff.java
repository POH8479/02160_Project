package hospitalmanagmentsystem.users;
/**
 * A Health Staff is a User who is medicaly working with patients.
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
	 * @param patient The Patient that is being admited
	 * @param department The department to admit the patient to
	 */
	public void admitPatient(Patient patient, Department department);

	/**
	 * Discharges the given patient from their department, removing the patient
	 * from the Departments patient list and the patients department variable
	 *
	 * @param patient The Patient that is being discharged
	 */
	public void dichargePatient(Patient patient);

	/**
	 * Assigns the given Patient to the given Bed, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @param bed The Bed that they are being assigned
	 */
	public Bed assignBed(Patient patient, Bed bed);

	/**
	 * Returns the medical data of the given patient as a String
	 *
	 * @param patient The Patient whos medical data is being requested
	 */
	public String getMedicalData(Patient patient);

	/**
	 * This method updates the given Patients medical record. The data String
	 * is appended to the Paitents medical data variable.
	 *
	 * @param patient The Patient whos medical data is being edited
	 * @param data The new medical data
	 */
	public String editMedicalData(Patient patient, String data);
}
