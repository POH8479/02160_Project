import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import java.util.Objects;

/**
 *
 * @author Pieter O'Hearn
 */
public class PatientTest {
	// test Patients
	static Patient p1;
	static Patient p2;
	static Patient p3;
	static Patient p4;
	static Patient p5;
	static Patient p6;

	// Departments
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;

	// Users
	static Admin admin;
	static Doctor doc;
	static Nurse nurse;
	static User user;

	// Beds
	static Bed b1;
	static Bed b2;
	static Bed b3;

	@BeforeClass
	public static void setUpBeforeClass() throws IllegalAccessException {
		// Create Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// Create Patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		p3 = new Patient("Anna", "Hogan", "21/08/1988", "Georg Brandes Pl. 2-6, 1307 K??benhavn", "+4552373549");
		p4 = new Patient("Asger", "Conradsen", "29/05/1999", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p5 = new Patient("Karoline", "??stergaard", "11/02/1994", "259 Nordvej 2800 Kongens Lyngby", "+4582373943");
		p6 = new Patient("Kun", "Zhu", "08/06/1996", "259 Nordvej 2800 Kongens Lyngby", "+4539562047");

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

		// create Users
		admin = new Admin("Steve Jobs", "+180249625");
		doc = new Doctor("Dr. Smith", "+4545252525", "Emergency");
		nurse = new Nurse("John Doe", "+4512345678", "Emergency");
		user = new User("James Gosling", "San Francisco Bay Area, California, U.S.", "+141558396");
	}

	/**
	 * Tests the getPatientInfo method of the Patient class
	 */
	@Test
	public void getPatientInfoTest() {
		assertEquals("Pieter", p1.getPatientInfo().get("First Name"));
		assertEquals("O'Hearn", p1.getPatientInfo().get("Last Name"));
		assertEquals("12/01/1990", p1.getPatientInfo().get("Birth Date"));
		assertEquals("259 Nordvej 2800 Kongens Lyngby", p1.getPatientInfo().get("Address"));
		assertEquals("Emergency", p1.getPatientInfo().get("Department"));
		assertEquals("false", p1.getPatientInfo().get("Deceased"));
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 * @throws IllegalArgumentException
	 */
	@Test
	public void updateDepartmentToManagementTest() throws IllegalArgumentException {
		// Test that error is thrown when trying to update Patient to Management department
		try {
			p1.updateDepartment(man);
			fail("Expected an AccessDeniedException to be thrown");
		} catch(IllegalArgumentException expected) {
			assertEquals(expected.getMessage(), "Patients can not be assigned to the Managment Department");
		}
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 * @throws IllegalAccessException
	 */
	@Test
	public void updateDepartmentTest() throws IllegalAccessException {
		// check the original Department is Emergency
		assertEquals(p1.getPatientInfo().get("Department"), "Emergency");

		// update the department and check it has changed
		p1.updateDepartment(outPa);
		assertEquals(p1.getPatientInfo().get("Department"), "Outpatient");
	}

	/**
	 * Tests the updateBed method of the Patient class
	 * @throws IllegalAccessException
	 */
	@Test
	public void updateBedTest() throws IllegalArgumentException, IllegalAccessException {
		// test assigning a patient to an empty bed in the same department
		p1.updateDepartment(em);
		p1.updateBed(b1);
		assertEquals(b1.getBedID(),p1.getPatientInfo().get("Bed ID"));

		// test assigning a patient to an empty bed in another department
		try {
			p2.updateBed(b2); // p2 is in inPa
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException expected) {
			assertEquals(String.format("Bed %s is in a different department to Jack", b2.getBedID()),expected.getMessage());
		}
	}

	/**
	 * Tests the getRecord method of the Patient class
	 */
	@Test
	public void getRecordTest() {
		// Give the patients a record
		String r1 = "The patient has a fracture on their right index finger, I recomend strapping the fingre and some good whiskey to reduce the pain.";
		String r2 = "The patient has severe lacerations on their right leg.";
		p1.updateRecord(r1);
		p2.updateRecord(r2);

		// check the records match
		assertEquals(r1,p1.getRecord());
		assertEquals(r2,p2.getRecord());
	}

	/**
	 * Tests the updateRecord method of the Patient class
	 */
	@Test
	public void updateRecordTest() {
		// Original records
		String r5 = "The patient is experiencing severe stomach pains.";
		String r6 = "The patient has severe lacerations on their right leg.";

		// updated records
		String uR5 = "The patient is experiencing severe stomach pains.\nI have assigned them to Dr. Smith who has better experience with this.";
		String uR6 = "The patient has severe lacerations on their right leg.\nI have cleaned the wound and have told the patient to keep it covered and clean.";

		// update and test
		p5.updateRecord(r5);
		p6.updateRecord(r6);

		assertEquals(r5, p5.getRecord());
		assertEquals(r6, p6.getRecord());

		// update again and test the new message has been appended to a new line
		p5.updateRecord("I have assigned them to Dr. Smith who has better experience with this.");
		p6.updateRecord("I have cleaned the wound and have told the patient to keep it covered and clean.");

		assertEquals(uR5,p5.getRecord());
		assertEquals(uR6,p6.getRecord());
	}

	/**
	 * Tests the constructor
	 */
	@Test
	public void testConstructor() {
		// create a new Patient
		Patient newPatient = new Patient("name", "surname", "01/01/2000", "address", "phoneNo");

		// check variables
		assertFalse(Objects.equals(newPatient.getPatientInfo().get("Patient ID"),null));
		assertTrue(Objects.equals(newPatient.getRecord(),null));
		assertTrue(newPatient.getPatientInfo().get("Department").equals("None"));
		assertEquals("false",newPatient.getPatientInfo().get("Deceased"));
	}
}
