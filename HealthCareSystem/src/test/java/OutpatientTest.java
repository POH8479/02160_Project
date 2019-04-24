import static org.junit.Assert.*;

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
		}

		/**
		 * Testing add method in Emergency class
		 */
		@SuppressWarnings("unlikely-arg-type")
		@Test
		public void addPatientTest() throws IllegalAccessException{
			// add the p1 to patientList in outpatient class
			outPa.addPatient(p1);
			// checks if the p1 is added to patientList, and p1 is not added in another department
			assertTrue(outPa.getPatientList().containsValue(p1));
			assertFalse(inPa.getPatientList().containsValue(p1));
			assertFalse(em.getPatientList().containsValue(p1));
			assertFalse(man.getPatientList().containsValue(p1));

			// add the p2 to patientList in Emergency class
			outPa.addPatient(p2);
			// checks if the p2 is added to patientList, and p2 is not added in another department
			assertTrue(outPa.getPatientList().containsValue(p2));
			assertFalse(inPa.getPatientList().containsValue(p2));
			assertFalse(em.getPatientList().containsValue(p2));
			assertFalse(man.getPatientList().containsValue(p2));
			
		}

		/**
		 * Testing getPatientData method in User class
		 */
		@SuppressWarnings("unlikely-arg-type")
		@Test
		public void removePatientTest() throws IllegalAccessException{
			// remove the p1 to patientList in outpatient class
			outPa.removePatient(p1);
			// checks if the p1 is added to patientList
			assertFalse(outPa.getPatientList().containsValue(p1));

			// remove the p2 to patientList in outpatient class
			outPa.removePatient(p2);
			// checks if the p2 is added to patientList
			assertFalse(outPa.getPatientList().containsValue(p2));
		}

	}
