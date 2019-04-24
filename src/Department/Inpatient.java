package Department;

import java.util.ArrayList;
import java.util.List;

import Bed.*;
import Patient.*;
import User.*;

public class Inpatient {

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
