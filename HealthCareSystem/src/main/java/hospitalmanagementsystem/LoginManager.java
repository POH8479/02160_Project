/**
 * @author Asger Conradsen
 */

package hospitalmanagementsystem;

import java.util.ArrayList;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.departments.Outpatient;
import hospitalmanagementsystem.users.User;

public class LoginManager {
	ArrayList<User> users;
	
	public LoginManager() {
		Emergency emergency = Emergency.getInstance();
		Inpatient inpatient = Inpatient.getInstance();
		Management management = Management.getInstance();
		Outpatient outpatient = Outpatient.getInstance();
		
		users = new ArrayList<User>();
		users.addAll(emergency.getUsers());
		users.addAll(inpatient.getUsers());
		users.addAll(management.getUsers());
		users.addAll(outpatient.getUsers());
	}
	
	public boolean checkID(String userID) {
		for (User user : users) {
			if(userID.equals(user.getID())) return true;
		}
		return false;
	}
	
	
}
