import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import java.util.Objects;

/**
 *
 * @author Asger Conradsen
 */
 
 public class PersistenceLayerTest{
	 
	static PersistenceLayer persist;
	
	static Emergency em;
	static Inpatient inPa;
	static Outpatient outPa;
	static Management man;
	 
	static Admin admin;
	static Doctor doc;
	static Nurse nurse;
	static User user;
	
	static Bed b1;
	
	static Patient p1;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IllegalAccessException {
		
		persist = new PersistenceLayer();

		em = Emergency.getInstance();
		inPa = Inpatient.getInstance();
		outPa = Outpatient.getInstance();
		man = Management.getInstance();
		
		p1 = new Patient("Asger", "Conradsen", "04/10/1995", "487 downtown 2800 Kongens Lyngby", "+4585476964");
		p1.updateDepartment(em);
		
		
		admin = new Admin("Steve Jobs", "Cupertino, California, United States", "+180249625");
		doc = new Doctor("Dr. Smith", "Anker Engelunds Vej 1 Bygning 101A, 2800 Kgs. Lyngby", "+4545252525", "Emergency");
		nurse = new Nurse("John Doe", "123 Main St Anytown, Denmark", "+4512345678", "Emergency");
		user = new User("James Gosling", "San Francisco Bay Area, California, U.S.", "+141558396");
		
		b1 = new Bed(em);
	}
	 
	 /**
	 * Tests the save method of PersistenceLayer.
	 * Note that save is overloaded and returns a bool (true when succesfully saved) so that is what we check for.
	 */
	@Test
	public void saveTest(){
		//Saving departments
		assertEquals(true, persist.save(em));
		assertEquals(true, persist.save(inPa));
		assertEquals(true, persist.save(outPa));
		assertEquals(true, persist.save(man));
		
		//Saving a patient
		assertEquals(true, persist.save(p1, p1.getID(), em));
		
		//Saving users
		assertEquals(true, persist.save(admin, admin.getID(), em));
		assertEquals(true, persist.save(doc, doc.getID(), inPa));
		assertEquals(true, persist.save(nurse, nurse.getID(), outPa));
		assertEquals(true, persist.save(user, user.getID(), man));
		
		//Saving a bed
		assertEquals(true, persist.save(b1, b1.getID(), inPa));
	}
	
	@Test
	public void loadDepartmentTest(){
		//Testing loadDepartment by saving -> setting to null -> loading -> comparing a value.
		persist.save(em);
		String temp = em.getName();
		em = null;
		em = (Emergency) persist.loadDepartment(em);
		assertEquals(temp, em.getName());
	}
	
	@Test
	public void deleteTest(){
		//Delete department
		persist.save(em);
		assertEquals(true, persist.delete(inPa));
		
		//Delete patient/user/bed (returns true when deleted and false when it cannot find the file)
		persist.save(p1, p1.getID(), em);
		persist.save(doc, doc.getID(), inPa);
		persist.save(b1, b1.getID(), inPa);
		
		assertEquals(true, persist.delete(p1.getID(), em));
		assertEquals(true, persist.delete(doc.getID(), inPa));
		assertEquals(true, persist.delete(b1.getID(), inPa));
		assertEquals(false, persist.delete(p1.getID(), man));
		assertEquals(false, persist.delete(nurse.getID(), inPa));
		assertEquals(false, persist.delete(b1.getID(), outPa));
	}
	 
	 
	 
 }