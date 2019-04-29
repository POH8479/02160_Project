package hospitalmanagementsystem;

import java.util.ArrayList;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.User;

/**
 * @author Asger Conradsen
 */
public class LoginManager {
	ArrayList<User> users;
	
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
	
	public boolean checkID(User userCheck) {
		for (User user : users) {
			if(userCheck.equals(user)) {
				return true;
			}
		}
		return false;
	}
}
