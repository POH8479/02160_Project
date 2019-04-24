package hospitalManagmentSystem.departments;

import java.util.ArrayList;
import java.util.List;

public abstract class Department {
  int ID;
	String name;
	List<User> userList = new ArrayList<User>();

	protected void addUser(User User) {
		userList.add(User);
	}

	protected void removeUser(User User) {
		userList.remove(User);
	}

}
