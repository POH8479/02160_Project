import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagmentsystem.departments.*;
import hospitalmanagmentsystem.patient.Patient;
import hospitalmanagmentsystem.users.*;
import java.time.LocalDate;

/**
 *
 * @author Pieter O'Hearn
 */
public class PatientTest {
	// test Patients
	static Patient p1 = null;
	static Patient p2 = null;
	static Patient p3 = null;
	static Patient p5 = null;
	static Patient p6 = null;

	// Departments
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Managment man;

	// Users
	static Admin admin;
	static Doctor doc;
	static Nurse nurse;
	static User user;

	// Beds
	Bed b1;
	Bed b2;
	Bed b3;
	Bed b4;

	@BeforeClass
	public static void setUpBeforeClass() {
		// Create Departments
		em = new Emergency();
		inPa = new Inpatient();
		outPa = new Outpatient();
		man = new Managment();

		// Create Patients
		p1 = new Patient("Pieter", "O\'Hearn", LocalDate.of(1990, 1,12), "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		p3 = new Patient("Anna", "Hogan", LocalDate.of(1988, 8,21), "Georg Brandes Pl. 2-6, 1307 København", "+4552373549");
		p4 = new Patient("Asger", "Conradsen", LocalDate.of(1999, 5,29), "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p5 = new Patient("Karoline", "Østergaard", LocalDate.of(1994, 2,11), "259 Nordvej 2800 Kongens Lyngby", "+4582373943");
		p6 = new Patient("Kun", "Zhu", LocalDate.of(1996, 6,8), "259 Nordvej 2800 Kongens Lyngby", "+4539562047");

		// assign patients a Department
		p1.updateDepartment(em);
		p2.updateDepartment(inPa);
		p3.updateDepartment(em);
		p4.updateDepartment(em);
		p5.updateDepartment(em);
		p6.updateDepartment(outPa);

		// Create Beds
		b1 = new Bed(em);
		b2 = new Bed(em);
		b3 = new Bed(em);
		b4 = new Bed(em);
	}

	/**
	 * Tests the getPatientInfo method of the Patient class
	 */
	@Test
	public void getPatientInfoTest() {
		// Patients expected info strings
		String p1Info = "First Name: Pieter\nLast Name: O'Hearn\nBirthday: 12/1/1990\nAddress: 259 Nordvej 2800 Kongens Lyngby\n Phone Number: +4562473948\nDepartment: Emergency";
		String p2Info = "First Name: Jack\nLast Name: Rodman\nBirthday: 28/6/1997\nAddress: 259 Nordvej 2800 Kongens Lyngby\n Phone Number: +4562870942\nDepartment: Inpatient";
		String p3Info = "First Name: Anna\nLast Name: Hogan\nBirthday: 21/8/1988\nAddress: Georg Brandes Pl. 2-6, 1307 København\n Phone Number: +4552373549\nDepartment: Emergency";
		String p4Info = "First Name: Asger\nLast Name: Conradsen\nBirthday: 29/5/1999\nAddress: 87 downtown 2800 Kongens Lyngby\n Phone Number: +4585476964\nDepartment: Emergency";
		String p5Info = "First Name: Karoline\nLast Name: Østergaard\nBirthday: 11/2/1994\nAddress: 259 Nordvej 2800 Kongens Lyngby\n Phone Number: +4582373943\nDepartment: Emergency";
		String p6Info = "First Name: Kun\nLast Name: Zhu\nBirthday: 8/6/1996\nAddress: 259 Nordvej 2800 Kongens Lyngby\n Phone Number: +4539562047\nDepartment: Outpatient";

		assertArrayEquals(p1.getPatientInfo(), p1Info);
		assertArrayEquals(p2.getPatientInfo(), p2Info);
		assertArrayEquals(p3.getPatientInfo(), p3Info);
		assertArrayEquals(p4.getPatientInfo(), p4Info);
		assertArrayEquals(p5.getPatientInfo(), p5Info);
		assertArrayEquals(p6.getPatientInfo(), p6Info);
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 */
	@Test
	public void updateDepartmentTest() {
		// Patient 1 expected info strings before and after update
		String p1Info = "First Name: Pieter\nLast Name: O'Hearn\nBirthday: 12/1/1990\nAddress: 259 Nordvej 2800 Kongens Lyngby\n Phone Number: +4562473948\nDepartment: Emergency";
		String p1InfoUpdated = "First Name: Pieter\nLast Name: O'Hearn\nBirthday: 12/1/1990\nAddress: 259 Nordvej 2800 Kongens Lyngby\n Phone Number: +4562473948\nDepartment: Outpatient";

		// check the origonal info string matches
		assertArrayEquals(p1.getPatientInfo(), p1Info);

		// update the department and check again
		p1.updateDepartment(inPa);
		assertArrayEquals(p1.getPatientInfo(), p1InfoUpdated);

		// Test that error is thrown when trying to udate Patient to Managment department
		try {
			p1.updateDepartment(man);
			fail("Expected an AccessDeniedException to be thrown")
		} catch(AccessDeniedException expected) {
			assertThat(expected.getMessage(), is("Patients can not be assigned to the Managment Department"));
		}
	}

	/**
	 * Tests the updateBed method of the Patient class
	 */
	@Test
	public void updateBedTest() {

	}

	/**
	 * Tests the getRecord method of the Patient class
	 */
	@Test
	public void getRecordTest() {

	}

	/**
	 * Tests the updateRecord method of the Patient class
	 */
	@Test
	public void updateRecordTest() {


	}

	/**
	 * Tests if a non-medical staff can change sensitive medical data
	 */
	@Test(expected = AccessDeniedException.class)
	public void testAccessLevel() {

	}

	/**
	 * Tests the constructor
	 */
	@Test
	public void testConstructor() {

	}
}
