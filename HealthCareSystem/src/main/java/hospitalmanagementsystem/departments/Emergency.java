package hospitalmanagementsystem.departments;

import java.util.List;
import java.util.ArrayList;

import hospitalmanagementsystem.*;

public class Emergency extends Department {
	//capacity of emergency department
	  int capacity;

		List<Patient> patientList = new ArrayList<Patient>();

		public void addPatient(Patient Patient) {
			patientList.add(Patient);
		}

		public void removePatient(Patient Patient) {
			patientList.remove(Patient);

		}
}
