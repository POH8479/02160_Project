package hospitalmanagementsystem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import hospitalmanagementsystem.departments.*;

/**
 * 
 * @author Karoline
 */
public class Query {
	// INSTANCE VARIABLES
	private int inSize;
	private int outSize;
	private int emSize;
	private int manSize;
	private List<Integer> patientSizes;
	private int  maxPatients;
	private int minPatients;
	private int inUsers;
	private int outUsers;
	private int emUsers;
	private int manUsers;
	private List<Integer> userSizes;
	private int  maxUsers;
	private int minUsers;
	private List<String> departments;

	
	public Query() {
		//get number of patients currently in each department
		inSize = Inpatient.getInstance().getPatientList().size();
		outSize = Outpatient.getInstance().getPatientList().size();
		emSize = Emergency.getInstance().getPatientList().size();
		manSize = Management.getInstance().getPatientList().size();
		
		//get max and min number of patients
		patientSizes = Arrays.asList(inSize,outSize,emSize);
		maxPatients= Collections.max(patientSizes);
		minPatients = Collections.min(patientSizes);
		
		//get number of users currently in each department
		inUsers = Inpatient.getInstance().getUserList().size();
		outUsers = Outpatient.getInstance().getUserList().size();
		emUsers = Emergency.getInstance().getUserList().size();
		manUsers = Management.getInstance().getUserList().size();
		
		//get max and min number of users
		userSizes = Arrays.asList(inSize,outSize,emSize);
		maxUsers = Collections.max(userSizes);
		minUsers = Collections.min(userSizes);
		
		//Save department names as Strings
		departments = Arrays.asList("Inpatient","Outpatient","Emergency","Management");
	}
	
	public String depMostPatients() {
		//department with most admitted patients
		
		int i = patientSizes.indexOf(maxPatients);
		
		return departments.get(i);
	}
	
	public String depLeastPatients() {
		//least patients 
		
		int i = patientSizes.indexOf(minPatients);
		
		return departments.get(i);
	}
	
	public String depMostUsers() {
		//department with most admitted patients
		
		int i = userSizes.indexOf(maxUsers);
		
		return departments.get(i);
	}
	
	public String depLeastUsers() {
		//least patients 
		int i = userSizes.indexOf(minUsers);
		
		return departments.get(i);
	}
	
	public int totPatients() {
		//get total number of patients in entire hospital
		return inSize+emSize+outSize+manSize;
	}
	
	public int totUsers() {
		//total number of users in entire hospital
		return inUsers+outUsers+emUsers+manUsers;
	}
}