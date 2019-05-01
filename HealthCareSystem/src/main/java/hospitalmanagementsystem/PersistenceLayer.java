package hospitalmanagementsystem;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import hospitalmanagementsystem.departments.Department;


/**
 * @author Asger Conradsen (S151607)
 */
public class PersistenceLayer {
	//Defining the types of departments and objects we have
	static final String[] departments = {"Emergency", "Inpatient", "Outpatient", "Management"};
	static final String[] subfolders = {"Users", "Patients", "Beds"};
	
	/* Default constructor for the PersistencyLayer that creates the
	 * folder structure if it doesn't already exist.
	 */
	public PersistenceLayer() {
		File folder = new File("Departments");
		folder.mkdir();
		for (String department : departments) {
			folder = new File("Departments" + File.separator + department);
			folder.mkdir();
			for (String subfolder : subfolders) {
				folder = new File("Departments" + File.separator + department + File.separator + subfolder);
				folder.mkdir();
			}
		}
	}
	
	// Saves a department to the appropriate folder. Returns true if successful, else false.
	public boolean save(Department department) {
		// Creates directory path for the department
		String dir = "Departments" + File.separator + department.getName()
		+ File.separator + department.getName();
		
		// Writes department object to the file specified by the directory
		XMLEncoder e = null;
		try{
			e = new XMLEncoder(
                new BufferedOutputStream(
                    new FileOutputStream(dir)));
		} catch(FileNotFoundException fileNotFound) {
			return false;
		}
		System.out.println(dir);
		e.writeObject(department);
		e.close();
		return true;
	}
	
	// Saves an object in a specified department to the appropriate folder. Same return as before.
	public boolean save(Object obj, String ID, String department) {
		// Creates the directory for the object to be stored
		String dir = "Departments" + File.separator + department;
		char type = ID.charAt(0);
		switch (type) {
			case 'U':
				dir = dir + File.separator + "Users";
				break;
			case 'P':
				dir = dir + File.separator + "Patients";
				break;
			case 'B':
				dir = dir + File.separator + "Beds";
				break;
			default:
				return false;
		}
		dir = dir + File.separator + ID;
		
		// Writes object to the file specified by the directory
		XMLEncoder e = null;
		try{
			e = new XMLEncoder(
                new BufferedOutputStream(
                    new FileOutputStream(dir)));
		} catch(FileNotFoundException fileNotFound) {
			return false;
		}
		e.writeObject(obj);
		e.close();
		return true;
	}
	
	/*
	 * Loads a department /////////////////////////////////
	 * Note that it returns an object so the returned value must be cast
	 */
/*	public Object loadDepartment(Department department) {
		String dir = "Departments" + File.separator + department.getName()
		+ File.separator + department.getName();
		
		XMLDecoder d = null;
		try {
			d = new XMLDecoder(
	                new BufferedInputStream(
	                    new FileInputStream(dir)));
		}catch(FileNotFoundException fileNotFound) {
			System.out.println("Error: Could not find or open the file");
		}
		Object obj = new Object();
		obj = d.readObject();
		return obj;		
	}*/
	
	public void loadDepartment(Department department) {
		String dir = "Departments" + File.separator + department.getName();
		
		//Loads department
		XMLDecoder d = null;
		try {
			d = new XMLDecoder(
	                new BufferedInputStream(
	                    new FileInputStream(dir + File.separator + department.getName())));
		}catch(FileNotFoundException fileNotFound) {
			System.out.println("Error: Could not find or open the file");
		}
		department = (Department) d.readObject();
		
		//Loads users in the department
		department.setUserList(loadObjs(department, "users"));
		
		//Loads patients in the department
		department.setPatientList(loadObjs(department, "patients"));
		
		//Loads beds in the department
		department.setBedList(loadObjs(department, "beds"));
	}

	/*
	 * Returns an ArrayList of either users, patients or beds in the given departmen
	 * based on which "type" is passed to the method.
	 */
	private ArrayList<Object> loadObjs(Department department, String type){
		ArrayList<Object> objs = new ArrayList<Object>();
		type = type.toLowerCase();
		char charType;
		String dir = "Departments" + File.separator + department.getName();
		switch (type) {
			case "users":
				dir = dir + File.separator + "Users";
				charType = 'U';
				break;
			case "patients":
				dir = dir + File.separator + "Patients";
				charType = 'P';
				break;
			case "beds":
				dir = dir + File.separator + "Beds";
				charType = 'B';
				break;
			default:
				return objs;	
		}
		
		// Gets a list of files in the directory and checks if they are valid
		XMLDecoder d = null;
		File[] files = new File(dir).listFiles();
		
		for(File file : files) {
			if(file.isFile() && Character.toUpperCase(file.getName().charAt(0)) == charType) {
				try{
					d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream(dir + File.separator + file.getName())));
				} catch(FileNotFoundException fileNotFound) {
					System.out.println("Error: Could not find or open the file");
				}
				Object obj = new Object();
				obj = d.readObject();
				objs.add(obj);
			}
		}
		return objs;
	}
	
	
	// Deletes the file
	public Boolean delete(Department department) {
		// Creates directory path for the department
		String dir = "Departments" + File.separator + department.getName()
			+ File.separator + department.getName();
		File delFile = new File(dir);
		if(delFile.delete()) return true;
		else return false;
	}
	
	public Boolean delete(String ID, String department) {
		// Creates the directory for the object to be stored
		String dir = "Departments" + File.separator + department;
		char type = ID.charAt(0);
		switch (type) {
			case 'U':
				dir = dir + File.separator + "Users";
				break;
			case 'P':
				dir = dir + File.separator + "Patients";
				break;
			case 'B':
				dir = dir + File.separator + "Beds";
				break;
			default:
				return false;
		}
		dir = dir + File.separator + ID;
		
		//Attempts to delete the file
		File delFile = new File(dir);
		if(delFile.delete()) return true;
		else return false;	
	}
	

	
}
