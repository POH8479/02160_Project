import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 * Tests for Admin Class
 * @author Jack Rodman
 */
public class AdminTest {
	// User
	static Admin ad1;
	static Admin ad2;

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
	 * Initialise admin variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create Administrators
		ad1 = new Admin("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
		ad2 = new Admin("Andy Admin", "123 Main St Medicaltown, Germany", "+4912345678");

		// create the Patients
		p1 = new Patient("Pieter", "O\'Hearn", LocalDate.of(1990, 1,12), "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");

		// create the Departments
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();

		// create the Bed
		b1 = new Bed(em);
	}

	/**
	 * Tests the admitPatient method of the Admin Class
	 */
	@Test
	public void admitPatientTest() throws IllegalAccessException {
		
		//test that error is thrown when admitting patient to management dept
		try {
			ad1.admitPatient(p1, man);
			fail("Expected an AccessDeniedException to be thrown");
			} catch(IllegalAccessException expected) {
				assertEquals("Can not admit a patient to the Management department.",expected.getMessage());
			}
		
		// admit p1 to Inpatient and check both patients variable and departments list
		ad2.dischargePatient(p1);
		ad2.admitPatient(p1,inPa);
		assertEquals("Inpatient",p1.getPatientInfo().get("Department"));
		assertTrue(inPa.getPatientList().contains(p1));
		assertEquals(null,p1.getPatientInfo().get("Bed"));

		// admit p2 to Emergency and check that a bed is assigned and that both patients variable and departments list
		assertEquals("None",p2.getPatientInfo().get("Department"));
		ad1.admitPatient(p2,em);
		assertEquals("Emergency",p2.getPatientInfo().get("Department"));
		assertTrue(em.getPatientList().contains(p2));
	}

	// create a rule
	@Rule public ExpectedException exception = ExpectedException.none();

	/**
	 * Tests the admitPatient method of the Admin Class when the patient already has a Department
	 * @throws IllegalAccessException 
	 */
	@Test
	public void admitPatientErrorTest() throws IllegalAccessException {
		ad2.admitPatient(p1,outPa);
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Can not admit a patient who is already admitted to a department.");
		ad2.admitPatient(p1,outPa);
	}

	/**
	 * Tests the dischargePatient method of the Admin Class
	 */
	@Test
	public void dischargePatientTest() throws IllegalAccessException {
		// ad2 discharges patient1 from Inpatient and check is Department is null
		assertEquals("Outpatient",p1.getPatientInfo().get("Department"));
		ad2.dischargePatient(p1);
		assertEquals("None",p1.getPatientInfo().get("Department"));
	}

	/**
	 * Tests the dischargePatient method of the Admin Class when the patient already has a Department
	 * @throws IllegalAccessException 
	 */
	@Test
	public void dischargePatientErrorTest() throws IllegalAccessException{
		ad2.dischargePatient(p1);
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Can not discharge a patient who is not already admitted into any department.");
		ad2.dischargePatient(p1);
	}

	/**
	 * Tests the assignBed method of the Admin Class
	 * @throws IllegalAccessException 
	 */
	@Test
	public void assignBedTest() throws IllegalArgumentException, IllegalAccessException {
		// admit p1 to the Emergency department and assign the bed b1 to them
		ad1.admitPatient(p1, em);
		ad1.assignBed(p1, b1);

		// check that the patients info and beds info reflect this
		assertEquals(p1.getPatientInfo().get("Patient ID"), b1.getPatient());
		assertEquals(b1.getBedID(),p1.getPatientInfo().get("Bed ID"));

		// try assign patient2 to the same bed, an IllegalArgumentException is expected
		try {
			ad1.assignBed(p2, b1);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException exception) {
			assertEquals(String.format("Bed %s is already occupied", b1.getBedID()),exception.getMessage());
		}
	}

	/**
	 * Tests the getMedicalData method of the Admin Class
	 */
	@Test
	public void getMedicalDataTest() {
		// get patient1 record which should be null
		assertEquals(null,ad1.getMedicalData(p1));

		// give p1 some medical data and test again
		p1.updateRecord("This patient has lost all their teeth. I recomend a diet of liquid food.");
		assertEquals("This patient has lost all their teeth. I recomend a diet of liquid food.",ad1.getMedicalData(p1));
	}

	/**
	 * Tests the editMedicalData method of the Admin Class
	 */
	@Test
	public void editMedicalDataTest() {
		// get patient2 record which should be null
		assertEquals(null,ad1.getMedicalData(p2));

		// edit patient2's medical data and test again
		ad1.editMedicalData(p2, "This Patient has died.");
		assertEquals("This Patient has died.",ad1.getMedicalData(p2));

		// edit patient2's medical data again and test it has been appended
		ad1.editMedicalData(p2, "Send patient to the morgue.");
		assertEquals("This Patient has died.\nSend patient to the morgue.",ad1.getMedicalData(p2));
	}

	/**
	 * Tests the removeUser method of the Admin Class
	 */
	@Test
	public void removeUserTest() {
		User badUser = new User("Name", "Address", "Phone");
		assertFalse(Objects.equals(badUser.getUserInfo().get("User ID"),"None"));
		ad1.removeUser(badUser);
		assertFalse(badUser.getDepartment().getUserList().contains(badUser));
	}
	
	/**
	 * Tests the addUser method of the Admin Class
	 */
	@Test
	public void addUserTest() {
		// Add one of each user
		Admin a = (Admin) ad1.addUser("Admin", "Admin's Address", "Admins Phone", "Admin");
		assertEquals('A',a.getUserInfo().get("User ID").charAt(0));
		
		Doctor d = (Doctor) ad1.addUser("Doctor", "Doctor's Address", "Doctors Phone", "Doctor");
		assertEquals('D',d.getUserInfo().get("User ID").charAt(0));
		
		Nurse n = (Nurse) ad1.addUser("Nurse", "Nurse's Address", "Nurses Phone", "Nurse");
		assertEquals('N',n.getUserInfo().get("User ID").charAt(0));
		
		User u = ad1.addUser("User", "User's Address", "Users Phone", "User");
		assertEquals('U',u.getUserInfo().get("User ID").charAt(0));
	}
	
	/**
	 * Tests the getDepartment method of the Admin Class
	 */
	@Test
	public void getDepartmentTest() {
		// TODO
	}
}
