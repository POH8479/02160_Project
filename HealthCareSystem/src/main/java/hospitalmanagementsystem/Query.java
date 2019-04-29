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
public static int inSize = Inpatient.getInstance().getPatientList().size();
public static int outSize = Outpatient.getInstance().getPatientList().size();
public static int emSize = Emergency.getInstance().getPatientList().size();
//get max and min number of patients
static List<Integer> patientSizes = Arrays.asList(inSize,outSize,emSize);
static int  maxPatients= Collections.max(patientSizes);
static int minPatients = Collections.min(patientSizes);

//get number of users currently in each department
public static int inUsers = Inpatient.getInstance().getUserList().size();
public static int outUsers = Outpatient.getInstance().getUserList().size();
public static int emUsers = Emergency.getInstance().getUserList().size();
public static int manUsers = Management.getInstance().getUserList().size();
//get max and min number of users
static List<Integer> userSizes = Arrays.asList(inSize,outSize,emSize);
static int  maxUsers = Collections.max(userSizes);
static int minUsers = Collections.min(userSizes);
//Save department names as Strings
static List<String> departments = Arrays.asList("Inpatient","Outpatient","Emergency","Management");

public static String depMostPatients() {
	//department with most admitted patients
	
	int i = patientSizes.indexOf(maxPatients);
	
	return departments.get(i);
}
public static String depLeastPatients() {
	//least patients 
	
	int i = patientSizes.indexOf(minPatients);
	
	return departments.get(i);
}
public static String depMostUsers() {
	//department with most admitted patients
	
	int i = userSizes.indexOf(maxUsers);
	
	return departments.get(i);
}
public static String depLeastUsers() {
	//least patients 
	int i = userSizes.indexOf(minUsers);
	
	return departments.get(i);
}
public static int totPatients() {
	//get total number of patients in entire hospital
	return inSize+emSize+outSize;
}
public static int totUsers() {
	//total number of users in entire hospital
	return inUsers+outUsers+emUsers;
}

}

