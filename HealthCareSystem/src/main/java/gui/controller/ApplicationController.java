package gui.controller;

import gui.model.*;
import gui.view.ManagementView;
import gui.view.RecordView;
import hospitalmanagementsystem.Patient;

public class ApplicationController {
	
	private LoginController loginController;
	private ManagementController managementColtroller;
	private RecordController recordController;
	
	/**
	 * Login method for the HMS application
	 */
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	
	public void manage(Session session) {
		managementColtroller = new ManagementController(session, this);

		ManagementView manView = new ManagementView(managementColtroller);
		managementColtroller.setView(manView);
		managementColtroller.display();
	}
	
	public void record(Session session, Patient patient) {
		recordController = new RecordController(session, this, patient);

		RecordView recordView = new RecordView(recordController);
		recordController.setView(recordView);
		recordController.display();
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
