import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * Testing the Doctor Class
 * @author Pieter O'Hearn
 */
public class DoctorTest {
	// User
	static Doctor d1;
	static Doctor d2;

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
	 * Set up the necessary resources for the Doctor Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create Doctors
		d1 = new Doctor("John Doe", "+4512345678", "Emergency");
		d2 = new Doctor("Jane Doctor", "+4912345678", "Inpatient");

		// create the Patients
		p1 = new Patient("Pieter", "O\'Hearn", "12/01/1990", "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", "28/06/1997", "259 Nordvej 2800 Kongens Lyngby", "+4562870942");

		// create the Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// create the Bed
		b1 = new Bed(em.getName());
	}

	/**
	 * Tests the Constructor method of the Doctor Class
	 */
	@Test
	public void ConstructorTest() {
		// create a doctor in Outpatient and check it works
		Doctor d3 = new Doctor("John Doe", "+4512345678", "Outpatient");
		assertEquals(outPa, d3.getDepartment());
		
		// create a doctor with an invalid department and expect an exception
		try {
			d3 = new Doctor("John Doe", "+4512345678", "Blahh");
			fail("Expected an IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			assertEquals("Blahh is an invalid department.", e.getMessage());
		}
	}
}