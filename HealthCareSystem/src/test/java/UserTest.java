import static org.junit.Assert.*;
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
		b1 = new Bed(em.getName());
	}


	/**
	 * Tests getUserInfo method in User class
	 */
	@Test
	public void getUserInfoTest() {
		//check that appended string returned matches user info
		assertEquals("John Doe",u1.getUserName());
	}
}
