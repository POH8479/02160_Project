package gui.controller;

import java.util.ArrayList;
import gui.model.*;
import gui.view.LoginView;
import hospitalmanagementsystem.users.*;
import hospitalmanagementsystem.LoginManager;
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
		// create a new Login Manager
		LoginManager loginManager = new LoginManager();
		
		// Find the user with that username and store in a local variable
		String [] input = username.split("@");
		User user = findUser(input[0]);
		
		// check if the loginManager returns true
		if (user != null && loginManager.checkID(user)) {
			
			//check to make sure username entry is in email format
			if (!username.contains("@kapjak.com")){ 
				view.showError();
				return;
			}
      
			// set the User for the session and close the Login Window
			session.setUser(findUser(input[0]));
			view.setVisible(false);
		} // if the username is "A1" but no user is found
		else if(user == null && username.equals("A1@kapjak.com")) {
			// create a new generic Admin user and add it to the user model
			user = new Admin("Super Admin", "12345678");
			session.getUserModel().addNewUser(user);
			
			// set the User for the session and close the Login Window
			session.setUser(user);
			view.setVisible(false);
		} // otherwise show an error message
		else {
			view.showError();
			return;
		}
		
		// check what type of user is logging in 
		switch(user.getType()) {
			// if an Admin open the management View
			case "Admin":
				app.manage(session);
				break;
			// if a Doctor open the healthStaff View
			case "Doctor":
				app.healthStaff(session);
				break;
			// if a Nurse open the healthStaff View
			case "Nurse":
				app.healthStaff(session);
				break;
			// otherwise open the general User view
			default:
				app.user(session);
		}
	}

	/**
	 * displays the Login view
	 */
	public void display() {
		// set the visibility of the Login view to true
		view.setVisible(true);
	}
	
	/**
	 * Finds the User who has this unique User ID. if no user is found, null is returned.
	 * @param userID The Users unique ID
	 * @return the User with this ID
	 */
	private User findUser(String userID) {
		// retrieve the User list from all the departments and store in userList
		ArrayList<User> userList = new ArrayList<User>();
		userList.addAll(Emergency.getInstance().getUserList());
		userList.addAll(Inpatient.getInstance().getUserList());
		userList.addAll(Outpatient.getInstance().getUserList());
		userList.addAll(Management.getInstance().getUserList());
		
		// search for a match and return if found
		for(User u : userList) {
			// if a match is found then return the user
			if(u.getUserID().equals(userID)) {
				return u;
			}
		}
		
		// otherwise return null
		return null;
	}
}