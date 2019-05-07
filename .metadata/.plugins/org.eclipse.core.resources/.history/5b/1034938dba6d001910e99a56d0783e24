import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.users.*;
import hospitalmanagementsystem.departments.*;

/**
 * Testing the Nurse Class
 * @author Pieter O'Hearn
 */
public class NurseTest {
	// User
	static Nurse n1;
	static Nurse n2;

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
	 * Set up the necessary resources for the Nurse Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create a Nurses
		n1 = new Nurse("John Doe", "+4512345678", "Emergency");
		n2 = new Nurse("Jane nurse", "+4912345678", "Inpatient");

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
	 * Tests the admitPatient method of the Nurse Class
	 * @throws IllegalAccessException
	 */
	@Test
	public void admitPatientTest() throws IllegalAccessException {
		// admit p1 to Inpatient and check both patients variable and departments list
		p1.updateDepartment(null);
		n2.admitPatient(p1,inPa);
		assertEquals("Inpatient",p1.getDepartment());
		assertTrue(inPa.getPatientList().contains(p1));
		assertEquals(null,p1.getBed());

		// admit to Emergency and check that a bed is assigned and that both patients variable and departments list
		assertEquals("None",p2.getDepartment());
		n1.admitPatient(p2,em);
		assertEquals("Emergency",p2.getDepartment());
		assertTrue(em.getPatientList().contains(p2));

		// check nurse from other departments can admitPatient
		n1.dischargePatient(p2);
		n1.admitPatient(p2,outPa);
		assertEquals("Outpatient",p2.getDepartment());

		// try and admit patient to the management class
		try {
			n1.dischargePatient(p2);
			n1.admitPatient(p2,man);
			fail("Should have thrown an IllegalAccessException");
		} catch(IllegalAccessException e) {
			assertEquals("Can not admit a patient to the Management department.",e.getMessage());
		}
	}

	// create a rule
	@Rule public ExpectedException exception = ExpectedException.none();

	/**
	 * Tests the admitPatient method of the Nurse Class when the patient already has a Department
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void admitPatientErrorTest() throws IllegalArgumentException, IllegalAccessException {
		p1.updateDepartment(Emergency.getInstance());
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Can not admit a patient who is already admitted to a department.");
		n2.admitPatient(p1,outPa);
	}

	/**
	 * Tests the dischargePatient method of the Nurse Class
	 * @throws IllegalAccessException
	 */
	@Test
	public void dischargePatientTest() throws IllegalAccessException {
		// nurse2 discharges patient1 from Inpatient and check is Department is null
		assertFalse(p1.getDepartment().equals("None"));
		n2.dischargePatient(p1);
		assertEquals("None",p1.getDepartment());

		// check nurses from other departments can discharge Patients
		n1.admitPatient(p2, outPa);
		assertEquals(em,n1.getDepartment());
		n1.dischargePatient(p2);
		assertEquals("None",p2.getDepartment());
	}

	/**
	 * Tests the dischargePatient method of the Nurse Class when the patient already has a Department
	 * @throws IllegalArgumentException
	 */
	@Test
	public void dischargePatientErrorTest() throws IllegalArgumentException {
		n2.dischargePatient(p1);
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Can not discharge a patient who is not already admitted into any department.");
		n2.dischargePatient(p1);
	}

	/**
	 * Tests the assignBed method of the Nurse Class
	 * @throws IllegalAccessException
	 */
	@Test
	public void assignBedTest() throws IllegalArgumentException, IllegalAccessException {
		// admit p1 to the Emergency department and assign the bed b1 to them
		n1.admitPatient(p1, em);
		n1.assignBed(p1, b1);

		// check that the patients info and beds info reflect this
		assertEquals(p1.getpatientID(), b1.getPatient());
		assertEquals(b1.getBedID(),p1.getBed());

		// try assign patient2 to the same bed, an IllegalArgumentException is expected
		try {
			n1.assignBed(p2, b1);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException exception) {
			assertEquals(String.format("Bed %s is already occupied", b1.getBedID()),exception.getMessage());
		}
	}

	/**
	 * Tests the getMedicalData method of the Nurse Class
	 */
	@Test
	public void getMedicalDataTest() {
		// get patient1 record which should be null
		assertEquals(null,n1.getMedicalData(p1));

		// give p1 some medical data and test again
		p1.setRecord("This patient has lost all their teeth. I recomend a diet of liquid food.");
		assertEquals("This patient has lost all their teeth. I recomend a diet of liquid food.",n1.getMedicalData(p1));
	}

	/**
	 * Tests the editMedicalData method of the Nurse Class
	 */
	@Test
	public void editMedicalDataTest() {
		// get patient2 record which should be null
		assertEquals(null,n1.getMedicalData(p2));

		// edit patient2's medical data and test again
		n1.editMedicalData(p2, "This Patient has died.");
		assertEquals("This Patient has died.",n1.getMedicalData(p2));

		// edit patient2's medical data again and test it has been appended
		n1.editMedicalData(p2, "Send patient to the morgue.");
		assertEquals("This Patient has died.\nSend patient to the morgue.",n1.getMedicalData(p2));
	}

	/**
	 * Tests the Constructor method of the Doctor Class
	 */
	@Test
	public void ConstructorTest() {
		// create a nurse in Outpatient and check it works
		Nurse n3 = new Nurse("John Doe", "+4512345678", "Outpatient");
		assertEquals(outPa, n3.getDepartment());

		// create a doctor with an invalid department and expect an exception
		try {
			n3 = new Nurse("John Doe", "+4512345678", "Blahh");
			fail("Expected an IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			assertEquals("Blahh is an invalid department.", e.getMessage());
		}
	}
}
