import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import java.time.LocalDate;
import java.util.Hashtable;

/**
 *
 * @author Pieter O'Hearn
 */
public class PatientTest {
	// test Patients
	static Patient p1 = null;
	static Patient p2 = null;
	static Patient p3 = null;
	static Patient p4 = null;
	static Patient p5 = null;
	static Patient p6 = null;

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
		em = new Emergency();
		inPa = new Inpatient();
		outPa = new Outpatient();
		man = new Management();

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

		// create Users
		admin = new Admin("Steve Jobs", "Cupertino, California, United States", "+180249625");
		doc = new Doctor("Dr. Smith", "Anker Engelunds Vej 1 Bygning 101A, 2800 Kgs. Lyngby", "+4545252525");
		nurse = new Nurse("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
		user = new User("James Gosling", "San Francisco Bay Area, California, U.S.", "+141558396");
	}

	/**
	 * Tests the getPatientInfo method of the Patient class
	 */
	@Test
	public void getPatientInfoTest() {
		// Patients expected info strings
		Hashtable<String, String> p1Info = new Hashtable<String, String>();
		p1Info.put("First Name", "Pieter");
		p1Info.put("Last Name", "O'Hearn");
		p1Info.put("Birthday", "12/1/1990");
		p1Info.put("Address", "259 Nordvej 2800 Kongens Lyngby");
		p1Info.put("Phone Number", "+4562473948");
		p1Info.put("Department", "Emergency");
		p1Info.put("Patient ID", "1");
		p1Info.put("Deceased", "False");

		Hashtable<String, String> p2Info = new Hashtable<String, String>();
		p2Info.put("First Name", "Jack");
		p2Info.put("Last Name", "Rodman");
		p2Info.put("Birthday", "28/6/1997");
		p2Info.put("Address", "259 Nordvej 2800 Kongens Lyngby");
		p2Info.put("Phone Number", "+4562870942");
		p2Info.put("Department", "Inpatient");
		p2Info.put("Patient ID", "2");
		p2Info.put("Deceased", "False");

		assertEquals(p1.getPatientInfo(), p1Info);
		assertEquals(p2.getPatientInfo(), p2Info);
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 * @throws IllegalAccessException
	 */
	@Test
	public void updateDepartmentToManagementTest() throws IllegalAccessException {
		// Test that error is thrown when trying to udate Patient to Managment department
		try {
			p1.updateDepartment(man);
			fail("Expected an AccessDeniedException to be thrown");
		} catch(IllegalAccessException expected) {
			assertEquals(expected.getMessage(), "Patients can not be assigned to the Managment Department");
		}
	}

	/**
	 * Tests the updateDepartment method of the Patient class
	 */
	@Test
	public void updateDepartmentTest() {
		// check the original Department is Emergency
		assertEquals(p1.getPatientInfo().get("Department"), "Emergency");

		// update the department and check it has changed
		p1.updateDepartment(outPa);
		assertEquals(p1.getPatientInfo().get("Department"), "Outpatient");
	}

	/**
	 * Tests the updateBed method of the Patient class
	 */
	@Test
	public void updateBedTest() throws IllegalArgumentException {
		// test assigning a patient to an empty bed in the same department
		p1.updateBed(b1);
		assertEquals(b1.getPatient(),p1);
		assertEquals(p1.getBed(),b1);

		// test assigning a patient to an empty bed in another department
		try {
			p2.updateBed(b2); // p2 is in inPa
			fail("Expected an AccessDeniedException to be thrown");
		} catch (IllegalArgumentException expected) {
			assertEquals(expected.getMessage(), String.format("Bed %s is in a different Department to Jack", b1.getBedID()));
		}

		// Test assigning a patient to an occupied bed
		try {
			p3.updateBed(b3);
			p4.updateBed(b3);
			fail("Expected an AccessDeniedException to be thrown");
		} catch (IllegalArgumentException expected) {
			assertEquals(expected.getMessage(), String.format("Bed %s is already occupied", b1.getBedID()));
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
		assertEquals(p1.getRecord(), r1);
		assertEquals(p2.getRecord(), r2);
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

		assertEquals(p5.getRecord(), r5);
		assertEquals(p6.getRecord(), r6);

		// update again and test the new message has been appended to a new line
		p5.updateRecord("I have assigned them to Dr. Smith who has better experience with this.");
		p6.updateRecord("I have cleaned the wound and have told the patient to keep it covered and clean.");

		assertEquals(p3.getRecord(), uR5);
		assertEquals(p4.getRecord(), uR6);
	}

	/**
	 * Tests the constructor
	 */
	@Test
	public void testConstructor() {
		// create a new Patient
		Patient newPatient = new Patient("name", "surname", LocalDate.of(2000, 1,1), "address", "phoneNo");

		// check variables
		assertTrue(Integer.parseInt(newPatient.getPatientInfo().get{"Patient ID"}) != 0 && Integer.parseInt(newPatient.getPatientInfo().get{"Patient ID"}) > 0);
		assertTrue(newPatient.getRecord().equals(null));
		assertTrue(newPatient.getDepartment().equals(null));
		assertFalse(newPatient.getDeceased());
	}
}
