package hospitalmanagementsystem;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.departments.Outpatient;
import hospitalmanagementsystem.users.User;

class QueryTest {
	

		//Define patients and departments
		static Query query;
		public static void Queries(){
		query = Query.getInstance();	
		}
		
		
		static Patient p1;
		static Patient p2;
		static Patient p3;
		static Patient p4;
		static Patient p5;
		static Patient p6;


		static User u1;
		static User u2;
		static User u3;
		static User u4;
		static User u5;
		static User u6;
		static User u7;
		static User u8;
		
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
		public static void AddPatients(){
		em.addPatient(p1);
		em.addPatient(p2);
		in.addPatient(p3);
		out.addPatient(p4);
		em.addPatient(p5);
		in.addPatient(p6);
		}
		
		public static void AddUsers() {
			em.addUser(u1);
			man.addUser(u2);
			man.addUser(u3);
			out.addUser(u4);
			out.addUser(u5);
			out.addUser(u6);
			in.addUser(u7);
			in.addUser(u8);
		}
	@Test
	@SuppressWarnings("unlikely-arg-type")
	void depMostPatientsTest() {
		assertEquals("Emergency",Query.depMostPatients());
	}
	@Test
	@SuppressWarnings("unlikely-arg-type")
	void depLeastPatientsTest() {
		assertEquals("Outpatient",Query.depMostPatients());
	}
	@Test
	@SuppressWarnings("unlikely-arg-type")
	void depMostUsersTest() {
		assertEquals("Outpatient",Query.depMostUsers());
	}
	@Test
	@SuppressWarnings("unlikely-arg-type")
	void depLeastUsersTest() {
		assertEquals("Emergency",Query.depMostUsers());
	}
	@Test
	@SuppressWarnings("unlikely-arg-type")
	void totPatientsTest() {
		assertEquals(6,Query.totPatients());
	}
	@Test
	@SuppressWarnings("unlikely-arg-type")
	void totUsersTest(){
		assertEquals(8,Query.totUsers());
	}

}
