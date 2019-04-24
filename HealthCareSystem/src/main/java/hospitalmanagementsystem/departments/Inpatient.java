package hospitalManagmentSystem.departments;

import java.util.ArrayList;
import java.util.List;

public class Inpatient extends Department {
  
  int capacity, nextBedIndex;

	List<Patient> patientList = new ArrayList<Patient>();
	List<Bed> bedList = new ArrayList<Bed>();

	public void addPatient(Patient Patient) {
		patientList.add(Patient);
	}

	public void removePatient(Patient Patient) {
		patientList.remove(Patient);

	}

}
