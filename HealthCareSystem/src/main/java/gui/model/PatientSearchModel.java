package gui.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import hospitalmanagementsystem.Patient;

/**
 * The Model for the Patients Table when a search has been completed. 
 * @author Pieter O'Hearn
 *
 */
public class PatientSearchModel extends AbstractTableModel {
	// static variables
	private static final long serialVersionUID = 3361752830545329131L;
	
	// instance variables
	private ArrayList<Patient> patients;
	
	/**
	 * Constructor of the PatientSearchModel. Creates an array of patients found in a search.
	 */
	public PatientSearchModel(ArrayList<Patient> searchedPatients) {
		// set the PatintSearchModel patient list to searchedPatients
		this.patients = searchedPatients;
	}
	
	/////////////////////////////////////////
	// METHODS BELOW TO EXTEND TABLE MODEL //
	/////////////////////////////////////////
	
	@Override
	public int getColumnCount() {
		// this is fixed: product and quantity
		return 9;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return patients.get(rowIndex).getPatientId();
		} else if (columnIndex == 1) {
			return patients.get(rowIndex).getFirstName();
		} else if (columnIndex == 2) {
			return patients.get(rowIndex).getLastName();
		} else if (columnIndex == 3) {
			if(Objects.equals(null,patients.get(rowIndex).getDepartment())) {
				return "-";
			}
			return patients.get(rowIndex).getDepartment().getName();
		} else if (columnIndex == 4) {
			return patients.get(rowIndex).getNumber();
		} else if (columnIndex == 5) {
			return patients.get(rowIndex).getAddress();
		} else if (columnIndex == 6) {
			return patients.get(rowIndex).getDOB();
		} else if (columnIndex == 7) {
			return patients.get(rowIndex).getDeceased();
		} else if (columnIndex == 8) {
			if(Objects.equals(null,patients.get(rowIndex).getBed())) {
				return "-";
			}
			return patients.get(rowIndex).getBed().getBedID();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "Patient ID";
		} else if (column == 1) {
			return "First Name";
		} else if (column == 2) {
			return "Last Name";
		} else if (column == 3) {
			return "Department";
		} else if (column == 4) {
			return "Phone Number";
		} else if (column == 5) {
			return "Address";
		} else if (column == 6) {
			return "D.O.B";
		} else if (column == 7) {
			return "Deceased";
		} else if (column == 8) {
			return "Bed";
		}
		return null;
	}
}
