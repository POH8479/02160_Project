package gui.model;

import hospitalmanagementsystem.users.*;

/**
 * The Session model for the current session of this application. 
 * @author Pieter O'Hearn
 *
 */
public class Session {
	// Static variables
	private static Session single_instance = null; 
	
	// Instance variables
	private User user;
	private UserModel userModel;
	private PatientModel patientModel;
	
	/**
	 * The private constructor for the Singleton Session class. creates a new instance of the Session.
	 */
	private Session() {
		// set the instance variables
		user = null;
		userModel = new UserModel();
		patientModel = new PatientModel();
	}
	
	// static method to create instance of Emergency class 
	/**
	 * The public static method for the Session class which creates a new Session object
	 *  or returns the saved instance of a previously created one.
	 * @return The Session instance
	 */
    public static Session getInstance() {
    	// check if an instance has already been created
        if (single_instance == null) {
        	// if not create a new one
            single_instance = new Session();
        }
  
        // return the saved instance
        return single_instance; 
    }

    /**
     * Sets the User for the session.
     * @param user The User
     */
	public void setUser(User user) {
		// Set the User
		this.user = user;
	}
	
	/**
	 * Returns the User for this session
	 * @return The User
	 */
	public User getUser() {
		// return the user for the session
		return this.user;
	}
	
	/**
	 * Returns the User model for this session.
	 * @return The userModel
	 */
	public UserModel getUserModel() {
		// return the user model
		return this.userModel;
	}
	
	/**
	 * Returns the Patient model for this session
	 * @return The patientModel
	 */
	public PatientModel getPatientModel() {
		return this.patientModel;
	}
}
