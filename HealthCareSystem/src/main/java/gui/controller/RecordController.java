package gui.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import gui.model.Session;
import gui.model.UserModel;
import gui.view.RecordView;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.users.HealthStaff;

public class RecordController {
	
	private UserModel userModel;
	private Session sessionModel;
	private RecordView view;
	private ApplicationController applicationController;
	private Patient patient;

	public RecordController(Session session, ApplicationController appController, Patient patient) {
		// 
		this.userModel = session.getUserModel();
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

	public String getPatientId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRecord() {
		// 
		if(this.patient.getRecord() == null) {
			return "";
		}
		return this.patient.getRecord();
	}

	public void saveRecord(String text) {
		// store entry with timestamp
		String recordUpdate = "\n" + LocalDate.now().toString() +"    " + LocalTime.now() + "\n" + text;
		
		// save the record
		((HealthStaff) sessionModel.getUser()).editMedicalData(this.patient, recordUpdate);
		
		// go back to management
		this.view.setVisible(false);
		this.applicationController.manage(this.sessionModel);
		
	}

}
