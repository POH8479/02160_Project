import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
*
* @author Asger Conradsen
*/
public class QueryTest{
	static Query testQuery;
	
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;
	
	static Patient p1;
	static Patient p2;
	
	static User u1;
	static User u2;
	static User u3;
	
	static Bed b1;
	
	/**
	 * Initializes objects with values used for testing
	 * @throws IllegalAccessException
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws IllegalAccessException {	
		testQuery = new Query();
		
		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();
		
		p1 = new Patient();
		p2 = new Patient();
		
		u1 = new User();
		u2 = new User();
		u3 = new User();
		
		b1 = new Bed();
		
	}
	
	/**
	 * Clean user- and patientlists before each test
	 */
	@Before
	public void clean() {
		em.setPatientList(new ArrayList<Patient>());
		inPa.setPatientList(new ArrayList<Patient>());
		outPa.setPatientList(new ArrayList<Patient>());
		
		em.setUserList(new ArrayList<User>());
		inPa.setUserList(new ArrayList<User>());
		outPa.setUserList(new ArrayList<User>());
		man.setUserList(new ArrayList<User>());
	}
	
	@Test
	public void depMostPatientsTest() {
		//Case where emergency has the most patients
		ArrayList<Patient> patients = new ArrayList<Patient>();
		patients.add(p1);
		em.setPatientList(patients);
		assertEquals("Emergency", testQuery.depMostPatients());
		
		//Case where emergency and inPatient has equal amount
		patients.clear();
		patients.add(p2);
		inPa.setPatientList(patients);
		assertEquals("Inpatient, Emergency", testQuery.depMostPatients());
	}
	
	@Test
	public void depLeastPatientsTest() {
		//Case where inpatient and outpatient has same amount
		ArrayList<Patient> patients = new ArrayList<Patient>();
		patients.add(p1);
		em.setPatientList(patients);
		assertEquals("Inpatient, Outpatient", testQuery.depLeastPatients());
		
		//case where outpatient has the least
		patients = new ArrayList<Patient>();
		patients.add(p2);
		inPa.setPatientList(patients);
		assertEquals("Outpatient", testQuery.depLeastPatients());
	}
	
	@Test
	public void depMostUsersTest() {
		//case where emergency has the most users
		ArrayList<User> users = new ArrayList<User>();
		users.add(u1);
		em.setUserList(users);
		assertEquals("Emergency", testQuery.depMostUsers());
	}
	
	@Test
	public void depLeastUsersTest() {
		//case where only emergency has users
		ArrayList<User> users = new ArrayList<User>();
		users.add(u1);
		em.setUserList(users);
		assertEquals("Inpatient, Outpatient, Management", 
				testQuery.depLeastUsers());
	}
	
	@Test
	public void totPatientsTest() {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		patients.add(p1);
		inPa.setPatientList(patients);
		assertEquals(1, testQuery.totPatients());
	}
	
	@Test
	public void totUsersTest() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(u1);
		inPa.setUserList(users);
		assertEquals(1, testQuery.totUsers());
	}
	
	@Test
	public void bedStatusTest() {
		ArrayList<Bed> beds = new ArrayList<Bed>();
		beds.add(b1);
		inPa.setBedList(beds);
		
		assertEquals("Inpatient: " + 1 +
					  "\nEmergency: " + 0, 
					  testQuery.bedStatus());
	}
}