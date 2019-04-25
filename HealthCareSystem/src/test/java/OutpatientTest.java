import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.Patient;
import hospitalmanagementsystem.departments.Emergency;
import hospitalmanagementsystem.departments.Inpatient;
import hospitalmanagementsystem.departments.Management;
import hospitalmanagementsystem.departments.Outpatient;

/**
 * 
 * @author Kun
 *
 */


public class OutpatientTest {

		// Patients
		static Patient p1;
		static Patient p2;

		// Departments
		static Emergency em;
		static Inpatient inPa;
		static Outpatient outPa;
		static Management man;

		/**
		 * Initialise User variables to use in testing
		 */
		@BeforeClass
		public static void setUpBeforeClass() {

			// create the Departments
			em = Emergency.getInstance();
			inPa = Inpatient.getInstance();
			outPa = Outpatient.getInstance();
			man = Management.getInstance();
			
			// create the patients
			p1 = new Patient("Pieter", "O\'Hearn", LocalDate.of(1990, 1,12), "259 Nordvej 2800 Kongens Lyngby", "+4562473948");
			p2 = new Patient("Jack", "Rodman", LocalDate.of(1997, 6,28), "259 Nordvej 2800 Kongens Lyngby", "+4562870942");
		}

		/**
		 * Testing add method in Emergency class
		 */
		@Test
		public void addPatientTest() throws IllegalAccessException{
			// add the p1 to patientList in outpatient class
			outPa.addPatient(p1);
			// checks if the p1 is added to patientList, and p1 is not added in another department
			assertTrue(outPa.getPatientList().contains(p1));
			assertFalse(inPa.getPatientList().contains(p1));
			assertFalse(em.getPatientList().contains(p1));

			// add the p2 to patientList in Emergency class
			outPa.addPatient(p2);
			// checks if the p2 is added to patientList, and p2 is not added in another department
			assertTrue(outPa.getPatientList().contains(p2));
			assertFalse(inPa.getPatientList().contains(p2));
			assertFalse(em.getPatientList().contains(p2));
			
		}

		/**
		 * Testing getPatientData method in User class
		 */
		@Test
		public void removePatientTest() throws IllegalAccessException{
			// remove the p1 to patientList in outpatient class
			outPa.removePatient(p1);
			// checks if the p1 is added to patientList
			assertFalse(outPa.getPatientList().contains(p1));

			// remove the p2 to patientList in outpatient class
			outPa.removePatient(p2);
			// checks if the p2 is added to patientList
			assertFalse(outPa.getPatientList().contains(p2));
		}

	}
