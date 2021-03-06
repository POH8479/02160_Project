import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.users.Nurse;

/**
 * Testing the Nurse Class
 * @author Pieter O'Hearn
 */
public class NurseTest {
	// Users
	static Nurse n1;
	static Nurse n2;

	/**
	 * Set up the necessary resources for the Nurse Test file
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create a Nurses
		n1 = new Nurse("John Doe", "+4512345678", "Emergency");
		n2 = new Nurse("Jane nurse", "+4912345678", "Inpatient");
	}
	
	/**
	 * Tests the getType method in the Nurse class.
	 */
	@Test
	public void getTypeTest() { 
		// ensure that getType returns "Nurse"
		assertEquals("Nurse", n1.getType());
	}
	
	/**
	 * Tests the moveDepartment method of the Doctor class.
	 */
	@Test
	public void moveDepartmentTest() {
		// Move the Nurse n2 to the Emergency Department
		n2.moveDepartment("Emergency");
		
		// test that the department has been changed
		assertEquals("Emergency", n2.getDepartment());
	}
	
	/**
	 * Tests the Nurse Constructor.
	 */
	@Test
	public void constructorTest() {
		// create a new nurse in the "Outpatient class" and without a department
		Nurse n3 = new Nurse("Nurse Three", "12345678", "Outpatient");
		Nurse n4 = new Nurse("Nurse Four", "12345678", null);
		
		// check they where created correctly
		assertEquals("Outpatient", n3.getDepartment());
		assertEquals(null, n4.getDepartment());
	}
	
	/**
	 * Tests an invalid constructor.
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void illegalConstructorTest() {
		// create a Nurse with an invalid department
		n2 = new Nurse("Name", "12345678", "Department");
	}
}