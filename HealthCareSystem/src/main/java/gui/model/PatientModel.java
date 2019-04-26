package gui.model;


import java.util.ArrayList;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.users.*;

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
	
	public void dischargePatient(String patientId, HealthStaff  user) {
		// discharge
		Patient toDischarge = findPatient(patientId);
		try {
			user.dischargePatient(toDischarge);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	public void removeUser(String patientID, Admin admin) {
		Patient toRemove = null;
		// search for a match in the user List
		for(Patient p : patients) {
			if(p.getPatientId().equals(patientID)) {
				// store
				toRemove = p;
				break;
			}
		}
		
		// remove from list
		patients.remove(toRemove);
		
		// remove form HMS TODO
//		admin.removePatient(toRemove);
		
		
		fireTableDataChanged(); // notify the views that data changed
		
	}

	public void admitPatient(String patientID, HealthStaff user, Department dept) throws IllegalAccessException, IllegalArgumentException {
		// find the patient and admit them to the department
		Patient toAdmit = findPatient(patientID);
		user.admitPatient(toAdmit, dept);
	}
	
	public Patient findPatient(String patientId) {
		// search for a match in the patient List
		for(Patient p : patients) {
			if(p.getPatientId().equals(patientId)) {
				// return patient
				return p;
			}
		}
		
		// else return null
		return null;
		
	}

	public void edit(String patientId, String firstName, String lastName, String dOB, String address, String phone) {
		// find the user
		Patient toEdit = findPatient(patientId);
		
		// edit
		toEdit.setFirstName(firstName);
		toEdit.setLastName(lastName);
		toEdit.setPhoneNo(phone);
		toEdit.setAddress(address);
		toEdit.setPhone(phone);
		
		fireTableDataChanged(); // notify the views that data changed
	}
}
