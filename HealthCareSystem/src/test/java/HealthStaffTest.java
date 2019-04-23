import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.Bed;
import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import java.time.LocalDate;


public class HealthStaffTest {
	
	// test Patients
	static Patient p1 = null;
	static Patient p2 = null;
	static Patient p3 = null;
	static Patient p4 = null;
	static Patient p5 = null;
	static Patient p6 = null;

	// Departments
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;

	// Users
	static Admin admin;
	static Doctor doc;
	static Nurse nurse;
	static User user;

	// Beds
	static Bed b1;
	static Bed b2;
	static Bed b3;

	@BeforeClass
	public static void setUpBeforeClass() throws IllegalAccessException {
		// Create Departments
		em = new Emergency();
		inPa = new Inpatient();
		outPa = new Outpatient();
		man = new Management();

		// Create Patients
		p1 = new Patient("Pieter", "O\'Hearn", LocalDate.of(1990, 1,12), "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
		p2 = new Patient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		p3 = new Patient("Anna", "Hogan", LocalDate.of(1988, 8,21), "Georg Brandes Pl. 2-6, 1307 København", "+4552373549");
		p4 = new Patient("Asger", "Conradsen", LocalDate.of(1999, 5,29), "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p5 = new Patient("Karoline", "Østergaard", LocalDate.of(1994, 2,11), "259 Nordvej 2800 Kongens Lyngby", "+4582373943");
		p6 = new Patient("Kun", "Zhu", LocalDate.of(1996, 6,8), "259 Nordvej 2800 Kongens Lyngby", "+4539562047");

		// assign patients a Department
		p1.updateDepartment(em);
		p2.updateDepartment(inPa);
		p3.updateDepartment(em);
		p4.updateDepartment(em);
		p5.updateDepartment(em);
		p6.updateDepartment(outPa);

		// Create Beds
		b1 = new Bed(em);
		b2 = new Bed(em);
		b3 = new Bed(em);

		// create Users
		admin = new Admin("Steve Jobs", "Cupertino, California, United States", "+180249625");
		doc = new Doctor("Dr. Smith", "Anker Engelunds Vej 1 Bygning 101A, 2800 Kgs. Lyngby", "+4545252525");
		nurse = new Nurse("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
		user = new User("James Gosling", "San Francisco Bay Area, California, U.S.", "+141558396");
	}

	@Test
	public void admitPatientTest() {
		fail("Not yet implemented");
	}

}
