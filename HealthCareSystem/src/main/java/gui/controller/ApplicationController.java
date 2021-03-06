package gui.controller;

import gui.model.Session;
import gui.view.HealthStaffView;
import gui.view.ManagementView;
import gui.view.RecordView;
import gui.view.UserView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.*;

/**
 * The main controller of the Graphical User Interface (GUI).
 * @author Pieter O'Hearn
 *
 */
public class ApplicationController {
	// Instance variables
	private LoginController loginController;
	private ManagementController managementController;
	private RecordController recordController;
	private HealthStaffController healthStaffController;
	private UserController userController;

	/**
	 * Constructor for the Application Controller Class.
	 * This is the main Controller used to control the Application.
	 */
	public ApplicationController() {
		// Load all the saved Files
		PersistenceLayer persist = new PersistenceLayer();
		persist.loadDepartment(Emergency.getInstance());
		persist.loadDepartment(Outpatient.getInstance());
		persist.loadDepartment(Inpatient.getInstance());
		persist.loadDepartment(Management.getInstance());
	}

	/**
	 * Login method for the HMS application which initialises a new LoginController and displays the login window.
	 */
	public void login() {
		// initialise a new Login Controller and display the login window
		loginController = new LoginController(this);
		loginController.display();
	}

	/**
	 * Starts the Management Controller used by the Admin User.
	 * @param session The current session
	 */
	public void manage(Session session) {
		// initialise a new Management Controller
		managementController = new ManagementController(session, this);

		// create a new Management View and display the Management Window
		ManagementView manView = new ManagementView(managementController);
		managementController.setView(manView);
		managementController.display();
	}

	/**
	 * Display the Management Display again once it has already been initialised.
	 */
	public void manageDisplay(Session session) {
		// try displaying the already initialised management Controller
		try {
			managementController.display();
		} // if not initialised, catch the exception and and call manage()
		catch(NullPointerException e) {
			manage(session);
		}
	}

	/**
	 * Starts the record Controller for a given patient.
	 * @param session The current session
	 * @param patient The patient whose record is being read/edited
	 */
	public void record(Session session, Patient patient) {
		// initialise a new Record Controller
		recordController = new RecordController(session, this, patient);

		// create a new Record View and display the Record Window
		RecordView recordView = new RecordView(recordController);
		recordController.setView(recordView);
		recordController.display();
	}

	/**
	 * Starts the healthStaff Controller used by all healthStaff Users who do not have admin privilages.
	 * @param session The current session
	 */
	public void healthStaff(Session session) {
		// initialise a new healthStaff Controller
		healthStaffController = new HealthStaffController(session, this);

		// create a new healthStaff View and display the healthStaff Window
		HealthStaffView healthStaffView = new HealthStaffView(healthStaffController);
		healthStaffController.setView(healthStaffView);
		healthStaffController.display();
	}

	/**
	 * Display the healthStaff Display again once it has already been initialised.
	 */
	public void healthStaffDisplay(Session session) {
		// try displaying the already initialised healthStaff Controller
		try {
			healthStaffController.display();
		} // if not initialised, catch the exception and and call healthStaff()
		catch(NullPointerException e) {
			healthStaff(session);
		}
	}

	/**
	 * Starts the User Controller used by all generic users.
	 * @param session The current session
	 */
	public void user(Session session) {
		// initialise a new User Controller
		userController = new UserController(session, this);

		// create a new User View and display the User Window
		UserView userView = new UserView(userController);
		userController.setView(userView);
		userController.display();
	}

	/**
	 * The Main method for the GUI program.
	 * @param args The command-line arguments
	 */
	public static void main(String[] args) {
		// initialise the ApplicationController app and call the login method to Start
		ApplicationController app = new ApplicationController();
		app.login();
	}
}
