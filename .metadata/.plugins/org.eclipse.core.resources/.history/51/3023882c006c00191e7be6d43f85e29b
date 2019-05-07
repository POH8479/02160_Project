package hospitalmanagementsystem.users;

import hospitalmanagementsystem.departments.*;

import java.util.Objects;

import hospitalmanagementsystem.*;

public class Nurse extends User implements HealthStaff{
	// Instance variables
	String department;

	public Nurse(String usersName, String phone, String department) {
		super(usersName, phone, "N");
    
		//assign department based on input
		switch(department==null?"null":department) {
			case "Emergency":
				this.department = department;
				Emergency.getInstance().addUser(this);
				break;
			case "Inpatient":
				this.department = department;
				Inpatient.getInstance().addUser(this);
				break;
			case "Outpatient":
				this.department = department;
				Outpatient.getInstance().addUser(this);
				break;
			case "null":
				this.department = null;
				break;
			default:
				throw new IllegalArgumentException(String.format("%s is an invalid department.",department));
		}
		
		// Save the new User
		PersistenceLayer persist = new PersistenceLayer();
		boolean saved = persist.save(this, this.userID, this.department);
		if(!saved) {System.out.println("Nurse Not Saved111");}
	}

	/**
	 * Admits a given patient to a given department, updating the patients
	 * department variable and the departments patient list.
	 *
	 * @param patient The Patient that is being admitted
	 * @param department The department to admit the patient to
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void admitPatient(Patient patient, Department department) throws IllegalAccessException, IllegalArgumentException {
		// if department is Management then throw an exception
		if(department instanceof Management) {
			throw new IllegalAccessException("Can not admit a patient to the Management department.");
		} else if(!Objects.equals(patient.getPatientInfo().get("Department"), "None")) { 
			throw new IllegalArgumentException("Can not admit a patient who is already admitted to a department.");
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
	public void dischargePatient(Patient patient) throws IllegalArgumentException{
		// check if already checked out
		if(patient.getPatientInfo().get("Department").equals("None")) {
			throw new IllegalArgumentException("Can not discharge a patient who is not already admitted into any department.");
		} else {
			// remove the Patient from the departments patient list
			patient.getDepartment().removePatient(patient);
			
			// Update the patients department variable
			patient.updateDepartment(null);
		}
	}

	/**
	 * Assigns the given Patient a Bed in the department, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @return The Bed the patient is assigned to
	 */
	public Bed assignBed(Patient patient, Bed bed) throws IllegalAccessException {
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
	
	public String getDepartment() {
		return this.department;
	}
	
	@Override
	public String getType() {
		return "Nurse";
	}
	
	public void moveDepartment(String department) {
		// change department
		if(this.department != null) {
			switch(this.department) {
				case "Emergency":
					Emergency.getInstance().getUserList().add(this);
				case "Outpatient": 
					Outpatient.getInstance().getUserList().add(this);
				case "Inpatient": 
					Inpatient.getInstance().getUserList().add(this);
				case "Management": 
					Management.getInstance().getUserList().add(this);
			}
		}
		this.department = department;
		switch(department) {
			case "Emergency":
				Emergency.getInstance().getUserList().add(this);
			case "Outpatient": 
				Outpatient.getInstance().getUserList().add(this);
			case "Inpatient": 
				Inpatient.getInstance().getUserList().add(this);
			case "Management": 
				Management.getInstance().getUserList().add(this);
		}
	}
}
