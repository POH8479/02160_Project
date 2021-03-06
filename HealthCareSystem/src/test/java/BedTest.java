import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;

public class BedTest {
	// Beds
	static Bed b1;
	static Bed b2;
	static Bed b3;
	
	// Patients
	static Patient p1;
	static Patient p2;
	static Patient p3;

	/**
	 * Initialise the variables to use in testing.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create a bed in the Emergency and Inpatient departments
		b1 = new Bed("Emergency");
		b2 = new Bed("Emergency");
		b3 = new Bed("Emergency");
		
		// create 2 patients
		p1 = new Patient();
		p1.setDepartment("Emergency");
		p2 = new Patient();
		p2.setDepartment("Emergency");
		p3 = new Patient();
		p3.setDepartment("Inpatient");
	}
	
	/**
	 * Tests the updatePatient method of the Bed class.
	 */
	@Test
	public void updatePatientTest() {
		// test adding the patient to right bed
		Bed bed = new Bed("Inpatient");
		Patient patient = new Patient();
		patient.setDepartment("Inpatient");
		bed.updatePatient(patient);
		assertEquals(patient.getPatientID(), bed.getPatient());
	}
	
	/**
	 * Tests the updatePatient method of the Bed class with a patient already occupying the bed Occupied.
	 */
	@Test
	public void updatePatientOccupiedTest() throws IllegalArgumentException {
		// try adding a null patient to the bed
		try {
			b1.updatePatient(p2);
		} catch(IllegalArgumentException e) {
			assertEquals(String.format("Bed %s is already occupied", b1.getBedID()),e.getMessage());
		}
	}
	
	/**
	 * Tests the updatePatient method of the Bed class with a patient in the wrong department.
	 */
	@Test
	public void updatePatientWrongDepartmentTest() throws IllegalArgumentException {
		// try adding the wrong patient to bed
		try {
			b3.updatePatient(p3);
		} catch(IllegalArgumentException e) {
			assertEquals(String.format("Bed %s is in a different Department to null",b3.getBedID()),e.getMessage());
		}
	}
	
	/**
	 * Tests the updateDepartment method of the Bed class.
	 */
	@Test
	public void updateDepartmentTest() {
		// update the department and check it has changed
		b2.updateDepartment("Inpatient");
		assertEquals("Inpatient", b2.getDepartment());
	}

	/**
	 * Tests the updateDepartment method of the Bed class with an invalid department.
	 */
	@Test
	public void updateInvalidDepartmentTest() throws IllegalArgumentException {
		// try update the department to an invalid one
		try {
			b2.updateDepartment("Management");
		} catch(IllegalArgumentException e) {
			assertEquals("Management is an invalid department.", e.getMessage());
		}
	}
	
	/**
	 * Tests the set patient method of the Bed Class.
	 */
	@Test
	public void setPatientTest() {
		// set the patient of bed b1
		b1.setPatient("P1");
		assertEquals("P1", b1.getPatient());
	}
	
	/**
	 * Tests the constructor of the Bed class trying to create a bed in a wrong department.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void illegalConstructorTest() {
		// try create a be in Management
		new Bed("Management");
	}
}
