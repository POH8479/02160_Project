package gui.controller;

import java.util.ArrayList;
import java.util.Objects;
import gui.model.*;
import gui.view.LoginView;
import hospitalmanagementsystem.users.*;
import hospitalmanagementsystem.departments.*;

/**
 * The Controller for the Login 
 * @author Pieter O'Hearn
 *
 */
public class LoginController {
	// Instance variables
	private ApplicationController app;
	private Session session;
	private LoginView view;
	
	/**
	 * Constructor for the Login Controller 
	 * @param application The application
	 */
	public LoginController(ApplicationController application) {
		// save the application and create a new Session and View
		this.app = application;
		this.session = Session.getInstance();
		this.view = new LoginView(this);
	}
	
	/**
	 * Validates the credentials of the user
	 * @param username The User ID e.g "U5"
	 * @param password the same as the username
	 */
	public void validateCredentials(String username, String password) {
		User u = findUser(username);
		// if the username is Admin the super user is evoked
		if (u == null && username.equals("A1")) {
			Admin genericAdmin = new Admin("Super Admin", "Super Admin's Address", "+45 12345678");
			session.getUserModel().addNewUser(genericAdmin);
			session.setUser(genericAdmin);
			view.setVisible(false);
			app.manage(session);
		} else if (Objects.equals(u,null)) {
			view.showError();
		} else {
			session.setUser(u);
			view.setVisible(false);
			
			switch(u.getType()) {
				case "Admin":
					app.manage(session);
					break;
				case "Doctor":
					app.healthStaff(session);
					break;
				case "Nurse":
					app.healthStaff(session);
					break;
				default:
					app.user(session);
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
