package hospitalmanagementsystem;

import hospitalmanagementsystem.departments.Department;

/**
 * 
 * @author Jack Rodman
 */
public class Patient {
	// STATIC VARIABLES
	private static PersistenceLayer persist = new PersistenceLayer();

	// INSTANCE VARIABLES
	String name;
	String surname;
	String patientID;
	String bday;
	String address;
	String phoneNo;
	String deceased;
	String record;
	String dept;
	String bed;

	// CONSTRUCTORS
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

	/**
	 * A Blank COnstructor for the Persistence Layer
	 */
	public Patient() {}
	
	// METHODS
	/**
	 * Updates a patient's department to enable admitting or moving a patient to a new department
	 * @param department
	 * @throws IllegalArgumentException
	 */
	public void updateDepartment(Department department) throws IllegalArgumentException {
		// check if department is Management
		if(department.getName().equals("Management")) {
			throw new IllegalArgumentException("Patients can not be assigned to the Managment Department");
		} else { 
			// Delete from current department
			persist.delete(this.patientID, this.dept);
			
			// check if the patient is in a bed
			if(this.bed != null && (this.dept.equals("Emergency") || this.dept.equals("Inpatient"))) {
				// if they are remove the patient from the bed
				for(Bed bed : department.getBedList()) {
					if(bed.getBedID().equals(this.bed)) {
						bed.setPatient(null);
						this.bed = null;
						break;
					}
				}
			}
			
			// update the department
			this.dept = (department==null) ? null:department.getName();
			
			// Save the new Patient
			persist.save(this, this.patientID, this.dept);
		}
	}

	// GETTER METHODS
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return this.name;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return this.surname;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPatientID() {
		return this.patientID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDOB() {
		return this.bday;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDeceased() {
		return this.deceased;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRecord() {
		return this.record;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDepartment() {
		return this.dept;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBed() {
		return this.bed;
	}

	// SETTER METHODS
	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.name = firstName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.surname = lastName;
	}

	/**
	 * 
	 * @param patientID
	 */
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	
	/**
	 * 
	 * @param dOB
	 */
	public void setDOB(String dOB) {
		this.bday = dOB;
		
	}
	
	/**
	 * 
	 * @param newAddress
	 */
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	/**
	 * 
	 * @param phone
	 */
	public void setPhoneNo(String phone) {
		this.phoneNo = phone;
	}

	/**
	 * 
	 * @param deceased
	 */
	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}
	
	/**
	 * Updates a patients medical record
	 * @param data
	 */
	public void setRecord(String data) {
		if(this.record != null) {
			this.record = this.record + "\n" + data;
		} else {
			this.record = data;
		}
	}
	
	/**
	 * 
	 * @param department
	 */
	public void setDepartment(String department) {
		this.dept = department;
	}

	/**
	 * 
	 * @param bedID
	 */
	public void setBed(String bedID) {
		this.bed = bedID;
	}
}