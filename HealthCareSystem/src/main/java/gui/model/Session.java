package gui.model;

import hospitalmanagementsystem.users.*;

public class Session {
	private static Session single_instance = null; 
	private User user;
	private UserModel userModel;
	private PatientModel patientModel;
	
	private Session() {
		user = null;
		userModel = new UserModel();
		patientModel = new PatientModel();
	}
	
	// static method to create instance of Emergency class 
    public static Session getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Session();
        }
  
        return single_instance; 
    }

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public String getUsername() {
		return user.getUserName();
	}
	
	public String getRole() {
		return this.user.getType();
	}
	
	public UserModel getUserModel() {
		return this.userModel;
	}
	
	public PatientModel getPatientModel() {
		return this.patientModel;
	}
}
