package hospitalmanagementsystem.users;

import hospitalmanagementsystem.departments.*;

import java.util.Objects;

import hospitalmanagementsystem.*;

/**
 *
 * @author Pieter O'Hearn
 */
public class Admin extends User implements HealthStaff{
	// Instance Variables
	String department;

	/**
	 * Creates a new Admin of the Hospital Management
	 */
	public Admin(String usersName, String phone) {
		// call the super
		super(usersName, phone, "A");
		
		// set the department to Admin
		this.department = Management.getInstance().getName();
		
		// update the departments list
		Management.getInstance().addUser(this);

		// Save the new User
		PersistenceLayer persist = new PersistenceLayer();
		boolean saved = persist.save(this, this.userID, this.department);
		if(!saved) {System.out.println("Admin Not Saved!!!");}
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
		} else if(!Objects.equals(patient.getDepartment(), null)) { 
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
		if(patient.getDepartment().equals("None")) {
			throw new IllegalArgumentException("Can not discharge a patient who is not already admitted into any department.");
		} else {
			// remove the Patient from the departments patient list
			switch(patient.getDepartment()) {
				case "Emergency":
					Emergency.getInstance().removePatient(patient);
				case "Inpatient":
					Inpatient.getInstance().removePatient(patient);
				case "Outpatient":
					Outpatient.getInstance().removePatient(patient);
			}
			
			// Update the patients department variable
			patient.updateDepartment(null);
			PersistenceLayer persist = new PersistenceLayer();
			persist.save(patient, patient.getpatientID(), null);
		}
	}

	/**
	 * Assigns the given Patient a Bed in the department, updating the patients Bed
	 * variable and the beds Patient variable.
	 *
	 * @param patient The Patient who is being assigned a Bed
	 * @return The Bed the patient is assigned to
	 * @throws IllegalAccessException 
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
	 * @param patient The Patient who's medical data is being requested
	 * @return A string of the patients medical data
	 */
	public String getMedicalData(Patient patient){
		// request the updated record and return it
		return patient.getRecord();
	}

	/**
	 * This method updates the given Patients medical record. The data String
	 * is appended to the Paitent's medical data variable.
	 *
	 * @param patient The Patient who's medical data is being edited
	 * @param data The new medical data
	 * @return A string of the patients updated medical data
	 */
	public String editMedicalData(Patient patient, String data){
		// append the data to the patients medical data
		patient.setRecord(data);

		// request the updated record and return it
		return patient.getRecord();
	}

	/**
	 * Creates a New User and adds it to the Hospital Management System.
	 *
	 * @param usersName The name of the User
	 * @param usersAddress the Address of the User
	 * @param phone The Phone Number of the User
	 * @return The new User
	 */
	public User addUser(String usersName, String usersAddress, String phone, String typeOfUser) {
		// check what typeOfUser
		switch(typeOfUser) {
			case "Admin":
				return new Admin(usersName, phone);
			case "Doctor":
				return new Doctor(usersName, phone, "Emergency");
			case "Nurse":
				return new Nurse(usersName, phone, "Emergency");
			default:
				return new User(usersName, phone);
		}
	}

	/**
	 * Removes a User from the Hospital Management System (HMS).
	 *
	 * @param oldUser The user to be removed from the HMS
	 */
	public void removeUser(User oldUser) {
		// remove patient from department userlist
		switch(oldUser.getDepartment()) {
			case "Emergency":
				Emergency.getInstance().getUserList().remove(oldUser);
			case "Outpatient": 
				Outpatient.getInstance().getUserList().remove(oldUser);
			case "Inpatient": 
				Inpatient.getInstance().getUserList().remove(oldUser);
			case "Management": 
				Management.getInstance().getUserList().remove(oldUser);
		}
	}

	public String getDepartment() {
		return this.department;
	}
	
	@Override
	public String getType() {
		return "Admin";
	}
	
	// Cannot change department
	public void moveDepartment(String department) {}
}
