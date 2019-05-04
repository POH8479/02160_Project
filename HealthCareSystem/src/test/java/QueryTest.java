import static org.junit.Assert.assertEquals;
import org.junit.Test;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.Query;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.departments.Outpatient;
import hospitalmanagementsystem.users.User;

class QueryTest {
	

		//Define patients and departments
		static Query query;
		public static void Queries(){
		query = new Query();	
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
	void depMostPatientsTest() {
		assertEquals("Emergency",query.depMostPatients());
	}
	@Test
	void depLeastPatientsTest() {
		assertEquals("Outpatient",query.depMostPatients());
	}
	@Test
	void depMostUsersTest() {
		assertEquals("Outpatient",query.depMostUsers());
	}
	@Test
	void depLeastUsersTest() {
		assertEquals("Emergency",query.depMostUsers());
	}
	@Test
	void totPatientsTest() {
		assertEquals(6,query.totPatients());
	}
	@Test
	void totUsersTest(){
		assertEquals(8,query.totUsers());
	}
}
