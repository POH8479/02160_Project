package gui.model;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import hospitalmanagementsystem.Patient;

public class PatientModel extends AbstractTableModel {

	private static final long serialVersionUID = -8100080945056395023L;
	private ArrayList<Patient> patients;
	
	public PatientModel() {
		patients = new ArrayList<Patient>();
	}
	
	public void addNewPatient(Patient newPatient) {
		patients.add(newPatient);
		fireTableDataChanged(); // notify the views that data changed
	}
	
	public void removePatient(String name) {
		
		fireTableDataChanged(); // notify the views that data changed
	}
	
	
	// methods below to extend table model
	
	@Override
	public int getColumnCount() {
		return 9; // this is fixed: product and quantity
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
			return patients.get(rowIndex).getBed().getBedID();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "User ID";
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
