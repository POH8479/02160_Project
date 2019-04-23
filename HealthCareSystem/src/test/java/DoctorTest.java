import static org.junit.Assert.*;

import org.junit.Test;
import hospitalmanagementsystem.*;

/**
 * Testing the Doctor Class
 * @author Pieter O'Hearn
 */
public class DoctorTest {
	// User
	static Doctor d1;

	/**
	 * Set up the necessary resources for the Doctor Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create a Nurse
		n1 = new Doctor("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
	}

	/**
	 * Tests the admitPatient method of the Doctor Class
	 */
	@Test
	public void admitPatientTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the dischargePatient method of the Doctor Class
	 */
	@Test
	public void dischargePatientTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the assignBed method of the Doctor Class
	 */
	@Test
	public void assignBedTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the getMedicalData method of the Doctor Class
	 */
	@Test
	public void getMedicalDataTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the editMedicalData method of the Doctor Class
	 */
	@Test
	public void editMedicalDataTest() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the performTest method of the Doctor Class
	 */
	@Test
	public void performTestTest() {
		fail("Not yet implemented");
	}
}
