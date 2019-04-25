package gui.controller;

import gui.model.*;
import gui.view.ManagementView;

public class ApplicationController {
	
	private LoginController loginController;
	private ManagementController adminColtroller;
	
	/**
	 * Login method for the HMS application
	 */
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	
	public void manage(Session session) {
		adminColtroller = new ManagementController(new UserModel(), new PatientModel(), session);

		ManagementView manView = new ManagementView(adminColtroller);
		adminColtroller.setView(manView);
		adminColtroller.display();
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationController app = new ApplicationController();
		app.login();
	}
}
