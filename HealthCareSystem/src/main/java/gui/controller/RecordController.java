package gui.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import gui.model.Session;
import gui.view.RecordView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.HealthStaff;

/**
 * 
 * @author pieterohearn
 *
 */
public class RecordController {
	
	private Session sessionModel;
	private RecordView view;
	private ApplicationController applicationController;
	private Patient patient;

	public RecordController(Session session, ApplicationController appController, Patient patient) {
		// 
		this.sessionModel = session;
		this.applicationController = appController;
		this.patient = patient;
	}

	public void display() {
		// 
		view.setVisible(true);
	}

	public void setView(RecordView recordView) {
		// 
		this.view = recordView;
		this.view.setSession(sessionModel);
	}

	public String getRecord() {
		// 
		if(this.patient.getRecord() == null) {
			return "";
		}
		return this.patient.getRecord();
	}

	public void saveRecord(String text) {
		// store entry with user id, name and timestamp
		String recordUpdate = "\n" + this.sessionModel.getUser().getUserID() + "  " + this.sessionModel.getUser().getUserName() + "\n" + LocalDate.now().toString() +"    " + LocalTime.now() + "\n" + text;
		
		// save the record
		((HealthStaff) sessionModel.getUser()).editMedicalData(this.patient, recordUpdate);
		
		// go back to department
		this.view.setVisible(false);
		if(this.sessionModel.getUser().getType().equals("Admin")) {
			this.applicationController.manageDisplay(this.sessionModel);
		} else {
			this.applicationController.healthStaffDisplay(this.sessionModel);
		}
		
		
	}

	public String getPatientId() {
		return this.patient.getPatientId();
	}

}
