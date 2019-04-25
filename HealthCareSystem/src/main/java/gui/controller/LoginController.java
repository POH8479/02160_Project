package gui.controller;

import java.util.ArrayList;
import java.util.Objects;

import gui.model.*;
import gui.view.LoginView;
import hospitalmanagementsystem.users.*;
import hospitalmanagementsystem.departments.*;

public class LoginController {
	
	private ApplicationController app;
	private Session session;
	private LoginView view;
	
	/**
	 * Constructor for the Login Controller 
	 * @param application
	 */
	public LoginController(ApplicationController application) {
		// save the application and create a new Session and View
		this.app = application;
		this.session = new Session();
		this.view = new LoginView(this);
	}
	
	/**
	 * Validates the credentials of the user
	 * @param username The User ID e.g "U5"
	 * @param password the same as the username
	 */
	public void validateCredentials(String username, String password) {
		// if the username is Admin the super user is evoked
		if (username.equals("Admin")) {
			Admin genericAdmin = new Admin("Admin", "Admin's Address", "Phone");
			session.setUser(genericAdmin);
			view.setVisible(false);
			app.manage(session);
		} else {
			User u = findUser(username);
			if (Objects.equals(u,null)) {
				view.showError();
			} else {
				session.setUser(u);
			}
		}
	}

	public void display() {
		view.setVisible(true);
	}
	
	private User findUser(String userID) {
		// retrieve the User list from all the departments and store in userList
		ArrayList<User> userList = Emergency.getInstance().getUserList();
		userList.addAll(Inpatient.getInstance().getUserList());
		userList.addAll(Outpatient.getInstance().getUserList());
		userList.addAll(Management.getInstance().getUserList());
		
		// search for a match and return if found
		for(User u : userList) {
			if(u.getUserID().equals(userID)) {
				return u;
			}
		}
		
		return null;
	}

}
