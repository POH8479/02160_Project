package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import java.util.List;

import hospitalmanagementsystem.*;

public class Outpatient extends Department {
	  int capacity;

		List<Patient> patientList = new ArrayList<Patient>();

		public void addPatient(Patient patient) {
			patientList.add(patient);
		}

		public void removePatient(Patient patient) {
			patientList.remove(patient);

		}
}
