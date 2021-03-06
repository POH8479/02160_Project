package hospitalmanagementsystem;

import java.util.ArrayList;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.User;

/**
 * The Login Manager Class is used to manage the users login Credentials.
 * @author Asger Conradsen
 */
public class LoginManager {
	// INSTANCE VARIABLES
	private ArrayList<User> users;
	
	// CONSTRUCTOR
	/**
	 * Creates a new Instance of the Login Manager.
	 */
	public LoginManager() {
		Emergency emergency = Emergency.getInstance();
		Inpatient inpatient = Inpatient.getInstance();
		Management management = Management.getInstance();
		Outpatient outpatient = Outpatient.getInstance();
		
		users = new ArrayList<User>();
		users.addAll(emergency.getUserList());
		users.addAll(inpatient.getUserList());
		users.addAll(management.getUserList());
		users.addAll(outpatient.getUserList());
	}
	
	// METHODS
	/**
	 * Checks the given ID is a falid ID of a user on file.
	 * @param userCheck The ID to check
	 * @return True or False
	 */
	public boolean checkID(User userCheck) {
		for (User user : users) {
			if(userCheck.equals(user)) {
				return true;
			}
		}
		return false;
	}
}