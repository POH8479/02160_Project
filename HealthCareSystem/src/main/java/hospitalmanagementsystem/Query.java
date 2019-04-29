package hospitalmanagementsystem;


import java.util.*;

import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Outpatient;

//@author Karoline 

public class Query {
// static variable single_instance of type Query
private static Query single_instance = null; 

public static Query getInstance() 
{ 
    if (single_instance == null) {
        single_instance = new Query();
    }

    return single_instance; 
}
//get number of patients currently in each department
public int inSize = Inpatient.getInstance().getPatientList().size();
public int outSize = Outpatient.getInstance().getPatientList().size();
public int emSize = Emergency.getInstance().getPatientList().size();
//get max and min number of patients
List<Integer> patientSizes = Arrays.asList(inSize,outSize,emSize);
int  maxPatients= Collections.max(patientSizes);
int minPatients = Collections.min(patientSizes);

//get number of users currently in each department
public int inUsers = Inpatient.getInstance().getUserList().size();
public int outUsers = Outpatient.getInstance().getUserList().size();
public int emUsers = Emergency.getInstance().getUserList().size();
public int manUsers = Management.getInstance().getUserList().size();
//get max and min number of users
List<Integer> userSizes = Arrays.asList(inSize,outSize,emSize);
int  maxUsers = Collections.max(userSizes);
int minUsers = Collections.min(userSizes);
//Save department names as Strings
List<String> departments = Arrays.asList("Inpatient","Outpatient","Emergency","Management");

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
	return inSize+emSize+outSize;
}
public int totUsers() {
	//total number of users in entire hospital
	return inUsers+outUsers+emUsers;
}

}

//number of patients discharged
//
