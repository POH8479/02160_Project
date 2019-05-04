import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;

/**
 *
 * @author Asger Conradsen
 */
 public class LoginManagerTest{
	 static final String[] departments = {"Emergency", "Inpatient", "Outpatient", "Management", "Temp"};
	 static final String[] subfolders = {"Users", "Patients", "Beds"};
	 
	 static PersistenceLayer persist;
	 
	 static Admin admin;
	 static Doctor doc;
	 static Nurse nurse;
	 static User user;
 
	 static Emergency em;
	 static Inpatient inPa;
	 static Outpatient outPa;
	 static Management man;
		
		
	 @BeforeClass
	 public static void setUpBeforeClass() {
		 persist = new PersistenceLayer();
		 
		 admin = new Admin("Steve Jobs","+180249625");
		 doc = new Doctor("Dr. Smith", "+4545252525", "Emergency");
		 nurse = new Nurse("John Doe", "+4512345678", "Emergency");
		 
		 em = Emergency.getInstance();
		 inPa = Inpatient.getInstance();
		 outPa = Outpatient.getInstance();
		 man = Management.getInstance();
	 }

	 
	 /**
	  * Tests the constructor. The user list is not public and does
	  * not have a get function so the test is very limited.
	  */
	 @Test
	 public void testConstructor() {
		 new LoginManager();
	 }
	 
	 /**
	  * Test the checkID method for two cases. One where the users is 
	  * in the system and one where the user is not.
	  */
	 @Test
	 public void checkIDTest()
	 {
		//adds users to a depart so the loginManager will load them
		 ArrayList<User> users = new ArrayList<User>();
		 users.add(doc);
		 users.add(nurse);
		 users.add(admin);
		 
		 LoginManager loginManager = new LoginManager();
		 
		 // checks against one of the users we've added
		 assertTrue(loginManager.checkID(doc));
		 
		 // checks against a users who is not in the system
		 user = new User("James Gosling", "+141558396");
		 assertFalse(loginManager.checkID(user));
	 }
 }