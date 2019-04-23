import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Hashtable;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		p1 = new Patient("Pieter", "O\'Hearn", LocalDate.of(1990, 1,12), "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");

		// create the Departments
		em = new Emergency();
		inPa = new Inpatient();
		outPa = new Outpatient();
		man = new Management();

		// create the Bed
		b1 = new Bed(em);
	}
	
	
	/**
	 * Tests getUserInfo method in User class 
	 */
	@Test
	public void getUserInfoTest() {
		//check that appended string returned matches user info
		assertEquals(u1.getUserInfo(), "Name: John Doe\nUser ID: 1\nAddress: 1123 Main St Anytown, Denmark\nPhone Number: +4512345678");
	}
	
	/**
	 * Testing registerPatient method in User class
	 */
	@Test
	public void registerPatientTest() {
		//register a patient and check that its unique ID is no longer null
		Patient newpat = u1.registerPatient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		assertFalse(newpat.getPatientInfo().get("Patient ID").equals(null));
		
		//test that new patient with same info has a different Patient ID
		Patient failpat = u1.registerPatient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		assertFalse(newpat.getPatientInfo().get("Patient ID").equals(failpat.getPatientInfo().get("Patient ID")));
	}

	/**
	 * Testing getPatientData method in User class
	 */
	@Test
	public void getPatientDataTest() {
		Hashtable<String,String> patData = new Hashtable<String,String>();
		patData.put("First Name", "Jack");
		patData.put("Last Name", "Rodman");
		patData.put("First Name",);
		patData.put("First Name",);
		
		//check that appended string returned matches patient info
		assertEquals(u1.getPatientData(p2), "Name: Jack Rodman\nUser ID: "+ p1.idnum + "\nAddress: 259 Nordvej 2800 Kongens Lyngby\nPhone Number: +4562870942");
		
		//check that getPatientData also works after registering a patient
		assertEquals(u1.registerPatient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942"), "Name: Jack Rodman\nUser ID: "+ p1.idnum + "\nAddress: 259 Nordvej 2800 Kongens Lyngby\nPhone Number: +4562870942");
	}
}
