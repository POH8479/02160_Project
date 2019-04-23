import static org.junit.Assert.*;

import org.junit.Test;
import hospitalmanagementsystem.*;

/**
 * Testing the Nurse Class
 * @author Pieter O'Hearn
 */
public class NurseTest {
	// User
	static Nurse n1;

	/**
	 * Set up the necessary resources for the Nurse Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create a Nurse
		n1 = new Nurse("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
	}

	/**
	 * Tests the admitPatient method of the Nurse Class
	 */
	@Test
	public void admitPatientTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the dischargePatient method of the Nurse Class
	 */
	@Test
	public void dischargePatientTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the assignBed method of the Nurse Class
	 */
	@Test
	public void assignBedTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the getMedicalData method of the Nurse Class
	 */
	@Test
	public void getMedicalDataTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the editMedicalData method of the Nurse Class
	 */
	@Test
	public void editMedicalDataTest() {
		fail("Not yet implemented");
	}
}
