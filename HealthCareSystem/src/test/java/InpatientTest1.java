import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.User;

//@author Karoline



class InpatientTest1 {
	
	//Define patients and departments
	static Patient p1;

	static User u1;
	
	static Management man;
	static Inpatient in;
	static Outpatient out;
	static Emergency em;
	public static void Departments(){
	em = Emergency.getInstance();
	in = Inpatient.getInstance();
	out = Outpatient.getInstance();
	man = Management.getInstance();
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void addPatientTest() {
		
		in.addPatient(p1);
		assertTrue(in.getPatientList().containsValue(p1));

	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void removePatientTest() {
		
		in.removePatient(p1);
		assertFalse(in.getPatientList().containsValue(p1));

	}
	@Test
	public void getNameTest() {
		assertEquals("Inpatient",in.getName());
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void addUserTest(){
		in.addUser(u1);
		assertTrue(in.getUserList().containsValue(u1));
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void removeUserTest(){
		in.removeUser(u1);
		assertFalse(in.getUserList().containsValue(u1));
	}

}