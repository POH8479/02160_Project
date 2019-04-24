package hospitalManagmentSystem.departments;

public class Outpatient extends Department {
	  int capacity;

		List<Patient> patientList = new ArrayList<Patient>();

		public void addPatient(Patient patient) {
			patientList.add(Patient);
		}

		public void removePatient(Patient patient) {
			patientList.remove(patient);

		}
}
