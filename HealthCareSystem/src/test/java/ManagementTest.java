import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import hospitalmanagementsystem.departments.Management;
/**
 * 
 * @author Kun Zhu
 *
 */
public class ManagementTest {
	// create management
	static Management ma;
	
	@BeforeClass 
	public static void setUpBeforeClass() {
		// create the Departments
		ma = Management.getInstance();
	}

	@Test
	public void getNameTest(){
		ma.getName();
		assertEquals("Management", ma.getName());
	}

}
