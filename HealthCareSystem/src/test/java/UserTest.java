import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * Tests for User Class
 * @author Jack Rodman
 */
public class UserTest {
	// User
	static User u1;
	static User u2;

	// Patients
	static Patient p1;
	static Patient p2;

	// Departments
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;

	// Beds
	static Bed b1;

	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create users
		u1 = new User("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
		u2 = new User("User Ulysses", "123 Main St Medicaltown, Germany", "+4912345678");

		// create the Patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");

		// create the Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// create the Bed
		b1 = new Bed(em);
	}


	/**
	 * Tests getUserInfo method in User class
	 */
	@Test
	public void getUserInfoTest() {
		// create a hashtable of the expected return
		Hashtable<String,String> expected = new Hashtable<String,String>();
		expected.put("Name", "John Doe");
		expected.put("User ID", u1.getUserInfo().get("User ID"));
		expected.put("Address", "123 Main St Anytown, Denmark");
		expected.put("Phone Number", "+4512345678");

		//check that appended string returned matches user info
		assertEquals(expected,u1.getUserInfo());
	}

	/**
	 * Testing registerPatient method in User class
	 */
	@Test
	public void registerPatientTest() {
		//register a patient and check that its unique ID is no longer null
		Patient newpat = u1.registerPatient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		assertFalse(newpat.getPatientInfo().get("Patient ID").equals(null));

		//test that new patient with same info has a different Patient ID
		Patient failpat = u1.registerPatient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		assertFalse(newpat.getPatientInfo().get("Patient ID").equals(failpat.getPatientInfo().get("Patient ID")));
	}

	/**
	 * Testing getPatientData method in User class
	 */
	@Test
	public void getPatientDataTest() {
		// create a hashtable of the expected return
		Hashtable<String,String> expected = new Hashtable<String,String>();

		expected.put("Last Name", "Rodman");
		expected.put("Bed ID", "None");
		expected.put("Birth Date", "28/06/1997");
		expected.put("Patient ID", p2.getPatientInfo().get("Patient ID"));
		expected.put("Phone Number", "+4562870942");
		expected.put("Address", "259 Nordvej 2800 Kongens Lyngby");
		expected.put("Deceased", "false");
		expected.put("Department", "None");
		expected.put("First Name", "Jack");

		//check that appended string returned matches patient info
		assertEquals(expected,u1.getPatientData(p2));
	}
}
