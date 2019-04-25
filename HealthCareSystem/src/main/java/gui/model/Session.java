package gui.model;

import hospitalmanagementsystem.users.*;

public class Session {
	private User user;
	
	public Session() {
		user = null;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		return user.getUserName();
	}
	
	public String getRole() {
		return this.user.getType();
	}
}
