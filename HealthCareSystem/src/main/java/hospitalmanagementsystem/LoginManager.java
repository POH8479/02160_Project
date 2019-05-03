package hospitalmanagementsystem;

import java.util.ArrayList;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.User;

/**
 * @author Asger Conradsen
 */
public class LoginManager {
	// INSTANCE VARIABLES
	private ArrayList<User> users;
	
	// CONSTRUCTOR
	/**
	 * 
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
	 * 
	 * @param userCheck
	 * @return
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
