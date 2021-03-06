import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import hospitalmanagementsystem.users.User;

/**
 * Tests for User Class
 * @author Jack Rodman
 */
public class UserTest {
	// Users
	static User u1;
	static User u2;

	/**
	 * Initialise User variables to use in testing
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// create users
		u1 = new User("John Doe", "12345678");
		u2 = new User("User Ulysses", "12345678");
	}

	/**
	 * Test the editUser() method in the User class.
	 */
	@Test
	public void editUserTest() { 
		// edit the User
		u1.editUser("Sam Evens", "23456789");
		
		// check that the users info has changed
		assertEquals("Sam Evens", u1.getUserName());
		assertEquals("23456789", u1.getNumber());
	}
	
	/**
	 * Tests the getType method in the User class.
	 */
	@Test
	public void getTypeTest() { 
		// ensure that getType returns "User"
		User u3 = new User();
		assertEquals("User", u3.getType());
	}
	
	/**
	 * Tests the Get UserID Method of the User class.
	 */
	@Test
	public void getUserIDTest() {
		// create a User and check it creates a valid UserID
		User u4 = new User("Test User", "12345678");
		assertTrue(u4.getUserID().charAt(0) == 'U');
		
		// check that it is not Equal to any others created
		assertNotEquals(u1.getUserID(),u4.getUserID());
		assertNotEquals(u2.getUserID(),u4.getUserID());
	}
	
	/**
	 * Tests the constructor for other Users.
	 */
	@Test
	public void constructorTest() {
		// create a new User with an 'A' User ID
		User aType = new User("Admin", "12345678", "A");
		assertTrue(aType.getUserID().charAt(0) == 'A');
	}
}
