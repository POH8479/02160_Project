import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.User;

//@author Karoline



class DepartmentTest {
	
	//Define patients and departments
	static Patient p1;
	static Patient p2;
	static Patient p3;

	static User u1;
	static User u2;
	static User u3;
	static User u4;
	
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
		assertFalse(out.getPatientList().containsValue(p1));
		assertFalse(em.getPatientList().containsValue(p1));

		
		out.addPatient(p2);
		assertTrue(out.getPatientList().containsValue(p2));
		assertFalse(in.getPatientList().containsValue(p2));
		assertFalse(em.getPatientList().containsValue(p2));
		
		em.addPatient(p3);
		assertTrue(em.getPatientList().containsValue(p3));
		assertFalse(out.getPatientList().containsValue(p3));
		assertFalse(in.getPatientList().containsValue(p3));



	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void removePatientTest() {
		
		in.removePatient(p1);
		assertFalse(in.getPatientList().containsValue(p1));
		assertFalse(out.getPatientList().containsValue(p1));
		assertFalse(em.getPatientList().containsValue(p1));

		out.removePatient(p2);
		assertFalse(in.getPatientList().containsValue(p2));
		assertFalse(out.getPatientList().containsValue(p2));
		assertFalse(em.getPatientList().containsValue(p2));
		
		em.removePatient(p3);
		assertFalse(in.getPatientList().containsValue(p3));
		assertFalse(out.getPatientList().containsValue(p3));
		assertFalse(em.getPatientList().containsValue(p3));

	}
	@Test
	public void getNameTest() {
		assertEquals("Emergency",em.getName());
		assertEquals("Management",man.getName());
		assertEquals("Outpatient",out.getName());
		assertEquals("Inpatient",in.getName());
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void addUserTest(){
		man.addUser(u1);
		assertTrue(man.getUserList().containsValue(u1));
		assertFalse(em.getUserList().containsValue(u1));
		assertFalse(in.getUserList().containsValue(u1));
		assertFalse(out.getUserList().containsValue(u1));
		
		in.addUser(u2);
		assertTrue(in.getUserList().containsValue(u2));
		assertFalse(em.getUserList().containsValue(u2));
		assertFalse(man.getUserList().containsValue(u2));
		assertFalse(out.getUserList().containsValue(u2));
		
		out.addUser(u3);
		assertTrue(out.getUserList().containsValue(u3));
		assertFalse(em.getUserList().containsValue(u3));
		assertFalse(in.getUserList().containsValue(u3));
		assertFalse(man.getUserList().containsValue(u3));
		
		em.addUser(u4);
		assertTrue(em.getUserList().containsValue(u4));
		assertFalse(man.getUserList().containsValue(u4));
		assertFalse(in.getUserList().containsValue(u4));
		assertFalse(out.getUserList().containsValue(u4));
		
		
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void removeUserTest(){
		man.removeUser(u1);
		assertFalse(man.getUserList().containsValue(u1));
		assertFalse(em.getUserList().containsValue(u1));
		assertFalse(in.getUserList().containsValue(u1));
		assertFalse(out.getUserList().containsValue(u1));
		
		in.removeUser(u2);
		assertFalse(man.getUserList().containsValue(u2));
		assertFalse(em.getUserList().containsValue(u2));
		assertFalse(in.getUserList().containsValue(u2));
		assertFalse(out.getUserList().containsValue(u2));
		
		man.removeUser(u3);
		assertFalse(man.getUserList().containsValue(u3));
		assertFalse(em.getUserList().containsValue(u3));
		assertFalse(in.getUserList().containsValue(u3));
		assertFalse(out.getUserList().containsValue(u3));
		
		man.removeUser(u4);
		assertFalse(man.getUserList().containsValue(u4));
		assertFalse(em.getUserList().containsValue(u4));
		assertFalse(in.getUserList().containsValue(u4));
		assertFalse(out.getUserList().containsValue(u4));
	}

}
